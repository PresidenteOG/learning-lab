#START PICK
def START():
    import os
    os.system('clear')
    print("Bienvenido a los numeros de adivinanza!")
    print()
    print("Que prefiere?")
    print()
    print("OPCION 1:Que la maquina intente adivinar tu numero")
    print("OPCION 2:Que intentes adivinar el numero de la maquina")
    print()
    def REPICK():
        Select = int(input("Elige una de las opciones! (1-2):"))
        os.system('clear')

        if Select == 1:
            print("OPCION 1 selecionado!")
            
            def START1():
                import random
                Numero = random.randint(1,100) 
                #CODE
                os.system('clear')
                print("Welcome to Daniel Adanegbe Molina Minigame!")
                def starting1():
                    Message = int(input("Adivina el numero del 1 al 100! "))
                    if Message >= 0 and Message <= 100:
                        #TRYS
                        Intentos = 5
                        while Intentos != 0 or Message == Numero:
                            if Message == Numero:
                                Intentos = 0
                                print("Has ganado! Volver a intentar?")
                                Pick = input("(Y/N):")
                                if Pick == "Y":
                                    os.system('clear')
                                    starting1()
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
                            print(f"Has perdido! El numero era {Numero}! Volver a intentar?")
                            Pick = input("(Y/N):")
                            if Pick == "Y":
                                os.system('clear')
                                starting1()
                    else:
                        print("Numero fuera del rango!")
                        starting1()
                starting1()
            START1()
        elif Select == 2:
            print("OPCION 2 selecionado!")

            def START2():
                #RANDOM
                import random
                #CODE
                os.system('clear')
                print("Welcome to Daniel Adanegbe Molina Minigame 2!")
                def starting2():
                    NumerADD = int(input("Inserte su numero! (1-100) "))
                    os.system('clear')
                    if NumerADD >= 0 and NumerADD <= 100:
                        low = 0
                        max = 100
                        #TRYS
                        Intentos = 5
                        while Intentos != 0:
                            Numero = random.randint(low,max)
                            print(f"Intentos faltantes {Intentos}")
                            Inserted = input(f"Es tu numero {Numero}? [Acertado,Mayor,Menor] ({Numero}>{NumerADD}): ")
                            if Inserted == "Acertado":
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
                            print("Has ganado!, volver a intentar?")
                            Pick = input("(Y/N):")
                            if Pick == "Y":
                                os.system('clear')
                                starting2()
                        else:
                            print("Has perdido!, volver a intentar?")
                            Pick = input("(Y/N):")
                            if Pick == "Y":
                                os.system('clear')
                                starting2()
                    else:
                        print("Numero insertido incorrecto!")
                        starting2()
                starting2()
            START2()
        elif Select != 1 or Select != 2:
            print("Opcion insertado es incorrecto!")
            REPICK()
    REPICK()        
START()

#MADE BY: DANIEL ADANEGBE MOLINA (SMIX2CT)
#HOURS DONE: 2