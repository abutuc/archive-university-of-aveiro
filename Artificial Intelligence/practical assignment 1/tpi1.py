from tree_search import *
from cidades import *
from blocksworld import *

# Student Name: André Butuc; Student Nmec: 103530

def func_branching(connections,coordinates):
    #IMPLEMENT HERE
    # branching factor = n_neighbor_cities/n_cities - 1
    # 1 connection implies two neighbour cities, there aren't two way connections with different costs, so if we take each connection
    # and multiply it by 2 we get the total amount of neighbors
    return len(connections)*2/len(coordinates) - 1

class MyCities(Cidades):
    def __init__(self,connections,coordinates):
        super().__init__(connections,coordinates)
        # ADD CODE HERE IF NEEDED
        self.branching_estimate = func_branching(connections, coordinates)

class MySTRIPS(STRIPS):
    def __init__(self,optimize=False):
        super().__init__(optimize)

    def simulate_plan(self,state,plan):
        #IMPLEMENT HERE
        for p in plan:
            state = self.result(state, p)
        
        return state

 
class MyNode(SearchNode):
    def __init__(self,state,parent,arg3=0,arg4=0,arg5=0):
        super().__init__(state,parent)
        #ADD HERE ANY CODE YOU NEED
        self.cost = arg3
        self.heuristic = arg4
        self.depth = arg5

