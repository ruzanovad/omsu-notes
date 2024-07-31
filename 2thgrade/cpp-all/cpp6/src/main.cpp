#include <iostream>
#include "../include/FrequencyDictOnBST.h"

void freqDictTest0() {
  FrequencyDictOnBST dict;
  dict.addWord("d");
  dict.addWord("b");
  dict.addWord("f");
  dict.addWord("a");
  dict.addWord("c");
  dict.addWord("e");
  dict.addWord("g");
  std::cout << dict << "\n";
  std::cout << "FindWord: " << dict.findWord("c") << "\n";
  std::cout << "DeleteWord:\n";
  dict.deleteWord("c");
  std::cout << dict << "\n";
  std::cout << "GetSumCount: " << dict.getSumCount() << "\n";
}

void freqDictTest1() {
  FrequencyDictOnBST dict;
  dict.addWord("doom");
  dict.addWord("bed");
  dict.addWord("from");
  dict.addWord("apple");
  dict.addWord("color");
  dict.addWord("egg");
  dict.addWord("good");
  std::cout << dict << "\n";
  std::cout << "FindWord: " << dict.findWord("c") << "\n";
  std::cout << "DeleteWord:\n";
  dict.deleteWord("c");
  std::cout << dict << "\n";
  std::cout << "GetSumCount: " << dict.getSumCount() << "\n";
  dict.addWord("color");
  dict.addWord("egg");
  std::cout << "FindWord \"egg\": " << dict.findWord("egg") << "\n";
  std::cout << "GetSumCount: " << dict.getSumCount() << "\nDelete \"color\"\n";
  dict.deleteWord("color");
  std::cout << "GetSumCount: " << dict.getSumCount() << "\n";
  dict.deleteWord("color");
  std::cout << dict << "\n";
  dict.deleteWord("doom");
  std::cout << dict << "\n";
}

int main() {
  freqDictTest0();
  std::cout << "================\n";
  freqDictTest1();
  std::cout << "================\n";
  return 0;
}
