> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о свойствах внутренности и замыкания","label":"----","_index":0}] Теорема 1 (о свойствах внутренности и замыкания).
> Пусть $X$ - топологическое пространство. Тогда
> 1) для любого множества $A\subset X \quad \DeclareMathOperator{Int}{int} \Int A \subset A \subset\DeclareMathOperator{cl}{cl} \cl A$
> 2) если $A\subset B \subset X$, то
> $$\Int A \subset \int B \quad \cl A \subset \cl B$$
> 3) $$\Int A = \bigcup \{V\in \DeclareMathOperator{op}{Op} \op X:\;V\subset A \}$$
> 4) $$\cl A = \bigcap \{V\in \DeclareMathOperator{Cl}{Cl} \Cl X:\;V\supset A \}$$
> 5) $\forall A, B\in X$
>    $$\Int (A\cap B) = \Int A \cap \Int B\quad \Int (A\cup B) \supset \Int A \cup \Int B$$
>    $$\cl (A\cup B) = \cl A \cup \cl B\quad \cl (A\cap B) \subset \cl A \cap \cl B$$
>  6) $A\subset X\implies$
>       $$A\in \op X\Leftrightarrow \Int A =  A\Leftrightarrow A\cap \partial A = \varnothing$$
>       $$A\in \Cl X\Leftrightarrow\cl A = A\Leftrightarrow A\supset \partial A$$
>  $$\Int A = A\setminus \partial A, \quad \cl A = A\cup \partial A = \Int A\sqcup \partial A, \quad \partial A = \cl A\setminus \Int A$$
>  $\cl \varnothing = \Int \varnothing = \varnothing; \cl X = \Int X = X$
>  7) $A\subset X$
>     $\Int(\Int A) = \Int A, \quad \cl (\cl A) = A$
>     

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о единственности предела","label":"--","_index":1}] Теорема 2 (о единственности предела).
> В хаусдорфовом топологическом пространстве каждая сходящаяся последовательность имеет единственный предел.

[[Bare minimum#^a219d8]] 

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о свойствах бикомпактных множеств","label":"---","_index":2}] Теорема 3 (о свойствах бикомпактных множеств).
> В каждом топологическом пространстве справедливы следующие утверждения:
> 1) Любое конечное множество бикомпактно
> 2) Объединение конечного семейства бикомпактных множеств бикомпактно
> 3) Замкнутое подмножество бикомпактного множества бикомпактно и, следовательно, компактно:
> 4) Образ бикомпакта при непрерывном отображении бикомпактен
> 5) множество, состоящее из членов сходящейся последовательности и ее предела, бикомпактно
> 6) бикомпактное подмножество хаусдорфова топологического пространства замкнуто и, следовательно, компактно.

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о свойствах полуметрик","label":"--","_index":3}] Теорема 4 (о свойствах полуметрик).

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о свойствах шаров","label":"--","_index":4}] Теорема 5 (о свойствах шаров).

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"критерий замкнутости","label":"-","_index":5}] Теорема 6 (критерий замкнутости).
> Подмножество полуметрического пространства замкнуто тогда и только тогда, когда оно содержит предел каждой своей сходящейся последовательности

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"принцип вложенных шаров","label":"--","_index":6}] Теорема 7 (принцип вложенных шаров).

> [!math|{"type":"corollary","number":"auto","setAsNoteMathLink":false,"title":"","label":"","_index":7}] Следствие 8.
> Бикомпактное подмножество полуметрического пространство полно.

^e69c92

[[metric spaces#^9756fd]] 
[[metric spaces#^f173ae]] 

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"критерий бэровости","label":"-","_index":8}] Теорема 9 (критерий бэровости).
> 

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"Бэр","label":"","_index":9}] Теорема 10 (Бэр).
> Полное полуметрическое пространство является бэровским.

[[Bare minimum#^84172f]] 

[[Bare minimum#^a9f449]] 

[[Bare minimum#^c7ee59]] 

[[Bare minimum#^a229a9]] 


![[Pasted image 20240105193001.png]]
![[Pasted image 20240105193019.png]]
> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о наилучшем приближении в ГП","label":"----","_index":10}] Теорема 11 (о наилучшем приближении в ГП).
> Пусть E - замкнутое [[Vector spaces#^5d7adc|выпуклое]] подмножество гильбертова пространства H. Тогда для любого $x\in H$ существует, и притом единственный, вектор $y\in E$ такое, что $$||x-y|| = dist(x, E)$$

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о проекции","label":"-","_index":11}] Теорема 12 (о проекции).
> Пусть H - гильбертово пространство и E - его замкнутое подпространство. Тогда для любого вектора $x\in H$ существует единственный вектор $y\in E$ такой, что $x-y\perp E$.

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о ряде Фурье","label":"--","_index":12}] Теорема 13 (о ряде Фурье).
> Пусть H - гильбертово пространство, $\{e_{n}| n\in \mathbb N\}$ - ортонормированная система в H, $x\in H$ и $c_{n}:=(x, e_{n})(n\in \mathbb{N})$. Тогда:
> 1) ряд $\sum\limits_{n=1}^{\infty}{|c_n|}^2$ сходится и выполняется неравенство Бесселя
>    $$\sum\limits_{n=1}^{\infty}{|c_{n|}^{2}\leqslant}||x||^{2}$$
>  2) ряд Фурье вектора х сходится в H
>  3) сумма ряда Фурье является ортогональной проекцией х на множество $\cl (span \{e_{n}| n\in \mathbb{N}\})$
>  4) $\sum\limits_{n=1}^{\infty}c_{n}e_{n} = x$ тогда и только тогда, когда выполнено равенство Парсеваля-Стеклова:$$\sum\limits_{n=1}^{\infty}{{|c_n|}}^{2}=||x||^2$$

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"об эквивалентности норм","label":"--","_index":13}] Теорема 14 (об эквивалентности норм).
> Если две сравнимые нормы на векторном пространстве превращают его в банаховы пространства, то эти нормы эквивалентны.

[[Bare minimum#^e420d7]] 

[[Bare minimum#^20fd36]] 

[[Bare minimum#^71493e]] 

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"о ряде Неймана","label":"--","_index":14}] Теорема 15 (о ряде Неймана).
> Пусть $X$ - банахово пространство и непрерывный линейный оператор $A\in L(X)$ таков, что $||A||<1$. Тогда оператор $I_X-A$ непрерывно обратим и $$(I_{X}-A)^{-1}= \sum\limits_{k=0}^{\infty}A^{k},$$
> где $A^{0}:= I_X$

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"об обратимости оператора","label":"--","_index":15}] Теорема 16 (об обратимости оператора).
> Пусть $X$ и $Y$ - нормированные пространства, причем хотя бы одно из них полное. Предположим, что оператор $A\in L(X, Y)$ непрерывно обратим.
> Тогда для любого оператора $\Delta \in L(X, Y)$ такого, что $||\Delta|| < ||A^{-1}||^{-1}$, оператор $B:=A + \Delta$ непрерывно обратим, причем
> $$||B^{-1}-A^{-1}|| \leqslant \frac{||\Delta ||\cdot ||A^{-1}||^2}{1 - ||\Delta ||\cdot ||A^{-1}||}$$

