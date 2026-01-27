#ifndef CPP6_FREQUENCYDICTONBST_H
#define CPP6_FREQUENCYDICTONBST_H
#include <iostream>
#include <string>

struct Node {
  std::string word_;
  std::size_t count_;
  Node* left_;
  Node* right_;
  Node(std::string word = std::string(), std::size_t count = std::size_t(),
       Node* left = nullptr, Node* right = nullptr)
      : word_(word), count_(count), left_(left), right_(right) {}
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

class FrequencyDictOnBST {
  Node* copy(const Node*);
  void clear(Node*);
  size_t findWordRec(Node*, const std::string&) const;
  void addWordRec(Node*&, const std::string&);
  size_t getSum(const Node*) const;
  void del2(Node*&, Node*&);
  void delWord(Node*&, const std::string&);

 public:
  FrequencyDictOnBST();
  FrequencyDictOnBST(const FrequencyDictOnBST&);
  FrequencyDictOnBST& operator=(const FrequencyDictOnBST&);
  FrequencyDictOnBST(FrequencyDictOnBST&&);
  FrequencyDictOnBST& operator=(FrequencyDictOnBST&&);
  ~FrequencyDictOnBST();
  size_t findWord(const std::string&) const;
  void addWord(const std::string&);
  void deleteWord(const std::string&);
  size_t getSumCount() const;
  friend std::ostream& operator<<(std::ostream&, const FrequencyDictOnBST&);

 private:
  Node* root_;
};

#endif  //CPP6_FREQUENCYDICTONBST_H
