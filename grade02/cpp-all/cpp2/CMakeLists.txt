cmake_minimum_required(VERSION 3.24)
project(cpp2)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp2 include/ArrayBasedQueue.h src/ArrayBasedQueue.cpp include/ArrayBasedQueueIterator.h src/ArrayBasedQueueIterator.cpp src/main.cpp)
