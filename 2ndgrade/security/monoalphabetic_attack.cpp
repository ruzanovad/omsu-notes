#include <iostream>
#include <string>
#include <map>
#include <algorithm>
#include <random>
#include <set>
#include <fstream>

using namespace std;

size_t only_letter_length(string &s)
{
    size_t count = 0;
    for (auto &x : s)
    {
        count += isalpha(x);
    }
    return count;
}
// const unsigned long long fact26 = 403291461126605635584000000ULL;

// Function to perform a letter frequency attack
void letter_freq_attack(string ciphertext, int num_plaintexts)
{
    map<char, double> freq_table = {
        {'a', 8.2}, {'b', 1.5}, {'c', 2.8}, {'d', 4.3}, {'e', 13.0}, {'f', 2.2}, {'g', 2.0}, {'h', 6.1}, {'i', 7.0}, {'j', 0.1}, {'k', 0.8}, {'l', 4.0}, {'m', 2.4}, {'n', 6.7}, {'o', 7.5}, {'p', 1.9}, {'q', 0.1}, {'r', 6.0}, {'s', 6.3}, {'t', 9.1}, {'u', 2.8}, {'v', 1.0}, {'w', 2.4}, {'x', 0.2}, {'y', 2.0}, {'z', 0.1}};

    // Count the frequency of each letter in the ciphertext
    map<char, int> ciphertext_freq = {{'a', 0}, {'b', 0}, {'c', 0}, {'d', 0}, {'e', 0}, {'f', 2.2}, {'g', 0}, {'h', 6.1}, {'i', 7.0}, {'j', 0.1}, {'k', 0.8}, {'l', 0}, {'m', 2.4}, {'n', 6.7}, {'o', 7.5}, {'p', 1.9}, {'q', 0.1}, {'r', 6.0}, {'s', 0}, {'t', 9.1}, {'u', 2.8}, {'v', 1.0}, {'w', 2.4}, {'x', 0.2}, {'y', 2.0}, {'z', 0.1}};
    for (auto &x : ciphertext_freq)
    {
        x.second = 0;
    }
    for (char c : ciphertext)
    {
        if (isalpha(c))
        {
            char lc = tolower(c);
            ciphertext_freq[lc]++;
        }
    }

    // Sort the letters of the ciphertext by frequency
    vector<pair<char, int>> sorted_freq(ciphertext_freq.begin(), ciphertext_freq.end());
    sort(sorted_freq.begin(), sorted_freq.end(),
         [](pair<char, int> &a, pair<char, int> &b)
         {
             return a.second > b.second;
         });
    vector<char> ciphertext_letters;
    for (auto &p : sorted_freq)
    {
        ciphertext_letters.push_back(p.first);
    }

    // Get the most common letters in English order of frequency
    vector<pair<char, double>> english_letters(freq_table.begin(), freq_table.end());
    sort(english_letters.begin(), english_letters.end(),
         [](pair<char, double> &a, pair<char, double> &b)
         {
             return a.second > b.second;
         });

    // Create a map to map ciphertext letters to English letters
    map<char, char> mapping;
    for (int i = 0; i < ciphertext_letters.size(); i++)
    {
        mapping[ciphertext_letters[i]] = english_letters[i].first;
    }

    // Decrypt the ciphertext using the mapping
    string plaintext = "";
    for (char c : ciphertext)
    {
        if (isalpha(c))
        {
            if (isupper(c))
            {
                char lc = tolower(c);
                plaintext += toupper(mapping[lc]);
            }
            else
            {
                plaintext += mapping[c];
            }
        }
        else
        {
            plaintext += c;
        }
    }

    // Score the plaintext based on letter frequency
    double score = 0;
    for (auto &p : freq_table)
    {
        char letter = p.first;
        double freq = (count(plaintext.begin(), plaintext.end(), letter) + count(plaintext.begin(), plaintext.end(), letter + ('A' - 'a'))) /
                      double(only_letter_length(plaintext));
        score += (p.second - freq*100) * (p.second - freq*100);
    }
    set<map<char, char>> permutations;
    permutations.insert(mapping);
    // Store the plaintexts and their scores
    vector<pair<string, double>> plaintext_scores(num_plaintexts, make_pair("", 0));
    plaintext_scores[0] = make_pair(plaintext, score);

    // Generate num_plaintexts-1 new mappings by swapping two random letters in the mapping
    random_device rd;
    mt19937 gen(rd());
    uniform_int_distribution<> distr(0, 25);

    for (int i = 1; i < num_plaintexts; i++)
    {
        map<char, char> new_mapping = mapping;
        size_t count_of_operations = 0;
        do
        {
            // Swap two random letters in the mapping
            int index1 = distr(gen);
            int index2 = distr(gen);
            while (index1 == index2)
            {
                index1 = distr(gen);
                index2 = distr(gen);
            }
            char temp = new_mapping[ciphertext_letters[index1]];
            new_mapping[ciphertext_letters[index1]] = new_mapping[ciphertext_letters[index2]];
            new_mapping[ciphertext_letters[index2]] = temp;
            count_of_operations++;
        } while (permutations.find(new_mapping) == permutations.end() && count_of_operations < num_plaintexts);
        // Decrypt the ciphertext using the new mapping
        string new_plaintext = "";
        for (char c : ciphertext)
        {
            if (isalpha(c))
            {
                if (isupper(c))
                {
                    char lc = tolower(c);
                    new_plaintext += toupper(new_mapping[lc]);
                }
                else
                {
                    new_plaintext += new_mapping[c];
                }
            }
            else
            {
                new_plaintext += c;
            }
        }

        // Score the new plaintext based on letter frequency
        double new_score = 0;
        for (auto &p : freq_table)
        {
            char letter = p.first;
            double freq = (count(new_plaintext.begin(), new_plaintext.end(),
                                 letter) +
                           count(new_plaintext.begin(), new_plaintext.end(), letter + ('A' - 'a'))) /
                          double(only_letter_length(new_plaintext));
            new_score += (p.second - freq*100) * (p.second - freq*100);
        }

        plaintext_scores[i] = make_pair(new_plaintext, new_score);
    }

    // Sort the plaintexts by their score
    sort(plaintext_scores.begin(), plaintext_scores.end(),
         [](pair<string, double> &a, pair<string, double> &b)
         {
             return a.second < b.second;
         });

    // Print the top num_plaintexts plaintexts
    for (int i = 0; i < num_plaintexts; i++)
    {
        cout << "Plaintext " << i + 1 << ": " << plaintext_scores[i].first << ", Score: "
             << plaintext_scores[i].second << endl;
    }
}

int main()
{
    ifstream fin("blabla.txt");
    if (!fin)
        return 1;

    string ciphertext;
    string temp;
    while (getline(fin, temp))
    {
        ciphertext += temp;
    }
    int num_plaintexts = 10;
    // letter_freq_attack(ciphertext, num_plaintexts);
    letter_freq_attack("NNNNNNNGGGGGBBBBRRRZZO", 10);
    return 0;
}
