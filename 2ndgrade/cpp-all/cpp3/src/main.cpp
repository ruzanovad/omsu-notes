#include <iostream>

#include "./include/Deque.h"

int main() {
  Deque deque;
  deque.pushBack(4);
  deque.pushFront(3);
  deque.pushBack(5);
  deque.pushFront(2);
  deque.pushFront(1);

  Deque deque1(deque);

  std::cout << "Hello, World!" << std::endl;

  std::cout << "Front: " << deque.front() << "\n";

  std::cout << "Back: " << deque.back() << "\n";

  for (Deque::Iterator iterator = deque.iterator(); !iterator.finish();
       iterator.next()) {
    std::cout << iterator.getElement() << "\n";
  }

  std::cout << "----------\n";

  for (Deque::Iterator iterator = deque.iterator(); !iterator.finish();
       iterator.next()) {
    if (iterator.getElement() == 4) {
      deque.deleteAt(iterator);
    }
    std::cout << iterator.getElement() << "\n";
    if (iterator.getElement() == 3) {
      deque.pushAt(iterator, 999);
    }
  }

  std::cout << "----------\n";

  Deque::Iterator iterator999 = deque.find(999);
  std::cout << iterator999.getElement() << "\n";

  std::cout << "----------\n";

  deque.pushAt(iterator999, 777);
  for (Deque::Iterator iterator = deque.iterator(); !iterator.finish();
       iterator.next()) {
    std::cout << iterator.getElement() << "\n";
  }

  std::cout << "----------\n";

  for (Deque::Iterator iterator = deque1.iterator(); !iterator.finish();
       iterator.next()) {
    std::cout << iterator.getElement() << "\n";
  }

  return 0;
}
