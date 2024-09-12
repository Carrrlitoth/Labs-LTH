# In this cell, you can write your own "main" to run and evaluate your 
# implementation without using the visualisation above (should be considerably faster!)

from models import *
from Filters import HMMFilter

import numpy as np
import matplotlib.pyplot as plt
import random

#main code in this cell was made from inspiration of the localizer class
#as well as help from fellow classmates Nils Randevik and David Petersson

#Shift case depending on which configuration is being tested
CASE = 4

STEPS = 500

#Support method to make it easier grabbing essential values, moste of the code used from the localizer.py update method
#With help from David Petersson on which parts were needed
def locCalcs (trueState, sensor, nbrStates, probs, sm, case1, corGuess, totError):
    fPositions = probs.copy()

    for state in range(0, nbrStates, 4):
        fPositions[state:state+4] = sum(fPositions[state:state+4])

    estimate = sm.state_to_position((np.argmax(fPositions)))

    tsX, tsY, tsH = sm.state_to_pose(trueState)
    eX, eY = estimate

    if case1 and sensor == None:
        error = -1
    else:
        error = abs(tsX-eX)+abs(tsY-eY)
        totError += error
    
    if eX == tsX and eY == tsY:
        corGuess += 1

    return error, totError, corGuess


def main():

    #case configurations depending on whats being tested
    if CASE == 1:
        sm = StateModel(8, 8)
        tm = TransitionModel(sm)
        nbrStates = sm.get_num_of_states()
        probs1 = np.ones(nbrStates) / nbrStates
        probs2 = np.ones(nbrStates) / nbrStates
        sm.state_to_position(np.argmax(probs1))
        obserM1 = ObservationModel_NUF.ObservationModel(sm)
        obserM2 = ObservationModel_NUF.ObservationModel(sm)
        HMM1 = HMMFilter(probs1, tm, obserM1, sm)

    elif CASE == 2 or CASE == 3:
        if(CASE == 2):
            sm = StateModel(4, 4)
        else: 
            sm = StateModel(16, 20)
        tm = TransitionModel(sm)
        nbrStates = sm.get_num_of_states()
        probs1 = np.ones(nbrStates) / nbrStates
        probs2 = np.ones(nbrStates) / nbrStates
        sm.state_to_position(np.argmax(probs1))
        obserM1 = ObservationModel_NUF.ObservationModel(sm)
        obserM2 = ObservationModel_UF.ObservationModelUF(sm)
        HMM1 = HMMFilter(probs1, tm, obserM1, sm)
        HMM2 = HMMFilter(probs2, tm, obserM2, sm)

    elif CASE == 4:
        sm = StateModel(10, 10)
        tm = TransitionModel(sm)
        nbrStates = sm.get_num_of_states()
        probs1 = np.ones(nbrStates) / nbrStates
        probs2 = np.ones(nbrStates) / nbrStates
        sm.state_to_position(np.argmax(probs1))
        obserM1 = ObservationModel_NUF.ObservationModel(sm)
        obserM2 = ObservationModel_NUF.ObservationModel(sm)
        HMM1 = HMMFilter(probs1, tm, obserM1, sm)
        HMM2 = HMMFilter(probs2, tm, obserM2, sm)
        prevSteps = []
        trueState = random.randint(0, nbrStates-1)
        rs = RobotSim(trueState, sm)
        sensor0 = None
        for i in range(5): 
            trueState = rs.move_once(tm)
            sense = rs.sense_in_current_state(obserM1)
            prevSteps.append([trueState, sensor0])

    #starting values and initating variables to be worked with before loop
    trueState = random.randint(0, nbrStates-1)
    robS = RobotSim(trueState, sm)
    corGuessMod1 = 0
    corGuessMod2 = 0
    totError1 = 0
    totError2 = 0
    sums1 = []
    sums2 = []
    
    #approach used here was a combination of inspiration from update in localizer.py 
    #as well as the iterativ description in the handout
    for i in range(1, STEPS+1):
        case1 = False
        trueState = robS.move_once(tm)
        sensor1 = robS.sense_in_current_state(obserM1)
        sensor2 = robS.sense_in_current_state(obserM2)

        if CASE == 1:
            probs1 = HMM1.filter(sensor1)
            probs2 = np.diag(obserM2.get_o_reading(sensor2))
            case1 = True
        elif CASE == 2 or CASE == 3:
            probs1 = HMM1.filter(sensor1)
            probs2 = HMM2.filter(sensor2)
        elif CASE == 4:
            prevSteps.append([trueState, sensor1])
            trueState, sensor1 = prevSteps[0]
            prevSteps = prevSteps[1:]                   #slice away first value
            probs1 = HMM1.filter(sensor1)
            probs2 = HMM2.smoothing(sensor1, prevSteps)

            error1, totError1, corGuessMod1 = locCalcs(trueState, sensor1, nbrStates, probs1, sm, case1, corGuessMod1, totError1)
            error2, totError2, corGuessMod2 = locCalcs(trueState, sensor2, nbrStates, probs2, sm, case1, corGuessMod2, totError2)


        sums1.append(error1)
        sums2.append(error2)
        #sums1.append(totError1/i)
        #sums2.append(totError2/i)

    #Results
    #I really struggled with some of the math behind what values we actually were looking for
    #Since im studying bachelor degree in computer science i have not antended flerdimensionell analys
    #Here i got great help from previously mentioned fellow classmate Nils with understanding what values are actually important
    #as well as where to get them
    print("Case: ", CASE)
    print("First model: ", " Number of correct guesses: ", corGuessMod1, "|| Average numbers of errors: ", totError1/STEPS)
    temp1 = []
    for i in range(1, STEPS+1):
        temp1.append(i)
    plt.figure()
    plt.plot(temp1, sums1)

    print("Second model: ", " Number of correct guesses: ", corGuessMod2, "|| Average numbers of errors: ", totError2/STEPS)
    temp2 = []
    for i in range(1, STEPS+1):
        temp2.append(i)
    plt.figure()
    plt.plot(temp2, sums2)

    return
main()