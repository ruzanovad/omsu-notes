//
// Created by light on 19.02.2024.
//

#ifndef NUMERIC_METHODS_MATRIX_H
#define NUMERIC_METHODS_MATRIX_H

#include <stdio.h>
#include <fstream> // for file access
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <string>
#include <vector>
#include <tuple>
#include <cmath>

using std::vector;
using std::tuple;
class Matrix {
private:
    unsigned m_rowSize;
    unsigned m_colSize;
    vector<vector<double> > m_matrix;
public:
    Matrix(unsigned, unsigned, double);
    Matrix(const char *);
    Matrix(const Matrix &);
    ~Matrix();

    Matrix operator+(Matrix &);
    Matrix operator-(Matrix &);
    Matrix operator*(Matrix &);
    Matrix transpose();

    Matrix operator+(double);
    Matrix operator-(double);
    Matrix operator*(double);
    Matrix operator/(double);


    unsigned getRows() const;
    unsigned getCols() const;

    double& operator()(const unsigned &, const unsigned &);

};

#endif //NUMERIC_METHODS_MATRIX_H
