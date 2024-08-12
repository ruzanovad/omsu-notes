#include <iostream>
#include "../include/DynamicIntArray.h"

int main() {
    std::cout << "Hello, World!" << std::endl;

    DynamicIntArray dynamicIntArray13(2);
    for (int i = 0; i < dynamicIntArray13.getSize(); ++i) {
        std::cout << i << ": ";
        std::cin >> dynamicIntArray13[i];
    }
    std::cout << dynamicIntArray13 << std::endl;
    DynamicIntArray dynamicIntArray14(3);
    for (int i = 0; i < dynamicIntArray14.getSize(); ++i) {
        std::cout << i << ": ";
        std::cin >> dynamicIntArray14[i];
    }
    std::cout << dynamicIntArray14 << std::endl;
    std::cout << "+ : " << dynamicIntArray13 + dynamicIntArray14 << std::endl;

    return 0;
}
