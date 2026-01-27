cmake_minimum_required(VERSION 3.24)
project(cpp1)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp1 include/DynamicIntArray.h src/DynamicIntArray.cpp src/main.cpp)
