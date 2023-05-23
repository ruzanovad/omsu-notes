#include <bits/stdc++.h>
using namespace std;
typedef vector<vector<int>> matrix;
typedef vector<int> text;

std::vector<int> string_to_vector(std::string str)
{
    std::vector<int> vec;
    std::transform(str.begin(), str.end(), std::back_inserter(vec), [](char ch)
                   {
        if (std::isalpha(ch)) {
            return std::tolower(ch) - 'a';
        }
        return -1; });
    vec.erase(std::remove_if(vec.begin(), vec.end(), [](int num)
                             { return num < 0 || num > 25; }),
              vec.end());
    return vec;
}

std::string vector_to_string(const std::vector<int> &vec)
{
    std::string str(vec.size(), ' ');
    std::transform(vec.begin(), vec.end(), str.begin(), [](int num)
                   { return static_cast<char>(num + 'a'); });
    return str;
}

vector<vector<char>> numbers_to_chars(const vector<vector<int>> &numbers)
{
    vector<vector<char>> chars;

    for (const auto &row : numbers)
    {
        vector<char> char_row;
        for (const auto &num : row)
        {
            char c = num + 'a';
            char_row.push_back(c);
        }
        chars.push_back(char_row);
    }

    return chars;
}

vector<vector<int>> chars_to_numbers(const vector<vector<char>> &chars)
{
    vector<vector<int>> numbers;

    for (const auto &row : chars)
    {
        vector<int> num_row;
        for (const auto &c : row)
        {
            int num = c - 'a';
            num_row.push_back(num);
        }
        numbers.push_back(num_row);
    }

    return numbers;
}

int myPow(int x, unsigned int p)
{
    if (p == 0)
        return 1;
    if (p == 1)
        return x;

    int tmp = myPow(x, p / 2);
    if (p % 2 == 0)
        return tmp * tmp;
    else
        return x * tmp * tmp;
}
unsigned long long gcd(unsigned long long int a, unsigned long long int b)
{
    if (a == 0)
        return b;
    return gcd(b % a, a);
}
unsigned long long modularInverse(unsigned long long int a, unsigned long long int mod = 26)
{
    if (gcd(a, mod) != 1)
    {
        throw std::invalid_argument("Cannot find modular inverse of a");
    }

    auto phi = [](unsigned long long n) -> unsigned long long
    {
        unsigned long long result = n;
        for (int i = 2; i * i <= n; i++)
        {
            if (n % i == 0)
            {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        }
        if (n > 1)
            result -= result / n;
        return result;
    };

    auto binPow = [](unsigned long long a, unsigned long long b) -> unsigned long long
    {
        unsigned long long res = 1;
        while (b > 0)
        {
            if (b & 1)
                res = res * a;
            a = a * a;
            b >>= 1;
        }
        return res;
    };
    return (binPow(a, phi(mod) - 1)) % mod;
}

int md(int a, int b)
{
    if (b < 0) // you can check for b == 0 separately and do what you want
        return -md(-a, -b);
    int ret = a % b;
    if (ret < 0)
        ret += b;
    return ret;
}

int determinant(matrix mat)
{
    if (mat.size() == 1)
    {
        return mat[0][0];
    }
    else if (mat.size() == 2)
    {
        return mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0];
    }
    else
    {
        int det = 0;
        for (int i = 0; i < mat.size(); i++)
        {
            matrix minor;
            for (int j = 1; j < mat.size(); j++)
            {
                vector<int> row;
                for (int k = 0; k < mat.size(); k++)
                {
                    if (k != i)
                    {
                        row.push_back(mat[j][k]);
                    }
                }
                minor.push_back(row);
            }
            det += myPow(-1, i) * mat[0][i] * determinant(minor);
            det = md(det, 26);
        }
        return det;
    }
}

matrix matrix_multiply(matrix a, matrix b, int m = 26)
{
    int n = a.size(), k = b.size(), p = b[0].size(), g = a[0].size();
    if (g != k)
        throw 1;
    matrix result(n, vector<int>(p, 0));
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < p; j++)
        {
            for (int s = 0; s < k; s++)
            {
                result[i][j] += a[i][s] * b[s][j];
                result[i][j] %= m;
            }
        }
    }
    return result;
}

matrix matrix_inverse(matrix mat, int m = 26)
{
    int n = mat.size();
    matrix inverse(mat);
    int inv_det = modularInverse(determinant(mat), 26);
    for (int i = 0; i < inverse.size(); ++i)
    {
        for (int j = 0; j < inverse[i].size(); ++j)
        {
            inverse[i][j] = (inv_det * inverse[i][j]) % m;
        }
    }
    matrix invmat(n, text(n));

    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            matrix submat(n - 1, text(n - 1));
            for (int k = 0; k < n; k++)
            {
                for (int l = 0; l < n; l++)
                {
                    if (k < i && l < j)
                    {
                        submat[k][l] = mat[k][l];
                    }
                    else if (k < i && l > j)
                    {
                        submat[k][l - 1] = mat[k][l];
                    }
                    else if (k > i && l < j)
                    {
                        submat[k - 1][l] = mat[k][l];
                    }
                    else if (k > i && l > j)
                    {
                        submat[k - 1][l - 1] = mat[k][l];
                    }
                }
            }
            invmat[j][i] = md(myPow(-1, i + j) * determinant(submat) * inv_det, 26);
        }
    }

    return invmat;
}

void attack(vector<pair<text, text>> plain_cipher, size_t dim, matrix ciphertext)
{
    // C = PK
    // K = P^-1 C
    // P = C K^-1
    if (plain_cipher.size() != dim || plain_cipher.size() != ciphertext[0].size())
    {
        exit(EXIT_FAILURE);
    }
    matrix P;
    matrix C;
    for (auto &x : plain_cipher)
    {
        P.push_back(x.first);
        C.push_back(x.second);
    }

    matrix P_inv = matrix_inverse(P);
    matrix key = matrix_multiply(P_inv, C);
    matrix key_inv = matrix_inverse(key);
    matrix plaintext = matrix_multiply(ciphertext, key_inv);
    cout << vector_to_string(plaintext[0]) << endl;
    for (int i = 0; i < plaintext.size(); ++i)
    {
        for (int j = 0; j < plaintext[i].size(); ++j)
        {
            cout << plaintext[i][j] << " ";
        }
        cout << endl;
    }
}

int main()
{
    // matrix key = {{5, 17, 0, 3}, {7, 11, 13, 6}, {5, 15, 5, 17}, {1, 6, 3, 5}};
    text p = string_to_vector("pink");
    // 15 8 13 10
    // ciphers = { {3, 2, 22, 21},}
    // {1, 22, 10, 25},
    // {18, 25, 18, 6},
    // {16, 19, 16, 2}
    // text ss = string_to_vector("play");
    // text sss = string_to_vector("seas");
    // text ssss = string_to_vector("tear");
    // text c = {24, 0, 17, 0};
    text c = string_to_vector("yara");
    // cout << vector_to_string(c);
    vector<pair<text, text>> texts = {{string_to_vector("help"), {3, 2, 22, 21}},
                                      {string_to_vector("play"), {20, 0, 7, 23}},
                                      {string_to_vector("ciph"), {18, 25, 18, 6}},
                                      {string_to_vector("tear"), {10, 1, 25, 10}}};
    attack(texts, 4, vector<vector<int>>(1, c));
    cout << endl;
}