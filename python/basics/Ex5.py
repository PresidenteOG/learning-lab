#! /usr/bin/python3
#Busqueda
#https://www.datacamp.com/tutorial/python-subprocess

import sys
import subprocess
from llibreria import exitonerror, exitonsuccess

def pingoption(target):
    subprocess.run(["ping", target])

def nslookupoption(target):
    subprocess.run(["nslookup", target])

def quitoption():
    exitonsuccess("Saliendo!")

if len(sys.argv) != 2:
    exitonerror("Falta un parametro!",1)

target = sys.argv[1]

while True:
    print("Menu:")
    print("1. PingOption")
    print("2. Nslookup")
    print("3. Sortir")
    try:
        option = int(input("Elige una opcion: "))
    except ValueError:
        print("Texto incorrecto")

    if option == 1:
        pingoption(target)
    elif option == 2:
        nslookupoption(target)
    elif option == 3:
        quitoption()
    else:
        sys.stderr.write('Error! \n')