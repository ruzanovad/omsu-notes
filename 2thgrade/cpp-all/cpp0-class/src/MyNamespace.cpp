#include "../include/MyNamespace.h"

namespace MyNamespace {
    Box::Box() {
        _length = 0;
        _width = 0;
        _height = 0;
        _weight = 0;
        _value = 0;
    }

    Box::Box(int length, int width, int height, double weight, int value) {
        _length = length;
        _width = width;
        _height = height;
        _weight = weight;
        _value = value;
    }

    Box::~Box() {
        _length = 0;
        _width = 0;
        _height = 0;
        _weight = 0;
        _value = 0;
    }

    void Box::set_length(int length) {
        _length = length;
    }

    void Box::set_width(int width) {
        _width = width;
    }

    void Box::set_height(int height) {
        _height = height;
    }

    void Box::set_weight(double weight) {
        _weight = weight;
    }

    void Box::set_value(int value) {
        _value = value;
    }

    int Box::get_length() {
        return _length;
    }

    int Box::get_width() {
        return _width;
    }

    int Box::get_height() {
        return _height;
    }

    double Box::get_weight() {
        return _weight;
    }

    int Box::get_value() {
        return _value;
    }

    int get_sum_value(Box boxes[], int size) {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            sum += boxes[i].get_value();
        }
        return sum;
    }

    bool is_less_than(Box box, int val) {
        return (box.get_length() + box.get_width() + box.get_height() <= val);
    }

    double get_max_weight(Box boxes[], int size, int maxV) {
        int V;
        double max = -1;
        for (int i = 0; i < size; i++) {
            V = boxes[i].get_length() * boxes[i].get_width() * boxes[i].get_height();
            if (V <= maxV) {
                if (max < boxes[i].get_weight()) {
                    max = boxes[i].get_weight();
                }
            }
        }
        return max;
    }

    bool is_fit(Box boxes[], int size) {
        bool flag = true;

        struct BoxV {
            Box box;
            int V = 0;
        };

        BoxV boxes_V[size];

        for (int i = 0; i < size; i++) {
            boxes_V[i].box = boxes[i];
            boxes_V[i].V = boxes[i].get_length() * boxes[i].get_width() * boxes[i].get_height();
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                int _v = boxes_V[j].V;
                if (_v > boxes_V[j + 1].V) {
                    BoxV _boxV = boxes_V[j];
                    boxes_V[j] = boxes_V[j + 1];
                    boxes_V[j + 1] = _boxV;
                }
            }
        }

        for (int i = 0; i < size - 1; i++) {
            Box box1 = boxes_V[i].box;
            Box box2 = boxes_V[i + 1].box;

            if (box1.get_length() > box2.get_length() || box1.get_width() > box2.get_width() ||
                box1.get_height() > box2.get_height()) {
                flag = false;
            }
        }

        return flag;
    }

    bool operator==(Box box1, Box box2) {
        return box1.get_length() == box2.get_length() && box1.get_width() == box2.get_width() &&
               box1.get_height() == box2.get_height() && box1.get_weight() == box2.get_weight() &&
               box1.get_value() == box2.get_value();
    }

    std::ostream &operator<<(std::ostream &os, Box box) {
        os << "Length: " << box.get_length();
        os << ", Width: " << box.get_width();
        os << ", Height: " << box.get_height();
        os << ", Weight: " << box.get_weight();
        os << ", Value: " << box.get_value() << ".";
        return os;
    }

    std::istream &operator>>(std::istream &is, Box &box) {
        std::cout << "Enter length: ";
        is >> box._length;
        std::cout << "Enter width: ";
        is >> box._width;
        std::cout << "Enter height: ";
        is >> box._height;
        std::cout << "Enter wight: ";
        is >> box._weight;
        std::cout << "Enter value: ";
        is >> box._value;
        return is;
    }

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
}