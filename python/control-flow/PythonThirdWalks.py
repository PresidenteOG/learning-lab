numero1 = 1
numero2 = 3

resultado = numero1 + numero2

if resultado > 10:
    print("Es mayor que 10!")
elif resultado > 6 and resultado < 10:
    print("Es mayor que 6 y menor que 10!",resultado)
elif numero1 + numero2 < 5:
    print("Resultado muy pequeño, no se podia resultado < 5?")
else:
    print("El resultado es 5!")

print("Programa terminado!")
