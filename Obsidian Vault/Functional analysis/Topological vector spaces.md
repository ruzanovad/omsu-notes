> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Линейная топология","label":"-","_index":0}] Определение 1 (Линейная топология).
> Топология на векторном пространстве X над полем $\mathbb F$ называется линейной, если в ней непрерывны линейные операции:
> $$(+)\;:\;X\times X\to X$$
> $$(\cdot)\;:\;\mathbb F\times X\to X$$
> или
> $$\forall x, y\in X \; \forall W\in O_{x+y}^{X}\; \exists U\in O_{x}^{X}, V\in O_{y}^{X}\;:\;U+V\subset W$$
> $$\forall x\in X, \lambda \in \mathbb F\quad \forall V\in O_{\lambda x}^{X}\; \exists U\in O_{x}^{X}, \varepsilon > 0:\; \forall \mu \in \mathbb F \; |\mu-\lambda|< \varepsilon\implies\mu U\subset V$$

^8ea736

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Топологическое векторное пространство","label":"--","_index":1}] Определение 2 (Топологическое векторное пространство).
> Векторное пространство с заданной на нем [[#^8ea736|линейной]]  топологией.

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"Хан-Банах-Сухомлинов","label":"--","_index":2}] Теорема 3 (Хан-Банах-Сухомлинов).
> Пусть $p$ - полунорма на в.п. Х. Тогда для любого подпространства $X_{0}{\subset}X$ и любого линейного функционала $f_{0}$ на $X_{0}$ такого, что для всех $x\in X_{0\quad}|f_{0}(x)|\leqslant p(x)$ существует линейный функционал $f$ на $X$ такой, что $f_{X_{0}}=f_{0}$ и для всех $x\in X |f(x)|\leqslant p(x)$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Ограниченное подмножество ","label":"--","_index":3}] Определение 4 (Ограниченное подмножество ).
> Подмножество А т.в.п. Х называется ограниченным, если оно поглощается любой окрестностью нуля, т.е.
> $$\forall V\in O^{X}\; \exists \varepsilon >0 :\; \lambda A\subset V \text{ при } |\lambda|\leqslant \varepsilon$$

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"Критерий Колмогоров ограниченности","label":"--","_index":4}] Теорема 5 (Критерий Колмогоров ограниченности).
> Подмножество А т.в.п. ограничено $\Leftrightarrow$
> $$\forall \{x_{n}\}_{n\in \mathbb N}\subset A\; \forall \{\lambda_{n}\}_{n\in \mathbb N}\subset \mathbb R \; \lambda_{n}\to_{n\to \infty} 0\implies \lambda_{n}x_{n}\to_{n\to \infty} \mathbb 0$$

Для линейных отображений в т.в.п. равносильны следующие утверждения
непрерывность в нуле = непрерывно = равномерно непрерывно

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Ограниченное линейное отображение","label":"--","_index":5}] Определение 6 (Ограниченное линейное отображение).
> Отображает ограниченное отображение в ограниченное.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Топологическая прямая сумма","label":"--","_index":6}] Определение 7 (Топологическая прямая сумма).
> - Прямая сумма алгебраическая
> - Все проекции $P_{k}: X\to X_k$ непрерывны

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Локально выпуклое пространство","label":"--","_index":7}] Определение 8 (Локально выпуклое пространство).
> В каждой окрестности нуля содержится выпуклая окрестность нуля

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Spec","label":"spec","_index":8}] Определение 9 (Spec).
> Множество всех непрерывных полунорм на Х обозначается $\DeclareMathOperator{Spec}{Spec} \Spec X$

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"Мультинорма","label":"","_index":9}] Определение 10 (Мультинорма).
> Любое непустое множество полунорм на векторном пространстве Х.

> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":false,"title":"Рисс","label":"","_index":10}] Теорема 11 (Рисс).
> На конечномерном в.п. существует единственная хаусдорфова линейная топология

Если X, Y - т.в.п. над общим полем, причем X - [[Functional analysis/Bare minimum#^fe1157|хаусдорфово]] и конечномерно.
Тогда $\mathscr{L}(X, Y)=L(X, Y)$




