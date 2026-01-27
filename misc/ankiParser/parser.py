txt = input()
with open(txt+".txt", 'r') as file, open(txt+'_anki.txt', 'w') as out:
    for line in file.readlines():
        out.writelines(map(str.replace('$$', '')))