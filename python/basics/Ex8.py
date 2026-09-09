#!/usr/bin/python3

import os
import signal
import time
from llibreria import onctrlc,onkill

pidfile = "my.pid"

if os.path.exists(pidfile):
    os.remove(pidfile)

open("my.pid", "w").write(str(os.getpid()))

signal.signal(signal.SIGINT, onctrlc)
signal.signal(signal.SIGTERM, onkill)

i = 0
while True:
    print(i)
    time.sleep(1)
    i += 1