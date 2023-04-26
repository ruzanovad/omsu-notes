\documentclass[a4paper]{article}
\usepackage{cmap}
\usepackage[utf8]{inputenc}
\usepackage[T2A]{fontenc}
\usepackage{amsfonts}
\usepackage{amsmath, amsthm}
\usepackage{amssymb}
\usepackage{hyperref}
\usepackage{multicol}
\usepackage{xcolor}

\newcommand\letsymbol{\mathord{\sqsupset}}
\usepackage[russian]{babel}
\renewcommand\qedsymbol{$\blacktriangleright$}
\newtheorem{theorem}{Теорема}[section]
\newtheorem{lemma}{Лемма}[section]
\theoremstyle{definition}
\newtheorem*{example}{Пример}
\newtheorem*{definition}{Определение}
\newtheorem*{statement}{Утверждение}
\theoremstyle{remark}
\newtheorem*{remark}{Замечание}

\setlength{\topmargin}{-0.5in}
\setlength{\oddsidemargin}{-0.5in}
\textwidth 185mm
\textheight 250mm

\begin{document}
\tableofcontents
\section{Введение}
\begin{definition}[Методы оптимизации]
    Раздел прикладной математики, содержание которого составляет теория и методы решения оптимизационных задач
\end{definition}
\begin{definition}[Оптимизационная задача]
    Задача выбора наилучшего варианта (в некотором смысле) из имеющихся
\end{definition}
\begin{definition}[Задача оптимизации]
        $\begin{cases}
        f(x)\to \min(\max) \\
        x\in D
    \end{cases}$
\end{definition}
D - множество допустимых решений, $f:D\to \mathbb{R}$
\begin{definition}[Задача МП]
    $\begin{cases}
        (1) f(x)\to \min (\max) [extr] (opt)\\
        (2) g_i(x) \# 0, i=1,\dots, m - \text{ограничения} \\
        (3)x\in \mathbb{R}^n 
    \end{cases}$
    \(x = (x_1, ..., x_n) \, f(x): \mathbb{R}^n \to \mathbb{R}, \, g_i(x) : \mathbb{R}^n \to \mathbb{R}\)
\end{definition}
\begin{definition}[Допустимое решение]
    $x\in \mathbb{R}^n$, удовл (2), называется допустимым решением задачи.
\end{definition}
\begin{definition}[Оптимальное решение]
    Допустимое решение $x^*\in D$ задачи 1 - 3 называется оптимальным решением, если $f(x) \leq f(x^*) \, \forall x\in D$ в случае задачи максимизации и $f(x) \geq f(x^*) \, \forall x\in D$ в случае задачи минимизации
\end{definition}
Глобальный оптимум -  $x^*$
\begin{definition}[Локальный оптимум]
    Допустимое решение $\widetilde{x}\in D$ задачи 1 - 3 называется локальным оптимумом, если $f(x) \leq f(\widetilde{x})$ для всех х из некоторой окрестности $\widetilde{x}$ в случае задачи максимизации и $f(x) \geq f(\widetilde{x})$ для всех х из некоторой окрестности $\widetilde{x}$ в случае задачи минимизации
\end{definition}
\begin{definition}[Разрешимая/неразрешимая]
    Задача 1 - 3, которая обладает оптимальным решением, называется разрешимой, иначе неразрешимой
