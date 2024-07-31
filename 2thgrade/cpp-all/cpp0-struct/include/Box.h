#ifndef CPP0_STRUCT_BOX_H
#define CPP0_STRUCT_BOX_H

#include <iostream>

struct Box {
    int length;
    int width;
    int height;

    double weight;

    int value;
};

bool operator==(Box, Box);

std::ostream &operator<<(std::ostream &, Box);

Box operator>>(std::istream &, Box &);

Box create_box(int, int, int, double, int);

int get_sum_value(Box[], int);

bool is_less_than(Box, int);

double get_max_weight(Box[], int, int);

bool is_fit(Box[], int);

#endif //CPP0_STRUCT_BOX_H
