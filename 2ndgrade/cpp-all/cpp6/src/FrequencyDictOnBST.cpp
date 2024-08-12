#include "../include/FrequencyDictOnBST.h"

#include <string>

Node* FrequencyDictOnBST::copy(const Node* root) {
  if (!root) {
    return nullptr;
  }
  Node* cp = new Node(root->word_, root->count_);
  cp->left_ = copy(root->left_);
  cp->right_ = copy(root->right_);
  return cp;
}

void FrequencyDictOnBST::clear(Node* root) {
  if (!root) {
    return;
  }
  clear(root->left_);
  clear(root->right_);
  delete root;
}

size_t FrequencyDictOnBST::findWordRec(Node* root,
                                       const std::string& str) const {
  if (!root) {
    return 0;
  }
  if (str.compare(root->word_) < 0) {
    return findWordRec(root->left_, str);
  }
  if (str.compare(root->word_) > 0) {
    return findWordRec(root->right_, str);
  }
  return root->count_;
}

void FrequencyDictOnBST::addWordRec(Node*& root, const std::string& str) {
  if (!root) {
    root = new Node(str, 1);
    return;
  }
  if (str.compare(root->word_) < 0) {
    return addWordRec(root->left_, str);
  } else if (str.compare(root->word_) > 0) {
    return addWordRec(root->right_, str);
  } else {
    root->count_++;
  }
}

size_t FrequencyDictOnBST::getSum(const Node* root) const {
  if (!root) {
    return 0;
  }
  return root->count_ + getSum(root->left_) + getSum(root->right_);
}

void FrequencyDictOnBST::del2(Node*& leftSubTree, Node*& root) {
  if (leftSubTree->right_) {
    del2(leftSubTree->right_, root);
  } else {
    root->word_ = leftSubTree->word_;
    root->count_ = leftSubTree->count_;
    root = leftSubTree;
    leftSubTree = leftSubTree->left_;
  }
}

void FrequencyDictOnBST::delWord(Node*& root, const std::string& word) {
  if (!root) {
    return;
  }
  if (word.compare(root->word_) < 0) {
    return delWord(root->left_, word);
  }
  if (word.compare(root->word_) > 0) {
    return delWord(root->right_, word);
  }
  Node* nodeToDel = root;
  if (root->count_ > 1) {
    root->count_--;
  } else {
    if (!root->left_) {
      root = root->right_;
    } else if (!root->right_) {
      root = root->left_;
    } else {
      del2(root->left_, nodeToDel);
    }
    delete nodeToDel;
  }
}

FrequencyDictOnBST::FrequencyDictOnBST() {
  root_ = nullptr;
}

FrequencyDictOnBST::FrequencyDictOnBST(const FrequencyDictOnBST& cp) {
  root_ = copy(cp.root_);
}

FrequencyDictOnBST& FrequencyDictOnBST::operator=(
    const FrequencyDictOnBST& cp) {
  clear(root_);
  root_ = copy(cp.root_);
  return *this;
}

FrequencyDictOnBST::FrequencyDictOnBST(FrequencyDictOnBST&& mv) {
  std::swap(root_, mv.root_);
  mv.root_ = nullptr;
}

FrequencyDictOnBST& FrequencyDictOnBST::operator=(FrequencyDictOnBST&& mv) {
  clear(root_);
  std::swap(root_, mv.root_);
  mv.root_ = nullptr;
  return *this;
}

FrequencyDictOnBST::~FrequencyDictOnBST() {
  clear(root_);
}

size_t FrequencyDictOnBST::findWord(const std::string& word) const {
  return findWordRec(root_, word);
}

void FrequencyDictOnBST::addWord(const std::string& word) {
  addWordRec(root_, word);
}

void FrequencyDictOnBST::deleteWord(const std::string& word) {
  delWord(root_, word);
}

size_t FrequencyDictOnBST::getSumCount() const {
  return getSum(root_);
}

std::ostream& operator<<(std::ostream& os, const FrequencyDictOnBST& dict) {
  if (!dict.root_) {
    os << "Dictionary is empty!";
    return os;
  }
  if (dict.root_->left_) {
    os << dict.root_->left_;
  }
  os << ": " << dict.root_->word_ << " : ";
  if (dict.root_->right_) {
    os << dict.root_->right_;
  }
  return os;
}
