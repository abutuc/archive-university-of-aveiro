#encoding: utf8

# YOUR NAME: André Gabriel Butuc
# YOUR NUMBER: 103530

# COLLEAGUES WITH WHOM YOU DISCUSSED THIS ASSIGNMENT:
# - Exercise 3a was adapted from professor Luís Seabra Lopes's code which he wrote in TP class;
# - Exercise 3b:
#       * was adapted from professor Luís Seabra Lopes's code which he wrote in TP class;
#       * colleague Mariana Andrade 103823 suggested to use a separate function to generate lambda expressions;
#       * Online resource for lambda expressions behaviour inside a dictionary and a for loop: https://stackoverflow.com/questions/34854400/python-dict-of-lambda-functions



from semantic_network import *
from bayes_net import *
from constraintsearch import *


class MySN(SemanticNetwork):

    def __init__(self):
        SemanticNetwork.__init__(self)
        # ADD CODE HERE IF NEEDED
        pass

    def is_object(self,user,obj):
        user_assoc = [valid_decl for valid_decl in [decl for decl in self.query_local(user=user, e1=obj) if decl.relation.name != "subtype" and decl.relation.name != "member"] + [decl for decl in self.query_local(user=user, e2=obj) if decl.relation.name != "subtype" and decl.relation.name != "member"] if valid_decl.relation.card is None]
        user_decl = self.query_local(user=user,e1=obj,rel="member")
        
        if len(user_assoc) + len(user_decl) == 0:
            return False
        else:
            return True

    def is_type(self,user,type):
        user_decl_subtype = [decl.relation for decl in self.query_local(user=user, rel="subtype")]
        user_decl_assoc = set([decl.relation for decl in self.query_local(user=user) if decl.relation.name != "subtype" and decl.relation.name != "member"])
        
        for sub_relation in user_decl_subtype:
            if sub_relation.entity1 == type or sub_relation.entity2 == type:
                return True 
        
        for assoc in user_decl_assoc:
            if assoc.card == "one" or assoc.card == "many":
                if assoc.entity1 == type or assoc.entity2 == type:
                    return True
        return False

    
    def infer_type(self,user,obj):
        if not self.is_object(user=user, obj=obj):
            return None
        user_assoc = [decl.relation for decl in self.query_local(user=user) if decl.relation.name != "member" and decl.relation.name != "subtype"]
        user_decl_member = [decl.relation for decl in self.query_local(user=user, e1=obj, rel="member")]
        if len(user_decl_member) != 0:
            return user_decl_member[0].entity2

        possible_assoc_infer_e2 = ""
        possible_assoc_infer_e1 = ""
        for assoc in user_assoc:
            if assoc.entity2 == obj:
                possible_assoc_infer_e2 = assoc.name
            elif assoc.entity1 == obj:
                possible_assoc_infer_e1 = assoc.name
        
        if possible_assoc_infer_e2 != "":
            user_card_infer= [decl.relation for decl in self.query_local(user=user, rel=possible_assoc_infer_e2) if decl.relation.name != "member" and decl.relation.name != "subtype" and decl.relation.card is not None]
            for assoc in user_card_infer:
                if assoc.entity1 != obj and assoc.entity2 != obj:         
                    return self.infer_signature(user, assoc.name)[1]
        
        elif possible_assoc_infer_e1 != "":
            user_card_infer= [decl.relation for decl in self.query_local(user=user, rel=possible_assoc_infer_e1) if decl.relation.name != "member" and decl.relation.name != "subtype" and decl.relation.card is not None]
            for assoc in user_card_infer:
                if assoc.entity1 != obj and assoc.entity2 != obj:         
                    return self.infer_signature(user, assoc.name)[0]

        return "__unknown__"
            
    def infer_signature(self,user,assoc):
        user_decl_assoc = [decl for decl in self.query_local(user=user, rel=assoc) if decl.relation.name != "subtype" and decl.relation.name != "member"]
        if len(user_decl_assoc) == 0:
            return None
        
        user_assoc_card = [decl for decl in user_decl_assoc if decl.relation.card is not None]
        if len(user_assoc_card) != 0:
            assoc = user_assoc_card[0]
            return (assoc.relation.entity1, assoc.relation.entity2)
        
        else:
            assoc = user_decl_assoc[0].relation
            type_entity1 = self.infer_type(user,assoc.entity1)
            type_entity2 = self.infer_type(user, assoc.entity2)
            return (type_entity1, type_entity2)


