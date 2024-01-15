> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Топологическое пространство","label":"-","_index":0}] Определение 1 (Топологическое пространство).
> Множество $X$ называется топологическим пространством, если на нем определено отображение
> $$\mathscr{O}: X\ni x\mapsto \mathscr{O}(x)\subset \mathscr{P}(X)$$
> такое, что для любого $x\in X$ класс $\mathscr O(x)$ обладает следующими свойствами:
> 1) $\forall V\in O(x) \quad x\in V$
> 2) $\forall U, V\in O(x) \quad U \cap V \in O(x)$
> 3) $\forall U \in O(x) \quad (U \subset V \subset X\to V\in O(x))$
> 4) $\forall V\in O(x)\exists U\in O(x): \quad U\subset V \wedge (\forall y \in U \quad U\in O(y))$

^38c1b0

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Открытое множество","label":"-","_index":1}] Определение 2 (Открытое множество).
> Подмножество ТП является открытым, если оно является окрестностью каждой своей точки.

^86ebb5

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Замкнутое множество","label":"-","_index":2}] Определение 3 (Замкнутое множество).
> Подмножество ТП является замкнутым, если его дополнение открыто.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Окрестность точки","label":"-","_index":3}] Определение 4 (Окрестность точки).
> Элемент [[#^38c1b0|класса]] $O(x)$ - окрестность точки $x\in X$.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Внутренняя точка множества","label":"--","_index":4}] Определение 5 (Внутренняя точка множества).
> Точка $x\in A$ называется внутренней точкой множества $A$, если $A$ является окрестностью этой точки. 

^60e3e5

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Точка прикосновения множества","label":"--","_index":5}] Определение 6 (Точка прикосновения множества).
> Точка $x\in X$ называется внутренней точкой множества $A$, если пересечение любой ее окрестности с множеством А не пусто.

^64faf4

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Предельная точка множества","label":"--","_index":6}] Определение 7 (Предельная точка множества).
> Точка $x\in X$ называется предельной точкой множества А, если каждая окрестность этой точки содержит точки множества, отличные от данной.
> $$\forall U\in O(x) \quad U \cap A \setminus \{x\} \neq \varnothing$$
> 

^67c64d

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Внутренность множества","label":"-","_index":7}] Определение 8 (Внутренность множества).
> Множество всех [[#^60e3e5|внутренних]] точек множества.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Замыкание множества","label":"-","_index":8}] Определение 9 (Замыкание множества).
> Множество всех [[#^64faf4|точек прикосновения]] множества.

^035384

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Хаусдорфово пространство","label":"-","_index":9}] Определение 10 (Хаусдорфово пространство).
> Любые две различные его точки имеют непересекающиеся окрестности.

^fe1157

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Предел последовательности в топологическом пространстве","label":"----","_index":10}] Определение 11 (Предел последовательности в топологическом пространстве).
> x  - предел последовательности $x_n$, если
> $$\forall V\in O_{x}^{X}\;\exists n_{0}\in \mathbb{N} : \quad \forall n\geqslant n_{0}\quad x_{n}\in V$$
> 

^678bc5

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Критерий непрерывности отображения одного топологического пространства в другое","label":"-------","_index":11}] Определение 12 (Критерий непрерывности отображения одного топологического пространства в другое).
> Для любого отображения f топологического пространства X в топологическое пространство Y равносильны следующие утверждения:
> 1) f - непрерывно, т.е $$\forall x\in X\; \forall V\in O^{Y}_{f(x)}\; \exists U\in O_{x}^{X}: f(U) \subset V$$
> 2) $$\forall x\in X \forall V\in O^{Y}_{f(x)} \; f^{-1}(V)\in O_{x}^{X}$$
> 3) Прообраз открытого открыт $$\forall V\in \DeclareMathOperator{op}{Op} \op Y \; f^{-1}(V)\in \op X$$
> 4) Прообраз замкнутого замкнут $$\forall V\in \DeclareMathOperator{cl}{Cl} \cl Y \; f^{-1}(V)\in \cl X$$

^a219d8

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Бикомпактное множество","label":"-","_index":12}] Определение 13 (Бикомпактное множество).
> Подмножество ТП называется бикомпактным, если любое его открытое покрытие имеет конечное подпокрытие.

^ac95a8

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Компактное множество","label":"-","_index":13}] Определение 14 (Компактное множество).
> Замкнутое бикомпактное множество

^e5af67

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Полуметрика","label":"","_index":14}] Определение 15 (Полуметрика).
> Пусть X - непустое множество. Отображение $$d:X^{2}\to \mathbb{R}$$
> называется полуметрикой на X, если $\forall x, y, z\in X$:
> 1) $$d(x, x) = 0$$
> 2) $$d(x, y) = d(y, x)$$
> 3) $$d(x, z)\leqslant d(x, y) + d(y, z)$$

