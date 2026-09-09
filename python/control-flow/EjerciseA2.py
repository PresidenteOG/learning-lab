#NUMBER MINIGAME 2
print("Option 2")
import os

#RANDOM
import random
low = 0
max = 100


#TRYS
Intentos = 5

#CODE
os.system('clear')
print("Welcome to Daniel Adanegbe Molina Minigame 2!")
NumerADD = int(input("Inserte su numero! (1-100) "))
os.system('clear')
if NumerADD >= 0 and NumerADD <= 100:
    while Intentos != 0:
        Numero = random.randint(low,max)
        print(f"Intentos faltantes {Intentos}")
        Inserted = input(f"Es tu numero {Numero}? [Acertado,Mayor,Menor] ({Numero}>{NumerADD}): ")
        if Inserted == "Acertado":
            print("Has perdido!")
            Intentos = 0
        elif Inserted == "Mayor":
            max = Numero - 1
            Intentos -= 1
        elif Inserted == "Menor":
            low = Numero + 1
            Intentos -= 1
        else:
            print()
            print("Texto insertado es incorrecto!")
        if Intentos == 0 and Numero != NumerADD:
            print("Has ganado!")
else:
    print()
    print("Texto insertado es incorrecto!")


