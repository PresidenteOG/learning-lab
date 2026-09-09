
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
                            YN2 = input("Quieres buscar otro pokemon? (Si/No) ")
                            if YN2 == "Si":
                                os.system('clear')
                                Find()
                            elif YN2 == "No":
                                os.system('clear')
                                exit
                            else:
                                repeating()
                        repeating()
            return
        Find()
    elif YN == "No":
        os.system('clear')
    else:
        print("Texto insertado es incorrecto!")
        print("")
        RepetirTexto1()
    #BBuscarPokemon = input("Busqueda de Pokemons!: ")
RepetirTexto1()

#MADE BY: DANIEL ADANEGBE MOLINA (SMIX2CT)
#HOURS DONE: 2