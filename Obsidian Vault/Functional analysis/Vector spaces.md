> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Линейный оператор (линейное отображение)","label":"---","_index":0}] Определение 1 (Линейный оператор (линейное отображение)).
> Пусть $X, Y$ - векторные пространства над полем $\mathbb F$. Отображение $f:X\to Y$ называется линейным отображением, если $$\forall x_{1,}x_{2} \in \dom f,\;\lambda, \mu\in \mathbb F\quad \lambda x_{1}+ \mu x_{2}\in \dom f \; \text{и} \;f(\lambda x_{1}+ \mu x_{2}) = \lambda f(x_{1})+\mu f(x_{2})$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Уравновешенное множество","label":"-","_index":1}] Определение 2 (Уравновешенное множество).
> Пусть X - в.п. над полем $\mathbb F$ . Его подмножество V называется уравновешенным, если $$\forall \lambda \in \mathbb F \quad (|\lambda| \leqslant 1 \implies \lambda V\subset V)$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Выпуклое множество","label":"-","_index":2}] Определение 3 (Выпуклое множество).
> Пусть X - в.п. над полем $\mathbb F$ . Его подмножество V называется выпуклым, если$$\forall \mu, \lambda\geqslant 0 \quad (\lambda + \mu = 1\implies \lambda V +\mu V \subset V)$$

^5d7adc

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Абсолютно выпуклое множество","label":"--","_index":3}] Определение 4 (Абсолютно выпуклое множество).
> Пусть X - в.п. над полем $\mathbb F$ . Его подмножество V называется абсолютно выпуклым, если$$\forall \mu, \lambda\in \mathbb F \quad (|\lambda| + |\mu| \leqslant 1\implies \lambda V +\mu V \subset V)$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Поглощающее множество","label":"-","_index":4}] Определение 5 (Поглощающее множество).
> Пусть $X$ - в.п. над полем $\mathbb F$. Подмножество $V\subset X$ называется поглощающим, если 
> $$\forall x\in X\; \exists \varepsilon >0:\; \forall \lambda \in \mathbb F\quad (|\lambda|\leqslant \varepsilon \implies \lambda x\in V)$$


> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Алгебраический базис","label":"-","_index":5}] Определение 6 (Алгебраический базис).
> Максимальное по включению линейно независимое подмножество В.П.
> Называется также базисом Гамеля

- Теорема о базисе...
  Любое лнз содержится в некотором максимальном по включению лнз пространства
  Базис существует
- Критерий базиса
  Любой вектор представим в виде ЛК множества <=> множество базис

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Размерность векторного пространства","label":"--","_index":6}] Определение 7 (Размерность векторного пространства).
> Мощность его алгебраического базиса

> [!math|{"type":"remark","number":"auto","setAsNoteMathLink":false,"title":"","label":"","_index":7}] Замечание 8.
> Каждое подпространство векторного пространства имеет алгебраическое дополнение.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Фактор-пространство","label":"-","_index":8}] Определение 9 (Фактор-пространство).
> X - в.п., E - его подпространство. Для векторов $x, y\in X$ положим
> $$x \sim y \Leftrightarrow x-y\in E$$
> $$[x] :=\{y\in X|y\sim x\} = x+E$$
> $$X_{E}  \quad [x]+[y]= [x+y] \quad \lambda \cdot[x] = [\lambda\cdot x]$$
> Полученное пространство над тем же полем называется фактор-пространством векторного пространства Х по подпространству Е.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Коразмерность пространства","label":"-","_index":9}] Определение 10 (Коразмерность пространства).
> $co \dim E = \dim X{/}_{E}$
> 

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"_index":10}] Теорема 11.
> Для любого В.П. X  и любого его подпространства E справедливо равенство
> $$\dim X = \dim E +co\dim X_{/_E}$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Гиперпространство","label":"","_index":11}] Определение 12 (Гиперпространство).
> Подпространство В.П. называется гиперпространством, если его коразмерность равна 1.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Гиперплоскость","label":"","_index":12}] Определение 13 (Гиперплоскость).
> Гиперпространство со сдвигом на вектор.

Элементы фактор-пространства $X/_{E}$  являются гиперплоскостями.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Оболочка","label":"","_index":13}] Определение 14 (Оболочка).
> Наименьшее уравновешенное/выпуклое/абсолютно выпуклое множество, содержащее данное.

Соответствие - линейное подмножество

$$bal A = \bigcup_{\lambda \in \mathbb F, |\lambda|\leqslant 1} \lambda A$$
$$co A = \{\sum\limits_{k=1}^{n}\lambda_{k}x_k|\lambda_k\geqslant 0, \sum\limits_{k=1}^{n}\lambda_{k}= 1\}$$
$$abs co A = co A = \{\sum\limits_{k=1}^{n}\lambda_{k}x_k| \sum\limits_{k=1}^{n}|\lambda_{k}|\leqslant 1\}$$
$\mathscr L(X, Y)$ - множество всех линейных отображений X в Y
$\mathscr{L} (X, X) = End (X)$
> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Коядро оператора","label":"-","_index":14}] Определение 15 (Коядро оператора).
> $co \ker A = X/_{\ker A}$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Кообраз оператора","label":"-","_index":15}] Определение 16 (Кообраз оператора).
> $co im A = X /_{im A}$

мономорфизм:
	взаимно однозначный оператор
эпиморфизм:
	отображает X на Y, т.е. $im \;A = Y$
изоморфизм:
	взаимно однозначно отображает X на Y
Линейный функционал - отображение из векторного пространства в поле скаляров
