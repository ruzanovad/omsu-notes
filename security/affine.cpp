//"https://cp-algorithms.com/algebra/module-inverse.html"

#include <iostream>
#include <exception>
#include <string>
#include <utility>
#include <iostream>

const std::string upperalpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
const std::string loweralpha = "abcdefghijklmnopqrstuvwxyz";

namespace affine {
    unsigned long long modularInverse(unsigned long long a, unsigned long long mod);

    unsigned long long gcd(unsigned long long a, unsigned long long b);

    std::string encodeAffine(std::string plaintext, std::string alphabet, int a, int b);

    std::string decodeAffine(std::string ciphertext, std::string alphabet, int a, int b);

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

    std::string encodeAffine(std::string plaintext, std::string alphabet, int a, int b) {
        if (gcd(a, alphabet.size()) != 1) {
            throw std::invalid_argument("Cannot find modular inverse of a");
        }

        std::string ciphertext = std::move(plaintext);
        char first_low = tolower(alphabet[0]);
        char first_up = toupper(first_low);

        for (auto &x: ciphertext) {
            if (islower(x)) {
                x = first_low + (a * (int) (x - first_low) + b) % alphabet.size();
            } else {
                x = first_up + (a * (int) (x - first_up) + b) % alphabet.size();
            }
        }
        return ciphertext;
    }

    std::string decodeAffine(std::string ciphertext, std::string alphabet, int a, int b) {
        if (gcd(a, alphabet.size()) != 1) {
            throw std::invalid_argument("Cannot find modular inverse of a");
        }
        int a_inv = modularInverse(a, alphabet.size());
        std::string plaintext = std::move(ciphertext);
        char first_low = tolower(alphabet[0]);
        char first_up = toupper(first_low);

        for (auto &x: plaintext) {
            if (islower(x)) {
                x = first_low + (a_inv * (int(x - first_low) - b)) % alphabet.size();
            } else {
                x = first_up + (a_inv * (int(x - first_up) - b)) % alphabet.size();
            }
        }
        return plaintext;
    }
}
signed main() {
    {
        std::string P = "ABC";
        std::string C = affine::encodeAffine(P, loweralpha, 3, 1);
        std::cout << C << '\n';
        std::string decode = affine::decodeAffine(C, upperalpha, 3, 1);
        std::cout << decode << '\n';
    }
    {
        std::string P = "abcdef";
        std::string C = affine::encodeAffine(P, loweralpha, 3, 1);
        std::cout << C << '\n';
        std::string decode = affine::decodeAffine(C, upperalpha, 3, 1);
        std::cout << decode << '\n';
    }
}