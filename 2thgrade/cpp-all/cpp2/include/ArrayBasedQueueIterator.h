#ifndef CPP2_ARRAYBASEDQUEUEITERATOR_H
#define CPP2_ARRAYBASEDQUEUEITERATOR_H

#include "ArrayBasedQueue.h"

class ArrayBasedQueueIterator {
private:
    ArrayBasedQueue &_arrayBasedQueue;
    int _index;
public:
    explicit ArrayBasedQueueIterator(ArrayBasedQueue &arrayBasedQueue);

    void start();

    void next();

    bool finish();

    int getValue();
};

#endif //CPP2_ARRAYBASEDQUEUEITERATOR_H
