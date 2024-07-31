cmake_minimum_required(VERSION 3.24)
project(cpp3)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp3 include/Deque.h src/Deque.cpp include/IList.h include/IIterator.cpp src/main.cpp)

