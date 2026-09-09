#! /usr/bin/python3

#Comentario:
#Aviso, no es lo que pide EXACTAMENTE del ejercicio, pero veia unos puntos
#absurdos o no necesarios:
# Si "index" té un valor igual o superior a la variable "paramsSize", que invoque la funció "exitOnError" passant-li un missatge i el codi d'error 4.

import sys
from llibreria import exitonsuccess,exitonerror

params = sys.argv
paramsSize = len(params)

hasHelloParameter = False
hasByeParameter = False
helloName = ""
byeName = ""
for index in range(0, paramsSize):
    if params[index] == "-h" or params[index] == "--hello":
        if hasHelloParameter:
            exitonerror("mas de -H/-hello",1)
        hasHelloParameter = True
        if index + 1 < paramsSize:
            helloName = params[index+1]
    if params[index] == "-b" or params[index] == "--bye":
        if hasByeParameter:
            exitonerror("Mas de un -b/--bye",3)
        hasByeParameter = True
        if index + 1 < paramsSize:
            byeName = params[index+1]

if hasHelloParameter:
    exitonsuccess(f"Hola {helloName}!")
elif hasByeParameter:
    exitonsuccess(f"Adéu {byeName}!")
else:
    exitonerror("Error: falta de -h/--hello o -b/--bye.", 5)