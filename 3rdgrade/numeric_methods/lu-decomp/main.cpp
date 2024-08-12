#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <cmath>
#include <cassert>
#include <iomanip>
#include <ctime>

#define matrix vector<vector<long double>>

using namespace std;

// ibm866
const long double eps = 1e-6;

vector<long double> forwardSubstitution(const matrix &L, const vector<long double> &Pb);

vector<long double> backwardSubstitution(const matrix &U, const vector<long double> &y);

vector<long double> subtract(const vector<long double> &a, const vector<long double> &b)
{
    vector<long double> sub(a.size(), 0);
    for (int i = 0; i < a.size(); ++i)
    {
        sub[i] = a[i] - b[i];
    }
    return sub;
}

void printMatrix(const matrix &mat)
{
    for (const auto &row : mat)
    {
        for (auto elem : row)
        {
            cout << setprecision(3) << elem << "\t";
        }
        cout << endl;
    }
}

void printVector(const vector<long double> &vector)
{
    for (auto elem : vector)
    {
        cout << setprecision(3) << elem << "\t";
    }
    cout << endl;
}

vector<long double> mult(const matrix &A, const vector<long double> &x)
{
    vector<long double> vec(A.size(), 0);

    for (int i = 0; i < A.size(); ++i)
    {
        for (int j = 0; j < x.size(); ++j)
        {
            vec[i] += A[i][j] * x[j];
        }
    }

    return vec;
}

matrix multmat(const matrix &A, const matrix &B)
{
    size_t n = A.size();
    matrix result(n, vector<long double>(n, 0));

    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < n; ++j)
        {
            for (int k = 0; k < n; ++k)
            {
                result[i][j] += A[i][k] * B[k][j];
            }
        }
    }
    //    printMatrix(result);
    //    cout << endl;
    return result;
}

long double get_sign(int rule, int i)
{
    long double sign;
    switch (rule)
    {
    case 1:
        sign = 2. * (i % 2) - 1.;
        break;
    case 2:
        sign = 2. * ((i + 1) % 2) - 1.;
        break;
    default:
        sign = 1.;
    }
    return sign;
}

// ����� ������ � ������饩 ���
vector<long double> LU(matrix &U, vector<long double> &b)
{
    matrix L; // L - �㫥��� ����砫쭮

    size_t n = U.size();
    L.assign(n, vector<long double>(n, 0));

    for (int i = 0; i < n; ++i)
    {
        long double maxElement = abs(U[i][i]);
        int pivotRow = i;
        // ���ᨬ���� ������� �� ����� � ⥪�饬 �⮫�� i
        for (int k = i + 1; k < n; ++k)
        {
            if (abs(U[k][i]) > maxElement)
            {
                maxElement = abs(U[k][i]);
                pivotRow = k;
            }
        }
        // ���� ����⠢�塞 ���窨
        swap(U[i], U[pivotRow]);
        //        swap(P[i], P[pivotRow]);
        swap(L[i], L[pivotRow]);
        swap(b[i], b[pivotRow]);

        // Decomposition
        for (int j = i + 1; j < n; ++j)
        {
            L[j][i] = U[j][i] / U[i][i];
            for (int k = i; k < n; ++k)
            {
                U[j][k] -= L[j][i] * U[i][k];
            }
        }
        L[i][i] = 1;
    }
    //    printMatrix(L);
    //    printMatrix(U);
    //    printVector(b);

    vector<long double> x = backwardSubstitution(U, forwardSubstitution(L, b));
    return x;
}

vector<long double> forwardSubstitution(const matrix &L, const vector<long double> &Pb)
{
    size_t n = L.size(); // L[i][i] is 1
    vector<long double> y(n, 0.0);
    for (int i = 0; i < n; ++i)
    {
        long double sum = 0.0;
        for (int j = 0; j < i; ++j)
        {
            sum += L[i][j] * y[j];
        }
        y[i] = Pb[i] - sum;
    }
    return y;
}

