#include "../include/ArrayBasedQueue.h"

#include <iostream>

ArrayBasedQueue::ArrayBasedQueue() : _size(10), _front(0), _rear(0) {
    _array = new int[10];
}

ArrayBasedQueue::ArrayBasedQueue(const ArrayBasedQueue &arrayBasedQueue) : _size(arrayBasedQueue._size),
                                                                           _front(arrayBasedQueue._front),
                                                                           _rear(arrayBasedQueue._rear) {
    _array = new int[_size];
    for (int i = 0; i < _size; i++) {
        _array[i] = arrayBasedQueue._array[i];
    }
}

ArrayBasedQueue &ArrayBasedQueue::operator=(const ArrayBasedQueue &arrayBasedQueue) {
    _size = arrayBasedQueue._size;
    _front = arrayBasedQueue._front;
    _rear = arrayBasedQueue._rear;
    delete[] _array;
    _array = new int[_size];
    for (int i = 0; i < _size; i++) {
        _array[i] = arrayBasedQueue._array[i];
    }
    return *this;
}

ArrayBasedQueue::ArrayBasedQueue(ArrayBasedQueue &&arrayBasedQueue) : _size(arrayBasedQueue._size),
                                                                      _front(arrayBasedQueue._front),
                                                                      _rear(arrayBasedQueue._rear),
                                                                      _array(arrayBasedQueue._array) {
    arrayBasedQueue._array = nullptr;
    arrayBasedQueue._size = 0;
    arrayBasedQueue._front = 0;
    arrayBasedQueue._rear = 0;
}

ArrayBasedQueue &ArrayBasedQueue::operator=(ArrayBasedQueue &&arrayBasedQueue) {
    if (this == &arrayBasedQueue) { return *this; }
    std::swap(_size, arrayBasedQueue._size);
    std::swap(_front, arrayBasedQueue._front);
    std::swap(_rear, arrayBasedQueue._rear);
    std::swap(_array, arrayBasedQueue._array);
    return *this;
}

ArrayBasedQueue::~ArrayBasedQueue() {
    delete[] _array;
    _size = 0;
    _front = 0;
    _rear = 0;
}

ArrayBasedQueue::ArrayBasedQueue(std::size_t size) : _size(size), _front(0), _rear(0) {
    _array = new int[_size];
}

void ArrayBasedQueue::push(const int &element) {
    if ((_rear + 1) % (_size + 1) == _front) {
        throw std::overflow_error("Queue is full");
    }
    _array[_rear++ % (_size + 1)] = element;
}

int ArrayBasedQueue::pop() {
    if (isEmpty()) {
        throw std::underflow_error("Queue is empty!");
    }
    return _array[_front++ % (_size + 1)];
}

int ArrayBasedQueue::front() {
    if (isEmpty()) {
        throw std::underflow_error("Queue is empty!");
    }
    return _array[_front % (_size + 1)];
}

std::size_t ArrayBasedQueue::size() const {
    return _size;
}

void ArrayBasedQueue::clear() {
    _front = 0;
    _rear = 0;
}

bool ArrayBasedQueue::isEmpty() const {
    return _front % (_size + 1) == _rear % (_size + 1);
}