#CMakeLists.txt
cmake_minimum_required(VERSION 3.4.1)

add_library(
    md5
    SHARED
    MD5.cpp
    native-lib-MD5.cpp
)

find_library(
    log-lib
    log
)
target_link_libraries(
    md5
    ${log-lib}
)