vector<long double> backwardSubstitution(const matrix &U, const vector<long double> &y)
{
    size_t n = U.size();
    vector<long double> x(n, 0.0);
    for (int i = n - 1; i >= 0; --i)
    {
        long double sum = 0.0;
        for (int j = i + 1; j < n; ++j)
        {
            sum += U[i][j] * x[j];
        }
        x[i] = (y[i] - sum) / U[i][i];
    }
    return x;
}

long double norm(const vector<long double> &x)
{
    long double ret = 0;
    for (long double i : x)
    {
        ret = max(ret, abs(i));
    }
    return ret;
}

long double norm(const matrix &x)
{
    long double ret = 0;
    size_t n = x.size(), m = x[0].size();
    for (int i = 0; i < n; ++i)
    {
        for (int j = 0; j < m; ++j)
        {
            ret = max(ret, abs(x[i][j]));
        }
    }
    return ret;
}

void print_results(matrix &A, vector<long double> &b, const vector<long double> &expected_x, bool log = false)
{
    if (log)
    {
        cout << "����� A:" << endl;
        printMatrix(A);
        cout << "�ࠢ�� ����:" << endl;
        printVector(b);
    }
    auto A_copy(A);
    auto b_copy(b);
    vector<long double> actual_x = LU(A, b);

    cout << "��筮� �襭��:" << endl;
    printVector(expected_x);
    cout << "�ਡ�������� �襭��:" << endl;
    printVector(actual_x);
    auto z = subtract(expected_x, actual_x);

    cout << "�訡��:" << norm(z) << endl;
    cout << "�⭮�⥫쭠� �訡��: " << norm(z) / norm(expected_x) << endl;

    auto mul = mult(A_copy, actual_x);
    auto r = subtract(mul, b_copy);

    cout << "���離�: " << norm(r) << endl;

    cout << "�⭮�⥫쭠� ���離�: " << norm(r) / norm(b_copy) << endl;

    assert(norm(z) < eps);
    cout << endl;
}

