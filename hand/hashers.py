import itertools
import hashlib
import time
from multiprocessing import Pool
wordfiles=[]
hashfiles=[]
for i in range(1,31):
    wordfile=open("word"+str(i)+".txt","w")
    wordfiles.append(wordfile)
    hashfile = open("hash" + str(i) + ".txt", "w")
    hashfiles.append(hashfile)



def matchHash(x):
    for string in itertools.combinations("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!#$%&'()*+,-./:;<=>?@[\]^_`{|}~",x):
        plain="".join(string)
        wordfiles[x].write(plain+"\n")
        print(plain)
        hexhash=hashlib.md5(plain.encode()).hexdigest()
        hashfiles[x].write(hexhash+"\n")
        print(hexhash)
    wordfiles[x].close()
    hashfiles[x].close()


if __name__ == "__main__":
    #arr = [2,3,8]

    with Pool(32) as p:
        print(p.map(matchHash, [1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30]))

    print("well done")