\end{definition}
\section{Линейное программирование}
\subsection{Постановка задачи (ЛП), теоремы эквивалентности}
\begin{definition}[Общая задача ЛП]
$    \begin{cases}
        f(x) = c_0 + \sum_{j = 1}^n c_j x_j \to \max (\min) \\ 
        \sum_{j = 1}^{n} a_{ij}x_j \# b_i, \, i = 1, \dots, m \\
        x_j \geq 0, j \in J\subseteq \{1, \dots, n\}
    \end{cases}$, где \(x = (x_1, ..., x_n)\in \mathbb{R}^n\) -  вектор переменных

    Матричная запись:

    $\begin{cases}
        f(x) = (c, x) \to \max(\min)\\
        Ax \# b \\
        x_j \geq 0, j \in J\subseteq \{1, \dots, n\}
    \end{cases}$, $x = \begin{pmatrix}
        x_1 \\ \vdots \\ x_n
        \end{pmatrix}, b = \begin{pmatrix}
            b_1 \\ \vdots \\ b_m
            \end{pmatrix}, 
            A = \begin{pmatrix} 
                a_{11} & \dots  & a_{1n}\\
    \vdots & \ddots & \vdots\\
    a_{m1} & \dots  & a_{mn} 
    \end{pmatrix}$
\end{definition}
\begin{definition}[Стандартная (симметрическая) форма]
    $\begin{cases}
        f(x) = c_0 + \sum_{j = 1}^n c_j x_j \to \max (\min) \\
        \sum_{j = 1}^{n} a_{ij}x_j \leq (\geq) b_i, \, i = 1, \dots, m \\ 
        x_j \geq 0, j = 1, \dots, n
    \end{cases}$
\end{definition}
\begin{definition}[КЗЛП]
    $\begin{cases}
        f(x) = c_0 + \sum_{j = 1}^n c_j x_j \to \max\\
        \sum_{j = 1}^{n} a_{ij}x_j =b_i, \, i = 1, \dots, m \\ 
        x_j \geq 0, j = 1, \dots, n
    \end{cases}$
\end{definition}
\begin{definition}[Основная задача ЛП]
    $\begin{cases}
        f(x) = c_0 + \sum_{j = 1}^n c_j x_j \to \max\\
        \sum_{j = 1}^{n} a_{ij}x_j \leq b_i, \, i = 1, \dots, m
    \end{cases}$
\end{definition}
\begin{definition}[Эквивалентные ЗЛП (ЗМП)]
    Две задачи ЛП $P_1, P_2$ называются \textit{эквивалентными}, если любому допустимому решению задачи $P_1$ соответствует некоторое допустимое решение задачи $P_2$ и наоборот, причем оптимальному решению одной задачи соответствует оптимальное решение другой задачи.
\end{definition}
\begin{theorem}[Первая теорема эквивалентности]
    Для любой ЗЛП существует эквивалентная ей каноническая ЗЛП.
\end{theorem}
\begin{theorem}[Вторая теорема эквивалентности]
    Для любой ЗЛП существует эквивалентная ей симметрическая ЗЛП.
\end{theorem}
\subsection{Каноническая задача ЗЛП. Базисные решения}
\begin{definition}[Базисное решение]
    Пусть $\overline{x}$ - решение $Ax = B$. Тогда вектор $\overline{x}$ называется базисным решением СЛАУ, если система вектор-столбцов матрицы А, соответствующая ненулевым компонентам вектора $\overline{x}$, ЛНЗ
\end{definition}
\begin{remark}
    Если система однородная, то x = $\overline{0}$ - базисное решение
\end{remark}
\begin{definition}
    Неотрицательное базисное решение СЛУ называется базисным решением канонической задачи ЛП
\end{definition}
\begin{definition}[Вырожденное БР]
    $\overline{x}$ - БР КЗЛП называется вырожденным, если число ненулевых компонент меньше ранга матрицы А, иначе невырожденное
