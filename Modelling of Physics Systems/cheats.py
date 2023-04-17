def calc_m(data):
    """ data must be a list of tuples """
    N = len(data)
    numerador = (N * sum(coord[0]*coord[1] for coord in data)) - (sum(coord[0] for coord in data) * sum(coord[1] for coord in data))
    denominador = (N * sum(coord[0] ** 2 for coord in data)) - (sum(coord[0] for coord in data))**2
    return numerador/denominador

def calc_b(data):
    """ data must be a list of tuples"""
    N = len(data)
    numerador = (sum(coord[0] ** 2 for coord in data) * sum(coord[1] for coord in data)) - (sum(coord[0] for coord in data) * sum(coord[0] * coord[1] for coord in data))
    denominador = (N * sum(coord[0] ** 2 for coord in data)) - (sum(coord[0] for coord in data)) ** 2
    return numerador/denominador

def calc_r2(data):
    """ data must be a list of tuples"""
    N = len(data)
    numerador = ( (N * sum(coord[0] * coord[1] for coord in data)) - (sum(coord[0] for coord in data) * sum(coord[1] for coord in data))) ** 2 
    denominador = ((N * sum(coord[0] ** 2 for coord in data)) - (sum(coord[0] for coord in data)) ** 2) * ((N*sum(coord[1]**2 for coord in data)) - (sum(coord[1] for coord in data))**2)
    return numerador/denominador


def calc_deltaM(m, r2, N):
    """ m é o declie, r2 é o erro relativo ao quadrado, N é o número de medições"""
    return abs(m) * (((1/r2) - 1)/(N-2))**(1/2)

def calc_deltaB(deltaM, data):
    """ incerteza do declive, data must a list of tuples """
    return deltaM * (sum(coord[0] ** 2 for coord in data)/len(data))**(1/2)