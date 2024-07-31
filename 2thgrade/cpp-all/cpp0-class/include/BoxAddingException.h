#include <iostream>
#include <utility>

class BoxAddingException : public std::exception {
private:
    std::string _message;
public:
    BoxAddingException(std::string message) : _message(std::move(message)) {}
    std::string what() {
        return _message;
    }
};