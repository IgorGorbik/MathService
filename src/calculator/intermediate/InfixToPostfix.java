package calculator.intermediate;

import java.util.Stack;

/**
 *
 * @author Игорь
 */
public class InfixToPostfix {

    public static String convert(String expression) {
        
        boolean b = false;
        int j = -1;

        StringBuilder sb = new StringBuilder();

        Stack<Character> op = new Stack<>();

        char[] chars = expression.toCharArray();

        int N = chars.length;

        for (int i = 0; i < N; i++) {

            char ch = chars[i];

            if ((i-j)==1 && (ch == '-' || ch == '+')) {
                b = true;
            }
            
            if (b) {
                sb.append('0').append(' ');
                b = false;
            }

            if (Character.isDigit(ch) || ch == '.') {
                while ((i < N) && (Character.isDigit(chars[i]) || chars[i] == '.')) {
                    sb.append(chars[i++]);
                }
                i--;
                sb.append(' ');
            } else if (ch == '(') {
                j = i;
                op.push(ch);
            } else if (ch == ')') {
                while (op.peek() != '(') {
                    sb.append(op.pop()).append(' ');
                }
                op.pop();
            } else if (isOperator(ch)) {
                while (!op.isEmpty() && getPriority(op.peek()) >= getPriority(ch)) {
                    sb.append(op.pop()).append(' ');
                }
                op.push(ch);
            }
        }

        while (!op.isEmpty()) {
            sb.append(op.pop()).append(' ');
        }

        return sb.toString();
    }

    private static boolean isOperator(char ch) {
        return ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    private static int getPriority(char operator) {
        switch (operator) {
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return 0;
    }

}