class MyTree(SearchTree):

    def __init__(self,problem, strategy='breadth',optimize=0,keep=0.25):
        if optimize in [2,4]:
            self.problem = problem
            self.strategy = strategy
            self.solution = None
            self.non_terminals = 0
        else:
            super().__init__(problem,strategy)
        #ADD HERE ANY CODE YOU NEED
        if optimize:
            if optimize in [2,4]:
                root = (problem[1], None, 0, 0, 0)
            else:
                root = (problem.initial, None, 0, 0, 0)
        else:
            root = MyNode(problem.initial, None)
        self.all_nodes = [root]
        self.open_nodes = [0]
        self.closed_nodes = []
        self.optimize = optimize
        self.keep = keep

    def astar_add_to_open(self,lnewnodes):
        self.open_nodes.extend(lnewnodes)
        if self.optimize:
            self.open_nodes.sort(key=lambda n: self.all_nodes[n][2] + self.all_nodes[n][3])
        else:
            self.open_nodes.sort(key=lambda n: self.all_nodes[n].cost + self.all_nodes[n].heuristic)
        #IMPLEMENT HERE
        pass


    # remove a fraction of open (terminal) nodes
    # with lowest evaluation function
    # (used in Incrementally Bounded A*)
    def forget_worst_terminals(self):
        #IMPLEMENT HERE
        if len(self.open_nodes) != 0:
            if self.optimize in [2, 4]:
                d = sum([self.all_nodes[n][4] for n in self.open_nodes])/len(self.open_nodes)
                branching_estimate = self.problem[0][5]
            elif self.optimize == 1:
                d = sum([self.all_nodes[n][4] for n in self.open_nodes])/len(self.open_nodes)
                branching_estimate = self.problem.domain.branching_estimate
            else:
                d = sum([self.all_nodes[n].depth for n in self.open_nodes])/len(self.open_nodes)
                branching_estimate = self.problem.domain.branching_estimate
            
            max_nodes_given_depth = (branching_estimate ** (d+1) - 1) / (branching_estimate - 1)
            
            numkeep = math.trunc(self.keep * max_nodes_given_depth) + 1
            # keep only best evaluated open_nodes (which are ordered by their A* evaluation)
            self.open_nodes = self.open_nodes[:numkeep]

    # procurar a solucao
    def search2(self):
        #IMPLEMENT HERE
        while self.open_nodes != []:
            nodeID = self.open_nodes.pop(0)
            node = self.all_nodes[nodeID]
            if not self.optimize:
                if self.problem.goal_test(node.state):
                    self.solution = node
                    self.terminals = len(self.open_nodes)+1
                    return self.get_path(node)
                lnewnodes = []
                self.non_terminals += 1
                for a in self.problem.domain.actions(node.state):
                    newstate = self.problem.domain.result(node.state,a)
                    if newstate not in self.get_path(node):
                        newnode = MyNode(newstate,nodeID, node.cost + self.problem.domain.cost(node.state, a), self.problem.domain.heuristic(newstate, self.problem.goal), node.depth + 1)
                        lnewnodes.append(len(self.all_nodes))
                        self.all_nodes.append(newnode)
                self.add_to_open(lnewnodes)
            else:
                if self.optimize == 2:
                    if self.problem[0][4](node[0], self.problem[2]):
                        self.solution = node
                        self.terminals = len(self.open_nodes)+1
                        return self.get_path_tup(node)
                    lnewnodes = []
                    self.non_terminals += 1
                    for a in self.problem[0][0](node[0]):
                        newstate = self.problem[0][1](node[0],a)
                        if newstate not in self.get_path_tup(node):
                            newnode = (newstate,nodeID, node[2] + self.problem[0][2](node[0], a), self.problem[0][3](newstate, self.problem[2]), node[4] + 1)
                            lnewnodes.append(len(self.all_nodes))
                            self.all_nodes.append(newnode)
                    self.add_to_open(lnewnodes)

                elif self.optimize == 4:
                    self.closed_nodes.append(nodeID)
                    if self.problem[0][4](node[0], self.problem[2]):
                        self.solution = node
                        self.terminals = len(self.open_nodes)+1
                        return self.get_path_tup(node)
                    lnewnodes = set()
                    temp_nodes = []
                    self.non_terminals += 1

                    for a in self.problem[0][0](node[0]):
                        newstate = self.problem[0][1](node[0],a)
                        if newstate not in self.get_path_tup(node):
                            newnode = (newstate,nodeID, node[2] + self.problem[0][2](node[0], a), self.problem[0][3](newstate, self.problem[2]), node[4] + 1)
                            lnewnodes.add(len(temp_nodes))
                            temp_nodes.append(newnode)
                    
                    set1 = set([temp_nodes[nID][0] for nID in lnewnodes]) - (set([self.all_nodes[nID][0] for nID in self.open_nodes]) | set([self.all_nodes[nID][0] for nID in self.closed_nodes]))
                    set2 = set([temp_nodes[nID][0] for nID in lnewnodes]) & (set([self.all_nodes[nID][0] for nID in self.open_nodes]) | set([self.all_nodes[nID][0] for nID in self.closed_nodes]))

                    dfnodes = []
                    for state in set1:
                        for i in lnewnodes:
                            if temp_nodes[i][0] == state:
                                dfnodes.append(len(self.all_nodes))
                                self.all_nodes.append(temp_nodes[i])
                    
                    for state in set2:
                        for i in range(len(self.all_nodes)):
                            if self.all_nodes[i][0] == state:
                                for f in lnewnodes:
                                    if temp_nodes[f][0] == state:
                                        nod = temp_nodes[f]
                                        if nod[2] < self.all_nodes[i][2]:
                                            self.all_nodes[i] = nod
                    self.add_to_open(dfnodes)

                else:
                    if self.problem.goal_test(node[0]):
                        self.solution = node
                        self.terminals = len(self.open_nodes)+1
                        return self.get_path_tup(node)
                    lnewnodes = []
                    self.non_terminals += 1
                    for a in self.problem.domain.actions(node[0]):
                        newstate = self.problem.domain.result(node[0],a)
                        if newstate not in self.get_path_tup(node):
                            newnode = (newstate,nodeID, node[2] + self.problem.domain.cost(node[0], a), self.problem.domain.heuristic(newstate, self.problem.goal), node[4] + 1)
                            lnewnodes.append(len(self.all_nodes))
                            self.all_nodes.append(newnode)
                    self.add_to_open(lnewnodes)
            if self.strategy == 'IBA*':
                self.forget_worst_terminals()
        return None

    def get_path_tup(self,node):
        if node[1] == None:
            return [node[0]]
        path = self.get_path_tup(self.all_nodes[node[1]])
        path += [node[0]]
        return(path)

    def add_to_open_sorted(self, lnewnodes):
        if self.strategy == 'breadth':
            self.open_nodes.extend(lnewnodes)
        elif self.strategy == 'depth':
            self.open_nodes[:0] = lnewnodes
        elif self.strategy in [ 'A*', 'IBA*' ]:
            self.astar_add_to_open(lnewnodes)
        #self.open_nodes.sort()

# If needed, auxiliary functions can be added




