# main method code, function to generate 100 prime numbers, function to wirte it to a text file
import random
import RabinMiller
import time

#generate random numbers and check if their primes by sending them to my RabinMiller class where i have a check function, then put into list
def genPrimeNbrs(bitSize, rm):

    nbrList = list()
    time_1 = time.time()
    while len(nbrList) < 100:
        randNbr = random.getrandbits(bitSize)
        if rm.checkPrime(randNbr):
            if randNbr not in nbrList:
                nbrList.append(randNbr) 
    time_2 = time.time()
    print("Calculation time: ", time_2-time_1)
    return nbrList #list of 100 checked prime numbers

#function to write a list of numbers to a txt file
def write2file(numbers, fileName):
    with open(fileName, 'w') as file:
        for number in numbers:
            file.write(str(number) + '\n')


#get the rabinmiller object, calls genPrimeNbrs, calls write to file
rm = RabinMiller.RabinMiller()
primeNbrList = genPrimeNbrs(1024, rm) #generate primes based on a specifik bitsize
write2file(primeNbrList, "1024.txt") #write it to file