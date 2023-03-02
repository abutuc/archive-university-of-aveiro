

# Guiao de representacao do conhecimento
# -- Redes semanticas
# 
# Inteligencia Artificial & Introducao a Inteligencia Artificial
# DETI / UA
#
# (c) Luis Seabra Lopes, 2012-2020
# v1.9 - 2019/10/20
#


# Classe Relation, com as seguintes classes derivadas:
#     - Association - uma associacao generica entre duas entidades
#     - Subtype     - uma relacao de subtipo entre dois tipos
#     - Member      - uma relacao de pertenca de uma instancia a um tipo
#

from functools import reduce
from unittest.mock import patch
from collections import Counter
from statistics import mean

class Relation:
    def __init__(self,e1,rel,e2):
        self.entity1 = e1
#       self.relation = rel  # obsoleto
        self.name = rel
        self.entity2 = e2
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

#   Exemplo:
#   a = Association('socrates','professor','filosofia')
class AssocOne(Association):
    registo = dict()
    def __init__(self, e1, assoc, e2):
        if not (assoc, e2) in AssocOne.registo or AssocOne.registo[(assoc, e2)] == e1:
            Association.__init__(self, e1, assoc, e2)
            AssocOne.registo[(assoc, e2)] = e1

class AssocNum(Association):
    def __init__(self, e1, assoc, e2):
        if isinstance(e2, (int, float)):
            Association.__init__(self, e1, assoc, e2)
# Subclasse Subtype
class Subtype(Relation):
    def __init__(self,sub,super):
        Relation.__init__(self,sub,"subtype",super)


#   Exemplo:
#   s = Subtype('homem','mamifero')

# Subclasse Member
class Member(Relation):
    def __init__(self,obj,type):
        Relation.__init__(self,obj,"member",type)

#   Exemplo:
#   m = Member('socrates','homem')

# classe Declaration
# -- associa um utilizador a uma relacao por si inserida
#    na rede semantica
#
class Declaration:
    def __init__(self,user,rel):
        self.user = user
        self.relation = rel
    def __str__(self):
        return "decl("+str(self.user)+","+str(self.relation)+")"
    def __repr__(self):
        return str(self)

#   Exemplos:
#   da = Declaration('descartes',a)
#   ds = Declaration('darwin',s)
#   dm = Declaration('descartes',m)

