cmake_minimum_required(VERSION 3.25)
project(cpp5)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp5 ./src/main.cpp ./include/BTree.h ./src/BTree.cpp)
