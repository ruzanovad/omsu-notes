- [Кластеризация — Викиконспекты (ifmo.ru)](https://neerc.ifmo.ru/wiki/index.php?title=%D0%9A%D0%BB%D0%B0%D1%81%D1%82%D0%B5%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F)
- [Kleinberg_2002.pdf (alexhwilliams.info)](http://alexhwilliams.info/itsneuronalblog/papers/clustering/Kleinberg_2002.pdf)
-

- Scale-Invariance. For any distance function d and any $\alpha$ > 0, we have f(d) = f($\alpha$ $\cdot$ d).
- Richness. Range(f) is equal to the set of all partitions of S
- Consistency. Let d and d' be two distance functions. If f(d) = $\Gamma$, and d' is a $\Gamma$-transformation of d, then f(d') = $\Gamma$.
> [!math|{"type":"theorem","number":"auto","setAsNoteMathLink":true,"title":"Kleinberg","label":"kleinberg","_index":0}] Теорема 1 (Kleinberg).
> For each $n\geqslant 2$, there is no clustering function $f$ that satisfies Scale-Invariance, Richness, and Consistency.

> [!math|{"type":"definition","number":"auto","setAsNoteMathLink":true,"label":"","title":"Гиперпараметр","_index":0}] Определение 1 (Гиперпараметр).
> Гиперпараметры — это параметры, значения которых задаются до начала обучения, не изменяются в процессе обучения и не зависят от заданного набора данных.
- Эвристические алгоритмы
- Эволюционные алгоритмы (критерий качества - гиперпараметр)

| Эвристические | Эволюционные |
|---| ---|
|K-means, DBSCAN, [[Иерархический алгоритм]] | [Эволюционные алгоритмы кластеризации — Викиконспекты (ifmo.ru)](https://neerc.ifmo.ru/wiki/index.php?title=%D0%AD%D0%B2%D0%BE%D0%BB%D1%8E%D1%86%D0%B8%D0%BE%D0%BD%D0%BD%D1%8B%D0%B5_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D1%8B_%D0%BA%D0%BB%D0%B0%D1%81%D1%82%D0%B5%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8) |


> [! ] 
> Эволюционные алгоритмы осуществляют непосредственный поиск в пространстве возможных разбиений с целью найти оптимальное по задаваемой в качестве параметра мере качества.


