/* Description: This program checks if the (), [] and {} in a sequence are
   matched and nested correctly. It uses a stack and shows each step.

Programmed by: <Arjhiey Catalan> <BSIT II> <48160> <CC104>
 
   Last Modified: October 20, 2026
 
   Version: 1.0
 
   Acknowledgements: Claude (Anthropic) - used to help draft the program.
*/
package delimitercheckertrace;

import java.util.Scanner;
import java.util.Stack;

public class DelimiterCheckerTrace {

    // prints the stack from bottom to top
    static String showStack(Stack<Character> stack) {
        if (stack.isEmpty()) {
            return "empty";
        }
        String text = "";
        for (char c : stack) {
            text = text + c + " ";
        }
        return text.trim();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter a sequence: ");
        String input = scan.nextLine();
        System.out.println("Input: " + input);
        System.out.println();

        String opening = "([{";
        String closing = ")]}";
        Stack<Character> stack = new Stack<>();
        String reason = "";

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (opening.indexOf(ch) != -1) {
                // opening delimiter, so push it
                System.out.println("Read: " + ch);
                stack.push(ch);
                System.out.println("Operation: PUSH " + ch);
                System.out.println("Stack: " + showStack(stack));
                System.out.println();
            }
            else if (closing.indexOf(ch) != -1) {
                // closing delimiter, so check the top of the stack
                System.out.println("Read: " + ch);

                if (stack.isEmpty()) {
                    reason = "Closing delimiter " + ch + " has no matching opening delimiter.";
                    break;
                }

                char top = stack.peek();
                System.out.println("Operation: PEEK -> " + top);

                if (opening.indexOf(top) == closing.indexOf(ch)) {
                    stack.pop();
                    System.out.println("Operation: POP " + top);
                    System.out.println("Stack: " + showStack(stack));
                    System.out.println();
                }
                else {
                    reason = "Mismatched delimiters: " + ch + " does not match " + top + ".";
                    break;
                }
            }
            // any other character (like a, b, +) is skipped
        }

        // if no error yet but the stack still has items
        if (reason.equals("") && !stack.isEmpty()) {
            reason = "Unmatched opening delimiter remains: " + showStack(stack);
        }

        if (reason.equals("")) {
            System.out.println("Result: Valid");
        } else {
            System.out.println("Result: Invalid");
            System.out.println("Reason: " + reason);
        }
    }
}