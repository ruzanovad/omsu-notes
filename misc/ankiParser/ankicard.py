import re
import os

def ankimanip(path):
    string = "> [!math|{}]"

# getcwd - Return a string representing the current working directory.
f = []
for (dirpath, dirnames, filenames) in os.walk(os.path.abspath(os.getcwd())):
    f.extend(filenames)
    for filename in filenames:
        if os.path.splitext(filename)[1] == 'md':
            ankimanip(filename)
    break

