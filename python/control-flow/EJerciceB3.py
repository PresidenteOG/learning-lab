import os
#NOMBRE                    #V/A/D
charmander = ["Charmander",150,50,45],
Charizard =  ["Charizard",300,150,100],
mew       =  ["Mew",350,50,50],
lugia =      ["Lugia",500,180,100],
mewtwo =     ["Mewtwo",450,150,100],
alakazam =   ["Alakazam",200,80,90],
chansy  =    ["Chansy",120,20,300],
jolteon =    ["Jolteon",130,80,30],
zapdos =     ["Zaptdos",150,200,90],
snorlax =    ["Snorlax",200,230,10],

pokedex= [charmander,Charizard,mew,lugia, mewtwo,alakazam,chansy,jolteon,zapdos,snorlax]
os.system('clear')
def Start():
    def Inf1():
        print("Selectiona uno de las opciones!")
        print()
        print("Opcion 1: Informacion de pokemons!")
        print("Opcion 2: Batalla de pokemon!")
        print()
        Select = int(input("Elige una de las opciones! (1-2):"))
        if Select == 1:
            os.system('clear')
            def Opcion1():
                print("Bienvenido al info pokedex!")
                print("")
                def RepetirTexto1():
                    YN = input("Quieres buscar un pokemon? (Si/No) ")
                    if YN == "Si":
                        os.system('clear')
                        filas = len(pokedex)
                        print(f"Hay de momento {filas} pokemons en la pokedex")
                        print("")
                        def Find():
                            Research = input("Que pokemon quieres buscar? ")
                            os.system('clear')
                            for i in pokedex:
                                for Eat in i:
                                    if Eat[0] == Research:
                                        print(f"Informacion de {Eat[0]} ")
                                        print()
                                        print(f"Vida: {Eat[1]}",)
                                        print(f"Ataque: {Eat[2]}",)
                                        print(f"Defensa: {Eat[3]}",)
                                        print()
                                        def repeating():
                                            YN2 = input("Quieres buscar otro pokemon? [SI/NO] ")
                                            if YN2 == "SI":
                                                os.system('clear')
                                                Find()
                                            elif YN2 == "NO":
                                                os.system('clear')
                                                def Back():
                                                    os.system('clear')
                                                    PICK2 = input("Volver al inicio? [SI/NO] ")
                                                    if PICK2 == "SI":
                                                        Inf1()
                                                    elif PICK2 == "NO":
                                                        exit
                                                    else:
                                                        Back()
                                                Back()
                                            else:
                                                repeating()
                                        repeating()
                            return
                        Find()
                    elif YN == "No":
                        def B():
                            PICK2 = input("Volver al inicio? [SI/NO] ")
                            if PICK2 == ("SI"):
                                Inf1()
                            elif PICK2 == ("NO"):
                                exit
                            else:
                                B()
                            os.system('clear')
                        B()
                    else:
                        print("Texto insertado es incorrecto!")
                        print("")
                        RepetirTexto1()
                    #BBuscarPokemon = input("Busqueda de Pokemons!: ")
                RepetirTexto1()
            Opcion1()
        elif Select == 2:
            os.system('clear')
            def Opcion2():
                print("Bienvenido a la batalla pokemon!")
                print("")
                def RepetirTexto1():
                    YN = input("Quieres hacer un combate pokemon? [SI/NO] ") 
                    if YN == "SI":
                        os.system('clear')
                        filas = len(pokedex)
                        print(f"Hay de momento {filas} pokemons para pelear")
                        print("")
                        def Find():
                            Research = input("Que primer pokemon saco? ")
                            os.system('clear')
                            for i in pokedex:
                                for Eat in i:
                                    if Eat[0] == Research:
                                        print(f"Has sacado a {Eat[0]}!")
                                        StatA = Eat[0]
                                        StatA1 = Eat[1]
                                        StatA2 = Eat[2]
                                        StatA3 = Eat[3]
                            print()
                            Research2 = input("Que segundo pokemon saco? ")
                            for i in pokedex:
                                for Eat in i:
                                    if Eat[0] == Research2:
                                        print(f"Has sacado a {Eat[0]}!")
                                        StatB = Eat[0]
                                        StatB1 = Eat[1]
                                        StatB2 = Eat[2]
                                        StatB3 = Eat[3]
                            os.system('clear')
                            print("PELEA DE POKEMONS!")
                            print()
                            print(f"{StatA} VS {StatB}")
                            def FunctionSelect():
                                POINT1 = StatA1 + StatA2 - StatB3
                                POINT2 = StatB1 + StatB2 - StatA3
                                if POINT1 > POINT2:
                                    print("")
                                    print(f"{StatA} GANO LA BATALLA!")
                                elif POINT1 < POINT2:
                                    print("")
                                    print(f"{StatB} GANO LA BATALLA!")
                                elif POINT1 == POINT2:
                                    print("")
                                    print("Ambos pokemons empataron!")
                                def A():
                                    print("")
                                    PICK = input("Crear otra batalla? [SI/NO] ")
                                    if PICK == ("SI"):
                                        Find()
                                    elif PICK == ("NO"):
                                        def B():
                                            os.system('clear')
                                            PICK2 = input("Volver al inicio? [SI/NO] ")
                                            if PICK2 == ("SI"):
                                                Inf1()
                                            elif PICK2 == ("NO"):
                                                exit
                                            else:
                                                B()
                                        B()
                                    else:
                                        A()
                                A()
                            FunctionSelect() 
                        Find()
                    elif YN == "NO":
                        os.system('clear')
                        def B():
                            PICK2 = input("Volver al inicio? [SI/NO] ")
                            if PICK2 == ("SI"):
                                Inf1()
                            elif PICK2 == ("NO"):
                                exit
                            else:
                                B()
                        B()
                    else:
                        os.system('clear')
                        print("Texto insertado es incorrecto!")
                        print("")
                        RepetirTexto1()
                    #BBuscarPokemon = input("Busqueda de Pokemons!: ")
                RepetirTexto1()
            Opcion2()
        else:
            Inf1()
    Inf1()
Start()