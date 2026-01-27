#ifndef CPP4_NODE_H
#define CPP4_NODE_H

template <typename K, typename V>
struct Node {
  K key_;
  V value_;
  Node* prev_;
  Node* next_;

  explicit Node(const K& key = K(), const V& value = V())
      : key_(key), value_(value), prev_(nullptr), next_(nullptr) {}

  Node(const K& key, const V& value, Node<K, V>* prev, Node<K, V>* next)
      : key_(key), value_(value), prev_(prev), next_(next) {}

  friend bool operator==(const Node<K, V>& x, const Node<K, V>& y) {
    return x.key_ == y.key_ && x.value_ == y.value_;
  }

  friend bool operator!=(const Node<K, V>& x, const Node<K, V>& y) {
    return x.key_ != y.key_ || x.value_ != y.value_;
  }
};

#endif  //CPP4_NODE_H
