#include <iostream>
#include <vector>

namespace MyNamespace {
    class Box {
    private:
        int _length;
        int _width;
        int _height;

        double _weight;

        int _value;

    public:
        Box();

        ~Box();

        Box(int, int, int, double, int);

        void set_length(int);

        void set_width(int);

        void set_height(int);

        void set_weight(double);

        void set_value(int);

        int get_length();

        int get_width();

        int get_height();

        double get_weight();

        int get_value();

        friend bool operator==(Box, Box);

        friend std::ostream &operator<<(std::ostream &, Box);

        friend std::istream &operator>>(std::istream &, Box &);
    };

    int get_sum_value(Box[], int);

    bool is_less_than(Box, int);

    double get_max_weight(Box[], int, int);

    bool is_fit(Box[], int);

    class BoxAddingException : public std::exception {
    private:
        std::string _message;
    public:
        BoxAddingException(std::string message) : _message(std::move(message)) {}

        std::string what() {
            return _message;
        }
    };

    class Container {
    private:
        std::vector<Box> _boxes;
        int _length;
        int _width;
        int _height;
        double _max_weight;
    public:
        Container(int, int, int, double);

        int get_sum_length();

        int get_sum_width();

        int get_sum_height();

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
}

