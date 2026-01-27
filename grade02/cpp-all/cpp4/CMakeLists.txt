cmake_minimum_required(VERSION 3.25)
project(cpp4)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp4 src/main.cpp include/HashTable.h include/Node.h include/Deque.h include/LinkedHashTable.h)
