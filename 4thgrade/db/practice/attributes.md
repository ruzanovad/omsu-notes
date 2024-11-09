### Должности
- **A1**: id должности (PK)
- **A2**: Название должности
- **A3**: Тип должности
- **A4**: id сотрудника (FK)
- **A5**: id подразделения (FK)
- **A6**: График работы
- **A7**: Дата назначения
- **A8**: Дата окончания должности
### Подразделения
- **B1**: id подразделения (PK)
- **B2**: Название подразделения
- **B3**: id текущего руководителя (FK)
- **B4**: id группы подразделений (FK)
- **B5**: Родительское подразделение id (FK)
- **B6**: Место нахождения
- **B7**: Адрес электронной почты
- **B8**: Телефон
- **B9**: Адрес сайта
- **B10**: Положение о подразделении
- **B11**: Тип подразделения
### Группы подразделений
- **C1**: id группы подразделений (PK)
- **C2**: Название группы
### Реализуемые уровни образования
- **D1**: id уровня (PK)
- **D2**: Наименование
### Профиль
- **E1**: id профиля (PK)
- **E2**: Наименование профиля
- **E3**: id образовательной программы (FK)
- **E4**: id уровня образования (FK)
- **E5**: id подразделения (FK)
- **E6**: Форма обучения
- **E7**: Нормативные сроки обучения
- **E8**: Срок действия госаккредитации
- **E9**: Использование электронного обучения и дистанционных технологий
- **E10**: Учебный план (id документа) (FK)

### Дисциплина
- **F1**: id дисциплины (PK)
- **F2**: Название дисциплины
- **F3**: id профиля (FK)
- **F4**: Программа курса
- **F5**: Обязательная/формируемая часть
- **F6**: Форма обучения
- **F7**: Обязательная дисциплина/дисциплина по выбору/факультатив
- **F8**: Ответственный за разработку программы дисциплины (id должности)
### Запись студента на специальность
- **G1**: id записи (PK)
- **G2**: id студента (FK)
- **G3**: id специальности (FK)
- **G4**: Номер зачётной книжки
- **G5**: Группа
- **G6**: Курс
- **G7**: Дата зачисления
- **G8**: Дата отчисления
### Студент
- **H1**: id студента (PK)
- **H2**: ФИО
- **H3**: Номер студенческого билета
- **H4**: Статус студента

### Документ
- **I1**: id документа (PK)
- **I2**: Название документа
- **I3**: Тип документа
- **I4**: Дата создания
- **I5**: Дата назначения
- **I6**: Автор/Ответственный (FK)
- **I7**: Файл документа
- **I8**: Описание документа
### Сотрудники
- **J1**: id сотрудника (PK)
- **J2**: ФИО
- **J3**: Email
- **J4**: Телефон
- **J5**: Паспортные данные
- **J6**: id уровня образования (FK)
### Уровень образования (учебная степень) (сотрудники)
- **K1**: id "учёной степени" (PK)
- **K2**: Название учёной степени
- **K3**: Дата присуждения
- **K4**: Организация

### Образовательные программы (направление подготовки)
- **L1**: id образовательной программы (PK)
- **L2**: id подразделения (FK)
- **L3**: Наименование образовательной программы
- **L4**: Код программы