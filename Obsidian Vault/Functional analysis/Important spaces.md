> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"$B(T)$","label":"bt","_index":0}] Определение 1 ($B(T)$).
> Множество ограниченных числовых функций, определенных на множестве T

^9ce8e9

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"$BC(T)$","label":"bct","_index":1}] Определение 2 ($BC(T)$).
> Множество непрерывных ограниченных числовых функций, определенных на множестве T, т.е.
> $$BC(T) = B(T) \cap C(T)$$

^682ed8

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"$C(X, Y)$","label":"cx-y","_index":2}] Определение 3 ($C(X, Y)$).
> Множество всех непрерывных отображений Т.П. X в Т.П. Y = $C(X, Y)$. Если $Y  = \mathbb F := \mathbb R \vee \mathbb C$, то вместо $C(X, Y)$ пишут $C(X)$.

^5d37d2
K - компакт

> [!math|{"type":"remark","number":"auto","setAsNoteMathLink":false,"title":"","label":"","_index":3}] Замечание 4.
> При $0 < p \leqslant +\infty$ каждое пространство $L^p(T, \mu)$ является фактор-пространством пространства $\mathscr{L}^{p}(T, \mu)$ по подпространству E, состоящему из $\mu$-измеримых функций, $\mu$-почти всюду равных нулю на множестве T.


- B(T) и BC(T) становятся [[Functional analysis/Bare minimum#^50232f|банаховыми]] при наделении их чебышевской нормой
  $$||x|| = \sup_{t\in T}|x(t)|$$
- C(K) :$$||x|| = \sup_{t\in K}|x(t)|$$
> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":false,"title":"$C^k$","label":"ck"}] Определение ($C^k$).
> множество всех k-раз непрерывно дифференцируемых на отрезке $[a,b]\subset \mathbb R$

# [[Calculus Inequations]]

| Пространство | метрика | комментарий | полнота | норма |
| ---- | ---- | ---- | ---- | ---- |
| B(T) | $d_{\infty} (f, g) :=\sup_{t\in T}\|f(t)-g(t)\|$ | [[#^9ce8e9]] | да, доказано в учебнике | $\|\|x\|\| = \sup_{t\in T}\|x(t)\|$ |
| BC(T) | $d_{\infty} (f, g) :=\sup_{t\in T}\|f(t)-g(t)\|$ | [[#^682ed8]] | да, полно, в учебнике | $\|\|x\|\| = \sup_{t\in T}\|x(t)\|$ |
| C[a,b] | $d_{\infty} (f, g) :=\sup_{t\in T}\|f(t)-g(t)\|$ | [[#^5d37d2]] | да |  |
| $C^k[a,b]$ | $d(f, g) :=\max_{0\leq i \leq k}\sup_{a\leq t \leq b}\|f^{(i)}(t) - g^{(i)}(t)\|$ |  | да |  |
| s | $d(x, y):=\sum\limits_{n=1}^{\infty}2^{-n} \frac{\|x_n -y_n\|}{1+\|x_n + y_n\|}$ | sequences | да, т.к. F полно |  |
| c | $d_{\infty}(x, y):=\sup_{n\in\mathbb N}\|x_n-y_n\|$ | множество всех сходящихся последовательностей |  |  |
| $c_0$ | $d_{\infty}(x, y):=\sup_{n\in\mathbb N}\|x_n-y_n\|$ | множество последовательностей, сходящихся к 0 |  |  |
| $l^p$<br> | при p <= 1 $d_{p}(x, y):=\sum\limits_{n=1}^{\infty} \|x_n-y_n\|^p$, p >=1 $d_{p}:=(\sum\limits_{n=1}^{\infty} \|x_n-y_n\|^p)^{1/p}$ | $x\in s: \sum\limits \|x_n\|^p<+\infty$ |  | банахово при p >=1 |
| $l^\infty$ | $d_{\infty}(x, y):=\sup_{n\in\mathbb N}\|x_n-y_n\|$ | множество всех ограниченных числовых последовательностей |  |  |
| $L^{p}(S, \mu)$ | $d_{p}(f, g):=\int_{S} \|f-g\|^{p} d\mu, o<p < 1$<br>$d_{p}(f, g):=(\int_{S} \|f-g\|^{p} d\mu)^{1/p}, 1\leq p < +\infty$ ==ПОЛУМЕТРИКИ== | множество всех $\mu$-измеримых на S числовых функций, абсолютно интегрируемых в p-той степени<br>==А потом отождествляем функции равные почти всюду, получаем метрическое пространство ассоциированное с полуметрическим== | да, полно, если полная мера |  |
 Множество финально нулевых последовательностей не является полным 
 Если $\mathscr L^{\infty}(S, \mu)$, получаем множество ==существенно== ограниченных
 $$d_{\infty}=\inf\limits_{E\subset S, \mu(E) = 0}\sup_{t\in S\setminus E} |f(t)-g(t)|$$ 