cmake_minimum_required(VERSION 3.24)
project(cpp0-struct)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp0-struct include/Box.h src/Box.cpp src/main.cpp)
