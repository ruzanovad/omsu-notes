#include <iostream>
/*
* статические структуры: массивы, строки
* динамические структуры: односвязный список, двусвязный список
* абстрактный тип данных - тип данных, реализация которого скрыта от пользователя
* доступ осуществляется только через некоторый набор действий
* набор действий называется интерфейсом АТД

* стек - стопка
* вершина стека - последний добавленный элемент
* глубина стека
* ограниченный и неограниченный стек
* last in first out
* стек - очередь в магните
*
* операции стека:
* конструктор
* деструктор
* is empty
* add
* pop
* is full (для стека с ограниченной глубиной)
* get set (вершина)
* delete
* make_empty
*/

// стек на основе массива

class StackArr
{
    private:
        int maxSize;
        double* stack_arr;
        int top_ind;

        void fail(int);
    public:
        StackArr(int);
        StackArr(const StackArr&);
        ~StackArr();

        double top(); // посмотреть вершину
        void pop(); // удалить вершину
        void push(double);
        bool isEmpty();
        bool isFull();

        friend void print(const StackArr&);
};

StackArr::StackArr(int size)
{
    maxSize = size;
    if (size < 2) fail(4);
    stack_arr = nullptr;
    stack_arr = new (std::nothrow) double[maxSize];
    if (!stack_arr) fail(1);
    top_ind = -1;
    std::cout << "Это конструктор, детка\n";
}

// не глубокое копирование
StackArr::StackArr(const StackArr& st)
{
    maxSize = st.maxSize;
    top_ind = st.top_ind;
    stack_arr = st.stack_arr;
    std::cout << "Это конструктор копирования, детка\n";
}

StackArr::~StackArr()
{
    delete[] stack_arr;
    stack_arr = nullptr;
    std::cout << "Это деструктор, детка\n";
}

bool StackArr::isFull()
{
    return top_ind == maxSize - 1;
}

bool StackArr::isEmpty()
{
    return top_ind == -1;
}

void StackArr::push(double value)
{
    if (isFull()) fail(2);
    else stack_arr[++top_ind] = value;
}

double StackArr::top()
{
    if (isEmpty()) fail(3);
    return stack_arr[top_ind];
}

void StackArr::pop()
{
    if (isEmpty()) fail(3);
    else top_ind--;
}

void print(const StackArr& st)
{
    std::cout << "Стек: ";
    for (int i = st.top_ind; i > -1; i--)
        std::cout << st.stack_arr[i] << " ";
    std::cout << std::endl;
}

void StackArr::fail(int code)
{
    switch (code)
    {
        case 1:
            std::cout << "Не хватает памяти!\n";
            break;
        case 2:
            std::cout << "Переполнение стека!\n";
            break;
        case 3:
            std::cout << "Стек пуст!\n";
            break;
        case 4:
            std::cout << "Некорректное значение глубины стека!\n";
            break;
    }
    exit(1);
}

int main()
{
    setlocale(LC_ALL, "rus");
    StackArr st1(4);
    st1.push(3); st1.push(34343434);
    st1.push(3.5); st1.push(-2.3);

    std::cout << st1.top() << std::endl;
    st1.pop();
    std::cout << st1.top() << std::endl;
    st1.pop();
    std::cout << st1.top() << std::endl;
    st1.pop();
    std::cout << st1.top() << std::endl;
}
