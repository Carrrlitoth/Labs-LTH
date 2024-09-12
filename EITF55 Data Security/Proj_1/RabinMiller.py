#avgöra om ett tal är ett primtal, följ pseudokoden från upggiftsbeskrivningen
import random

class RabinMiller:
    def checkPrime(self, bigNbr):

        if bigNbr%2 == 0 or bigNbr<3:
            return False
        
        r, s = 0, bigNbr - 1
        while s % 2 == 0:
            r += 1
            s //= 2
        
        a = random.randint(2, bigNbr-2)
        x = pow(a, s, bigNbr)

        if x==1 or x==bigNbr-1:
            return True
        
        for j in range(1, r):
            x =  pow(a, pow(2, j) * s, bigNbr)
            if x==1:
                return False
            if x==bigNbr-1:
                return True
        return False
    

