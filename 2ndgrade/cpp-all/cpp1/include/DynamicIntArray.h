#ifndef CPP1_DYNAMICINTARRAY_H
#define CPP1_DYNAMICINTARRAY_H

#include <iostream>

class DynamicIntArray {
private:
    int _size;
    int _buffer;
    int *_array;
public:
    // rule of 6
    DynamicIntArray();

    explicit DynamicIntArray(int);

    DynamicIntArray(int, int);

    DynamicIntArray(const DynamicIntArray &);

    DynamicIntArray &operator=(const DynamicIntArray &);

    DynamicIntArray(DynamicIntArray &&);

    DynamicIntArray &operator=(DynamicIntArray &&);

    ~DynamicIntArray();

    int getSize() const;

    int &operator[](int);

    int operator[](int) const;

    void resize(int);

    bool operator==(const DynamicIntArray &) const;

    bool operator!=(const DynamicIntArray &) const;

    bool operator<(const DynamicIntArray &) const;

    bool operator<=(const DynamicIntArray &) const;

    bool operator>(const DynamicIntArray &) const;

    bool operator>=(const DynamicIntArray &) const;

    friend DynamicIntArray operator+(const DynamicIntArray &, const DynamicIntArray &);

    friend std::ostream &operator<<(std::ostream &, const DynamicIntArray &);

    friend std::istream &operator>>(std::istream &, DynamicIntArray &);

    void reserve(int);

    int capacity() const;

    void pushBack(int);

    int popBack();

    DynamicIntArray(int, int, int);
};

#endif //CPP1_DYNAMICINTARRAY_H