# classe SemanticNetwork
# -- composta por um conjunto de declaracoes
#    armazenado na forma de uma lista
#
class SemanticNetwork:
    def __init__(self,ldecl=None):
        self.declarations = [] if ldecl==None else ldecl
    def __str__(self):
        return str(self.declarations)
    def insert(self,decl):
        self.declarations.append(decl)
    def query_local(self,user=None,e1=None,rel=None,e2=None, _type=None):
        self.query_result = \
            [ d for d in self.declarations
                if  (user == None or d.user==user)
                and (e1 == None or d.relation.entity1 == e1)
                and (rel == None or d.relation.name == rel)
                and (e2 == None or d.relation.entity2 == e2)
                and (_type == None or isinstance(d.relation,_type)) ]
        return self.query_result

    def show_query_result(self):
        for d in self.query_result:
            print(str(d))

    def list_associations(self):
        assoc = self.query_local(_type=Association)
        return set([a.relation.name for a in assoc])

    def list_objects(self):
        objs = self.query_local(_type=Member)
        return set([obj.relation.entity1 for obj in objs])

    def list_users(self):
        return set([decl.user for decl in self.query_local()])

    def list_types(self):
        return set([tp.relation.entity2 for tp in self.query_local(_type=(Member, Subtype))])
    
    def list_local_associations(self, entity):
        assoc = self.query_local(e1=entity, _type=Association)
        return set([a.relation.name for a in assoc])

    def list_relations_by_user(self, u):
        declarations = self.query_local(user=u)
        return set([decl.relation.name for decl in declarations])

    def associations_by_user(self, u):
        declarations = self.query_local(user=u, _type=Association)
        return len(set([decl.relation.name for decl in declarations]))
    
    def list_local_associations_by_user(self, entity):
        declarations = self.query_local(e1 = entity, _type=Association)
        return set([(decl.relation.name, decl.user) for decl in declarations])

    # True if e1 is predecessor of e2; False
    def predecessor(self, A, B):
        if (A == B):
            return True
        else:
            results = self.query_local(e1=B, _type=(Member, Subtype))
            for r in results:
                self.predecessor(A, r.relation.entity2)
                return True
        return False
    

    def predecessor_path(self, A, B):
        if (A == B):
            return [A]
        else:
            results = self.query_local(e1=B, _type=(Member, Subtype))
            for r in results:
                return self.predecessor_path(A, r.relation.entity2) + [B]
        return None


    def query(self, entity, assoc=None):
        pds = [decl.relation.entity2 for decl in self.query_local(e1=entity, _type=(Member, Subtype))]
        all_assoc = self.query_local(e1=entity, rel=assoc, _type=Association) + self.query_local(e2=entity, rel=assoc, _type=Association) 
        for p in pds:
            all_assoc += self.query(p, assoc)
        return all_assoc
    

    def query2(self, entity, rel=None):
        pds = [decl.relation.entity2 for decl in self.query_local(e1=entity, _type=(Member, Subtype))]

        all_rel = self.query_local(e1=entity, rel=rel) + self.query_local(e2=entity)
        for p in pds:
            all_rel += self.query(p, rel)
        return all_rel

    def query_cancel(self, entity, assoc=None):
        pds = [decl.relation.entity2 for decl in self.query_local(e1=entity, _type=(Member, Subtype))]

        all_assoc = self.query_local(e1=entity, rel=assoc, _type=Association) + self.query_local(e2=entity, rel=assoc, _type=Association) 
        for p in pds:
            all_assoc += [decl for decl in self.query_cancel(p, assoc) if not decl.relation.name in [assoc.relation.name for assoc in all_assoc]]
        return all_assoc

    def query_down(self, tipo, assoc, first_level=True):
        desc = [decl.relation.entity1 for decl in self.query_local(e2=tipo, _type=(Member, Subtype))]
        
        if first_level:
            all_assoc = []
        else:
            all_assoc = self.query_local(e1=tipo, rel=assoc, _type=Association) + self.query_local(e2=tipo, rel=assoc, _type=Association) 
        
        for d in desc:
            all_assoc += self.query_down(d,assoc, first_level=False)
            
        return all_assoc
    
    def query_induce(self, tipo, assoc=None):
        desc_assoc = self.query_down(tipo, assoc)
        if desc_assoc: 
            c = Counter([d.relation.entity2 for d in desc_assoc])
            return c.most_common(1)[0][0]

    def query_local_assoc(self, e1, assoc):
        local = self.query_local(e1=e1, rel=assoc, _type = Association)

        if local:
            count = Counter(l.relation.entity2 for l in local)
            if isinstance(local[0].relation, AssocOne):
                valor, cont = count.most_common(1)[0]
                return valor, cont/len(local)
            
            if isinstance(local[0].relation, AssocNum):
                return mean([l.relation.entity2 for l in local])

            t = 0
            res = []
            for a, b in [(v, c/len(local)) for (v,c) in count.most_common()]:
                res.append((a,b))
                t += b
                if t >= 0.75:
                    break
            return res
        
    
    def query_assoc_value(self, E, A):
        local = self.query_local(e1=E, rel=A, _type=Association)
        c_l = Counter([l.relation.entity2 for l in local])
        if len(c_l) == 1:
            return c_l[0][1]
        else:
            herdados = self.query(entity=E, assoc=A)
            vs = c_l + Counter([h.relation.entity2 for h in herdados])
            return vs.most_common(1)[0][0]

