#include <iostream>
#include <string>
#include <vector>
#include <exception>

#define LOGGING

#ifdef LOGGING
#define LOG(x) std::cout << x << std::endl
#else
#define LOG(x) ...
#endif

namespace hill {
    unsigned long long modularInverse(unsigned long long a, unsigned long long mod);

    unsigned long long gcd(unsigned long long a, unsigned long long b);

    std::vector<std::vector<int>> mult(std::vector<std::vector<int>> &A, std::vector<std::vector<int>> &B);

    std::string encode(std::string plaintext, std::vector<std::vector<int>> &key);

    inline int det(std::vector<std::vector<int>> &B);

    unsigned long long modularInverse(unsigned long long int a, unsigned long long int mod) {
        if (gcd(a, mod) != 1) {
            throw std::invalid_argument("Cannot find modular inverse of a");
        }

        auto phi = [](unsigned long long n) -> unsigned long long {
            unsigned long long result = n;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    while (n % i == 0)
                        n /= i;
                    result -= result / i;
                }
            }
            if (n > 1)
                result -= result / n;
            return result;
        };

        auto binPow = [](unsigned long long a, unsigned long long b) -> unsigned long long {
            unsigned long long res = 1;
            while (b > 0) {
                if (b & 1)
                    res = res * a;
                a = a * a;
                b >>= 1;
            }
            return res;
        };
        return (binPow(a, phi(mod) - 1)) % mod;
    }

    unsigned long long gcd(unsigned long long int a, unsigned long long int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    //2x2
    std::vector<std::vector<int>> inverseMatrix(std::vector<std::vector<int>> &matrix) {
        std::vector<std::vector<int>> result(2, std::vector<int>(2, 0));
        if (det(matrix) == 0)
            throw std::invalid_argument("cannot find inverse matrix");
        int detInv = modularInverse(det(matrix), 26);
        result[0][0] = ((matrix[1][1]%26 +26) * detInv) % 26;
        result[1][1] = ((matrix[0][0]%26 + 26) * detInv) % 26;
        result[0][1] = ((-matrix[0][1]%26 +26) * detInv) % 26;
        result[1][0] = ((-matrix[1][0]%26  +26)* detInv) % 26;
        return result;
    }

    // 2x2
    int det(std::vector<std::vector<int>> &B) { return (B[0][0] * B[1][1] - B[0][1] * B[1][0]) % 26; }

    std::vector<std::vector<int>> mult(std::vector<std::vector<int>> &A, std::vector<std::vector<int>> &B) {
        if (A.empty() || B.empty() || A[0].empty() || B[0].empty())
            throw std::invalid_argument("empty matrix");
        size_t m = A.size(), n = A[0].size(), k = B.size(), p = B[0].size();
        if (n != k)
            throw std::invalid_argument("cannot multiply matrices");
        std::vector<std::vector<int>> result(m, std::vector<int>(p, 0));
        for (int i = 0; i < m; ++i) {
            if (A[i].size() != n)
                throw std::invalid_argument("invalid matrix A");
            for (int j = 0; j < p; ++j) {
                if (B[j].size() != p)
                    throw std::invalid_argument("invalid matrix B");
                for (int t = 0; t < n; ++t) {
                    result[i][j] += (A[i][t] * B[t][j]);
                    result[i][j] %= 26;
                }
            }
        }
        return result;
    }

    std::string encode(std::string plaintext, std::vector<std::vector<int>> &key) {
        if (key.size() != 2 || key[0].size() != 2 || key[1].size() != 2)
            throw std::invalid_argument("invalid key");
        while (plaintext.size() % key.size()) {
            plaintext.push_back('z');
        }
        std::string ciphertext(plaintext.size(), 'a');
        for (int i = 0; i < plaintext.size(); i += 2) {
            std::vector<std::vector<int>> p(1, std::vector<int>());
            p[0].push_back(plaintext[i] - 'a');
            p[0].push_back(plaintext[i + 1] - 'a');
            std::vector<std::vector<int>> c = mult(p, key);
            ciphertext[i] = (char) ((int) 'a' + c[0][0]);
            ciphertext[i + 1] = (char) ((int) 'a' + c[0][1]);
            LOG(p[0][0] << " " << p[0][1] << " | " << ciphertext[i]-'a' << " " << ciphertext[i+1]-'a');
        }
        return ciphertext;
    }


    std::string decode(std::string ciphertext, std::vector<std::vector<int>> &key) {
        if (key.size() != 2 || key[0].size() != 2 || key[1].size() != 2 || det(key) == 0 ||
            ciphertext.size() % key.size())
            throw std::invalid_argument("invalid key");

        std::vector<std::vector<int>> invKey = inverseMatrix(key);
        std::string plaintext(ciphertext.size(), 'a');
        for (int i = 0; i < ciphertext.size(); i += 2) {
            std::vector<std::vector<int>> p(1, std::vector<int>());

            p[0].push_back(ciphertext[i] - 'a');
            p[0].push_back(ciphertext[i + 1] - 'a');
            std::vector<std::vector<int>> c = mult(p, invKey);

            plaintext[i] = (char) ((int) 'a' + c[0][0]);
            plaintext[i + 1] = (char) ((int) 'a' + c[0][1]);
            LOG(p[0][0] << " " << p[0][1] << " | " << plaintext[i]-'a' << " " << plaintext[i+1]-'a');
        }
        return plaintext;
    }
}

int main() {
    std::string p = "meetmeattheusualplaceattenratherthaneightoclock";
    std::vector<std::vector<int>> k(2, std::vector<int>(2, 0));
    k[0][0] = 9;
    k[0][1] = 4;
    k[1][0] = 5;
    k[1][1] = 7;
    std::string c = hill::encode(p, k);
    std::cout << c << std::endl;
    std::cout << hill::decode(c, k) << std::endl;
}