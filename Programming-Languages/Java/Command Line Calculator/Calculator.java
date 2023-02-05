import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {
            try {
                System.out.print("Enter first number: ");
                double num1 = Double.parseDouble(input.next());
                System.out.print("Enter second number: ");
                double num2 = Double.parseDouble(input.next());
                System.out.print("Enter an operator (+, -, *, /): ");
                char operator = input.next().charAt(0);
                double result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        result = num1 / num2;
                        break;
                    default:
                        System.out.println("Invalid operator!");
                        continue;
                }
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Invalid input!");
            }
            System.out.print("Do you want to perform another calculation? (y/n): ");
            char choice = input.next().charAt(0);
            if (choice == 'n') {
                repeat = false;
            }
        }
        input.close();
    }
}
