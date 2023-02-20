

# Creating a game named "Kaun Banega Crorepati" in Python without using Object Oriented Programming.
import random

questions = [
    "What is the capital of India?",
    "What is the capital of Pakistan?",
    "What is the capital of Bangladesh?",
    "What is the capital of Nepal?",
    "What is the capital of Sri Lanka?",
    "What is the capital of Bhutan?",
    "What is the capital of Maldives?",
    "What is the capital of Afghanistan?",
    "What is the capital of China?",
    "What is the capital of Japan?"
    ]

answers = [
    "Delhi", "Islamabad","Dhaka", "Kathmandu",
    "Colombo", "Thimphu", "Male", "Kabul", "Beijing", "Tokyo"]

# Menu for the game.
print("Welcome to Kaun Banega Crorepati!")
print("You will be asked 10 questions. For each correct answer, you will be awarded Rs. 10,000.")
print("If you answer a question incorrectly, you will lose the game.\n")
print("You can quit the game at any time by typing 'quit'.")
print("Let's begin!\n")

print("Press Enter to continue...")
input()

# The main game loop.
reward = 0
for i in range(10):
    # Clear the Terminal.
    print("\033c")
    print("\n--------------------------------------------------->")

    print("Question " + str(i + 1) + ": " + questions[i])

    # Randomizing the order of the answers from the correct answer to next 3 answers.
    random_answers = [answers[i]]
    for j in range(3):
        random_answers.append(answers[(i + j + 1) % 10])
    random.shuffle(random_answers)
    for j in range(4):
        print(str(j + 1) + ". " + random_answers[j])

    # Getting the user's answer.
    answer = input("Your answer: ")
    if answer == "quit":
        print("Thank you for playing Kaun Banega Crorepati!")
        break
    # Checking if the user's answer is correct.
    elif answer == answers[i]:
        reward += 10000
        print("Correct! Your current reward is Rs. " + str(reward) + ".")
    else:
        print("Incorrect! You have lost the game. Your final reward is Rs. " + str(reward) + ".")
        break

    print("\nPress Enter to continue...")
    input()

# The end of the game.
print("\n--------------------------------------------------->")
if reward == 100000:
    print("Congratulations! You have won Rs. 1,00,000!")
