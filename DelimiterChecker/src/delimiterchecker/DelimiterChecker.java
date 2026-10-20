/* Description: Checks if (), [] and {} in a sequence are matched and nested
   correctly. It uses a stack.
 
   Programmed by: <Arjhiey Catalan> <BSIT II> <48160> <CC104>
 
   Last Modified: October 20, 2026
 
   Version: 1.0
 
   Acknowledgements: Claude (Anthropic) - used to help draft the program.s
*/
package delimiterchecker;

import java.util.Scanner;
import java.util.Stack;

public class DelimiterChecker {

    static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    static boolean matches(char open, char close) {
        return (open == '(' && close == ')')
            || (open == '[' && close == ']')
            || (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = sc.hasNextLine() ? sc.nextLine() : "";

        Stack<Character> stack = new Stack<>();
        String reason = "";

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    reason = "Closing delimiter " + c + " has no matching opening delimiter.";
                    break;
                }
                char top = stack.peek();
                if (matches(top, c)) {
                    stack.pop();
                } else {
                    reason = "Mismatched delimiters: " + c + " does not match top of stack " + top + ".";
                    break;
                }
            }
            // other characters (a, +, 1 ...) are ignored
        }

        if (reason.isEmpty() && !stack.isEmpty()) {
            reason = "Unmatched opening delimiter remains: " + stack;
        }

        if (reason.isEmpty()) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
            System.out.println("Reason: " + reason);
        }
    }
}