vector<long double> generate_actual_solution(int n, long double alpha = 1, long double beta = 1)
{
    vector<long double> return_value(n, 0);
    for (int i = 0; i < n; ++i)
    {
        long double pi_half = acos(-1.) * 0.5;
        long double gamma = sin(pi_half * ((i + 0.0) / (n - 1)));
        long double sign = get_sign(2, i);
        return_value[i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
    }
    return return_value;
}

matrix first_class(int n, long double alpha, long double beta, bool inv = false, int sign_rule = 1)
{
    matrix T(n, vector<long double>(n, 0)), T_inv(n, vector<long double>(n, 0)),
        J(n, vector<long double>(n, 0)), return_value(n, vector<long double>(n, 0));
    T[0][0] = 1;
    // T_inv[0][0] = n;
    for (int j = 0; j < n; ++j)
    {
        T_inv[0][j] = n - max(0, j);
    }
    long double gamma = 0;
    long double sign = get_sign(sign_rule, 0);
    if (inv)
    {
        J[0][0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));

        for (int i = 1; i < n; ++i)
        {
            T[i][i] = 2;
            T[i - 1][i] = T[i][i - 1] = -1;

            for (int j = 0; j < n; ++j)
            {
                T_inv[i][j] = n - max(i, j);
            }
            sign = get_sign(sign_rule, i);
            gamma = sqrt((i + 0.) / (n - 1));
            J[i][i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        }
    }
    else
    {
        J[0][0] = sign * ((1 - gamma) * alpha + gamma * beta);
        for (int i = 1; i < n; ++i)
        {
            T[i][i] = 2;
            T[i - 1][i] = T[i][i - 1] = -1;
            for (int j = 0; j < n; ++j)
            {
                T_inv[i][j] = n - max(i, j);
            }

            sign = get_sign(sign_rule, i);
            gamma = sqrt((i + 0.) / (n - 1));
            auto value = sign * ((1 - gamma) * alpha + gamma * beta);
            J[i][i] = value;
        }
    }
    // printMatrix(T);
    // printMatrix(T_inv);
    // printMatrix(multmat(T, T_inv));
    // printMatrix(J);
    // printMatrix(multmat(T, T_inv));
    return_value = multmat(multmat(T, J), T_inv);

    return return_value;
}

matrix second_class(int n, long double alpha, long double beta, bool inv = false, int sign_rule = 1)
{
    matrix T(n, vector<long double>(n, 0)), T_inv(n, vector<long double>(n, 0)),
        J(n, vector<long double>(n, 0)), return_value(n, vector<long double>(n, 0))
        // , J_inv(n, vector<long double>(n, 0))
        ;
    T[0][0] = 1;
    // T_inv[0][0] = n;

    long double gamma = 0;
    long double sign = get_sign(sign_rule, 0);
    for (int j = 0; j < n; ++j)
    {
        T_inv[0][j] = n - max(0, j);
    }
    if (!inv)
    {
        J[0][0] = sign * ((1 - gamma) * alpha + gamma * beta);
        for (int i = 1; i < n; ++i)
        {
            T[i][i] = 2;
            T[i - 1][i] = T[i][i - 1] = -1;

            for (int j = 0; j < n; ++j)
            {
                T_inv[i][j] = n - max(i, j);
            }
            J[i][i] = sign * ((1 - gamma) * alpha + gamma * beta);
            sign = get_sign(sign_rule, i);

            gamma = sqrt((i + 0.) / (n - 1));
        }
        J[0][1] = 1; // ���⪠ 2 �� 2
    }
    else
    {
        long double gamma = 0;
        long double sign = get_sign(sign_rule, 0);
        // J_inv[0][0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
                J[0][0] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
        // cout << J[0][0] * J[0][0] << endl;
        // J_inv[0][1] = -(J_inv[0][0] * J_inv[0][0]); // ���⪠ 2 �� 2
        J[0][1] = -(J[0][0] * J[0][0]); 

        for (int i = 1; i < n; ++i)
        {
            T[i][i] = 2;
            T[i - 1][i] = T[i][i - 1] = -1;

            for (int j = 0; j < n; ++j)
            {
                T_inv[i][j] = n - max(i, j);
            }
            // J_inv[i][i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
            J[i][i] = 1. / (sign * ((1 - gamma) * alpha + gamma * beta));
            sign = get_sign(sign_rule, i);

            gamma = sqrt((i + 0.) / (n - 1));
        }
    }
    // printMatrix(J);
    // printMatrix(J_inv);
    // printMatrix(multmat(J, J_inv));
    // printMatrix(multmat(T, T_inv));
    // matrix mm = multmat(multmat(T, J_inv), T_inv);
    // if (inv)
    //     return_value = multmat(multmat(T, J_inv), T_inv);
    // else
        return_value = multmat(multmat(T, J), T_inv);
    // printMatrix(multmat(mm, return_value));
    return return_value;
}

int main()
{
    {
        matrix A = {
            {2, -1, -2},
            {-4, 6, 3},
            {-4, -2, 8}};
        vector<long double> b = {-8, -11, -3};
        vector<long double> expected_x = {-24.625, -127. / 12, -46. / 3};
        //        vector<long double> actual_x = LU(A, b);

        print_results(A, b, expected_x);
    }
    {
        matrix A = {
            {1, 2, 1},
            {2, 2, 1},
            {1, 1, 1}};
        vector<long double> b = {2, 1, 1};
        vector<long double> expected_x = {-1, 1, 1};
        //        vector<long double> actual_x = LU(A, b);

        print_results(A, b, expected_x);
    }
    {
        matrix A = {
            {1, 2.01, 1},
            {2, 2, 1},
            {1, 1, 1}};
        vector<long double> b = {2, 1, 1};
        vector<long double> expected_x = {-100. / 101, 100. / 101, 1};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
            {1, 2.01, 1},
            {2, 2, 1},
            {1, 1, 0.999}};
        vector<long double> b = {2, 1, 1};
        vector<long double> expected_x = {-99801. / 100798, 49850. / 50399, 500. / 499};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
            {1, 2.01, 1},
            {2, 2, 1},
            {1, 1, 0.999}};
        vector<long double> b = {2, 1.0001, 1};
        vector<long double> expected_x = {-997909201. / 1007980000, 9970001. / 10079800, 9999. / 9980};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
            {1, 2, 1.1},
            {2, 0, 1},
            {0, -2.02, 0}};
        vector<long double> b = {0, 1, 1.00001};
        vector<long double> expected_x = {11099. / 121200, -100001. / 202000, 49501. / 60600};

        print_results(A, b, expected_x);
    }

    {
        matrix A = {
            {1}};
        vector<long double> b = {2};
        vector<long double> expected_x = {2};

        print_results(A, b, expected_x);
    }

    // ������ ������ �����

    int n = 100;

    cout << "n = " << n << endl;

    vector<pair<long double, long double>> params = {{1, 10},
                                           {1, 100},
                                           {1, 1000},
                                           {1, 10000},
                                           {1, 100000},
                                           {1, 1000000},
                                           {1, 10000000000000},
                                           {0.1, 1},
                                           {0.01, 1},
                                           {0.001, 1},
                                           {0.0001, 1},
                                           {0.00001, 1},
                                           {0.000001, 1},
                                           {0.0000001, 1},
                                           {0.0000000000000001, 1}}; // error is high

    cout << "����� ������:\n";
    cout << "alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho" << endl;
    for (auto &pair : params)
    {
        auto alph = pair.first, beta = pair.second;

        auto A = first_class(n, alph, beta), A_copy(A);
        auto A_inv = first_class(n, alph, beta, true);
        // printMatrix(multmat(A, A_inv));

        //        cout << endl;
        auto x_starred = generate_actual_solution(n, alph, beta);

        auto f = mult(A, x_starred), f_copy(f);

        auto x_tilde = LU(A, f);

        vector<long double> r = subtract(mult(A_copy, x_tilde), f_copy);

        long double error = norm(subtract(x_tilde, x_starred)), zeta = error / norm(x_starred), rho = norm(r) / norm(f_copy);

        cout << setprecision(3) << alph << '\t' << beta << '\t' << norm(A_copy) << '\t' << norm(A_inv) << '\t'
             << norm(A_copy) *
                    norm(A_inv)
             << '\t' << error
             << '\t' << zeta << '\t' << norm(r) << '\t' << rho << endl;
    }

    cout << "\"᫮���\" ������:\n";
    cout << "alpha\tbeta\tnormA\tnorm_A_inv\tnu_A\tz\tzeta\tr\trho" << endl;
    for (auto &pair : params)
    {
        auto alph = pair.first, beta = pair.second;

        auto A = second_class(n, alph, beta), A_copy(A);
        auto A_inv = second_class(n, alph, beta, true);
        // printMatrix(A);
        // printMatrix(A_inv);
        // printMatrix(multmat(A, A_inv));
        auto x_starred = generate_actual_solution(n, alph, beta);

        auto f = mult(A, x_starred), f_copy(f);

        auto x_tilde = LU(A, f);

        vector<long double> r = subtract(mult(A_copy, x_tilde), f_copy);

        long double error = norm(subtract(x_tilde, x_starred)), zeta = error / norm(x_starred), rho = norm(r) / norm(f_copy);

        cout << setprecision(3) << alph << '\t' << beta << '\t' << norm(A_copy) << '\t' << norm(A_inv) << '\t'
             << norm(A_copy) *
                    norm(A_inv)
             << '\t' << error
             << '\t' << zeta << '\t' << norm(r) << '\t' << rho << endl;
    }
}