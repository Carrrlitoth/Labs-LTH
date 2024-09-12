import gym
import random
import requests
import numpy as np
import argparse
import sys
from gym_connect_four import ConnectFourEnv
import time
import copy

env: ConnectFourEnv = gym.make("ConnectFour-v0")
envCopy: ConnectFourEnv = gym.make("ConnectFour-v0")

SERVER_ADDRESS = "https://vilde.cs.lth.se/edap01-4inarow/"
API_KEY = 'nyckel'
STIL_ID = ["ca2128kj-s"] # TODO: fill this list with your stil-id's

def call_server(move):
   res = requests.post(SERVER_ADDRESS + "move",
                       data={
                           "stil_id": STIL_ID,
                           "move": move, # -1 signals the system to start a new game. any running game is counted as a loss
                           "api_key": API_KEY,
                       })
   # For safety some respose checking is done here
   if res.status_code != 200:
      print("Server gave a bad response, error code={}".format(res.status_code))
      exit()
   if not res.json()['status']:
      print("Server returned a bad status. Return message: ")
      print(res.json()['msg'])
      exit()
   return res

def check_stats():
   res = requests.post(SERVER_ADDRESS + "stats",
                       data={
                           "stil_id": STIL_ID,
                           "api_key": API_KEY,
                       })

   stats = res.json()
   return stats

"""
You can make your code work against this simple random agent
before playing against the server.
It returns a move 0-6 or -1 if it could not make a move.
To check your code for better performance, change this code to
use your own algorithm for selecting actions too
"""
def opponents_move(env):
   env.change_player() # change to oppoent
   avmoves = env.available_moves()
   if not avmoves:
      env.change_player() # change back to student before returning
      return -1

   # TODO: Optional? change this to select actions with your policy too
   # that way you get way more interesting games, and you can see if starting
   # is enough to guarrantee a win
   action = random.choice(list(avmoves))

   state, reward, done, _ = env.step(action)
   if done:
      if reward == 1: # reward is always in current players view
         reward = -1
   env.change_player() # change back to student before returning
   return state, reward, done

def student_move(): # My implementation of minimax searh with alpha beta pruning
   """
   TODO: Implement your min-max alpha-beta pruning algorithm here.
   Give it whatever input arguments you think are necessary
   (and change where it is called).
   The function should return a move from 0-6
   """
   envCopy.reset(board=env.board)
   bestMove =-1
   bestScore =-1000000
   
   for move in envCopy.available_moves():
      envCopy.reset(board=env.board)
      newBoard, result, done, _ = envCopy.step(move)
      if(done):
            if result ==1:
               return move
      score = miniMax(newBoard, 4, bestScore, 1000000, False)
      if(score > bestScore):
         bestScore = score
         bestMove = move
      
      print("Move: ", move, " Value: ", score, " The best move so far: ", bestMove)
   return bestMove

def miniMax(inBoard, depth, alpha, beta, maxPlayer): #Metoden returnerar värdet på hur bra en specifikt sekvens av drag 
   envCopy.reset(board=inBoard)
   if depth ==0 :
      return window_maker(inBoard)
   
   # Sortera moves i en lista innan de undersöks

   moves = list(envCopy.available_moves())

   if maxPlayer: # If players turn
      value = -1000000
      for move in moves:
         envCopy.reset(board=inBoard)
         newBoard, result, done, _ = envCopy.step(move)
         if(done):
            if result ==1:
               return 999999
         value = max(value, miniMax(newBoard, depth-1, alpha, beta, False))
         alpha = max(alpha, value) 
         if value >=beta:
            break
      return value
   else : # If opponents turn
      value = 1000000
      for move in moves:
         envCopy.reset(board=inBoard)
         envCopy.change_player()
         newBoard, result, done, _ = envCopy.step(move)
         if(done):
            if result ==1:
               return -999999
         value = min(value, miniMax(newBoard, depth-1, alpha, beta, True))
         beta = min(beta, value) 
         if alpha >=value:
            break
      return value

def window_maker(board):
   score =0
   center =[]
   cols =[]
   rows =[]
   diagonal_window =[]
   window =[]

   # Center
   for element in list(board[:, 3]):
      center.append(int(element))
   center_score = center.count(1)
   score += center_score *5

   # Rows
   for row in range(6):
      rows =[]
      for element in list(board[row,:]):
         rows.append(int(element))
      for col in range(4):
         window = rows[col:col+4]
         score += evaluation(window)

   # Collums
   for col in range(7):
      cols =[]
      for element in list(board[:,col]):
         cols.append(int(element))
      for row in range(3):
         window = cols[row: row+4]
         score += evaluation(window)


   # Diagonals
   for row in range(3):
      for col in range(4):
         window = []
         for i in range(4):
            window.append(board[row+i][col+i])
            diagonal_window.append(board[row+3-i][col+i])
         score += evaluation(window)
         score += evaluation(diagonal_window)

   return score