^a4bf41

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Метрика","label":"","_index":15}] Определение 16 (Метрика).
> Пусть X - непустое множество. Отображение $$d:X^{2}\to \mathbb{R}$$
> называется метрикой на X, если $\forall x, y, z\in X$:
> 1) $$d(x, y) = 0 \Leftrightarrow x = y$$
> 2) $$d(x, y) = d(y, x)$$
> 3) $$d(x, z)\leqslant d(x, y) + d(y, z)$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Равномерно непрерывное отображение полуметрических пространств","label":"----","_index":16}] Определение 17 (Равномерно непрерывное отображение полуметрических пространств).
> Отображение f из полуметрического пространства $(X, d)$ в полуметрическое пространство $(Y, \rho)$ называется равномерно непрерывным на множестве $A\subset X$, если выполнены условия:
> 1) $A\subset dom f$
> 2) $$\forall \varepsilon > 0 \exists \delta > 0 : \forall x_{1}, x_{2}\in A\; d(x_{1}, x_{2})< \delta\implies \rho(f(x_{1}), f(x_{2}))< \varepsilon$$
> 

^617652

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Последовательность Коши","label":"-","_index":17}] Определение 18 (Последовательность Коши).
> Последовательность $\{x_n\}_{n\in \mathbb{N}}$ элементов полуметрического пространства $(X, d)$ называется последовательностью Коши (фундаментальная последовательность), если 
> $$\forall \varepsilon>0 \; \exists n_{0}\in\mathbb{N}: \forall n, m\geqslant n_{0\;}d(x_{n}, x_{m})< \varepsilon$$

