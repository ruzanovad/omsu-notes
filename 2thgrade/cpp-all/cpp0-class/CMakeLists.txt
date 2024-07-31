cmake_minimum_required(VERSION 3.24)
project(cpp0-class)

set(CMAKE_CXX_STANDARD 14)

add_executable(cpp0-class include/Box.h include/Container.h include/BoxAddingException.h include/MyNamespace.h src/Box.cpp src/Container.cpp src/MyNamespace.cpp src/main.cpp)
