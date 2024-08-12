text = "53‡‡†305))6*;4826)4‡.)4‡);806*;48†8¶60))85;;]8*;:‡*8†83(88)5*†;46(;88*96*?;8)*‡(;485);5*†2:*‡(;4956*2(5*—4)8¶8*;4069285);)6†8)4‡‡;1(‡9;48081;8:8‡1;48†85;4)485†528806*81(‡9;48;(88;4(‡?34;48)4‡;161;:188;‡?;"

print(text.replace("8","e").replace(";","t").replace("4", "h").replace(")","s").replace("‡","o").replace("*","n").replace("5","a").replace("6","i").replace("†","d").replace("(","r").replace("?","u").replace("3","g").replace("9","m").replace(":","y").replace("]","w").replace("1","f").replace("0","l").replace("2","b").replace(".","p").replace("¶","v").replace("—","c"))
s = set(text)
d = {}
for i in s:
    d[i] = text.count(i)/(len(text))*100
l = []
for j in d.items():
    l.append(j)
l.sort(key=lambda x: x[1], reverse=True)
for j in l:
    print(j)