^6a8f58

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":" Полнота подмножества полуметрического пространства","label":"----","_index":18}] Определение 19 ( Полнота подмножества полуметрического пространства).
> Подмножество [[#^a4bf41|ПМ/П]] называется полным, если любая его последовательность Коши сходится к некоторому элементу этого подмножества.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Принцип вложенных шаров","label":"--","_index":19}] Определение 20 (Принцип вложенных шаров).
> Для того, чтобы [[#^a4bf41|ПМП]] было полным, необходимо и достаточно, чтобы любая последовательность замкнутых, вложенных друг в друга шаров, радиусы которых стремятся к нулю, имела непустое пересечение.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Неподвижная точка","label":"-","_index":20}] Определение 21 (Неподвижная точка).
> $f$ - отображение множества X в себя. Точка $x\in X$ называется неподвижной точкой отображения $f$, если $f(x) = x$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Условие Липшица","label":"-","_index":21}] Определение 22 (Условие Липшица).
> Говорят, что отображение f из полуметрического пространства (X, d) в полуметрическое пространство $(Y, \rho)$ удовлетворяет на множестве $A\subset dom f$ условию Липшица с константой $L>0$, если 
> $$\forall x', x''\in A \quad \rho(f(x'), f(x'')) \leqslant L\cdot d(x', x'')$$
> 

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Сжимающее отображение","label":"-","_index":22}] Определение 23 (Сжимающее отображение).
> Отображение из одного ПМП в другое называется сжимающим, если оно липшицево с константой L < 1.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Теорема Банаха о неподвижной точке","label":"----","_index":23}] Определение 24 (Теорема Банаха о неподвижной точке).
> Каждое сжимающее отображение полного метрического пространства в себя имеет, и притом только одну, неподвижную точку.

^84172f

> [!danger] доказательство
> - рассмотрим сжимающее отображение
> - точка из Х
> - рассмотрим образ после n-ой итерации
> - неравенство треугольника для $f^{n+k}, f^{n}$ ($f^{n+k-1}$)
> - по цепочке применим, в конце получим $d(f^{n+1}(x_0), f^{n}(x_0))$
> - применяем свойства сжимающего отображения
> - Геометрическая прогрессия, сверху оценка суммой
> - Последовательность Коши
> - В силу полноты сходится к х^* = предел
> - Сжимающее отображение непрерывно
> - манипуляция с пределами, получили сжимающую точку
> - единственность проверяем через расстояние между двумя точками сжимающими
> - получаем через L и МЕТРИКУ то что d(y, x) = 0 


> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Линейное отображение","label":"-","_index":24}] Определение 25 (Линейное отображение).
> Пусть $X, Y$ - векторные пространства над полем $\mathbb F$. Отображение $f:X\to Y$ называется линейным отображением или линейным оператором, если для любых $x_{1}, x_{2}, \in \DeclareMathOperator{dom}{dom} \dom f , \lambda, \mu\in \mathbb F$
> $$\lambda x_{1}+ \mu x_{2}\in \dom f$$
> $$f(\lambda x_{1}+ \mu x_{2}) = \lambda f(x_{1})+ \mu f(x_2)$$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Ядро линейного оператора","label":"--","_index":25}] Определение 26 (Ядро линейного оператора).
> Прообраз нуля линейного отображения.
> Является подпространством.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Критерий взаимной однозначности линейного отображения","label":"----","_index":26}] Определение 27 (Критерий взаимной однозначности линейного отображения).
> Пусть $X, Y$ - в.п. над полем $\mathbb F$. Для того, чтобы линейный оператор $A$ из $X$ в $Y$ был взаимно однозначным, необходимо и достаточно, чтобы его ядро состояло из одного нуля: $\ker A = \{\mathbb{0}\}$
> 

^a9f449

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Полунорма","label":"","_index":27}] Определение 28 (Полунорма).
> Отображение $p:X\to \mathbb R$, заданное на векторном пространстве над полем $\mathbb F$, обладающее следующими свойствами:
> 1) абсолютная однородность$$\begin{equation}
\forall x \in X \forall \lambda \in \mathbb F p(\lambda x) = |\lambda| p(x) \text{}\end{equation}$$
> 2) неравенство треугольника$$\forall x, y\in X \; p(x + y) \leqslant p(x)+p(y)$$

^f12992


> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Норма","label":"","_index":28}] Определение 29 (Норма).
> Отображение $p:X\to \mathbb R$, заданное на векторном пространстве над полем $\mathbb F$, обладающее следующими свойствами:
> 1) абсолютная однородность$$\begin{equation}
\forall x \in X \forall \lambda \in \mathbb F p(\lambda x) = |\lambda| p(x) \text{}\end{equation}$$
> 2) неравенство треугольника$$\forall x, y\in X \; p(x + y) \leqslant p(x)+p(y)$$
> 3) $$p(x) = 0 \Leftrightarrow x = \mathbb 0$$
> 

^b93312

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Нормированное пространство","label":"-","_index":29}] Определение 30 (Нормированное пространство).
> Векторное пространство с нормой

^91c10c

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Банахово пространство","label":"-","_index":30}] Определение 31 (Банахово пространство).
> Полное нормированное пространство

^50232f

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Критерий непрерывности линейного оператора в нормированных пространствах","label":"------","_index":31}] Определение 32 (Критерий непрерывности линейного оператора в нормированных пространствах).
> Пусть $A$ - линейное отображение нормированного пространства $X$ в нормированное пространство $Y$. Тогда равносильны следующие утверждения:
> 1) $A$ непрерывно в нуле;
> 2) $A$ непрерывно;
> 3) $A$ [[#^617652|равномерно непрерывно]] ;
> 4) $A$ ограничено, т.е. отображает каждое ограниченное множество в ограниченное;
> 5) $\exists C>0:\;\forall x\in X\; ||Ax||_{Y}\leqslant C\cdot ||x||_{X}$;
> 6) $||A||:=\sup\limits_{||x||_{X}\leqslant 1} ||Ax||_{Y}< +\infty$

