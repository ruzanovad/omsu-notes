#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <cmath>
#include <cassert>
#include <random>
#include <iomanip>


#define matrix vector<vector<double>>

using namespace std;

// ibm866
const double eps = 1e-6;

vector<double> forwardSubstitution(const matrix &L, const vector<double> &Pb);

vector<double> backwardSubstitution(const matrix &U, const vector<double> &y);

vector<double> subtract(const vector<double> &a, const vector<double> &b) {
    vector<double> sub(a.size(), 0);
    for (int i = 0; i < a.size(); ++i) {
        sub[i] = a[i] - b[i];
    }
    return sub;
}

void printMatrix(const matrix &mat) {
    for (const auto &row: mat) {
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

vector<double> mult(const matrix &A, const vector<double> &x) {
    vector<double> vec(A.size(), 0);

    for (int i = 0; i < A.size(); ++i) {
        for (int j = 0; j < x.size(); ++j) {
            vec[i] += A[i][j] * x[j];
        }
    }

    return vec;
}

matrix multmat(const matrix &A, const matrix &B) {
    size_t n = A.size();
    matrix result(n, vector<double>(n, 0));

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }
//    printMatrix(result);
//    cout << endl;
    return result;
}

int get_sign() {
    int sign = rand() & 1;
    if (sign == 0) {
        sign = -1;
    }
    return sign;
}

vector<double> LU(const matrix &A, const vector<double> &b) {
    matrix L;     // L - нулевая изначально
    vector<int> P;

    size_t n = A.size();
    L.assign(n, vector<double>(n, 0));
    P.resize(n);
    iota(P.begin(), P.end(), 0);

    auto U = A;

    for (int i = 0; i < n; ++i) {
        double maxElement = abs(U[i][i]);
        int pivotRow = i;
        // максимальный элемент по модулю в текущем столбце i
        for (int k = i + 1; k < n; ++k) {
            if (abs(U[k][i]) > maxElement) {
                maxElement = abs(U[k][i]);
                pivotRow = k;
            }
        }
        swap(U[i], U[pivotRow]);
        swap(P[i], P[pivotRow]);
        swap(L[i], L[pivotRow]);

        // Decomposition
        for (int j = i + 1; j < n; ++j) {
            L[j][i] = U[j][i] / U[i][i];
            for (int k = i; k < n; ++k) {
                U[j][k] -= L[j][i] * U[i][k];
            }
        }
    }

    vector<double> Pb(b.size());
    for (int i = 0; i < U.size(); ++i) {
        Pb[i] = b[P[i]];
    }
    vector<double> x = backwardSubstitution(U, forwardSubstitution(L, Pb));
    return x;
}

vector<double> forwardSubstitution(const matrix &L, const vector<double> &Pb) {
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

vector<double> backwardSubstitution(const matrix &U, const vector<double> &y) {
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

double norm(const vector<double> &x) {
    double ret = 0;
    for (double i: x) {
        ret = max(ret, abs(i));
    }
    return ret;
}

double norm(const matrix &x) {
    double ret = 0;
    size_t n = x.size(), m = x[0].size();
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            ret = max(ret, abs(x[i][j]));
        }
    }
    return ret;
}

void print_results(matrix &A, vector<double> &b, const vector<double> &expected_x, bool log = false) {
    if (log) {
        cout << "Матрица A:" << endl;
        printMatrix(A);
        cout << "Правая часть:" << endl;
        printVector(b);
    }

    vector<double> actual_x = LU(A, b);

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

vector<double> generate_actual_solution(int n) {
    std::random_device r;

    std::default_random_engine e1(r());
    std::uniform_int_distribution<int> uniform_dist(-100, 100);

    vector<double> return_value(n, 0);
    for (auto &x: return_value) {
        x = uniform_dist(e1);
    }
    return return_value;
}

matrix first_class(int n, double alpha, double beta, bool inv = false) {
    matrix T(n, vector<double>(n, 0)), T_inv(n, vector<double>(n, 0)),
            J(n, vector<double>(n, 0)), return_value(n, vector<double>(n, 0));
    T[0][0] = 1;
    T_inv[0][0] = n;

    double gamma = 0;
    int sign = get_sign();
    if (inv) {
        J[0][0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        for (int i = 1; i < n; ++i) {
            T[i][i] = 2;
            T[i - 1][i] = -1;
            T[i][i - 1] = -1;

            T_inv[i][i] = n - i;
            T_inv[0][i] = n - i;
            T_inv[i][0] = n - i;

            sign = get_sign();
            gamma = sqrt((i + 0.) / (n - 1));
            J[i][i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        }
    } else {
        J[0][0] = sign * ((1 - gamma) * alpha + gamma * beta);
        for (int i = 1; i < n; ++i) {
            T[i][i] = 2;
            T[i - 1][i] = -1;
            T[i][i - 1] = -1;

            T_inv[i][i] = n - i;
            T_inv[0][i] = n - i;
            T_inv[i][0] = n - i;

            sign = get_sign();
            gamma = sqrt((i + 0.) / (n - 1));
            auto value = sign * ((1 - gamma) * alpha + gamma * beta);
            J[i][i] = value;
        }
//        printMatrix(J);
//        cout << endl;

    }
    return_value = multmat(multmat(T, J), T_inv);
//    printMatrix(T);
//    cout << endl;
//    printMatrix(T_inv);
//    cout << endl;
//    printMatrix(J);
    return return_value;
}

matrix second_class(int n, double alpha, double beta, bool inv = false) {
    matrix T(n, vector<double>(n, 0)), T_inv(n, vector<double>(n, 0)),
            J(n, vector<double>(n, 0)), return_value(n, vector<double>(n, 0));
    T[0][0] = 1;
    T_inv[0][0] = n;

    double gamma = 0;
    int sign = get_sign();
    if (!inv) {
        J[0][0] = sign * ((1 - gamma) * alpha + gamma * beta);
        for (int i = 1; i < n; ++i) {
            T[i][i] = 2;
            T[i - 1][i] = -1;
            T[i][i - 1] = -1;

            T_inv[i][i] = n - i;
            T_inv[0][i] = n - i;
            T_inv[i][0] = n - i;

            sign = get_sign();
            gamma = sqrt((i + 0.) / (n - 1));
            J[i][i] = sign * ((1 - gamma) * alpha + gamma * beta);
        }
        J[0][1] = 1; // клетка 2 на 2


    } else {
        J[0][0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        J[0][1] = -J[0][0] * J[0][0]; // клетка 2 на 2

        for (int i = 1; i < n; ++i) {
            T[i][i] = 2;
            T[i - 1][i] = -1;
            T[i][i - 1] = -1;

            T_inv[i][i] = n - i;
            T_inv[0][i] = n - i;
            T_inv[i][0] = n - i;

            sign = get_sign();
            gamma = sqrt((i + 0.) / (n - 1));
            J[i][i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        }
    }
    return_value = multmat(multmat(T, J), T_inv);
    return return_value;
}

int main() {
    {
        matrix A = {
                {2,  -1, -2},
                {-4, 6,  3},
                {-4, -2, 8}
        };
        vector<double> b = {-8, -11, -3};
        vector<double> expected_x = {-24.625, -127./12, -46./3};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x);
    }
    {
        matrix A = {
                {1, 2, 1},
                {2, 2, 1},
                {1, 1, 1}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-1, 1, 1};
        vector<double> actual_x = LU(A, b);

        print_results(A, b, expected_x);
    }
    {
        matrix A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    1}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-100. / 101, 100. / 101, 1};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    0.999}
        };
        vector<double> b = {2, 1, 1};
        vector<double> expected_x = {-99801. / 100798, 49850. / 50399, 500. / 499};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
                {1, 2.01, 1},
                {2, 2,    1},
                {1, 1,    0.999}
        };
        vector<double> b = {2, 1.0001, 1};
        vector<double> expected_x = {-997909201. / 1007980000, 9970001. / 10079800, 9999. / 9980};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
                {1, 2,     1.1},
                {2, 0,     1},
                {0, -2.02, 0}
        };
        vector<double> b = {0, 1, 1.00001};
        vector<double> expected_x = {11099. / 121200, -100001. / 202000, 49501. / 60600};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
                {1}
        };
        vector<double> b = {2};
        vector<double> expected_x = {2};

        print_results(A, b, expected_x);
    }

    // Задаем классы матриц

    int n = 300;

    cout << "n = " << n << endl;

    vector<pair<double, double>> params = {{1,     10},
                                           {1,     100},
                                           {1,     1000},
                                           {1,     10000},
                                           {0.1,   1},
                                           {0.01,  1},
                                           {0.001, 1}};

    cout << "Простые матрицы:\n";
    cout << "alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho" << endl;
    for (auto &pair: params) {
        auto alph = pair.first, beta = pair.second;

        auto A = first_class(n, alph, beta);
        auto A_inv = first_class(n, alph, beta, true);

//        printMatrix(A_inv);

//        cout << endl;
        auto x_starred = generate_actual_solution(n);

//        printVector(x_starred);
//        cout << endl;
//        cout << endl;

        auto f = mult(A, x_starred);

        auto x_tilde = LU(A, f);

        vector<double> r = subtract(mult(A, x_tilde), f);

        double error = norm(subtract(x_tilde, x_starred)), zeta = error / norm(x_starred), rho = norm(r) / norm(f);

        cout << setprecision(3) << alph << '\t' << beta << '\t' << norm(A) << '\t' << norm(A_inv) << '\t' << norm(A) *
                                                                                                             norm(A_inv)
             << '\t' << error
             << '\t' << zeta << '\t' << norm(r) << '\t' << rho << endl;
    }

    cout << "\"сложные\" матрицы:\n";
    cout << "alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho" << endl;
    for (auto &pair: params) {
        auto alph = pair.first, beta = pair.second;

        auto A = second_class(n, alph, beta);
        auto A_inv = second_class(n, alph, beta, true);

//        printMatrix(A_inv);

//        cout << endl;
        auto x_starred = generate_actual_solution(n);

//        printVector(x_starred);
//        cout << endl;
//        cout << endl;

        auto f = mult(A, x_starred);

        auto x_tilde = LU(A, f);

        vector<double> r = subtract(mult(A, x_tilde), f);

        double error = norm(subtract(x_tilde, x_starred)), zeta = error / norm(x_starred), rho = norm(r) / norm(f);

        cout << setprecision(3) << alph << '\t' << beta << '\t' << norm(A) << '\t' << norm(A_inv) << '\t' << norm(A) *
                                                                                                             norm(A_inv)
             << '\t' << error
             << '\t' << zeta << '\t' << norm(r) << '\t' << rho << endl;
    }


}