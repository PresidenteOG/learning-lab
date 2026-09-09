
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
print("Bienvenido a la batalla pokemon!")
print("")
def RepetirTexto1():
    YN = input("Quieres hacer un combate pokemon? (Si/No) ")
    if YN == "Si":
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
                    def A():
                        print("")
                        PICK = input("Crear otra batalla? [SI/NO]")
                        if PICK == ("Si"):
                            Find()
                        elif PICK == ("No"):
                            exit
                        else:
                            A()
                    A()
                elif POINT1 < POINT2:
                    print("")
                    print(f"{StatB} GANO LA BATALLA!")
                    def A():
                        print("")
                        PICK = input("Crear otra batalla? [SI/NO]")
                        if PICK == ("Si"):
                            Find()
                        elif PICK == ("No"):
                            exit
                        else:
                            A()
                    A()
                elif POINT1 == POINT2:
                    print("")
                    print("Ambos pokemons empataron!")
                    def A():
                        print("")
                        PICK = input("Crear otra batalla? [SI/NO]")
                        if PICK == ("Si"):
                            Find()
                        elif PICK == ("No"):
                            exit
                        else:
                            A()
                    A()
            FunctionSelect() 
        Find()
    elif YN == "No":
        os.system('clear')
    else:
        os.system('clear')
        print("Texto insertado es incorrecto!")
        print("")
        RepetirTexto1()
RepetirTexto1()

#MADE BY: DANIEL ADANEGBE MOLINA (SMIX2CT)
#HOURS DONE: 2