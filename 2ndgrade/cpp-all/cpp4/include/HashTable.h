#ifndef CPP4_HASHTABLE_H
#define CPP4_HASHTABLE_H

#include <cstddef>
#include <exception>
#include "./Deque.h"

template <typename K, typename V>
class HashTable {
  friend class HTIterator;
  size_t size_;
  size_t count_;
  Deque<K, V>* array_;

 public:
  class HTIterator {
    friend class HashTable;
    HashTable& hashTable_;
    typename Deque<K, V>::DequeIterator* curr_;
    size_t dequeIndex_;

   public:
    HTIterator(HashTable& hashTable) : hashTable_(hashTable), dequeIndex_(0) {
      curr_ = &(hashTable.array_[dequeIndex_]).iterator();
    }

    void start() {
      dequeIndex_ = 0;
      curr_ = &(hashTable_.array_[dequeIndex_]).iterator();
    }

    Node<K, V>* getElement() { return curr_->getElement(); }

    void next() { curr_->next(); }

    bool hasNext() {
      while (!curr_->hasNext()) {
        if (dequeIndex_ + 1 < hashTable_.size_) {
          curr_ = &(hashTable_.array_[++dequeIndex_]).iterator();
          curr_->start();
        } else {
          return false;
        }
      }
      return true;
    }
  };

  explicit HashTable(size_t size) : size_(size), count_(0) {
    array_ = new Deque<K, V>[size_];
  }

  HashTable(const HashTable& hashTable) : size_(hashTable.size_), count_(0) {
    array_ = new Deque<K, V>[size_];
    auto iterator = const_cast<HashTable&>(hashTable).iterator();
    while (iterator.hasNext()) {
      addElement(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next;
    }
  }

  HashTable& operator=(const HashTable& hashTable) {
    size_ = hashTable.size_;
    count_ = 0;
    clear();
    delete[] array_;
    array_ = new Deque<K, V>[size_];
    auto iterator = const_cast<HashTable&>(hashTable).iterator();
    while (iterator.hasNext()) {
      addElement(iterator.getElement()->key_, iterator.getElement()->value_);
      iterator.next;
    }
    return *this;
  }

  HashTable(HashTable&& hashTable) noexcept
      : size_(hashTable.size_),
        count_(hashTable.count_),
        array_(hashTable.array_) {
    hashTable.array_ = nullptr;
  }

  HashTable& operator=(HashTable&& hashTable) {
    std::swap(size_, hashTable.size_);
    std::swap(count_, hashTable.count_);
    std::swap(array_, hashTable.array_);
    return *this;
  }

  ~HashTable() {
    clear();
    delete[] array_;
  }

  void addElement(const K& key, const V& value) {
    if (count_ + 1 > (int)(size_ * 0.8)) {
      auto* tmp = new HashTable<K, V>(size_ * 3);
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
    return ;
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
  }

  void clear() {
    for (int i = 0; i < size_; ++i) {
      array_[i].clear();
    }
    count_ = 0;
  }

  bool isEmpty() { return count_ == 0; }

  size_t getSize() { return size_; }

  size_t getCount() { return count_; }

  HTIterator& iterator() {
    auto* tmp = new HTIterator(*this);
    return *tmp;
  }
};

#endif  //CPP4_HASHTABLE_H
