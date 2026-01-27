# cnt  = 0
#
# for i in range(26):
#     for j in range(26):
#         for k in range(26):
#             for f in range(26):
#                 # i j
#                 # k f
#                 if (i*f - j*k) % 13 == 0:
#                     cnt+=1
# print(cnt)

cnt  = 0

for i in range(1, 26):
    for j in range(26):
        for k in range(1, 26):
            for f in range(26):
                # i j
                # k f
                if (i % 13 or k % 13) and ((j % i)%13==0 and (f ) % k %13 == 0):
                    cnt+=1
print(cnt)