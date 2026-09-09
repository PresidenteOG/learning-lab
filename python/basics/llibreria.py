#! /usr/bin/python3
import sys
import os 
pidfile = "my.pid"

def exitonsuccess(message):
    print(message)
    sys.exit(0)

def exitonerror(message,code):
    sys.stderr.write(message, )
    sys.exit(code)

def onctrlc(sig, frame):
    print("No puedes hacer Ctrl+C >:)")

def onkill(sig, frame):
    if os.path.exists(pidfile):
        os.remove(pidfile)
    print("Me has matado! X-X")
    sys.exit(0)