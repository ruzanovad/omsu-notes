#include "../include/Deque.h"
#include <exception>

Deque::Deque() : _count(0) {
  _buff = new Node(-1, nullptr, nullptr);
  _buff->_prev = _buff;
  _buff->_next = _buff;
}

// work?)))
Deque::Deque(const Deque& deque) : _count(0) {
  _buff = new Node(-1, nullptr, nullptr);
  _buff->_prev = _buff;
  _buff->_next = _buff;
  Iterator iterator = const_cast<Deque&>(deque).iterator();
  iterator.start();
  while (!iterator.finish()) {
    pushBack(iterator.getElement());
    iterator.next();
  }
}

Deque& Deque::operator=(const Deque& deque) {
  _count = 0;
  _buff = new Node(-1, nullptr, nullptr);
  _buff->_prev = _buff;
  _buff->_next = _buff;
  Iterator iterator = const_cast<Deque&>(deque).iterator();
  iterator.start();
  while (!iterator.finish()) {
    pushBack(iterator.getElement());
    iterator.next();
  }
  return *this;
}

// work )))
Deque::Deque(Deque&& deque) : _buff(deque._buff), _count(deque._count) {
  deque._buff = nullptr;
}

Deque& Deque::operator=(Deque&& deque) {
  std::swap(_buff, deque._buff);
  std::swap(_count, deque._count);
  return *this;
}

Deque::~Deque() {
  while (_count != 0) {
    popBack();
  }
}

void Deque::pushFront(const int& value) {
  if (_buff->_next == _buff) {
    Node* newNode = new Node(value, _buff, _buff);
    _buff->_prev = newNode;
    _buff->_next = newNode;
  } else {
    Node* newNode = new Node(value, _buff, _buff->_next);
    _buff->_next->_prev = newNode;
    _buff->_next = newNode;
  }
  _count++;
}

int Deque::front() const {
  if (_buff->_next == _buff) {
    throw std::exception();
  }
  return _buff->_next->_value;
}

int Deque::popFront() {
  if (_buff->_next == _buff) {
    throw std::exception();
  }
  int result = _buff->_next->_value;
  Node* nBuff = _buff->_next;
  _buff->_next = nBuff->_next;
  _buff->_next->_prev = _buff;
  delete nBuff;
  _count--;
  return result;
}

void Deque::pushBack(const int& value) {
  if (_buff->_prev == _buff) {
    Node* newNode = new Node(value, _buff, _buff);
    _buff->_prev = newNode;
    _buff->_next = newNode;
  } else {
    Node* newNode = new Node(value, _buff->_prev, _buff);
    _buff->_prev->_next = newNode;
    _buff->_prev = newNode;
  }
  _count++;
}

int Deque::back() const {
  if (_buff->_prev == _buff) {
    throw std::exception();
  }
  return _buff->_prev->_value;
}

int Deque::popBack() {
  if (_buff->_next == _buff) {
    throw std::exception();
  }
  int result = _buff->_prev->_value;
  Node* nBuff = _buff->_prev;
  _buff->_prev = nBuff->_prev;
  _buff->_prev->_next = _buff;
  delete nBuff;
  _count--;
  return result;
}

void Deque::pushAt(IIterator& iiterator, const int& value) {
  auto iterator = dynamic_cast<Deque::Iterator&>(iiterator);
  Node* newNode = new Node(value, iterator._curr, iterator._curr->_next);
  iterator._curr->_next->_prev = newNode;
  iterator._curr->_next = newNode;
  iterator._deque._count++;
}

void Deque::deleteAt(IIterator& iiterator) {
  auto iterator = dynamic_cast<Deque::Iterator&>(iiterator);
  Node* nCurr = iterator._curr;
  nCurr->_prev->_next = nCurr->_next;
  nCurr->_next->_prev = nCurr->_prev;
  *iterator._curr = *iterator._curr->_next;
  nCurr = nullptr;
  iterator._deque._count--;
}

Deque::Iterator& Deque::find(const int& value) {
  auto* tmp = new Iterator(*this);

  tmp->start();
  while (!tmp->finish()) {
    if (tmp->getElement() == value) {
      return *tmp;
    }
    tmp->next();
  }
  throw std::exception();
}

void Deque::clear() {
  while (_count != 0) {
    popBack();
  }
}

bool Deque::isEmpty() {
  return _buff->_next == _buff;
}

size_t Deque::getCount() const {
  return _count;
}

Deque::Iterator& Deque::iterator() {
  auto* tmp = new Iterator(*this);
  return *tmp;
}