^c7ee59

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Норма оператора","label":"-","_index":32}] Определение 33 (Норма оператора).
> $||A|| := \sup\limits_{||x||_{X}\leqslant 1} ||Ax||_{Y}$ - норма линейного оператора $A\in L(X, Y)$

^b145c6

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Скалярное произведение","label":"-","_index":33}] Определение 34 (Скалярное произведение).
> Пусть $X$ - в.п. над полем $\mathbb F$. Отображение $(\cdot, \cdot): X^{2}\to \mathbb F$ называется скалярным произведением на $X$, если выполняются следующие условия:
> 1) $$\forall x_{1}, x_{2}, y\in X \; \forall \lambda, \mu \in \mathbb F \; (\lambda x_{1}+ \mu x_{2,}y) = \lambda (x_{1},y)+\mu (x_{2,}y)$$
> 2) $$\forall x, y\in X\; (x, y) = \overline{(y, x)}$$
> 3) $$\forall x\in X\; (x, x)\geqslant 0$$
> 4) $$\forall x\in X \; (x, x) = 0\implies x = \mathbb 0$$


> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Унитарное пространство","label":"-","_index":34}] Определение 35 (Унитарное пространство).
> Векторное пространство с заданным на нем скалярным произведением.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Неравенство Коши–Буняковского–Шварца","label":"-","_index":35}] Определение 36 (Неравенство Коши–Буняковского–Шварца).
> Пусть $X$ - векторное пространство и $(\cdot, \cdot)$ - скалярное произведение на $X$. Тогда для любых $x, y\in X$ выполняется неравенство $$|(x, y)|^{2}\leqslant(x,x)\cdot (y, y)$$

^a229a9

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Гильбертово пространство","label":"-","_index":36}] Определение 37 (Гильбертово пространство).
> Полное унитарное (евклидово) пространство

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Теорема Банаха о замкнутом графике","label":"----","_index":37}] Определение 38 (Теорема Банаха о замкнутом графике).
> Если линейный оператор отображает одно банахово пространство в другое, то его непрерывность равносильна его замкнутости.

^e420d7

[[TOPological spaces#^b30a5d]] 
[[#^c7ee59]] 

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Принцип равномерной ограниченности","label":"--","_index":38}] Определение 39 (Принцип равномерной ограниченности).
> Пусть $X$ - банахово пространство, а $Y$ - нормированное пространство. Тогда для любого множества операторов $\mathfrak A\subset L(X, Y)$ равносильны следующие утверждения:
> 1) $\sup\limits_{A\in \mathfrak A}||A|| < +\infty$
> 2) $\forall x\in X\;\sup\limits_{A\in \mathfrak A}||Ax|| < +\infty$

^20fd36

> [!danger] доказательство
>  1-> 2 очевидно


> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Теорема Банаха-Штейнгауза","label":"--","_index":39}] Определение 40 (Теорема Банаха-Штейнгауза).
> Пусть $X, Y$ - банаховы пространства, $\{A_n\}_{n\in \mathbb{N}}\subset L(X, Y)$ - последовательность непрерывных линейных операторов и
>$$E :=\{x\in X:\;\exists \lim_{n\to\infty}A_{n}x\}.$$
>Тогда равносильны следующие утверждения:
>1) $E = X$;
>2) $E$ плотно в $X$ и $\sup\limits_{n\in \mathbb N}||A_{n}|| < +\infty$
>При выполнении любого из этих условий линейный оператор $A$, определяемый равенством $Ax:=\lim\limits_{n\to\infty} A_{n}x\; (x\in X)$, непрерывен и справедливо равенство $||A||\leqslant \varliminf\limits_{n\to\infty}||A_{n}||$.

^71493e

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Компактный оператор","label":"-","_index":40}] Определение 41 (Компактный оператор).
> Линейное отображение $A$ ТВП $X$  в ТВП $Y$ называется компактным оператором, если существует окрестность нуля $V\in O^{X}$ такая, что множество $A(V)$ [[TOPological spaces#^3100b1|относительно компактно]] в Y.
> 
> Множество всех компактных линейных отображений $X$ в $Y$ обозначается $\mathscr K (X, Y)$