class MyBN(BayesNet):

    def __init__(self):
        BayesNet.__init__(self)
        # ADD CODE HERE IF NEEDED
        pass

    def markov_blanket(self,var):
        
        # Test if var is in the network
        if var not in self.dependencies.keys():
            return set()

        # Get var parents
        var_parents = set()
        for dependency in self.dependencies[var]:
            if dependency[0] != []:
                var_parents.add(dependency[0][0])
            elif dependency[1] != []:
                var_parents.add(dependency[1][0])
        
        # Get var children
        var_children = set()
        for child in self.dependencies.keys():
            for dependency in self.dependencies[child]:
                if dependency[0] != []:
                    if dependency[0][0] == var:
                        var_children.add(child)
                elif dependency[1] != []:
                    if dependency[1][0] == var:
                        var_children.add(child)
        
        # Get parents of var children
        var_parents_children = set()
        for child in var_children:
            for dependency in self.dependencies[child]:
                if dependency[0] != []:
                    var_parents_children.add(dependency[0][0])
                elif dependency[1] != []:
                     var_parents_children.add(dependency[1][0])
        
        set1 = var_parents.union(var_children)
        set2 = set1.union(var_parents_children) - {var}
        return list(set2)


class MyCS(ConstraintSearch):

    def __init__(self,domains,constraints):
        ConstraintSearch.__init__(self,domains,constraints)
        # ADD CODE HERE IF NEEDED
        pass

    def propagate(self,domains,var):
        edges = [e for e in self.constraints if e[1] == var]
        while edges != []:
            (xj, xi) = edges.pop()
            restriction = self.constraints[xj, xi]
            restricted_values = [x for x in domains[xj] if any(restriction(xj, x, xi, y) for y in domains[xi])]
            if len(restricted_values) < len(domains[xj]):
                domains[xj] = restricted_values
                edges += [e for e in self.constraints if e[1] == xj]

    # code i had before figuring out how to work with lambda functions when using a dict of lambda functions, adapted from the code provided by the professor Luís Seabra Lopes's code
    # def higherorder2binary(self,ho_c_vars,unary_c):
    #     tuple_var = ''.join(ho_c_vars)
    #     self.domains[tuple_var] = [t for t in cartesian_product(self.domains, ho_c_vars) if unary_c(t)]
    #     for (i, v) in enumerate(ho_c_vars):
    #         self.constraints[v, tuple_var] = lambda v,vx,tv,tvx: int(vx)==int(tvx[i])
    #         self.constraints[tuple_var,v] = lambda tv,tvx,v,vx: int(vx)==int(tvx[i])
    
    def higherorder2binary(self,ho_c_vars,unary_c):
        tuple_var = ''.join(ho_c_vars)
        self.domains[tuple_var] = [t for t in cartesian_product(self.domains, ho_c_vars) if unary_c(t)]
        for (i, v) in enumerate(ho_c_vars):
            first_constraint, second_constraint = lambda_generator(i)
            self.constraints[v, tuple_var] = first_constraint
            self.constraints[tuple_var,v] = second_constraint

def cartesian_product(domains, lvars):
    if lvars==[]:
        return [()]
    rec = cartesian_product(domains, lvars[1:])
    v = lvars[0]
    return [ (x,) + t for x in domains[v] for t in rec]

def lambda_generator(i):
        x = lambda v,vx,tv,tvx: int(vx)==int(tvx[i])
        y = lambda tv,tvx,v,vx: int(vx)==int(tvx[i])
        return x, y

