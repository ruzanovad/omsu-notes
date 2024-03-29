а) Для вычисления производящей функции для последовательности вероятностей P{ξ < n} мы используем формулу обыкновенной производящей функции:
$$
G(z) = \sum_{n=0}^\infty P\{\xi < n\} z^n
$$
Заметим, что событие $\{\xi < n\}$ равносильно событию $\{\xi \leq n-1\}$, поэтому мы можем записать:
$$
G(z) = \sum_{n=0}^\infty P\{\xi \leq n-1\} z^n
$$
Используя определение функции распределения, мы можем переписать это выражение в виде:
$$
G(z) = \sum_{n=0}^\infty F_\xi(n-1) z^n
$$
где $F_\xi(n)$ - функция распределения случайной величины $\xi$.
Теперь мы можем записать обыкновенную производящую функцию для последовательности вероятностей P{ξ < n}:
$$
G(z) = \sum_{n=0}^\infty F_\xi(n-1) z^n
$$
б) Для нахождения производящей функции для последовательности вероятностей P{ξ = 2n} мы можем использовать формулу обыкновенной производящей функции:
$$
G(z) = \sum_{n=0}^\infty P\{\xi = 2n\} z^n
$$
Заметим, что случайная величина $\xi$ принимает только четные значения, поэтому мы можем записать:
$$
G(z) = \sum_{n=0}^\infty P\{\xi = n\} z^{2n}
$$
Теперь мы можем записать обыкновенную производящую функцию для последовательности вероятностей P{ξ = 2n}:
$$
G(z) = \sum_{n=0}^\infty P\{\xi = n\} z^{n^2}
$$
а) Для вычисления производящей функции для последовательности вероятностей $P\{\xi < n\}$ мы использовали формулу $G(z)=\sum_{n=0}^{\infty}P\{\xi \leq n-1\}z^n$, которую можно переписать, используя свойство суммирования техники геометрической прогрессии, как $G(z)=\sum_{n=0}^{\infty}F_{\xi}(n-1)z^n=\sum_{n=0}^{\infty}(1-G_{\xi}(n))z^n$.
Отсюда мы можем записать $G(z)$ через $G_{\xi}(z)$, используя формулу $G(z)=\frac{G_{\xi}(z)}{1-z}$.

б) Для нахождения производящей функции для последовательности вероятностей $P\{\xi = 2n\}$ мы использовали формулу $G(z)=\sum_{n=0}^{\infty}P\{\xi = n\}z^{2n}$, которую можно переписать, используя свойство суммирования техники геометрической прогрессии, как $G(z)=\sum_{n=0}^{\infty}P\{\xi = 2n\}z^n$.
Отсюда мы можем записать $G(z)$ через $G_{\xi}(z)$, используя формулу $G(z)=G_{\xi}(z^2)$.

Для нахождения производящей функции для последовательности вероятностей $P\{\xi>n\}$ мы можем воспользоваться формулой обыкновенной производящей функции, записав ее следующим образом:
$$G(z)=\sum_{n=0}^{\infty} P\{\xi > n\} z^n = \sum_{n=0}^{\infty} (1- P\{\xi \leq n\}) z^n$$
Заметим, что событие $\{\xi \leq n\}$ равносильно событию $\{\xi < n+1\}$, поэтому мы можем переписать это выражение в виде:
$$G(z)=\sum_{n=0}^{\infty} (1-F_\xi(n+1))z^n$$
где $F_\xi(n) = P\{\xi \leq n\}$ - функция распределения случайной величины $\xi$.
Теперь мы можем выразить $G(z)$ через производящую функцию $G_\xi(z)$:
$$\begin{aligned} G(z) &= \sum_{n=0}^{\infty} (1-F_\xi(n+1))z^n\\ &=\sum_{n=0}^{\infty}(1-F_\xi(n)-f_\xi(n+1))z^n\\ &=\sum_{n=0}^{\infty}(1-f_\xi(n)z^n)f_\xi(0)-\sum_{n=0}^{\infty}f_\xi(n+1)z^n\\ &=\frac{1-G_\xi (z)}{1-z}-z G_\xi(z) \end{aligned}$$
где мы использовали формулу для суммы бесконечно убывающей геометрической прогрессии $f_\xi(0)$ - вероятность того, что $\xi=0$, а $f_\xi(n)$ - вероятность того, что $\xi=n$.

#

