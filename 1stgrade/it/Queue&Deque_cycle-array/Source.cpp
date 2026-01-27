#include <iostream>
#include <string>
#include "Header.h"

template<typename T>
Queue<T>::Queue(int n)
{
    maxSize = n+1;
    if (n < 1) error = 4;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else
        error = head = tail =  0;
}

template<typename T>
Queue<T>::Queue(const Queue& ob)
{
    maxSize = ob.maxSize;
    head = ob.head;
    tail = ob.tail;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else
    {
        error = 0;
        for (int i = ob.head; (i % ob.maxSize) != ob.tail; ++i)
            array[i % ob.maxSize] = ob.array[i % ob.maxSize];
    }
    //todo modular arithmetic based copying
}


template<typename T>
void Queue<T>::deepCopy(const Queue& ob)
{
    maxSize = ob.maxSize;
    head = ob.head;
    tail = ob.tail;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else 
    {
        error = 0;    
        for (int i = ob.head; (i % ob.maxSize) != ob.tail; ++i)
            array[i % ob.maxSize] = ob.array[i % ob.maxSize];
    }

    //todo modular arithmetic based copying
}

template<typename T>
Queue<T>::~Queue()
{
    makeEmpty();
    delete[] array;
    array = nullptr;
}

template<typename T>
void Queue<T>::makeEmpty()
{
    tail = 0;
    head = 0;
}

template<typename T>
bool Queue<T>::isEmpty() {
    return head == tail;
}

template<typename T>
bool Queue<T>::isFull() { return (tail + 1 + maxSize) % maxSize == head; }

template<typename T>
void Queue<T>::push(T value)
{
    if (isFull()) error = 2;
    else
    {
        array[tail] = value;
        tail = (tail+1) % maxSize;
        error = 0;
    }
}

template<typename T>
T Queue<T>::pop()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    else
    {

        T x = front();
        del();
        return x;
    }
}

template<typename T>
T Queue<T>::front()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    error = 0;
    return array[head];
}

template<typename T>
void Queue<T>::del()
{
    if (isEmpty())
        error = 3;
    else
    {
        head = (head+1) % maxSize;
        error = 0;
        //std::cout << "new head: " << head << "; tail: " << tail << std::endl;
    }
}

void print(const Queue<double>& st)
{
    std::cout << "Ξχεπεδό: ";
    for (int i = st.head; (i % st.maxSize) != st.tail; i++)
        std::cout << st.array[i % st.maxSize] << " ";
    std::cout << std::endl;
}

template<typename T>
short unsigned int Queue<T>::getError()
{
    short unsigned int x = error;
    error = 0;
    return x;
}

template<typename T>
Deque<T>::Deque(int n)
{
    maxSize = n+1;
    if (n < 2) error = 4;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else
    {
        head = 0;
        tail = 0;
        error = 0;
    }


}
template<typename T>
Deque<T>::Deque(const Deque& ob)
{
    maxSize = ob.maxSize;
    head = ob.head;
    tail = ob.tail;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else
    {
        error = 0;
        for (int i = ob.head; (i % ob.maxSize) != ob.tail; ++i)
            array[i % ob.maxSize] = ob.array[i % ob.maxSize];
    }
}

template<typename T>
void Deque<T>::deepCopy(const Deque& ob)
{
    maxSize = ob.maxSize;
    head = ob.head;
    tail = ob.tail;
    array = nullptr;
    array = new (std::nothrow) T[maxSize];
    if (!array) error = 1;
    else
    {
        error = 0;
        for (int i = ob.head; (i % ob.maxSize) != ob.tail; ++i)
            array[i % ob.maxSize] = ob.array[i % ob.maxSize];
    }
}

template<typename T>
Deque<T>::~Deque()
{
    makeEmpty();
    delete[] array;
    array = nullptr;
}

template<typename T>
void Deque<T>::makeEmpty()
{
    head = 0;
    tail = 0;
}

template<typename T>
bool Deque<T>::isEmpty() { return head == tail; }

template<typename T>
bool Deque<T>::isFull() { return (tail + 1 + maxSize) % maxSize == head; }

template<typename T>
void Deque<T>::push_back(T value)
{
    if (isFull()) error = 2;
    else
    {
        //std::cout << "push " << value << " to [" << tail << "]\n";
        array[tail] = value;
        tail = (tail + 1) % maxSize;
        error = 0;
        //for (int i = 0; i < maxSize; i++)
        //    std::cout << array[i] << " ";
        //std::cout << "\n";
        //std::cout << "new tail " << tail << std::endl;
    }
}

template<typename T>
void Deque<T>::push_front(T value)
{
    if (isFull()) error = 2;
    else
    {
        //std::cout << "old head " << head << std::endl;
        head = (head - 1 + maxSize) % maxSize;
        array[head] = value;
        error = 0;
        //for (int i = 0; i < maxSize; i++)
        //    std::cout << array[i] << " ";
        //std::cout << "\n";
        //std::cout << "push " << value << " to [" << head << "]\n";
    }
}

template<typename T>
T Deque<T>::pop_front()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    else
    {
        T x = front();
        del_front();
        return x;
    }
}

template<typename T>
T Deque<T>::pop_back()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    else
    {
        T x = back();
        del_back();
        return x;
    }
}

template<typename T>
T Deque<T>::front()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    error = 0;
    return array[head];
}

template<typename T>
T Deque<T>::back()
{
    if (isEmpty())
    {
        error = 3;
        return 1;
    }
    error = 0;
    return array[tail - 1];
}

template<typename T>
void Deque<T>::del_front()
{
    if (isEmpty())
        error = 3;
    else
    {
        //std::cout << "pop head" << "from [" << head << "]\n";
        head = (head + 1) % maxSize;
        error = 0;
    }
        
}

template<typename T>
void Deque<T>::del_back()
{
    if (isEmpty())
        error = 3;
    else
    {
        //std::cout << "del tail " << "from [" << tail << "]\n";
        tail = (tail - 1 + maxSize) % maxSize;
        error = 0;
        
    }

}

//template<typename T>
//int Deque<T>::newIndex_R(int old_ind)
//{
//    if (old_ind >= maxSize)
//        return 0;
//    else
//        return old_ind + 1;

//}
//template<typename T>
//int Deque<T>::newIndex_L(int old_ind)
//{
//    if (old_ind == 0)
//        return maxSize - 1;
//    else
//        return old_ind - 1;

//}

void print(const Deque<char>& st)
{
    std::cout << "Δεκ: ";
    for (int i = st.head; (i % st.maxSize) != st.tail; i++)
        std::cout << st.array[i % st.maxSize] << " ";
    std::cout << std::endl;
}

template<typename T>
short unsigned int Deque<T>::getError()
{
    short unsigned int x = error;
    error = 0;
    return x;
}

