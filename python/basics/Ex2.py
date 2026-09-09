#! /usr/bin/python3

import sys
print(sys.argv)

for params in range(1, len(sys.argv)):
        print("Hola " + sys.argv[params])