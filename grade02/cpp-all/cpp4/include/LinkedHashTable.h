#ifndef CPP4_LINKEDHASHTABLE_H
#define CPP4_LINKEDHASHTABLE_H

#include <cstddef>
#include <exception>
#include "./Deque.h"

template <typename K, typename V>
class LinkedHashTable {
  friend class HTIterator;
  size_t size_;
  size_t count_;
  Deque<K, V>* array_;
  Node<K, V>* buff_;

 public:
  class HTIterator {
    friend class LinkedHashTable;

   protected:
    LinkedHashTable& hashTable_;
    Node<K, V>* curr_;

   public:
    explicit HTIterator(LinkedHashTable& hashTable)
        : hashTable_(hashTable), curr_(hashTable.buff_->next_) {}

    void start() { curr_ = hashTable_.buff_->next_; }

    Node<K, V>* getElement() { return curr_; }

    void next() { curr_ = curr_->next_; }

    bool hasNext() { return curr_ != hashTable_.buff_; }
  };

  explicit LinkedHashTable(size_t size) : size_(size), count_(0) {
    array_ = new Deque<K, V>[size_];
    buff_ = new Node<K, V>();
    buff_->next_ = buff_;
    buff_->prev_ = buff_;
  }

  LinkedHashTable(const LinkedHashTable& hashTable)
      : size_(hashTable.size_), count_(0) {
    array_ = new Deque<K, V>[size_];
    buff_ = new Node<K, V>();
    buff_->prev_ = buff_;
    buff_->next_ = buff_;
    auto iterator = const_cast<LinkedHashTable&>(hashTable).iterator();
    while (iterator.hasNext()) {
      addElement(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next;
    }
  }

  LinkedHashTable& operator=(const LinkedHashTable& hashTable) {
    size_ = hashTable.size_;
    count_ = 0;
    buff_ = new Node<K, V>();
    buff_->prev_ = buff_;
    buff_->next_ = buff_;

    auto iterator = const_cast<LinkedHashTable&>(hashTable).iterator();
    while (iterator.hasNext()) {
      addElement(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next;
    }
    return *this;
  }

  LinkedHashTable(LinkedHashTable&& hashTable) noexcept
      : size_(hashTable.size_),
        count_(hashTable.count_),
        array_(hashTable.array_),
        buff_(hashTable.buff_) {
    hashTable.array_ = nullptr;
    hashTable.buff_ = nullptr;
  }

  LinkedHashTable& operator=(LinkedHashTable&& hashTable) {
    std::swap(size_, hashTable.size_);
    std::swap(count_, hashTable.count_);
    std::swap(array_, hashTable.array_);
    std::swap(buff_, hashTable.buff_);
    return *this;
  }

  ~LinkedHashTable() {
    clear();
    delete[] array_;
  }

  void addElement(const K& key, const V& value) {
    if (count_ + 1 > (int)(size_ * 0.8)) {
      auto* tmp = new LinkedHashTable<K, V>(size_ * 3);
      auto iter = iterator();
      while (iter.hasNext()) {
        tmp->addElement(iter.getElement()->key_, iter.getElement()->value_);
        iter.next();
      }
      std::swap(array_, tmp->array_);
      std::swap(size_, tmp->size_);
    }
    size_t hash_ = std::hash<K>()(key);
    array_[hash_ % size_].pushBack(key, value);
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

  V getElement(const K& key) {
    size_t hash_ = std::hash<K>()(key);
    Deque<K, V> elem = array_[hash_ % size_];
    auto iter = elem.iterator();
    while (iter.hasNext()) {
      if (iter.getElement()->key_ == key) {
        return iter.getElement()->value_;
      }
      iter.next();
    }
  }

  void deleteElement(const K& key) {
    size_t hash_ = std::hash<K>()(key);
    Deque<K, V>* elem = &(array_[hash_ % size_]);
    auto iter = elem->iterator();
    while (iter.hasNext()) {
      if (iter.getElement()->key_ == key) {
        elem->deleteAt(iter);
        count_--;
        break;
      }
      iter.next();
    }
    auto* iterHT = new HTIterator(*this);
    while (iterHT->hasNext()) {
      if (iterHT->getElement()->key_ == key) {
        auto* nCurr = iterHT->getElement();
        nCurr->prev_->next_ = nCurr->next_;
        nCurr->next_->prev_ = nCurr->prev_;
        *iterHT->getElement() = *iterHT->getElement()->next_;
        delete nCurr;
      }
      iterHT->next();
    }
  }

  void clear() {
    for (int i = 0; i < size_; ++i) {
      array_[i].clear();
    }
  }

  bool isEmpty() { return count_ == 0; }

  size_t getSize() { return size_; }

  size_t getCount() { return count_; }

  HTIterator& iterator() {
    auto* tmp = new HTIterator(*this);
    return *tmp;
  }
};

#endif  //CPP4_LINKEDHASHTABLE_H
