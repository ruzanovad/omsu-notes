#ifndef FREQDICTAVLT_H
#define FREQDICTAVLT_H

#include <iostream>

struct Node {
  std::string word_;
  std::size_t count_;
  int balance_;
  Node* left_;
  Node* right_;
  Node(std::string word = std::string(), std::size_t count = std::size_t(),
       int balance = 0, Node* left = nullptr, Node* right = nullptr)
      : word_(word),
        count_(count),
        balance_(balance),
        left_(left),
        right_(right) {}
  friend std::ostream& operator<<(std::ostream& os, const Node* node) {
    if (!node) {
      return os;
    } else {
      if (node->left_) {
        os << node->left_;
      }
      os << node->word_ << " ";
      if (node->right_) {
        os << node->right_;
      }
    }
    return os;
  }
};

class FreqDictAVLT {
  bool rRotation(Node*&);
  bool lrRotation(Node*&);
  bool balanceInsertToLeft(Node*&);
  bool balanceInsertToRight(Node*&);
  Node* copy(const Node*);
  void clear(Node*);
  size_t findWordRec(Node*, const std::string&) const;
  bool addWordRec(Node*&, const std::string&);
  size_t getSum(const Node*) const;
  void del2(Node*&, Node*&);
  void delWord(Node*&, const std::string&);

 public:
  FreqDictAVLT();
  FreqDictAVLT(const FreqDictAVLT&);
  FreqDictAVLT& operator=(const FreqDictAVLT&);
  FreqDictAVLT(FreqDictAVLT&&);
  FreqDictAVLT& operator=(FreqDictAVLT&&);
  ~FreqDictAVLT();
  size_t findWord(const std::string&) const;
  void addWord(const std::string&);
  void deleteWord(const std::string&);
  size_t getSumCount() const;
  friend std::ostream& operator<<(std::ostream&, const FreqDictAVLT&);

 private:
  Node* root_;
};

#endif  // FREQDICTAVLT_H
