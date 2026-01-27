#include "../include/ArrayBasedQueueIterator.h"

ArrayBasedQueueIterator::ArrayBasedQueueIterator(ArrayBasedQueue &arrayBasedQueue) : _arrayBasedQueue(arrayBasedQueue),
                                                                                     _index(0) {}

void ArrayBasedQueueIterator::start() {
    _index = _arrayBasedQueue._front;
}

void ArrayBasedQueueIterator::next() {
    ++_index %= (int) (_arrayBasedQueue._size + 1);
}

bool ArrayBasedQueueIterator::finish() {
    return _index % (_arrayBasedQueue._size + 1) == _arrayBasedQueue._rear % (_arrayBasedQueue._size + 1);
}

int ArrayBasedQueueIterator::getValue() {
    return _arrayBasedQueue._array[_index % (_arrayBasedQueue._size + 1)];
}
