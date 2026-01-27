#include "../include/FreqDictAVLT.h"

bool FreqDictAVLT::rRotation(Node*& rA) {
  Node* rB = rA->left_;
  rA->balance_ = rB->balance_ = 0;
  rA->left_ = rB->right_;
  rB->right_ = rA;
  rA = rB;
  return false;
}

bool FreqDictAVLT::lrRotation(Node*& rA) {
  Node* rB = rA->left_;
  Node* rC = rB->right_;
  if (rC->balance_ < 0) {
    rC->balance_ = rB->balance_ = 0;
    rA->balance_ = 1;
  } else if (rC->balance_ > 0) {
    rC->balance_ = rA->balance_ = 0;
    rB->balance_ = -1;
  } else {
    rC->balance_ = 0;
    rA->balance_ = 1;
    rB->balance_ = -1;
  }
  rA->left_ = rC->right_;
  rB->right_ = rC->left_;
  rC->left_ = rB;
  rC->right_ = rA;
  rA = rC;
  return false;
}

bool FreqDictAVLT::balanceInsertToLeft(Node*& root) {
  if (root->balance_ == 1) {
    root->balance_ = 0;
    return false;
  } else if (root->balance_ == 0) {
    root->balance_ = -1;
    return true;
  } else {
    if (root->left_->balance_ < 0) {
      return rRotation(root);
    }
    return lrRotation(root);
  }
}

bool FreqDictAVLT::balanceInsertToRight(Node*& root) {
  if (root->balance_ == -1) {
    root->balance_ = 0;
    return false;
  } else if (root->balance_ == 0) {
    root->balance_ = 1;
    return true;
  } else {
    if (root->right_->balance_ > 0) {
      return rRotation(root);
    }
    return lrRotation(root);
  }
}

Node* FreqDictAVLT::copy(const Node* root) {
  if (!root) {
    return nullptr;
  }
  Node* cp = new Node(root->word_, root->count_);
  cp->left_ = copy(root->left_);
  cp->right_ = copy(root->right_);
  return cp;
}

void FreqDictAVLT::clear(Node* root) {
  if (!root) {
    return;
  }
  clear(root->left_);
  clear(root->right_);
  delete root;
}

size_t FreqDictAVLT::findWordRec(Node* root, const std::string& str) const {
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

bool FreqDictAVLT::addWordRec(Node*& root, const std::string& str) {
  if (!root) {
    root = new Node(str, 1);
    return true;
  }
  if (str.compare(root->word_) < 0) {
    if (addWordRec(root->left_, str)) {
      return balanceInsertToLeft(root);
    }
    return false;
  } else if (str.compare(root->word_) > 0) {
    if (addWordRec(root->right_, str)) {
      return balanceInsertToRight(root);
    }
    return false;
  } else {
    root->count_++;
    return false;
  }
}

size_t FreqDictAVLT::getSum(const Node* root) const {
  if (!root) {
    return 0;
  }
  return root->count_ + getSum(root->left_) + getSum(root->right_);
}

void FreqDictAVLT::del2(Node*& leftSubTree, Node*& root) {
  if (leftSubTree->right_) {
    del2(leftSubTree->right_, root);
  } else {
    root->word_ = leftSubTree->word_;
    root->count_ = leftSubTree->count_;
    root = leftSubTree;
    leftSubTree = leftSubTree->left_;
  }
}

void FreqDictAVLT::delWord(Node*& root, const std::string& word) {
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

FreqDictAVLT::FreqDictAVLT() {
  root_ = nullptr;
}

FreqDictAVLT::FreqDictAVLT(const FreqDictAVLT& cp) {
  root_ = copy(cp.root_);
}

FreqDictAVLT& FreqDictAVLT::operator=(const FreqDictAVLT& cp) {
  clear(root_);
  root_ = copy(cp.root_);
  return *this;
}

FreqDictAVLT::FreqDictAVLT(FreqDictAVLT&& mv) {
  std::swap(root_, mv.root_);
  mv.root_ = nullptr;
}

FreqDictAVLT& FreqDictAVLT::operator=(FreqDictAVLT&& mv) {
  clear(root_);
  std::swap(root_, mv.root_);
  mv.root_ = nullptr;
  return *this;
}

FreqDictAVLT::~FreqDictAVLT() {
  clear(root_);
}

size_t FreqDictAVLT::findWord(const std::string& word) const {
  return findWordRec(root_, word);
}

void FreqDictAVLT::addWord(const std::string& word) {
  addWordRec(root_, word);
}

void FreqDictAVLT::deleteWord(const std::string& word) {
  delWord(root_, word);
}

size_t FreqDictAVLT::getSumCount() const {
  return getSum(root_);
}

std::ostream& operator<<(std::ostream& os, const FreqDictAVLT& dict) {
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
