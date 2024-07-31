#include <iostream>
#include <string>
#include "./include/HashTable.h"
#include "./include/LinkedHashTable.h"

using std::string;

void dequeTest() {
  Deque<char, string> deque;
  deque.pushBack('d', "Dd");
  deque.pushFront('c', "Cc");
  deque.pushBack('e', "Ee");
  deque.pushFront('b', "Bb");
  deque.pushFront('a', "Aa");

  Deque<char, string> deque1(deque);

  std::cout << "Front: " << deque.front()->key_ << ", " << deque.front()->value_
            << "\n";

  std::cout << "Back: " << deque.back()->key_ << ", " << deque.back()->value_
            << "\n";

  for (auto iterator = deque.iterator(); !iterator.hasNext(); iterator.next()) {
    std::cout << iterator.getElement()->key_ << ", "
              << iterator.getElement()->value_ << "\n";
  }

  std::cout << "----------\n";

  deque1.popBack();
  deque1.popFront();

  for (auto iterator = deque1.iterator(); iterator.hasNext(); iterator.next()) {
    std::cout << iterator.getElement()->key_ << ", "
              << iterator.getElement()->value_ << "\n";
  }
}

template <typename K, typename V>
void printHashTable(const HashTable<K, V>& hashTable) {
  std::cout << "----HASHTABLE---"
            << "\n";
  auto iter = const_cast<HashTable<K, V>&>(hashTable).iterator();
  while (iter.hasNext()) {
    std::cout << iter.getElement()->key_ << ": " << iter.getElement()->value_
              << "\n";
    iter.next();
  }
  std::cout << "----------------"
            << "\n";
}

template <typename K, typename V>
void printLinkedHashTable(const LinkedHashTable<K, V>& hashTable) {
  std::cout << "----LINKEDHASHTABLE---"
            << "\n";
  auto iter = const_cast<LinkedHashTable<K, V>&>(hashTable).iterator();
  while (iter.hasNext()) {
    std::cout << iter.getElement()->key_ << ": " << iter.getElement()->value_
              << "\n";
    iter.next();
  }
  std::cout << "----------------"
            << "\n";
}

// HashTable: empty - deleteElement, isEmpty
void hashTableEmptyTest() {
  auto* hashTable = new HashTable<int, string>(5);
  printHashTable(*hashTable);
  std::cout << "Delete:"
            << "\n";
  hashTable->deleteElement(1);
  printHashTable(*hashTable);
  std::cout << hashTable->isEmpty() << "\n";
}

// HashTable: filled - isEmpty, deleteElement, clear->deleteElement
void hashTableFilledTest() {
  auto* hashTable = new HashTable<int, string>(9);
  hashTable->addElement(1, "first");
  hashTable->addElement(10, "first1");
  hashTable->addElement(3, "third");
  hashTable->addElement(7, "seventh");
  hashTable->addElement(19, "first2");
  printHashTable(*hashTable);
  std::cout << "Delete:"
            << "\n";
  hashTable->deleteElement(3);
  printHashTable(*hashTable);
  hashTable->addElement(100007, "one hundred thousand and seventh");
  printHashTable(*hashTable);
  std::cout << "Delete:"
            << "\n";
  hashTable->deleteElement(222);
  printHashTable(*hashTable);
  std::cout << hashTable->isEmpty() << "\n";
  std::cout << "Clear:"
            << "\n";
  hashTable->clear();
  std::cout << hashTable->isEmpty() << "\n";
  std::cout << "Delete:"
            << "\n";
  hashTable->deleteElement(1);
}

// HashTable: full - addElement->getSize
void hashTableFullTest() {
  auto* hashTable = new HashTable<char, string>(3);
  hashTable->addElement('a', "Apple");
  hashTable->addElement('b', "Banana");
  printHashTable(*hashTable);
  std::cout << "Size: "
            << "\n";
  std::cout << hashTable->getSize() << "\n";
  hashTable->addElement('c', "Coconut");
  printHashTable(*hashTable);
  std::cout << "Size: "
            << "\n";
  std::cout << hashTable->getSize() << "\n";
}

// LinkedHashTable: empty - deleteElement, isEmpty
void linkedHashTableEmptyTest() {
  auto* lht = new LinkedHashTable<string, int>(5);
  printLinkedHashTable(*lht);
  std::cout << "Delete:"
            << "\n";
  lht->deleteElement("string");
  printLinkedHashTable(*lht);
  std::cout << "IsEmpty:"
            << "\n";
  std::cout << lht->isEmpty() << "\n";
}

// LinkedHashTable: filled - isEmpty, deleteElement, clear->deleteElement
void linkedHashTableFilledTest() {
  auto* lht = new LinkedHashTable<char, int>(5);
  lht->addElement('a', 1);
  lht->addElement('b', 2);
  lht->addElement('0', 0);
  printLinkedHashTable(*lht);
  std::cout << "Delete:"
            << "\n";
  lht->deleteElement('b');
  printLinkedHashTable(*lht);
  std::cout << "IsEmpty:"
            << "\n";
  std::cout << lht->isEmpty() << "\n";
  std::cout << "Clear:"
            << "\n";
  lht->clear();
  printLinkedHashTable(*lht);
  std::cout << "Clear:"
            << "\n";
  std::cout << lht->isEmpty() << "\n";
}

// LinkedHashTable: full - addElement->getSize
void linkedHashTableFullTest() {
  auto* lht = new LinkedHashTable<char, string>(3);
  lht->addElement('a', "Apple");
  lht->addElement('b', "Banana");
  printLinkedHashTable(*lht);
  std::cout << "Size:"
            << "\n";
  std::cout << lht->getSize() << "\n";
  lht->addElement('c', "Coconut");
  printLinkedHashTable(*lht);
  std::cout << "Size:"
            << "\n";
  std::cout << lht->getSize() << "\n";
}

int main() {
  hashTableEmptyTest();
  hashTableFilledTest();
  hashTableFullTest();
  linkedHashTableEmptyTest();
  linkedHashTableFilledTest();
  linkedHashTableFullTest();
  return 0;
}
