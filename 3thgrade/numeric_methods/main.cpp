//
// Created by light on 19.02.2024.
//

#include <iostream>
//#include "matrix.h"
#include <vector>
#include <algorithm>
#include <cmath>
#include <cassert>

using namespace std;

void printMatrix(const vector<vector<double>> &matrix) {
    for (const auto &row: matrix) {
        for (auto elem: row) {
            cout << elem << " ";
        }
        cout << endl;
    }
}

void printVector(const vector<double> &vector) {

    for (auto elem: vector) {
        cout << elem << " ";

        cout << endl;
    }
}

vector<double> LU(const vector<vector<double>> &A, const vector<double> &f) {
/*    Partial pivoting improves the numerical stability of the LU decomposition by selecting
    the largest element in the column (in absolute value) as the pivot element*/
    size_t n = A.size();

    // L is identity, U is copy of A
    vector<vector<double>> L(n, vector<double>(n, 0)), U(A);
    auto x = vector<double>(n, 0);


    // pivot matrix
    vector<int> P(n);
    for (int i = 0; i < n; i++) {
        P[i] = i;
        L[i][i] = 1;
    }

    {

    }
    for (int i = 0; i < n; i++) {
        // Partial Pivoting
        double max = U[P[i]][i];
        int maxIndex = i;
        // max element in i column (U becomes upper-diagonal)
        for (int k = i; k < n; k++) {
            if (fabs(U[P[k]][i]) > max) {
                max = fabs(U[P[k]][i]);
                maxIndex = k;
            }
        }

        std::swap(P[i], P[maxIndex]);

        // Decomposition into L and U
        for (int j = i + 1; j < n; j++) {
            L[P[j]][i] = U[P[j]][i] / U[P[i]][i];
            for (int k = i; k < n; k++) {
                U[P[j]][k] -= L[P[j]][i] * U[P[i]][k];
            }
        }
    }
    for (int i = 0; i < n; ++i) L[i][i] = 1.0;
//    printMatrix(L);

    for (int i = 0; i < n ; ++i){
        for(int j = 0; j < n; ++j){
            cout << L[P[i]][j] << " ";
        }
        cout << endl;
    }
    for (int i = 0; i < n ; ++i){
        for(int j = 0; j < n; ++j){
            cout << U[P[i]][j] << " ";
        }
        cout << endl;
    }

//    printMatrix(U);
    auto y = vector<double>(n, 0.0);
    for (int i = 0; i < n; ++i) {
        y[i] = f[P[i]];
        for (int j = 0; j < i; ++j) {
            y[i] -= L[P[i]][j] * y[j];
        }
        y[i] /= L[P[i]][i];
    }

    for (int i = n - 1; i >= 0; i--) {
        x[i] = y[i];
        for (int j = i + 1; j < n; j++) {
            x[i] -= U[P[i]][j] * x[j];
        }
        x[i] /= U[P[i]][i];
    }


    return x;

}
// (1, -1, 1) => (2, 1, 1) (свободный столбец)
// тестирующая часть
// генерация матрицы
// Ax = f
//x - приближенное решение
// (1.01, -1, 1)


int method(double **A, double **f, double **x, int n) {
    return 1;
}

double norm(double *x, int n) {
    double ret = 0;
    for (int i = 0; i < n; ++i) {
        ret = max(ret, abs(x[i]));
    }
    return ret;
}

int main() {
    {
        vector<vector<double>> A = {
                {2,  -1, -2},
                {-4, 6,  3},
                {-4, -2, 8}
        };
        vector<double> b = {-8, -11, -3};
        vector<double> expected_x = {2, -1, -2};
        printVector(expected_x);
        printVector(LU(A, b));
        assert(expected_x == LU(A, b));
    }
}