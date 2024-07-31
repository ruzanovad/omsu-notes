#include <vector>
#include <iostream>
#include "./Box.h"

class Container {
private:
    std::vector<Box> _boxes;
    int _length;
    int _width;
    int _height;
    double _max_weight;
public:
    Container(int, int, int, double);

    void add_box_by_index(int, Box);

    void delete_box(int);

    double get_sum_weight();

    int get_sum_value();

    Box get_box(int);

    int add_box(Box);

    Box &operator[](int);

    friend std::ostream &operator<<(std::ostream &, const Container &);

    friend std::istream &operator>>(std::istream &, Container &);

};