def evaluation(window): # Evaluates how good a move is based on the window containing the elements of a specifik 4 number sequence
   score =0
   
   if window.count(1) ==4 : # win
      score +=100000
   elif window.count(1) ==3 and window.count(0) ==1: # Close to win
      score +=50
   elif window.count(1) ==2 and window.count(0) ==2: # 50/50
      score +=25
   elif window.count(1) ==1 and window.count(0) ==3:
      score+=5

   if window.count(-1) ==4 : # Loss
      score +=100000
   elif window.count(-1) ==3 and window.count(0) ==1: # Close to loss
      score -=50
   elif window.count(-1) ==2 and window.count(0) ==2: # 50/50
      score -=25
   elif window.count(1) ==1 and window.count(0) ==3:
      score -=5

   if window.count(1) ==3 and window.count(-1) ==1: #We get intercepted and cant win on this particular move
      score -=30
   elif window.count(1) ==2 and window.count(-1) ==2: # Stalmate no one really beneftis
      score -=10
   elif window.count(1) ==2 and window.count(-1) ==1 and window.count(0) ==1: # Could be a good move depending on the position of -1
      score +=15
   elif window.count(1) ==1 and window.count(-1) ==2 and window.count(0) ==1: # Most likely not benefical
      score -=15

   return score

def play_game(vs_server = False):
   """
   The reward for a game is as follows. You get a
   botaction = random.choice(list(avmoves)) reward from the
   server after each move, but it is 0 while the game is running
   loss = -1
   win = +1
   draw = +0.5
   error = -10 (you get this if you try to play in a full column)
   Currently the player always makes the first move
   """

   wins =0
   ties =0
   loss =0
   #for _ in range(20):
   # default state
   state = np.zeros((6, 7), dtype=int)

   # setup new game
   if vs_server:
      # Start a new game
      res = call_server(-1) # -1 signals the system to start a new game. any running game is counted as a loss

      # This should tell you if you or the bot starts
      print(res.json()['msg'])
      botmove = res.json()['botmove']
      state = np.array(res.json()['state'])
      # reset env to state from the server (if you want to use it to keep track)
      env.reset(board=state)
   else:
      # reset game to starting state
      env.reset(board=None)
      # determine first player
      student_gets_move = random.choice([True, False])
      if student_gets_move:
         print('You start!')
         print()
      else:
         print('Bot starts!')
         print()

   # Print current gamestate
   print("Current state (1 are student discs, -1 are servers, 0 is empty): ")
   print(state)
   print()

   done = False
   while not done:
      # Select your move
      time_1 = time.time()
      stmove = student_move() # TODO: change input here
      time_2 = time.time()

      # make both student and bot/server moves
      if vs_server:
         # Send your move to server and get response
         res = call_server(stmove)
         print(res.json()['msg'])

         # Extract response values
         result = res.json()['result']
         botmove = res.json()['botmove']
         state = np.array(res.json()['state'])
         # reset env to state from the server (if you want to use it to keep track)
         env.reset(board=state)
      else:
         if student_gets_move:
            # Execute your move
            avmoves = env.available_moves()
            if stmove not in avmoves:
               print("You tied to make an illegal move! You have lost the game.")
               break
            state, result, done, _ = env.step(stmove)

         student_gets_move = True # student only skips move first turn if bot starts

         # print or render state here if you like

         # select and make a move for the opponent, returned reward from students view
         if not done:
            state, result, done = opponents_move(env)

      # Check if the game is over
      if result != 0:
         done = True
         if not vs_server:
            print("Game over. ", end="")
         if result == 1:
            print("You won!")
            wins +=1
         elif result == 0.5:
            print("It's a draw!")
            ties +=1
         elif result == -1:
            print("You lost!")
            loss+=1
         elif result == -10:
            print("You made an illegal move and have lost!")
            loss+=1
         else:
            print("Unexpected result result={}".format(result))
         if not vs_server:
            print("Final state (1 are student discs, -1 are servers, 0 is empty): ")
      else:
         print("Current state (1 are student discs, -1 are servers, 0 is empty): ")

      # Print current gamestate
      print(state)
      print("Move duration: ", time_2 - time_1)
      print()

   #print(wins, ties, loss)


   
   

def main():
   # Parse command line arguments
   parser = argparse.ArgumentParser()
   group = parser.add_mutually_exclusive_group()
   group.add_argument("-l", "--local", help = "Play locally", action="store_true")
   group.add_argument("-o", "--online", help = "Play online vs server", action="store_true")
   parser.add_argument("-s", "--stats", help = "Show your current online stats", action="store_true")
   args = parser.parse_args()

   # Print usage info if no arguments are given
   if len(sys.argv)==1:
      parser.print_help(sys.stderr)
      sys.exit(1)

   if args.local:
      play_game(vs_server = False)
   elif args.online:
      play_game(vs_server = True)

   if args.stats:
      stats = check_stats()
      print(stats)

   # TODO: Run program with "--online" when you are ready to play against the server
   # the results of your games there will be logged
   # you can check your stats bu running the program with "--stats"

if __name__ == "__main__":
    main()
