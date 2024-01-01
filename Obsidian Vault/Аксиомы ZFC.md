> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Ext (Аксиома экстенсиональности)","label":"ext--","_index":0}] Аксиома 1 (Ext (Аксиома экстенсиональности)).
> $$\forall X \forall Y (\forall u (u \in X \leftrightarrow u \in Y ) \to X = Y)$$

^5e626d

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Empty (Аксиома пустого множества)","label":"empty---","_index":1}] Аксиома 2 (Empty (Аксиома пустого множества)).
>   $$\exists X \;\forall u (u \in X \leftrightarrow u \neq u)$$

^ee2ade

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Pair (Аксиома пары)","label":"pair--","_index":2}] Аксиома 3 (Pair (Аксиома пары)).
> $$\forall X \;\forall Y \;\exists Z \;\forall u (u \in Z \leftrightarrow (u = X \vee u = Y ))$$

^b44a84

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Sep (схема аксиом выделения)","label":"sep---","_index":3}] Аксиома 4 (Sep (схема аксиом выделения)).
> $$\forall X \;\exists Y \;\forall u (u ∈ Y ↔ (u ∈ X \wedge \Phi (u)))$$

^1214de

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Union (Аксиома объединения)","label":"union--","_index":4}] Аксиома 5 (Union (Аксиома объединения)).
> $$\forall X \;\exists Y \;\forall u (u \in Y \leftrightarrow \exists v (v \in X \wedge u \in v))$$

^e9971e

> [!math|{"type":"definition","number":"","setAsNoteMathLink":false,"title":"Подмножество","label":""}] Определение (Подмножество).
> $$x \subseteq y := \forall v (v \in x \to v \in y)$$
> Если X ⊆ Y , то говорят, что X является подмножеством Y, или Y включает X.
> 

> [!math|{"type":"definition","number":"","setAsNoteMathLink":false,"title":"Собственное подмножество","label":"-"}] Определение (Собственное подмножество).
> Если $X \subseteq Y$ и $X \neq Y$ , то $Y$ называют собственным подмножеством $X$, и временами пишут $X\subsetneq Y$.

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Power (Аксиома степени)","label":"power--","_index":5}] Аксиома 6 (Power (Аксиома степени)).
> $$\forall X\; \exists Y\; \forall u (u \in Y \leftrightarrow u \subseteq X)$$

> [!math|{"type":"definition","number":"","setAsNoteMathLink":false,"title":"Индуктивное множество","label":"-","_index":6}] Определение (Индуктивное множество).
> Рассмотрим условие 
> $$Ind (x) := \varnothing \in x \wedge \forall u (u \in x \to u \cup \{u\} \in x).$$
> Будем называть X индуктивным, если имеет место $Ind (X)$. Интуитивно каждое **индуктивное** множество бесконечно. 

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Inf (Аксиома бесконечности)","label":"inf--","_index":6}] Аксиома 7 (Inf (Аксиома бесконечности)).
> $$\exists X\; Ind (X)$$

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Repl (Схема аксиом подстановки)","label":"repl---","_index":7}] Аксиома 8 (Repl (Схема аксиом подстановки)).
> $$\forall x \;\forall y_{1} \;\forall y_2 ((\Phi (x, y_{1}) \wedge \Phi (x, y_2)) \to y_{1} = y_{2}) \to \forall X \;\exists Y \;\forall y (y \in Y \leftrightarrow \exists x (x \in X \wedge \Phi (x, y)))$$

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Reg (Аксиома регулярности)","label":"reg--","_index":8}] Аксиома 9 (Reg (Аксиома регулярности)).
> $$\forall X (X \neq \varnothing \to \exists u (u \in X \wedge X \cap u = \varnothing))$$

> [!math|{"type":"axiom","number":"auto","setAsNoteMathLink":false,"title":"Choice (Аксиома выбора)","label":"choice--","_index":9}] Аксиома 10 (Choice (Аксиома выбора)).
> $$\forall X (\varnothing \notin X\to \exists f (f: X\to \bigcup X \wedge \forall u \in X (f(u)\in u)))$$

| [Ext](#^5e626d) | Множество определяется своими элементами |
| ---- | ---- |
| [Empty](#^ee2ade) | Гарантирует существование пустого множества |
| [Pair](#^b44a84) | Способ получать новые множества из двух имеющихся |
| [Sep](#^1214de) | Выделение нового множества с каким-то свойством |
| [Union](#^e9971e) | Объединение существует |
| Power | Булеан существует |
| Inf | Гарантирует существование индуктивного (а заодно и бесконечного) множества |
| Repl | В любом непустом семействе множеств a есть множество b, каждый элемент c которого не принадлежит данному семейству a. |
| Reg | Структура универса всех множеств |
| Choice | Камень преткновения |