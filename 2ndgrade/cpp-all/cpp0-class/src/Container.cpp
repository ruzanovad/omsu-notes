#include "../include/Container.h"
#include <../include/BoxAddingException.h>

Container::Container(int length, int width, int height, double max_weight) :
        _length(length), _width(width), _height(height), _max_weight(max_weight) {}

void Container::add_box_by_index(int index, Box box) {
    if (index <= _boxes.size() && index >= 0) {
        if (this->get_sum_weight() + box.get_weight() <= this->_max_weight) {
            _boxes.insert(_boxes.cbegin() + index, box);
        } else {
            throw BoxAddingException("overweight");
        }
    } else {
        throw std::exception();
    }
}

void Container::delete_box(int index) {
    if (index <= _boxes.size() && index >= 0) {
        _boxes.erase(_boxes.cbegin() + index);
    }
}

double Container::get_sum_weight() {
    double sum = 0;
    for (Box box: _boxes) {
        sum += box.get_weight();
    }
    return sum;
}

int Container::get_sum_value() {
    int sum = 0;
    for (Box box: _boxes) {
        sum += box.get_value();
    }
    return sum;
}

Box Container::get_box(int index) {
    if (index <= _boxes.size() && index >= 0) {
        return _boxes.at(index);
    } else {
        throw std::exception();
    }
}

int Container::add_box(Box box) {
    if (this->get_sum_weight() + box.get_weight() <= this->_max_weight) {
        _boxes.push_back(box);
        return static_cast<int>(_boxes.size() - 1);
    } else {
        throw BoxAddingException("overweight");
    }
}

Box &Container::operator[](int index) {
    return _boxes[index];
}

std::ostream &operator<<(std::ostream &os, const Container &container) {
    os << "Container:\n";
    os << "\tLength: " << container._length;
    os << ", Width: " << container._width;
    os << ", Height: " << container._height;
    os << ", Max Weight: " << container._max_weight;
    if (!container._boxes.empty()) {
        os << "\nBoxes: \n";
        for (const Box &_box: container._boxes) {
            os << "\t";
            os << _box;
            os << "\n";
        }
        os << "\n";
    } else {
        os << "\nContainer is empty!\n";
    }
    return os;
}

std::istream &operator>>(std::istream &is, Container &container) {
    std::cout << "Enter length: ";
    is >> container._length;
    std::cout << "Enter width: ";
    is >> container._width;
    std::cout << "Enter height: ";
    is >> container._height;
    std::cout << "Enter max weight: ";
    is >> container._max_weight;
    return is;
}
