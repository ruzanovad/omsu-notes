#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <cassert>


using namespace std;
// ibm866
const double eps = 1e-6;

vector<double> forwardSubstitution(const vector<vector<double>> &L, const vector<double> &Pb);

vector<double> backwardSubstitution(const vector<vector<double>> &U, const vector<double> &y);

vector<double> subtract(vector<double> &a, vector<double> &b) {
    vector<double> sub(a.size(), 0);
    for (int i = 0; i < a.size(); ++i) {
        sub[i] = a[i] - b[i];
    }
    return sub;
}

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

    }
    cout << endl;
}

vector<double> mult(vector<vector<double>> &A, vector<double> &x) {
    vector<double> vec(A.size(), 0);

    for (int i = 0; i < A.size(); ++i) {
        for (int j = 0; j < x.size(); ++j) {
            vec[i] += A[i][j] * x[j];
        }
    }
    return vec;
}

vector<double> LU(const vector<vector<double>> &A, const vector<double> &b) {
    vector<vector<double>> L, U;
    vector<int> P;

    size_t n = A.size();
    L.assign(n, vector<double>(n, 0));
    U = A;
    P.resize(n);
    iota(P.begin(), P.end(), 0);

    for (int i = 0; i < n; ++i) {
        double maxElement = abs(U[i][i]);
        int pivotRow = i;
        for (int k = i + 1; k < n; ++k) {
            if (abs(U[k][i]) > maxElement) {
                maxElement = abs(U[k][i]);
                pivotRow = k;
            }
        }
        swap(U[i], U[pivotRow]);
        swap(P[i], P[pivotRow]);
        if (i > 0) {
            swap(L[i], L[pivotRow]);
        }

        // Decomposition
        for (int j = i + 1; j < n; ++j) {
            L[j][i] = U[j][i] / U[i][i];
            for (int k = i; k < n; ++k) {
                U[j][k] -= L[j][i] * U[i][k];
            }
        }

    }
    for (int i = 0; i < n; ++i) {
        L[i][i] = 1;
    }

    vector<double> Pb(b.size());
    for (int i = 0; i < A.size(); ++i) {
        Pb[i] = b[P[i]];
    }
    vector<double> y = forwardSubstitution(L, Pb);
    vector<double> x = backwardSubstitution(U, y);
    return x;
}

vector<double> forwardSubstitution(const vector<vector<double>> &L, const vector<double> &Pb) {
    size_t n = L.size();
    vector<double> y(n, 0.0);
    for (int i = 0; i < n; ++i) {
        double sum = 0.0;
        for (int j = 0; j < i; ++j) {
            sum += L[i][j] * y[j];
        }
        y[i] = Pb[i] - sum;
    }
    return y;
}

vector<double> backwardSubstitution(const vector<vector<double>> &U, const vector<double> &y) {
    size_t n = U.size();
    vector<double> x(n, 0.0);
    for (int i = n - 1; i >= 0; --i) {
        double sum = 0.0;
        for (int j = i + 1; j < n; ++j) {
            sum += U[i][j] * x[j];
        }
        x[i] = (y[i] - sum) / U[i][i];
    }
    return x;
}

double norm(vector<double> &x) {
    double ret = 0;
    for (double i: x) {
        ret = max(ret, abs(i));
    }
    return ret;
}

void print_results(vector<vector<double>>& A, vector<double>& b, vector<double>& expected_x, vector<double>& actual_x ){
    cout << "Точное решение:" << endl;
    printVector(expected_x);
    cout << "Приближенное решение:" << endl;
    printVector(actual_x);
    auto z = subtract(expected_x, actual_x);

    cout << "Ошибка:" << norm(z) << endl;
    cout << "Относительная ошибка: " << norm(z) / norm(expected_x) << endl;

    auto mul = mult(A, actual_x);
    auto r = subtract(mul, b);

    cout << "Невязка: " << norm(r) << endl;

    cout << "Относительная невязка: " << norm(r) / norm(b) << endl;

    assert(norm(z) < eps);
    cout << endl;
}

int main() {
//    {
//        vector<vector<double>> A = {
//                {2,  -1, -2},
//                {-4, 6,  3},
//                {-4, -2, 8}
//        };
//        vector<double> b = {-8, -11, -3};
//        vector<double> expected_x = {2, -1, -2};
//        vector<double> actual_x = LU(A, b);
//        printVector(expected_x);
//        printVector(actual_x);
//        assert(expected_x == actual_x);
//    }
    {
        vector<vector<double>> A = {
                {1, 2, 1},
                {2, 2, 1},
                {1, 1, 1}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-1, 1, 1};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x, actual_x);
    }
    {
        vector<vector<double>> A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    1}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-100. / 101, 100. / 101, 1};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x, actual_x);
    }

    {
        vector<vector<double>> A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    0.999}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-99801. / 100798, 49850. / 50399, 500./499};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x, actual_x);
    }

    {
        vector<vector<double>> A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    0.999}
        };
        vector<double> b = {2, 1.0001, 1};
        vector<double> expected_x = {-997909201. / 1007980000, 9970001. / 10079800, 9999./9980};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x, actual_x);
    }

    {
        vector<vector<double>> A = {
                {1, 2, 1.1},
                {2, 0,    1},
                {0, -2.02,    0}
        };
        vector<double> b = {0, 1, 1.00001};
        vector<double> expected_x = {11099. / 121200, -100001. / 202000, 49501./60600};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x, actual_x);
    }
}