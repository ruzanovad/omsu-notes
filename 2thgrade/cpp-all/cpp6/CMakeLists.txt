cmake_minimum_required(VERSION 3.25)
project(cpp6)

set(CMAKE_CXX_STANDARD 17)

add_executable(cpp6 ./include/FrequencyDictOnBST.h ./include/FreqDictAVLT.h ./src/FrequencyDictOnBST.cpp ./src/FreqDictAVLT.cpp ./src/main.cpp)
