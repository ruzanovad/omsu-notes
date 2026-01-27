#pragma once

template <typename T>
class Queue
{
private:

	T* array;
	int head;
	int tail;
	int maxSize;
	int size;
	short unsigned int error;
public:
	Queue(int);
	Queue(const Queue&);
	~Queue();
	void makeEmpty();
	bool isEmpty();
	bool isFull();
	void push(T);
	T pop();
	T front();
	void del();

	void deepCopy(const Queue&);

	int length();
	int newIndex(int);
	friend void print(const Queue<double>&);

	short unsigned int getError();
};

template<typename T>
class Deque
{
private:
	T* array;
	int head;
	int tail;
	int maxSize;
	short unsigned int error;
public:
	Deque(int);
	Deque(const Deque&);
	~Deque();
	void makeEmpty();
	bool isEmpty();
	bool isFull();

	void push_back(T);
	T pop_back();
	void del_back();
	T back();

	void push_front(T);
	T pop_front();
	T front();
	void del_front();

	void deepCopy(const Deque&);

	//int newIndex_R(int);
	//int newIndex_L(int);

	short unsigned int getError();
	friend void print(const Deque<char>&);
};

template class Deque<char>;
template class Queue<double>;


