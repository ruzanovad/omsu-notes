#include "../include/BTree.h"
#include <algorithm>
#include <iostream>
#include <string>
#include <utility>

void BTree::clearElements(Node* root) {
  if (!root) {
    return;
  }
  clearElements(root->left_);
  clearElements(root->right_);
  delete root;
}

std::ostream& operator<<(std::ostream& os, const BTree& tree) {
  if (!tree.root_) {
    os << "Tree is empty!";
    return os;
  }
  if (tree.root_->left_ != nullptr) {
    os << tree.root_->left_;
  }
  os << "< " << tree.root_->data_ << " > ";
  if (tree.root_->right_ != nullptr) {
    os << tree.root_->right_;
  }
  return os;
}

Node* BTree::copyTree(const Node* root) {
  if (!root) {
    return nullptr;
  }
  Node* copy = new Node(root->data_);
  copy->left_ = copyTree(root->left_);
  copy->right_ = copyTree(root->right_);
  return copy;
}

int BTree::getEvenCountRec(const Node* root) {
  int result = 0;
  if (!root) {
    return result;
  }
  if (root->data_ % 2 == 0) {
    result = 1;
  }
  return result + getEvenCountRec(root->left_) + getEvenCountRec(root->right_);
}

bool BTree::isAllPositiveRec(const Node* root) {
  if (!root) {
    return true;
  }
  if (root->data_ > 0) {
    return isAllPositiveRec(root->left_) && isAllPositiveRec(root->right_);
  } else {
    return false;
  }
}

void BTree::deleteLeavesRec2(Node* root, Node* parent, const bool& left) {
  if (root->left_ != nullptr && root->right_ != nullptr) {
    deleteLeavesRec2(root->left_, root, true);
    deleteLeavesRec2(root->right_, root, false);
  } else if (root->left_ != nullptr) {
    deleteLeavesRec2(root->left_, root, true);
  } else if (root->right_ != nullptr) {
    deleteLeavesRec2(root->right_, root, false);
  } else {
    if (left) {
      parent->left_ = nullptr;
    } else {
      parent->right_ = nullptr;
    }
    delete root;
  }
}

void BTree::deleteLeavesRec(Node* root) {
  if (root->left_ != nullptr && root->right_ != nullptr) {
    deleteLeavesRec2(root->left_, root, true);
    deleteLeavesRec2(root->right_, root, false);
  } else if (root->left_ != nullptr) {
    deleteLeavesRec2(root->left_, root, true);
  } else if (root->right_ != nullptr) {
    deleteLeavesRec2(root->right_, root, false);
  } else {
    delete root;
  }
}

double BTree::getArifmeticAverageRec(Node* root, double& c) {
  if (!root) {
    return 0;
  }
  c++;
  return root->data_ + getArifmeticAverageRec(root->left_, c) +
         getArifmeticAverageRec(root->right_, c);
}

bool BTree::findElementRec(Node* root, const int& x, std::vector<int>& v) {
  if (!root) {
    return false;
  }
  if (x == root->data_) {
    return true;
  } else {
    v.push_back(1);
    if (findElementRec(root->right_, x, v)) {
      return true;
    } else {
      v.pop_back();
      v.push_back(0);
      if (findElementRec(root->left_, x, v)) {
        return true;
      } else {
        v.pop_back();
        return false;
      }
    }
  }
}

bool BTree::isBSTRec(const Node* root) {
  if (!root) {
    return true;
  }
  return ((root->left_ == nullptr) || root->data_ > root->left_->data_) &&
         ((root->right_ == nullptr) || root->data_ < root->right_->data_) &&
         isBSTRec(root->left_) && isBSTRec(root->right_);
}

void BTree::printByLevelsRec(const Node* root, int tabSize) {
  if (!root) {
    return;
  }
  tabSize += 4;
  printByLevelsRec(root->right_, tabSize);
  std::cout << "\n";
  std::cout << std::string(tabSize - 4, ' ') << root->data_ << "\n";
  printByLevelsRec(root->left_, tabSize);
}

BTree::BTree() {
  root_ = nullptr;
}

BTree::BTree(const BTree& cp) {
  root_ = copyTree(cp.root_);
}

BTree& BTree::operator=(const BTree& cp) {
  clear();
  root_ = copyTree(cp.root_);
  return *this;
}

BTree::BTree(BTree&& mv) {
  std::swap(root_, mv.root_);
  mv.root_ = nullptr;
}

BTree& BTree::operator=(BTree&& mv) {
  std::swap(root_, mv.root_);
  return *this;
}

BTree::~BTree() {
  clear();
}

bool BTree::addElement(const int& x, const std::vector<int>& path) {
  if (std::count(path.begin(), path.end(), 0) +
          std::count(path.begin(), path.end(), 1) !=
      path.size()) {
    return false;
  }
  Node** rt = &root_;
  for (int i : path) {
    if (i == 0) {
      rt = &(*rt)->left_;
    } else if (i == 1) {
      rt = &(*rt)->right_;
    }
  }
  if (!(*rt)) {
    *rt = new Node(x);
  } else {
    (*rt)->data_ = x;
  }
  return true;
}

int BTree::getEvenCount() {
  return getEvenCountRec(root_);
}

bool BTree::isAllPositive() {
  return isAllPositiveRec(root_);
}

void BTree::clear() {
  clearElements(root_);
  root_ = nullptr;
}

void BTree::deleteLeaves() {
  if (!isEmpty()) {
    deleteLeavesRec(root_);
  }
}

double BTree::getArifmeticAverage() {
  double count = 0;
  return getArifmeticAverageRec(root_, count) / count;
}

std::vector<int> BTree::findElement(const int& x) {
  std::vector<int> v;
  if (findElementRec(root_, x, v)) {
    return v;
  } else {
    v.clear();
    v.push_back(-1);
    return v;
  }
}

bool BTree::isEmpty() const {
  return root_ == nullptr;
}

bool BTree::isBinarySearchTree() {
  return isBSTRec(root_);
}

void printTreeByLevels(Node* root, int tabSize) {
  if (root == nullptr) {
    return;
  }
  std::queue<Node*> levelQueue;
  levelQueue.push(root);
  while (!levelQueue.empty()) {
    int levelSize = levelQueue.size();
    for (int i = 0; i < levelSize; i++) {
      Node* current = levelQueue.front();
      levelQueue.pop();
      std::cout << std::string(tabSize, ' ') << current->data_;
      if (current->left_) {
        levelQueue.push(current->left_);
      }
      if (current->right_) {
        levelQueue.push(current->right_);
      }
    }
    std::cout << std::endl;
  }
}

void BTree::printByLevels(bool rotate) {
  if (rotate) {
    printByLevelsRec(root_, 4);
  } else {
    printTreeByLevels(root_, 4);
  }
}
