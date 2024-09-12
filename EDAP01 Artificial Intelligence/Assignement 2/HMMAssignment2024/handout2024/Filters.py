
from operator import matmul
import random
import numpy as np

from models import *


#
# This solution was heavily inspiered by simons lecture notes and his example code
# I allso received great help from fellow ai-course classmate Nils Randevik in how to propperly use 
# the variables given in the __init__ function, especially how to think when using "self" in python
#
class HMMFilter:
    
    def __init__(self, probs, tm, om, sm):
        self.__tm = tm      #transmission model
        self.__om = om      #observation model
        self.__sm = sm      #state model
        self.__f = probs    #filtered probability
        
        
    def filter(self, sensorR) :     #forward filtering
        #collect the matrixes needed for the calculations 
        #transposed transition matrix and diagonale matrix O_reading with probabilities of the states
        transM = self.__tm.get_T_transp()
        obserM = self.__om.get_o_reading(sensorR)

        # here instead of doing as Simon i opted for using numpys dot function, makes the code clearer for me
        self.__f = obserM.dot(transM).dot(self.__f)

        #alpha calculation same as Simons
        self.__f /= np.sum(self.__f)

        return self.__f
    
    def smoothing(self, sensorR, prevSteps) :           #backward-forward filtering / smoothing
        #first we need the filtered vector to smoothe
        self.filter(sensorR)

        #my version of using the equivalent to Simons n
        n = self.__sm.get_num_of_states()

        #we then need to initialize b, based on Simons array building of 1s 
        b = np.ones(n)

        #for loop based on a combination of Simons approach in 
        #forward_filter_with_backward_smoothing, fixed_lag_smoothing and help from Nils in adapting it
        for i in range(4, -1, -1): 
            obserM = self.__om.get_o_reading(prevSteps[i][1])
            transM = self.__tm.get_T()
            b = obserM.dot(transM).dot(b)
        s = self.__f * b
        s /= np.sum(s)

        #return normalized s
        return s 

        
        
        
