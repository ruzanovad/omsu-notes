#include <iostream>

class QueueArr
{
private:
    double *que_arr;
    int maxSize;
    int head; // индекс начального элемента
    int tail; // индекс буферного элемента, следующего за последним в очереди
	int error; //0 все в порядке,1 - нехватка памяти, 2 - удаление из пустого

public:
	QueueArr(int);
	//QueueList(const StackList&);
	~QueueArr();

//	double top(); // посмотреть вершину
	void pop(); // удалить первый элемент
	void push(double); //положить в очередь
	bool empty();
	bool full();
	int size();
	double front();

	void makeEmpty();
	friend void print(const QueueArr&);
};

QueueArr::QueueArr(int size)
{
    if (size < 1) error = 3;
    maxSize = size;
	que_arr = nullptr;
	que_arr = new (std::nothrow) double [maxSize++];
	if (!que_arr)
		error = 1;
	else
	{
	    head = tail = 0;
		error = 0;
	}
	std::cout << "Это конструктор с параметрами, детка\n";
}
//
QueueArr::~QueueArr()
{
    delete[] que_arr;
    que_arr = nullptr;
	makeEmpty();
	error = 0;
	std::cout << "Это деструктор по умолчанию\n";
}
//
bool QueueArr::full()
{
    return (tail + 1)%(maxSize+1) == head;
}

bool QueueArr::empty()
{
    return head == tail;
}

void QueueArr::makeEmpty()
{
    head = tail = 0;
}
//bool QueueList::empty()
//{
//	return head == tail;
//}

//double StackList::top()
//{
//	if (empty())
//	{
//		error = 2;
//		return 1;
//	}
//	error = 0;
//	return top_ptr->data;
//	// (*top_ptr).data
//}
//
void QueueArr::push(double x)
{
    if (!full())
    {
        que_arr[tail] = x;
        tail = (tail++)%(maxSize++);
    }
    else error = 4;

}
void QueueArr::pop()
{
    if (empty()) error = 2;
    else
    {
        head = (head+1)%(maxSize+1);
        error = 0;
    }
}

double QueueArr::front()
{
    if (empty())
    {
        error = 2;
        return 1./0;
    }
    else
    {
        error = 0;
        return que_arr[head];
    }
}
//

int QueueArr::size()
{
    return (tail - head + maxSize) % (maxSize+1);
}

void print(QueueArr &que)
{
    double val;
    for (int i = 0; i < que.size(); i++)
    {
        val = que.front();
        std::cout << que.front() << " ";
        que.pop();
        que.push(val);

    }
}

int main()
{
    QueueArr que1(5);
}
