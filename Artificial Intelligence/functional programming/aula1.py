#Exercicio 1.1
def comprimento(lista):
	if lista == []:
		return 0
	return 1 + comprimento(lista[1:])

#Exercicio 1.2
def soma(lista):
	if lista == []:
		return 0
	return lista[0] + soma(lista[1:])


#Exercicio 1.3
def existe(lista, elem):
	if lista == []:
		return False
	
	elif lista[0] == elem:
		return True

	else:
		return existe(lista[1:], elem)

#Exercicio 1.4
def concat(l1, l2):
	if len(l2) == 0:
		return l1

	return concat(l1 + l2[:1], l2[1:])


#Exercicio 1.5
def inverte(lista):
	if lista == []:
		return []
	return lista[-1:] + inverte(lista[:-1])


#Exercicio 1.6
def capicua(lista):
	if lista == []:
		return True
	elif lista[:1] != lista[-1:]:
		return False
	capicua(lista[1:-1])
	return True

#Exercicio 1.7
def concat_listas(lista):
	if len(lista) == 1:
		return lista[0]
	return concat_listas([concat(lista[:1][0], lista[1:2][0])] + lista[2:])

#Exercicio 1.8
def substitui(lista, original, novo):
	if lista == []:
		return []
	
	elif lista[0] == original:
		return [novo] + lista[1:]

	else:
		return [lista[0]] + substitui(lista[1:], original, novo)

#Exercicio 1.9
def fusao_ordenada(lista1, lista2):
	if lista1 == [] and lista2 == []:
		return []
	elif lista1 == []:
		return lista2
	elif lista2 == []:
		return lista1
	else:
		if lista1[0] < lista2[0]:
			return [lista1[0]] + fusao_ordenada(lista1[1:], lista2)
		elif lista2[0] < lista1[0]:
			return [lista2[0]] + fusao_ordenada(lista1, lista2[1:])
		else:
			return [lista1[0]] + [lista2[0]] + fusao_ordenada(lista1[1:], lista2[1:])


#Exercicio 1.10
def lista_subconjuntos(lista):
	if lista == []:
		return [[]]
	
	x = lista_subconjuntos(lista[1:])

	return x + [[lista[0]] + y for y in x]


#Exercicio 2.1
def separar(lista):
	if lista == []:
		return ([], [])
	
	tup = separar(lista[1:])
	return ([lista[0][0]] + tup[0], [lista[0][1]] + tup[1])

#Exercicio 2.2
def remove_e_conta(lista, elem):
	if lista == []:
		return ([], 0)
	
	tup = remove_e_conta(lista[1:], elem)
	print(tup)
	if lista[0] == elem:
		return ([] + tup[0], tup[1] + 1)
	else:
		return ([lista[0]] + tup[0], tup[1])

#Exercicio 3.1
def cabeca(lista):
	pass

#Exercicio 3.2
def cauda(lista):
	pass

#Exercicio 3.3
def juntar(l1, l2):
	if l1 == [] and l2 == []:
		return []
	
	if (l1 == [] and l2 != []) or (l1 != [] and l2 == []):
		return None
	
	else:
		test = juntar(l1[1:], l2[1:])
		if test != None:
			return [(l1[0], l2[0])] + test
		else:
			return None
		

#Exercicio 3.4
def menor(lista):
	if lista == []:
		return None
	else:
		return menor2(lista, lista[0])
	
def menor2(lista, min):
	if lista == []:
		return min
	
	if lista[0] < min:
		min = lista[0]
	
	return menor2(lista[1:], min)
	



#Exercicio 3.6
def max_min(lista):
	pass
