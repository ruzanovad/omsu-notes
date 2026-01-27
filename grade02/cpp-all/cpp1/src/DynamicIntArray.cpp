#include "../include/DynamicIntArray.h"

#include <iostream>

DynamicIntArray::DynamicIntArray() : _size(10), _buffer(0) {
  _array = new int[_size];
}

DynamicIntArray::DynamicIntArray(int size) : _size(size), _buffer(0) {
  _array = new int[_size];
  for (int i = 0; i < _size; i++) {
    _array[i] = 0;
  }
}

DynamicIntArray::DynamicIntArray(int size, int n) : _size(size), _buffer(0) {
  _array = new int[_size];
  for (int i = 0; i < _size; i++) {
    _array[i] = n;
  }
}

DynamicIntArray::DynamicIntArray(const DynamicIntArray &dynamicIntArray)
    : _size(dynamicIntArray._size), _buffer(dynamicIntArray._buffer) {
  _array = new int[_size];
  for (int i = 0; i < _size; i++) {
    _array[i] = dynamicIntArray[i];
  }
}

DynamicIntArray::DynamicIntArray(DynamicIntArray &&dynamicIntArray)
    : _size(dynamicIntArray._size), _buffer(dynamicIntArray._buffer),
      _array(dynamicIntArray._array) {
  dynamicIntArray._array = nullptr;
  dynamicIntArray._size = 0;
  dynamicIntArray._buffer = 0;
}

DynamicIntArray::~DynamicIntArray() {
  delete[] _array;
  _size = 0;
  _buffer = 0;
}

int DynamicIntArray::getSize() const { return _size; }

int &DynamicIntArray::operator[](int index) {
  if (index < 0 || index >= _size) {
    throw std::exception();
  }
  return _array[index];
}

int DynamicIntArray::operator[](int index) const {
  if (index < 0 || index >= _size) {
    throw std::exception();
  }
  return _array[index];
}

void DynamicIntArray::resize(int newSize) {
  if (newSize <= _size + _buffer) {
    _buffer -= newSize - _size;
    _size = newSize;
  } else {
    int *newArray = new int[newSize];
    for (int i = 0; i < newSize; i++) {
      if (i < _size) {
        newArray[i] = _array[i];
      } else {
        newArray[i] = 0;
      }
    }
    delete[] _array;
    _size = newSize;
    _buffer = 0;
    _array = newArray;
  }
}

DynamicIntArray &
DynamicIntArray::operator=(const DynamicIntArray &dynamicIntArray) {
  _size = dynamicIntArray._size;
  _buffer = dynamicIntArray._buffer;
  delete[] _array;
  _array = new int[_size];
  for (int i = 0; i < _size; i++) {
    _array[i] = dynamicIntArray[i];
  }
  return *this;
}

DynamicIntArray &DynamicIntArray::operator=(DynamicIntArray &&dynamicIntArray) {
  if (this == &dynamicIntArray) {
    return *this;
  }
  std::swap(_size, dynamicIntArray._size);
  std::swap(_buffer, dynamicIntArray._buffer);
  std::swap(_array, dynamicIntArray._array);
  return *this;
}

bool DynamicIntArray::operator==(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return true;
  }
  if (_size == dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] != dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

bool DynamicIntArray::operator!=(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return true;
  }
  if (_size == dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] == dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

bool DynamicIntArray::operator<(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return false;
  }
  if (_size <= dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] >= dynamicIntArray[i]) {
        return false;
      }
    }
  } else if (_size > dynamicIntArray._size) {
    for (int i = 0; i < dynamicIntArray._size; i++) {
      if (_array[i] >= dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

bool DynamicIntArray::operator<=(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return false;
  }
  if (_size <= dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] > dynamicIntArray[i]) {
        return false;
      }
    }
  } else if (_size > dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] > dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

bool DynamicIntArray::operator>(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return false;
  }
  if (_size <= dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] <= dynamicIntArray[i]) {
        return false;
      }
    }
  } else if (_size < dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] <= dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

bool DynamicIntArray::operator>=(const DynamicIntArray &dynamicIntArray) const {
  if (this == &dynamicIntArray) {
    return false;
  }
  if (_size <= dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] < dynamicIntArray[i]) {
        return false;
      }
    }
  } else if (_size < dynamicIntArray._size) {
    for (int i = 0; i < _size; i++) {
      if (_array[i] < dynamicIntArray[i]) {
        return false;
      }
    }
  }
  return true;
}

DynamicIntArray operator+(const DynamicIntArray &arr0,
                          const DynamicIntArray &arr1) {
  auto *result = new DynamicIntArray(arr0._size + arr1._size);
  for (int i = 0; i < arr0._size; i++) {
    (*result)[i] = arr0[i];
  }
  for (int i = arr0._size; i < (arr0._size + arr1._size); i++) {
    (*result)[i] = arr1[i - arr0._size];
  }
  return *result;
}

std::ostream &operator<<(std::ostream &out,
                         const DynamicIntArray &dynamicIntArray) {
  for (int i = 0; i < dynamicIntArray._size; i++) {
    out << dynamicIntArray[i];
    if (i < dynamicIntArray._size - 1) {
      out << " ";
    }
  }
  return out;
}

std::istream &operator>>(std::istream &in, DynamicIntArray &dynamicIntArray) {
  int size;
  int num;
  std::cout << "Size: ";
  in >> size;
  if (size <= 0) {
    throw std::exception();
  } else {
    int *tmp = new int[size];
    for (int i = 0; i < size; i++) {
      in >> num;
      tmp[i] = num;
    }
    delete[] dynamicIntArray._array;
    dynamicIntArray._array = tmp;
  }
  return in;
}

void DynamicIntArray::reserve(int buffer) {
  _buffer = buffer;
  int *tmp = new int[_size + _buffer];
  for (int i = 0; i < _size; i++) {
    tmp[i] = _array[i];
  }
  delete[] _array;
  _array = tmp;
}

int DynamicIntArray::capacity() const { return _buffer; }

void DynamicIntArray::pushBack(int element) {
  if (_buffer < 1) {
    throw std::exception();
  }
  _array[_size++] = element;
  _buffer--;
}

int DynamicIntArray::popBack() {
  if (_size < 1) {
    throw std::exception();
  }
  _buffer++;
  return _array[--_size];
}

DynamicIntArray::DynamicIntArray(int size, int buffer, int n)
    : _size(size), _buffer(buffer) {
  _array = new int[_size + _buffer];
  for (int i = 0; i < _size; ++i) {
    _array[i] = n;
  }
}
