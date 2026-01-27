import numpy as np 

key = np.array([[5, 17, 0], [3, 7, 11], [13, 6, 15]])

def hill_encrypt(plain_text, key):
    plain_text = plain_text.lower()
    plain_text = ''.join(filter(str.isalpha, plain_text))
    n = len(key)
    if len(plain_text) % n != 0:
        plain_text = plain_text + 'x'*(n - len(plain_text) % n)
    plain_text_indexes = [ord(i) - 97 for i in plain_text]
    plain_text_indexes = np.array(plain_text_indexes).reshape(-1, n)
    cipher_text_indexes = np.dot(plain_text_indexes, key) % 26
    cipher_text = ''
    for i in range(cipher_text_indexes.shape[0]):
        for j in range(cipher_text_indexes.shape[1]):
            cipher_text += chr(cipher_text_indexes[i][j] + 97)
    return cipher_text.upper()

print(hill_encrypt('help', key)) #NCOJ
print(hill_encrypt('hill', key)) #STSZ
print(hill_encrypt('cipher', key)) #JRWLXS
print(hill_encrypt('matrix', key)) #BMDAKD