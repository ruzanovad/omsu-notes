// 2.23 from Stallings

#include <bits/stdc++.h>

using namespace std;

const double english_frequency_table[] = {
    8.2, 1.5, 2.8, 4.3, 13.0, 2.2, 2.0, 6.1, 7.0, 0.1, 0.8,
    4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0, 6.3, 9.1, 2.8, 1.0,
    2.4, 0.2, 2.0, 0.1};

void letter_freq_attack(string ciphertext, int num_plaintexts)
{
    double plaintext_scores[26] = {0};
    string plaintext;

    for (int key = 0; key < 26; key++)
    {
        plaintext = "";

        for (char c : ciphertext)
        {
            if (isalpha(c))
            {
                if (isupper(c))
                {
                    plaintext += char(int(c + key - 'A') % 26 + 'A');
                }
                else
                {
                    plaintext += char(int(c + key - 'a') % 26 + 'a');
                }
            }
            else
            {
                // ignore char, just add it to the text
                plaintext += c;
            }
        }

        for (char letter : string("abcdefghijklmnopqrstuvwxyz"))
        {
            double freq = (count(plaintext.begin(), plaintext.end(), letter) + count(plaintext.begin(), plaintext.end(), letter + ('A' - 'a'))) /
                          double(plaintext.length());
            int index = letter - 'a';
            // different metrics could have been used here
            plaintext_scores[key] += abs(english_frequency_table[index] - freq*100);
        }
    }

    // Sort the plaintexts by their score
    pair<int, double> sorted_scores[26];
    for (int i = 0; i < 26; i++)
    {
        sorted_scores[i] = make_pair(i, plaintext_scores[i]);
    }
    sort(sorted_scores, sorted_scores + 26,
         [](pair<int, double> a, pair<int, double> b)
         {
             return a.second < b.second;
         });

    // Print the top num_plaintexts plaintexts
    for (int i = 0; i < num_plaintexts; i++)
    {
        int key = sorted_scores[i].first;
        string plaintext = "";

        // Decrypt the ciphertext using the current key
        for (char c : ciphertext)
        {
            if (isalpha(c))
            {
                if (isupper(c))
                {
                    plaintext += char(int(c + key - 'A') % 26 + 'A');
                }
                else
                {
                    plaintext += char(int(c + key - 'a') % 26 + 'a');
                }
            }
            else
            {
                plaintext += c;
            }
        }
        cout.precision(20);
        cout << "Plaintext " << i + 1 << ": " << plaintext << ", Score: " << sorted_scores[i].second << endl;
    }
}

int main()
{

    letter_freq_attack("Khoor zruog", 10);

    //1
    letter_freq_attack("Xsjuf b qsphsbn uibu dbo qfsgpsn b mfuufs gsfrvfodz buubdl po bo beejujwf djqifs xjuipvu ivnbo joufswfoujpo", 10);

    //a = 3, b = 1;
    // doesn't work with affine
    letter_freq_attack("Iznjgnobog Crwo Q Fnoonkv", 26);

    letter_freq_attack("Ni vy il hin ni vy, Nbun cm nby koymncih", 20);
    letter_freq_attack("B TJNQMF NFTTBHF", 5);
    return 0;
}