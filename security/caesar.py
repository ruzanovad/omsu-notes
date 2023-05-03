import typing
import string


def encode(plaintext: str, offset: int) -> str:
    offset %= 26
    alphabet_lower = string.ascii_lowercase
    alphabet_upper = string.ascii_uppercase

    shifted_alphabet_lower = alphabet_lower[offset:] + alphabet_lower[:offset]
    shifted_alphabet_upper = alphabet_upper[offset:] + alphabet_upper[:offset]

    alphabet = alphabet_lower + alphabet_upper
    shifted_alphabet = shifted_alphabet_lower + shifted_alphabet_upper

    table = str.maketrans(alphabet, shifted_alphabet)  # map

    return plaintext.translate(table)


def decode(ciphertext: str, offset: int) -> str:
    return encode(ciphertext, -offset)

print("ABC")
print(encode("ABC", 2), "со сдвигом 2")
assert decode(encode("ABC", 2), 2) == "ABC"
print("abc")
print(encode("abc", -2), "со сдвигом -2")
assert decode(encode("abc", -2), -2) == "abc"
print("Syntax error")
print(encode("Syntax error", 1), "со сдвигом 1")
assert decode(encode("Syntax error", 1), 1) == "Syntax error"


