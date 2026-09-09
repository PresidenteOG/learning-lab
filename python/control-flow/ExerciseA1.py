#NUMBER MINIGAME
print("Option 1")
import os

#RANDOM
import random
Numero = random.randint(1,100) 

#TRYS
Intentos = 5

#CODE
os.system('clear')
print("Welcome to Daniel Adanegbe Molina Minigame!")
Message = int(input("Adivina el numero del 1 al 100! "))
if Message >= 0 and Message <= 100:
    while Intentos != 0 or Message == Numero:
     if Message == Numero:
        Intentos = 0
        print("Has guanyat!")
     elif Message != Numero:
        os.system('clear')
        if Message > Numero:
            print(f"El numero insertado es mayor! (??<{Message}) ")
        elif Message < Numero:
            print(f"El numero insertado es menor! (??>{Message})")
        print()
        print(f"Intentos faltantes {Intentos} ")
        print()
        Message = int(input("Adivina el numero del 1 al 100! "))
        Intentos -= 1
    if Intentos == 0:
       os.system('clear')
       print(f"Has perdido! El numero era {Numero}!")
else:
    print("Numero fuera del rango!")

