#include <iostream>
#include <map>
#include "../include/BTree.h"

std::ostream& operator<<(std::ostream& os, const std::vector<int>& v) {
  if (v.empty()) {
    os << "Vector is empty!";
    return os;
  }
  for (int i : v) {
    os << i << " ";
  }
  return os;
}

void emptyBTreeTest() {
  BTree tree;
  std::cout << tree << "\n";
  std::cout << tree.findElement(0) << "\n";
  tree.deleteLeaves();
  std::cout << tree << "\n";
  tree.clear();
  std::cout << tree << "\n";
  std::cout << tree.isAllPositive() << "\n";
  std::cout << tree.isEmpty() << "\n";
}

void filledBTreeTest0() {
  BTree tree;
  auto* v = new std::vector<int>;
  tree.addElement(50, *v);
  v->push_back(0);
  tree.addElement(40, *v);
  v->push_back(1);
  tree.addElement(45, *v);
  v->pop_back();
  v->pop_back();
  v->push_back(1);
  tree.addElement(60, *v);
  v->push_back(0);
  tree.addElement(55, *v);
  std::cout << tree << "\n";
  std::cout << "----------------\n";
  std::cout << "GetEvenCount: " << tree.getEvenCount() << "\n";
  std::cout << "----------------\n";
  std::cout << "IsAllPositive: " << tree.isAllPositive() << "\n";
  std::cout << "----------------\n";
  std::cout << "PrintByLevels:\n";
  tree.printByLevels(true);
  std::cout << "----------------\n";
  tree.deleteLeaves();
  std::cout << tree << "\n";
  std::cout << "----------------\n";
  std::cout << "GetArifmeticAverage: " << tree.getArifmeticAverage() << "\n";
  std::cout << "----------------\n";
  std::cout << "FindElement(0): " << tree.findElement(0) << "\n";
  std::cout << "----------------\n";
  std::cout << "IsBinarySearchTree: " << tree.isBinarySearchTree() << "\n";
  std::cout << "----------------\n";
  tree.clear();
  std::cout << tree << "\n";
  delete v;
}

void filledBTreeTest1() {
  BTree tree;
  auto* v = new std::vector<int>;
  tree.addElement(0, *v);
  v->push_back(0);
  tree.addElement(-50, *v);
  v->push_back(1);
  tree.addElement(-25, *v);
  v->pop_back();
  v->push_back(0);
  tree.addElement(-75, *v);
  v->pop_back();
  v->pop_back();
  v->push_back(1);
  tree.addElement(50, *v);
  v->push_back(0);
  tree.addElement(25, *v);
  v->pop_back();
  v->push_back(1);
  tree.addElement(75, *v);
  std::cout << tree << "\n";
  std::cout << "----------------\n";
  std::cout << "GetEvenCount: " << tree.getEvenCount() << "\n";
  std::cout << "----------------\n";
  std::cout << "IsAllPositive: " << tree.isAllPositive() << "\n";
  std::cout << "----------------\n";
  std::cout << "PrintByLevels:\n";
  tree.printByLevels(false);
  std::cout << "----------------\n";

  std::cout << "IsBinarySearchTree: " << tree.isBinarySearchTree() << "\n";
  std::cout << "----------------\n";
  tree.deleteLeaves();
  std::cout << tree << "\n";
  std::cout << "----------------\n";
  std::cout << "GetArifmeticAverage: " << tree.getArifmeticAverage() << "\n";
  std::cout << "----------------\n";
  std::cout << "FindElement(0): " << tree.findElement(0) << "\n";
  std::cout << "----------------\n";
  std::cout << "IsBinarySearchTree: " << tree.isBinarySearchTree() << "\n";
  std::cout << "----------------\n";
  tree.clear();
  std::cout << tree << "\n";
  delete v;
}

int main() {
  emptyBTreeTest();
  std::cout << "================\n";
  filledBTreeTest0();
  std::cout << "================\n";
  filledBTreeTest1();
  std::cout << "================\n";
  return 0;
}
