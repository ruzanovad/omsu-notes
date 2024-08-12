#include <iostream>

#include "./include/ArrayBasedQueueIterator.h"

int main() {
    std::cout << "Hello, World!" << std::endl;
    ArrayBasedQueue arrayBasedQueue(3);
    arrayBasedQueue.push(1);
    arrayBasedQueue.push(2);
    arrayBasedQueue.push(3);
    ArrayBasedQueueIterator arrayBasedQueueIterator(arrayBasedQueue);
    arrayBasedQueueIterator.start();
    while (!arrayBasedQueueIterator.finish()) {
        std::cout << arrayBasedQueueIterator.getValue() << std::endl;
        arrayBasedQueueIterator.next();
    }
    arrayBasedQueue.clear();
    arrayBasedQueue.push(8);
    std::cout << "Второй итератор" << std::endl;
    arrayBasedQueueIterator.start();
    while (!arrayBasedQueueIterator.finish()) {
        std::cout << arrayBasedQueueIterator.getValue() << std::endl;
        arrayBasedQueueIterator.next();
    }
}
