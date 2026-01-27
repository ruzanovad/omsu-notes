#ifndef CPP2_ARRAYBASEDQUEUE_H
#define CPP2_ARRAYBASEDQUEUE_H

#include <cstddef>

class ArrayBasedQueue {
private:
    std::size_t _size;
    int _front;
    int _rear;
    int *_array;
public:
    // rule of 6
    ArrayBasedQueue();

    ArrayBasedQueue(const ArrayBasedQueue &);

    ArrayBasedQueue &operator=(const ArrayBasedQueue &);

    ArrayBasedQueue(ArrayBasedQueue &&);

    ArrayBasedQueue &operator=(ArrayBasedQueue &&);

    ~ArrayBasedQueue();

    //other
    explicit ArrayBasedQueue(std::size_t);

    void push(const int &);

    int pop();

    int front();

    std::size_t size() const;

    void clear();

    bool isEmpty() const;

    friend class ArrayBasedQueueIterator;
};

#endif //CPP2_ARRAYBASEDQUEUE_H
