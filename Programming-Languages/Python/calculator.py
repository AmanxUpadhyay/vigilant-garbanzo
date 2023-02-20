
# Console Based Calculator
print("Welcome to the Python Calculator")
print("Press Enter to Continue...")
print("--------------------------------->")
input()

# Main Loop
while True:
    # Clear Screen
    print("\033c")
    print("Enter 'q' to Quit\n")

    # Get Input
    first_number = input("Enter First Number: ")
    if first_number == 'q':
        break
    second_number = input("\nEnter Second Number: ")
    if second_number == 'q':
        break

    Operator = input("\nEnter Operator: ")
    if Operator == 'q':
        break

    # Calculate
    if Operator == '+':
        answer = int(first_number) + int(second_number)
    elif Operator == '-':
        answer = int(first_number) - int(second_number)
    elif Operator == '*':
        answer = int(first_number) * int(second_number)
    elif Operator == '/':
        answer = int(first_number) / int(second_number)
    else:
        print("Invalid Operator")
        continue

    # Print Answer
    print("--------------------------------->")
    print("Answer: " + str(answer))
    print("--------------------------------->")

    print("\nPress Enter to Continue...")
    input()