Для нахождения производящей функции для последовательности вероятностей $P\{\xi \geq n\}$ мы можем воспользоваться формулой обыкновенной производящей функции, записав ее следующим образом:
$$G(z)=\sum_{n=0}^{\infty} P\{\xi \geq n\} z^n$$
Событие $\{\xi \geq n\}$ может быть выражено через событие $\{\xi > n-1\}$, поэтому мы можем переписать $G(z)$ следующим образом:
$$G(z)=\sum_{n=0}^{\infty} P\{\xi > n-1\} z^n$$
Теперь мы можем выразить $G(z)$ через производящую функцию $G_\xi(z)$:
$$\begin{aligned} G(z)&=\sum_{n=1}^{\infty} P\{\xi > n-1\} z^n + P\{\xi=0\} \\ &= z^0 P\{\xi=0\}+\sum_{n=1}^{\infty} P\{\xi > n-1\} z^n \\ & = f_\xi(0) +\sum_{n=1}^{\infty} f_\xi(n) z^n \\ &= G_\xi(z) - f_\xi(0)\\ &=\frac{1}{1-z} G_\xi(z) \end{aligned}$$
где $f_\xi(n)$ - вероятность того, что $\xi = n$, $f_\xi(0)$ - вероятность того, что $\xi = 0$. Последняя формула получена из свойства производящей функции $G_\xi(0) = f_\xi(0)$.
#
Для нахождения производящей функции для последовательности вероятностей $P\{\xi < n\}$ мы можем воспользоваться формулой обыкновенной производящей функции, записав ее следующим образом:
$$G(z)=\sum_{n=0}^{\infty} P\{\xi < n\} z^n$$
Событие $\{\xi < n\}$ может быть выражено через событие $\{\xi \leq n-1\}$, поэтому мы можем переписать $G(z)$ следующим образом:
$$G(z)=\sum_{n=0}^{\infty} P\{\xi \leq n-1\} z^n$$
Теперь мы можем выразить $G(z)$ через производящую функцию $G_\xi(z)$:
$$\begin{aligned} G(z)&=\sum_{n=0}^{\infty} P\{\xi \leq n-1\} z^n \\ &=\sum_{n=0}^{\infty} \left[\sum_{k=0}^{n-1} P\{\xi = k\} + P\{\xi = n\} \right]z^n\\ &= \sum_{n=0}^{\infty} \left[\sum_{k=0}^{n} P\{\xi = k\} \right]z^n \\ &= \sum_{n=0}^{\infty} f_\xi(n+1) z^n \\ &= \frac{1}{z} \left(G_\xi(z) - f_\xi(0) \right) \end{aligned}$$
где $f_\xi(n)$ - вероятность того, что $\xi = n$, $f_\xi(0)$ - вероятность того, что $\xi = 0$. Последняя формула получена из свойства производящей функции $G_\xi(0) = f_\xi(0)$.

#
Для того, чтобы выяснить, является ли данное выражение производящей функцией случайной величины, необходимо проверить, удовлетворяет ли оно условиям производящей функции.

Первое условие - соответствие области определения. Область определения производящей функции должна включать ноль.  В нашем случае знаменатель дроби равен нулю при $z^2 = 2$, то есть в точках $z=\sqrt{2}$ и $z=-\sqrt{2}$  функция не определена. Однако, поскольку случайная величина принимает только неотрицательные значения, производящая функция для нее должна иметь вид степенного ряда с неотрицательными коэффициентами. Также, если случайная величина принимает значение $n$ с вероятностью $p_n$, то коэффициент при $z^n$ в производящей функции будет равен $p_n$. 

В данном случае знаменатель дроби $2-z^2$ является положительным для значений $z$ на интервале $(-\sqrt{2}, \sqrt{2})$. То есть он определен для всех значений аргумента, включая $z=0$, и таким образом это условие выполняется.

Второе условие - сходимость ряда. Производящая функция должна сходиться в некоторой области, содержащей ноль. В данном случае $z^2/(2-z^2)$ имеет разложение в простые дроби, которое можно вычислить следующим образом:
$$\frac{z^2}{2-z^2}=-\frac{1}{\sqrt{2}}\cdot \frac{1}{z-\sqrt{2}} + \frac{1}{\sqrt{2}}\cdot\frac{1}{z+\sqrt{2}}.$$
Таким образом, исходное выражение является суммой двух функций, каждая из которых имеет сходящееся разложение в ряд Тейлора. Таким образом, мы можем заключить, что данное выражение является производящей функцией для некоторой последовательности, возможно, дискретной случайной величины.






Для случайной величины ξ с функцией распределения P{ξ = k} = p_k, ее производящая функция определяется как Gξ(z) = E[z^ξ] = Σ_k=0^∞ (p_k * z^k).

Поскольку ξ — неотрицательная целочисленная случайная величина, то P{ξ >= n} = Σ_k=n^∞ P{ξ = k}. 

Таким образом, производящая функция для P{ξ >= n} будет:

G(z) = E[z^(ξ-n)] = Σ_k=n^∞ P{ξ = k} * z^(k-n) 

= Σ_k=n^∞ (Σ_j=0^k P{ξ = j}) * z^(k-n)

= Σ_k=n^∞ (1 - Σ_j=0^(n-1) P{ξ = j}) * z^(k-n)

= Σ_k=n^∞ z^(k-n) - Σ_k=n^∞ Σ_j=0^(n-1) P{ξ =j} * z^(k-n)

= z^n * Σ_k=0^∞ P{ξ = k} * z^k - Σ_j=0^(n-1) Σ_k=0^∞ P{ξ = k} * z^k

= z^n * Gξ(z) - Σ_j=0^(n-1) z^j * Gξ(z)

Таким образом, мы можем выразить производящую функцию P{ξ >= n} через производящую функцию ξ, а именно:

G(z) = z^n * Gξ(z) / (1 - z) - Σ_j=0^(n-1) z^j * Gξ(z) / (1 - z)