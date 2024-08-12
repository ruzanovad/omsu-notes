#ifndef CPP3_DEQUE_H
#define CPP3_DEQUE_H

#include "./IList.h"

#include <cstddef>

struct Node {
  int _value;
  Node* _prev;
  Node* _next;

  Node(const int& t, Node* p, Node* n) : _value(t), _prev(p), _next(n) {}
};

class Deque : public IList {
  Node* _buff;
  size_t _count;

 public:
  class Iterator : public IIterator {
   protected:
    Deque& _deque;
    Node* _curr;

   public:
    friend class Deque;

    Iterator(Deque& deque) : _deque(deque), _curr(deque._buff->_next) {}

    void start() override { _curr = _deque._buff->_next; }

    int getElement() override { return _curr->_value; }

    void next() override { _curr = _curr->_next; }

    bool finish() override { return _curr == _deque._buff; }
  };

  Deque();

  Deque(const Deque&);

  Deque& operator=(const Deque&);

  Deque(Deque&&);

  Deque& operator=(Deque&&);

  ~Deque();

  void pushFront(const int&);

  int front() const;

  int popFront();

  void pushBack(const int&);

  int back() const;

  int popBack();

  void pushAt(IIterator&, const int&) override;

  void deleteAt(IIterator&) override;

  Iterator& find(const int&) override;

  void clear() override;

  bool isEmpty() override;

  size_t getCount() const override;

  Iterator& iterator() override;
};

#endif  //CPP3_DEQUE_H
