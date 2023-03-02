#Exercicio 4.1
import math


impar = lambda x : True if (x%2 != 0) else False

#Exercicio 4.2
positivo = lambda x: True if (x > 0) else False

#Exercicio 4.3
comparar_modulo = lambda x, y: True if (abs(x) < abs(y)) else False

#Exercicio 4.4
cart2pol = lambda x, y: (math.sqrt((x ** 2 + y ** 2)), math.atan2(y,x))

#Exercicio 4.5
ex5 = lambda f,g,h: lambda x, y, z: h(f(x, y), g(y,z))

#Exercicio 4.6
def quantificador_universal(lista, f):
    if lista == []:
        return True
    if f(lista[0]):
        return quantificador_universal(lista[1:], f)
    return False


#Exercicio 4.9
def ordem(lista, f):
    if len(lista) == 1:
        return lista[0]
        
    if f(lista[0], lista[1]):
        lista.pop(1)
        return ordem(lista, f)
    else:
        lista.pop(0)
        return ordem(lista, f)

#Exercicio 4.10
def filtrar_ordem(lista, f):
    if len(lista) == 1:
        print(lista[0])
        return lista[0], []
        
    if f(lista[0], lista[1]):
        elem = [lista.pop(1)]
        tup = filtrar_ordem(lista, f)
        return tup[0], elem + tup[1]

    else:
        elem = [lista.pop(0)]
        tup = filtrar_ordem(lista, f)
        return tup[0], elem + tup[1]

#Exercicio 5.2
def ordenar_seleccao(lista, ordem):
    if lista == []:
        return []
    
    menor, resto = filtrar_ordem(lista, ordem)
    return [menor] + ordenar_seleccao(resto, ordem)
