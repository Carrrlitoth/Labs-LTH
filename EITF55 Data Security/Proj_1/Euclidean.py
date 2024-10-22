# andra pseudo koden i uppgiften
import random

class Euclidean(object):
    #parametrar som tas emot: e, beräkna (p-1)*(q-1) i en variabel, skicka med hit istället för m
    def findD(self, a, m):
        d1, v1, v2, d2 = m, 0, 1, a

        while d2 != 0:
            q = d1 // d2
            t2 = v1 - q * v2
            t3 = d1 - q * d2
            v1, d1 = v2, d2
            v2, d2 = t2, t3

        v = v1
        d = d1

        if d != 1:
            raise ValueError("Inverse does not exist")
        elif v<0: #om strul ändra till if
            v +=m
            return v
        return v


"""
p = 13360596673522561128292010452398022934953307091129677106110527009158092383445279200923178346513928154733935949819528267150442791967684311350599971671723789
q = 10068166111505533700789502752077089121396593668161901133569868522466684514587829192378908024211225767516114071723057036245680100528051721816812034465653023
e = pow(2, 16)+1
phiN = (p-1)*(q-1)
ec = Euclidean()
d = ec.findD(e, phiN)
print("d:", d)
"""    
