package calculator.executors;

import java.util.Stack;

/**
 *
 * @author Игорь
 */
public class Executor {

    public static String execute(String expression) throws ArithmeticException {

        Stack<String> op = new Stack<>();

        char[] chars = expression.toCharArray();

        int N = chars.length;

        for (int i = 0; i < N; i++) {

            char ch = chars[i];

            if (Character.isDigit(ch) || ch == '.') {
                StringBuilder sb = new StringBuilder();
                while ((i < N) && (Character.isDigit(chars[i]) || chars[i] == '.')) {
                    sb.append(chars[i++]);
                }
                op.push(sb.toString());
            } else if (isOperator(ch)) {
                Double temp2 = Double.valueOf(op.pop());
                Double temp1 = Double.valueOf(op.pop());
                double tempResult = 0;
                switch (ch) {
                    case '+': {
                        tempResult = temp1 + temp2;
                        break;
                    }
                    case '-': {
                        tempResult = temp1 - temp2;
                        break;
                    }
                    case '*': {
                        tempResult = temp1 * temp2;
                        break;
                    }
                    case '/': {
                        if(temp2 == 0) {
                            throw new ArithmeticException();
                        }
                        tempResult = temp1 / temp2;
                        break;
                    }
                    case '^': {
                        tempResult = Math.pow(temp1, temp2);
                        break;
                    }
                    default:
                        break;
                }
                op.push(tempResult + "");
            }
        }
        return op.pop();
    }

    private static boolean isOperator(char ch) {
        return ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }
}
