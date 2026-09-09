#! /usr/bin/python3

import sys

username="admin"
password="1234"
retries=0

while True:
    usernameAnswer = input('Username: ')
    passwordAnswer = input('Password: ')

    if username == usernameAnswer and password == passwordAnswer:
        print(f"Numero de intentos: {retries}")
        break
    else:
        sys.stderr.write('Incorrecto! \n')
        retries += 1
