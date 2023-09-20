class vertice:
    def __init__(self, number) -> None:
        self.number = number
        self.A = set()
        self.B = set()
class Label:
    def __init__(self, sign, vertice, value) -> None:
        self.sign = sign
        self.vertice = vertice
        self.value = value

with open("input.txt") as file:
    # нумерация от 1 до ... n 
    S = [int(i) for i in file.readline()] # источники
    S_values = [int(i) for i in file.readline()]
    X = [int(i) for i in file.readline()] # промежуточные
    T = [int(i) for i in file.readline()] # стоки
    T_values = [int(i) for i in file.readline()]
    fiction_source = len(S)+len(X)+len(T)+1
    fiction_stock = len(S)+len(X)+len(T)+2

    C = [(len(S)+len(X)+len(T)+2)*[-1] for i in range(len(S)+len(X)+len(T)+2)]
    for i in range(len(S)):
        C[fiction_source-1][S[i]-1] = S_values[i] # фиктивный источник
    for i in range(len(T)):
        C[T[i]-1][fiction_stock-1] = T_values[i] # фиктивный сток
    m = int(file.readline()) # количество дуг
    d = dict()
    for _ in range(m):
        i, j, k = map(int, file.readline().split()) # каждую дугу через пробел + пропускная способность
        C[i-1][j-1] = k
        if i in d.keys():
            d[i].A.add(j)
        else:
            d[i] = vertice(i)
            d[i].A.add(j)
        if j in d.keys():
            d[j].B.add(i)
        else:
            d[j] = vertice(j)
            d[j].B.add(i)
        # множества A(x) и B(x)
    


    
    
    