\end{definition}
\begin{lemma}
    Если x и x' - Б.Р. КЗЛП, $x\neq x'$, то \[J(x) \neq J(x'), J(x)\subset J(x'), J(x) \supset J(x'),\] где $J(x) = \{j | x_j \neq 0, j = 1\dots n\}$
\end{lemma}
\begin{theorem}[О конечности множества базисных решений]
    Число базисных решений КЗЛП конечно
\end{theorem}
\begin{theorem}[О существовании оптимальных БР]
    Если КЗЛП разрешима, то существует ее оптимальное БР
\end{theorem}
\subsection{Симплекс-метод}
Рассмотрим КЗЛП.
\subsubsection{Симплекс-метод для приведенной ЗЛП}
\begin{definition}[Система с базисом]
    СЛАУ - СЛАУ с базисом, если в каждом уравнении имеется переменная с коэффициентом +1, отсутствующая в других уравнениях. Такие переменные будем называть базисными, остальные не базисными
\end{definition}
\begin{definition}[ПЗЛП]
    КЗЛП называется приведенной, если 
    \begin{enumerate}
        \item СЛАУ $Ax = B$ является системой с базисом
        \item Целевая функция выражена через небазисные переменные
    \end{enumerate}
\end{definition}
\begin{definition}[Прямо допустимая симплексная таблица]
    СТ называется прямо допустимой, если $a_{i0}\geq 0, i = 1, \dots, m$ (bшки)
\end{definition}
\begin{definition}[Двойственно допустимая симплексная таблица]
    СТ называется двойственно допустимой, если $a_{0j}\geq 0, i = 1, \dots, n+m$ (cшки)
\end{definition}
\begin{theorem}
    Если симплекс-таблица является прямо допустимой и $a_{0j}\geq 0, j = 1\dots, n+m$, то соответствующее базисное решение является оптимальным 
\end{theorem}
\begin{theorem}
    Если в симплекс-таблице существует $a_{0q}< 0, a_{iq}\leq 0, \, \forall i = 1\dots, m$, то задача неразрешима, потому что f неограничена на множестве допустимых решений
\end{theorem}
\begin{theorem}
    Если ведущая строка выбирается из условия минимума ключевого отношения, то следующаяя симплексная таблица будет прямо допустимой
\end{theorem}
\begin{theorem}[Об улучшении базисного решения]
    Если $\exists a_{0j}< 0, j = 1\dots n+m$, то возможен переход к новой прямо допустимой симплекс таблице, причем $f(x)\leq f(x')$, где x - БР старой таблицы, x'- БР новой таблицы,
    f(x) = $a_{00}$ старой таблицы, f(x') = $a_{00}-\frac{a_{p0}a_{0q}}{a{pq}}, a_{p0} = 0$ - вырожденное решение
\end{theorem}
\subsection{Каноническая ЗЛП}
\paragraph*{Метод искусственного базиса}
\begin{definition}[искусственные]
    $t_i\geq 0$ - искусственные переменные
\end{definition}
\begin{remark}[Свойства ВЗЛП]
    \begin{enumerate}
        \item ВЗЛП почти приведенная (нужно выразить $t_i$)
        \item $h(x, t) \leq 0 \quad \forall (x, t)\in \widetilde{D}$
        \item $\widetilde{D}\neq 0$ (например, есть $(0, \dots, n, b_1, \dots, b_m)$, n нулей)
        \item ВЗЛП всегда разрешима 
    \end{enumerate}
\end{remark}
\begin{theorem}[О существовании допустимого решения исходной КЗЛП]
    $$D\neq 0 \Leftrightarrow h^*(x, t)=0$$
\end{theorem}
\begin{theorem}[О преобразовании КЗЛП в эквивалентную ей приведенную]
    Если множество допустимых решений исходной КЗЛП непусто, то ПЗЛП, эквивалентная исходной КЗЛП, может быть получена из последней симплекс таблицы - таблицы ВЗЛП
\end{theorem}
\subsection{Двойственность в ЛП}
\begin{definition}
    Будем говорить, что знаки линейных ограничений ЗЛП согласованы с целевой функцией, если в задаче на max ограничения неравенства имеют вид "$\leq$", а в задаче на min ограничения на неравенство имеют вид "$\geq$"
\end{definition}
\begin{definition}[Двойственная задача]
    Для ЗЛП I двойственной задачей II является ЗЛП вида:
        $$f(x) = \sum_{j = 1}^n c_j x_j \to \max \leftrightarrow g(y) = \sum_{i = 1}^m b_i y_i\to \min,$$
        $$\sum_{j = 1}^n a_{ij} x_j \leq b_i, i = 1, \dots, l \leftrightarrow y_i\geq 0, i = 1...l,$$
        $$\sum_{j = 1}^n a_{ij} x_j = b_i, i = l+1, \dots m \leftrightarrow y_i\in \mathbb{R}, i = l+1, \dots, m,$$
        $$x_j\geq 0, i = 1, \dots p\leftrightarrow \sum_{i = 1}^m a_{ij} y_i \leq c_j, j = 1, \dots, p$$
        $$x_j \in \mathbb{R}, j = p+1, \dots n \leftrightarrow \sum_{i = 1}^m a_{ij} y_i \leq c_j, j = p+1, \dots, n$$
        Задачу I называют прямой, а II - двойственной. Стрелки соответствуют сопряженным ограничениям
\end{definition}
\begin{theorem}[Основное неравенство двойственности]
    $$\forall x\in D_I, \forall y\in D_{II}, f(x)\leq g(y)$$
\end{theorem}
\subsection{Теоремы двойственности}
\begin{lemma}[основная лемма]
    Пусть $\forall x\in D_I \neq \varnothing, f(x)\leq M < +\infty\implies \exists y \in D_{II}\, g(y)\leq M$
\end{lemma}
\begin{theorem}[Первая теорема двойственности]
    Если одна из пары двойственных задач разрешима, то разрешима и другая, причем оптимальное значение целевых функций совпадает, т.е $f(x^*) = g(y^*)$, где $x^*, y^*$ - оптимальные решения задач I, II соответственно
\end{theorem}
\begin{theorem}
    Вектор $x^* \in D_I$ является оптимальным решением задачи 
    I $\Leftrightarrow \exists y^* \in D_{II}$ т.ч  $g(y^*) = f(x^*)$    
\end{theorem}
\begin{definition}[Условия дополняющей нежесткости]
    Будем говорить, что $x\in D_I, y \in D_{II}$ удовлетворяют УДН, если при подстановке в любую пару сопряженных неравенств хотя бы одно из них обращается в равенство. Это означает, что следующие характеристические произведения обращаются в 0:
    \[(\sum_{j = 1}^n a_{ij}x_j - b_i)y_i = 0, i  = 1, \dots m\]
    \[x_i (\sum_{i = 1}^m a_{ij}y_i - c_j) = 0, j = 1, \dots n\]
\end{definition}
\begin{theorem}[Вторая теорема двойственности]
    $x^* \in D_I, y^*\in D_{II}.$ оптимальны в задачах I, II тогда и только тогда, когда они удовлетворяют УДН.
\end{theorem}
\begin{theorem}[Второй критерий оптимальности (следствие)]
    \(x^* \in D_I\) является оптимальным решением I \(\Leftrightarrow\)
    \(\exists y^* \in D_{II}\) т.ч. \(x^*\) и \(y^*\) удовлетворяют УДН
\end{theorem}
\subsection{Критерий разрешимости ЛП}
\begin{definition}[Точная верхняя грань функции]
    $M^*$ называется точной верхней гранью функции f(x) на множестве D, если \begin{enumerate}
        \item $\forall x \in D \quad f(x) \leq M^*$
        \item $\forall M < M^* \quad \exists x\in D \quad f(x)> M$
    \end{enumerate}
\end{definition}
\begin{lemma}[О точной верхней грани функции g(y) на $D_{II}$]
    $M^*< +\infty$ - точная верхняя грань $f(x)$ на $D_{I}$, тогда $\forall y \in D_{II} \quad g(y) \geq M^*$
\end{lemma}
\begin{theorem}[Критерий разрешимости]
    Целевая функция задачи ЛП ограничена сверху (снизу) на непустом множестве допустимых решений тогда и только тогда, когда задача максимизации (минимизации) разрешима
\end{theorem}
\subsection{Классификация пар двойственных задач}
\begin{theorem}[Малая теорема двойственности]
    Если $D_I \neq \varnothing, D_{II} \neq \varnothing\implies$ обе задачи точно разрешимы
\end{theorem}
\begin{theorem}[О причинах неразрешимости ЗЛП]
    $D_{I}\neq \varnothing$, целевая функция неограничена сверху на $D_I$ тогда и только тогда, когда II неразрешима, так как $D_{II}=\varnothing$
\end{theorem}
\paragraph*{Классификация}
\begin{enumerate}
    \item $D_I\neq \varnothing, D_{II} \neq \varnothing$ обе задачи разрешимы, т.к $f(x^*) = g(y^*)$
    \item $D_I\neq \varnothing, D_{II} = \varnothing$ обе неразрешимы, т.к $f(x)$ неограничена, $D_{II}=\varnothing$
    \item $D_I = \varnothing, D_{II} \neq \varnothing$ обе неразрешимы, т.к  $D_I = \varnothing, g\to +\infty$ на $D_{II}$
    \item $D_I = \varnothing, D_{II} = \varnothing$ обе неразрешимы
\end{enumerate}
\subsection{Экономическая интерпретация двойственной задачи и теорема двойственности}

\paragraph*{Экономический смысл двойственной переменной и задачи}
Линейные ограничения двойственной задачи связывают задачи всех ресурсов,
идущих на производство 1 ед. продукции, с прибылью от продажи этой единицы продукции $\implies y_i$ измеряются в ед. стоимости

Т.к $y_i$ соответствует ресурсам, то $y_i$ - некая цена ресурса. Будем называть ее условной ценой (двойственной оценкой на ресурсы).

Для интерпретации двойственной задачи посмотрим на предприятие как \textbf{на продавца ресурсов.}

Задача (II) определяет справедливые цены на ресурсы, в которой требуется определить набор оценок всех ресурсов, при котором для каждого вида продукции ресурсов затрачено на производство 1 ед. продукции \textbf{не меньше} дохода от ее реализации, при этом суммарная оценка ресурсов будет минимальна

\begin{theorem}[1]
    Суммарная прибыль от продажи произведенной продукции =  суммарной оценке всех ресурсов
\end{theorem}
$y_i^*$ - ценность i-того ресурса для производителя - доход, который может быть получен от 1 единицы использованного i-того ресурса
\begin{theorem}[2]
    \begin{itemize}
        \item ресурс 1 и 2 расходуется полностью - их называют дефицитными - они соответствуют $y_i^*\ge 0$

        % ресурс 3 расходуется не полностью 
        \item $x_1^* > 0, x_2^* > 0$, т.е продукция произвед. $\implies$ расходы ресурсов равны стоимости продажи этих продуктов

        если стоимость ресурсов, требуемых для производства 1 ед. прод > прибыль
    \end{itemize}
\end{theorem}
\subsection{Анализ на чувствительность модели ЛП}
\begin{definition}[Анализ чувствительности модели ЛП]
    Анализ чувствительности модели ЛП - исследование влияния изменения входных данных на оптимальное решение
\end{definition}
Рассмотрим частную задачу - анализ изменения оптимального решения при изменении запаса только одного ресурса.

$y_i^*$ рассмотрим как потенциальную возможность получить доп. доход.

Рассмотрим три задачи:

\[\begin{cases} (I)f = (c, x) \to \max \\ Ax\le b \\ x\ge 0 \\ b = (b_1, \dots, b_m) \end{cases}\]
\[\begin{cases} (I')f = (c, x) \to \max \\ Ax\le b' \\ x\ge 0 \\ b' = (b_1+\Delta b_1, \dots, b_m), D_I \subset I' \end{cases}\]
\[\begin{cases} (\overline{I})f = (c, x) \to \max \\ Ax\le \overline{b} \\ x\ge 0 \\ \overline{b} = \alpha b + (1- \alpha )b', \alpha \in (0, 1) \end{cases}\]

\begin{definition}[решения, имеющие одинаковую структуру]
    Будем говорить, что решения $x\in D_I$ и $x' \in D_{I'}$ имеют одинаковую структуру, если
    \begin{enumerate}
        \item совпадают по ассортименту, т.е. $x_j = 0 \Leftrightarrow {x_j}' = 0 j = 1, \dots, n$
        \item имеют одни и те же дефицитные ресурсы, т.е i-тое ограничение I выполняется на равенство тогда и только тогда, когда i-тое ограничение I' задачи выполняется на равенство
    \end{enumerate}
\end{definition}
\begin{lemma}[О планах одинаковой структуры]
    Пусть x* - опт решение I и $x' \in D_I'$ - решение той же структуры, тогда
    \begin{enumerate}
        \item x' - опт решение задачи I';
        \item для любого $\alpha \in (0, 1)$ существует оптимальное решение $\overline{I}$ имеющее эту же структуру
    \end{enumerate} 
\end{lemma}
\begin{remark}
    Изменять запас ресурса $P_1$ можно до тех пор, пока в задаче I' будет существовать оптимальный план той же структуры, что и в I
\end{remark}
\begin{definition}[Малое (допустимое) изменение]
    Малое (допустимое) изменение ресурса P1 - такое изменение $\Delta b_1 = b_1' - b_1$ для кот в задаче I' существует оптимальное решение той же структуры, что и оптимальное решение исходной задачи I
\end{definition}
В силу леммы, если $\Delta b_1$ - допустимое изменение ресурса, то и все меньшие изменения также допустимы.

Пусть $F(b_1, \dots, b_m)$ - max доход, который можно получить при запасах ресурсов $b_i$

\begin{definition}[3-я теорема двойственности]
    При допустимом изменении i-того ресурса приращение целевой функции прямо пропорционально изменению ресурса с коэффициентом пропорциональности, равным $y_i^*$

    $$\Delta_i F = \Delta b_i y_i^*, \Delta_i F = F(b_1, \dots, b_{i-1}, b_i + \Delta b_i, \dots, b_m)-F(b_1, \dots, b_{i-1}, b_i, \dots, b_m)$$
\end{definition}
\subsection{О конечности симплекс-метода}
\begin{definition}[вырожденная КЗЛП]
    КЗЛП является вырожденной, если среди ее БР имеются вырожденные.
\end{definition}
\begin{enumerate}
    \item Если КЗЛП не является вырожденной - в процессе работы симплекс-метода $f(x_1) < \dots < f(x^*)$ (с-метод конечен)
    \item $a_{p0} = 0\implies f(x) = f(x')$ БР сохраняется, но меняется набор базисных переменных 
\end{enumerate}
после некоторого числа итераций возможен возврат к уже встречавшимся ранее наборам базисных переменных - с-м может зациклиться
\paragraph*{Уточняющие правила}
\begin{enumerate}
    \item Правило Данцига - выбирается столбец $$a_{0q} = \min_{j: a_{0j}< 0} a_{0j}$$
    \item правило наибольшего приращения: выбираем такое q, при котором приращение наибольшее
    $$a_{00}' = a_{00} - \frac{a_{0q} a_{p0}}{a_{pq}}$$
    \item Правило Бленда

    Cтрока и столбец выбираются в соответствии с обычными правилами выбора, причем каждый раз из возможных выбирается переменная с наименьшим номером
    \item Лексикографическое правило выбора ведущей строки
    $$\frac{\overrightarrow{a_p} }{a_{pq}}  = \min_{a_{iq}>0}\frac{\overrightarrow{a_i} }{a_{iq}}$$
\end{enumerate}
\subsection{Двойственный симплекс-метод}
$$\textsubscript{мне по. чисто по.}$$
\end{document}