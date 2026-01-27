#ifndef CPP4_DEQUE_H
#define CPP4_DEQUE_H

#include <exception>
#include "./Node.h"

template <typename K, typename V>
class Deque {
  Node<K, V>* buff_;
  size_t count_;

 public:
  class DequeIterator {
    friend class Deque;

   protected:
    Deque& deque_;
    Node<K, V>* curr_;

   public:
    explicit DequeIterator(Deque& deque)
        : deque_(deque), curr_(deque.buff_->next_) {}

    void start() { curr_ = deque_.buff_->next_; }

    Node<K, V>* getElement() { return curr_; }

    void next() { curr_ = curr_->next_; }

    bool hasNext() { return curr_ != deque_.buff_; }
  };

  Deque() : count_(0) {
    buff_ = new Node<K, V>();
    buff_->prev_ = buff_;
    buff_->next_ = buff_;
  }

  Deque(const Deque& deque) : count_(0) {
    buff_ = new Node<K, V>();
    buff_->prev_ = buff_;
    buff_->next_ = buff_;
    DequeIterator iterator = const_cast<Deque&>(deque).iterator();
    iterator.start();
    while (iterator.hasNext()) {
      pushBack(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next();
    }
  }

  Deque& operator=(const Deque& deque) {
    count_ = 0;
    buff_ = new Node<K, V>();
    buff_->prev_ = buff_;
    buff_->next_ = buff_;
    DequeIterator iterator = const_cast<Deque&>(deque).iterator();
    iterator.start();
    while (iterator.hasNext()) {
      pushBack(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next();
    }
    return *this;
  }

  Deque(Deque&& deque) noexcept : buff_(deque.buff_), count_(deque.count_) {
    deque.buff_ = nullptr;
  }

  Deque& operator=(Deque&& deque) {
    std::swap(buff_, deque.buff_);
    std::swap(count_, deque.count_);
    return *this;
  }

  ~Deque() {
    while (count_ != 0) {
      popBack();
    }
  }

  void pushFront(const K& key, const V& value) {
    if (buff_->next_ == buff_) {
      auto newNode = new Node<K, V>(key, value, buff_, buff_);
      buff_->prev_ = newNode;
      buff_->next_ = newNode;
    } else {
      auto newNode = new Node<K, V>(key, value, buff_, buff_->next_);
      buff_->next_->prev_ = newNode;
      buff_->next_ = newNode;
    }
    count_++;
  }

  const Node<K, V>* front() const {
    if (buff_->next_ == buff_) {
      throw std::exception();
    }
    return buff_->next_;
  }

  Node<K, V>* popFront() {
    if (buff_->next_ == buff_) {
      throw std::exception();
    }
    auto result = new Node<K, V>(buff_->next_->key_, buff_->next_->value_);
    auto nBuff = buff_->next_;
    buff_->next_ = nBuff->next_;
    buff_->next_->prev_ = buff_;
    delete nBuff;
    count_--;
    return result;
  }

  void pushBack(const K& key, const V& value) {
    if (buff_->prev_ == buff_) {
      auto newNode = new Node<K, V>(key, value, buff_, buff_);
      buff_->prev_ = newNode;
      buff_->next_ = newNode;
    } else {
      auto newNode = new Node<K, V>(key, value, buff_->prev_, buff_);
      buff_->prev_->next_ = newNode;
      buff_->prev_ = newNode;
    }
    count_++;
  }

  const Node<K, V>* back() const {
    if (buff_->prev_ == buff_) {
      return nullptr;
    }
    return buff_->prev_;
  }

  Node<K, V>* popBack() {
    if (buff_->next_ == buff_) {
      throw std::exception();
    }
    auto result = new Node<K, V>(buff_->prev_->key_, buff_->prev_->value_);
    auto nBuff = buff_->prev_;
    buff_->prev_ = nBuff->prev_;
    buff_->prev_->next_ = buff_;
    delete nBuff;
    count_--;
    return result;
  }

  void pushAt(DequeIterator& iterator, const Node<K, V>& element) {
    Node<K, V>* newNode = new Node<K, V>(element.key_, element.value_,
                                         iterator._curr, iterator.curr_->next_);
    iterator.curr_->next_->prev = newNode;
    iterator.curr_->next_ = newNode;
    iterator.deque_.count_++;
  }

  void deleteAt(DequeIterator& iterator) {
    Node<K, V>* nCurr = iterator.curr_;
    nCurr->prev_->next_ = nCurr->next_;
    nCurr->next_->prev_ = nCurr->prev_;
    *iterator.curr_ = *iterator.curr_->next_;
    iterator.deque_.count_--;
  }

  void clear() {
    while (count_ != 0) {
      popBack();
    }
  }

  bool isEmpty() { return buff_->next_ == buff_; }

  size_t getCount() const { return count_; }

  DequeIterator& iterator() {
    auto* tmp = new DequeIterator(*this);
    return *tmp;
  }
};

#endif  //CPP4_DEQUE_H
