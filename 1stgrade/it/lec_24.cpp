// очередь на основе кольцевого списка
#include <iostream>

struct Node
{
	double data;
	Node* next;
};

class QueueList
{
private:
	Node* head;
	Node* tail; // буферный элемент [tail->next = head]
	int error; //0 все в порядке,1 - нехватка памяти, 2 - удаление из пустого

public:
	QueueList();
	//QueueList(const StackList&);
	~QueueList();

	double top(); // посмотреть вершину
	void pop(); // удалить вершину
	void push(double); //положить
	bool empty();

	void makeEmpty();
	friend void print(const QueueList&);
};

QueueList::QueueList()
{
	Node* buf = nullptr;
	buf = new (std::nothrow) Node;
	if (!buf)
		error = 1;
	else
	{
		head = tail = buf;
		buf->next = head;
		error = 0;
	}
	std::cout << "Это конструктор по умолчанию\n";
}
//
QueueList::~QueueList()
{
	makeEmpty();
	delete head;
	head = tail = nullptr;
	error = 0;
	std::cout << "Это деструктор по умолчанию\n";
}
//
//
void QueueList::makeEmpty()
{
	while (head != tail)
	{
		Node* buf = head;
		head = buf->next;
		tail->next = head;
		delete buf;
		error = 0;
	}
}
bool QueueList::empty()
{
	return head == tail;
}

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
void QueueList::push(double x)
{
	Node* tmp = nullptr;
	tmp = new (std::nothrow) Node;
	if (tmp == nullptr)
		error = 1;
	else
	{
		error = 0;
		//tail->next = tmp;
		tmp->data = x;
		tmp->next = head;
		tail = tmp;
		std::cout << "push\n";
	}

}
//
void print(const QueueList& que)
{
	Node* iter = que.head;
	if (iter != que.tail)
	{
		std::cout << iter->data << " ";
		while (iter->next != que.head)
		{
			iter = iter->next;
			std::cout << iter->data << " ";
		}
		std::cout << "\n";
	}

}
//
void QueueList::pop()
{
	if (!empty())
	{
		Node* tmp = head;
		head = tmp->next;
		tail->next = head;
		delete tmp;
		error = 0;
		std::cout << "pop\n";
	}
	else
		error = 2;
}


//StackList::StackList(const StackList& st)
//{
//	top_ptr = nullptr;
//	Node* it = nullptr, * tmp;
//	Node* it_st = st.top_ptr;
//	while (it_st != nullptr)
//	{
//		tmp = nullptr;
//		tmp = new (std::nothrow) Node;
//		if (tmp == nullptr)
//		{
//			error = 1;
//			makeEmpty();
//			return;
//		}
//		tmp->data = it_st->data;
//		tmp->next = nullptr;
//		if (top_ptr == nullptr)
//			top_ptr = it = tmp;
//		else
//		{
//			it->next = tmp;
//			it = it->next;
//		}
//		it_st = it_st->next;
//	}
//	std::cout << "Это конструктор по умолчанию\n";
//}
//
//StackList::~StackList()
//{
//	makeEmpty();
//}

int main()
{
	setlocale(LC_ALL, "rus");
	QueueList a;

	a.push(1); a.push(2); a.push(3);
	print(a);
	a.pop();
	print(a);
}
