#!/usr/bin/python3

#Busquedas:
# https://www.freecodecamp.org/espanol/news/lectura-y-escritura-de-archivos-en-python-como-crear-leer-y-escribir-archivos/
# https://www.datacamp.com/es/tutorial/how-to-check-if-a-file-exists-in-python
# https://rico-schmidt.name/pymotw-3/os/index.html
# https://www.programiz.com/python-programming/datetime
# https://www.freecodecamp.org/espanol/news/python-abre-archivo-como-leer-un-archivo-de-texto-linea-por-linea/
#Aqui no entendi realmente bien a que se referiria, pero yo he hecho
#de una carpeta List_file, ahi esta una lista de fechas

from llibreria import exitonerror
import os
from datetime import datetime

carpeta = "LIST_FILE"
archivo = "lista.txt"
ruta = carpeta + "/" + archivo

if not os.path.exists(ruta):
    if not os.path.exists(carpeta):
        try:
            os.mkdir(carpeta)
        except:
            exitonerror("No se ha podido crear la carpeta", 1)
    if not os.access(carpeta, os.W_OK):
        exitonerror("El directori no es accesible", 1)
    try:
        open(ruta, "w").close()
    except:
        exitonerror("No se ha podido crear la carpeta", 1)

if not os.access(ruta, os.R_OK):
    exitonerror("No se puede leer el archivo", 2)

if not os.access(ruta, os.W_OK):
    exitonerror("No se puede escribir el archivo", 3)

llista = []

llista = open(ruta).read().splitlines()

llista.append(datetime.now().strftime("%Y-%m-%d %H:%M:%S"))

open(ruta, "w").write("\n".join(llista) + "\n")
