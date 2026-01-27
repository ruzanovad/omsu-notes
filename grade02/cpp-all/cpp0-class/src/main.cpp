#include <iostream>
#include "../include/MyNamespace.h"

using namespace MyNamespace;

int main() {
    Box my_box0(30, 30, 30, 10.0, 10000);
    Box my_box1(20, 20, 20, 20.0, 20000);
    Box my_box2(10, 10, 10, 30.0, 30000);

    Box boxes[] = {my_box0, my_box1, my_box2};

    int sum = get_sum_value(boxes, 3);

    printf("Sum: %d\n", sum);

    printf("Is less than: %d\n", is_less_than(my_box0, 40));

    printf("Get max weight: %lf\n", get_max_weight(boxes, 3, 8000));

    printf("Is fit: %d\n", is_fit(boxes, 3));

    printf("my_box1 == my_box2: %d\n", my_box0 == my_box1);

    Box cin_box;

    std::cin >> cin_box;

    std::cout << cin_box << std::endl;

    std::cout << "----------------------------------------------------------------" << std::endl;

    Container container(1000, 1000, 1000, 10000);

    container.add_box(my_box0);
    container.add_box(my_box1);

    std::cout << container;

    container.add_box_by_index(2, cin_box);

    std::cout << container;

    container.delete_box(2);
    container[0] = cin_box;

    std::cout << container;

    std::cout << container[0];

    return 0;
}