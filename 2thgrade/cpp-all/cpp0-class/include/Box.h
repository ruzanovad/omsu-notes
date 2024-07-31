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
