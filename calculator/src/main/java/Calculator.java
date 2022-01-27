import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

public class Calculator {

    public double getSolution(String expression) {
        String replacedExpression = expression.replaceAll("\\s","");
        double result;
        if (hasAllowedChars(replacedExpression) && checkBrackets(replacedExpression) && checkOperators(replacedExpression)) {
            String preparedExpression = getPreparedExpressionFrom(replacedExpression);
            List<String> list = getReversePolishNotationFrom(preparedExpression);
            result = convertReversePolishNotationToNumber(list);
        } else {
            throw new IllegalArgumentException("Cannot calculate expression");
        }
        return result;
    }

    private boolean hasAllowedChars(String expression) {
        for (int i = 0; i < expression.length(); i++) {
            if (getPriorityOperation(expression.charAt(i)) == Argument.INVALID) {
                return false;
            }
        }
        return true;
    }

    private boolean checkBrackets(String expression) {
        int countOpeningBrackets = 0;
        int countClosingBrackets = 0;
        int sumIndexesOfOpeningBrackets = 0;
        int sumIndexesOfClosingBrackets = 0;

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                countOpeningBrackets++;
                sumIndexesOfOpeningBrackets = sumIndexesOfOpeningBrackets + i;
            }
            if (expression.charAt(i) == ')') {
                countClosingBrackets++;
                sumIndexesOfClosingBrackets = sumIndexesOfClosingBrackets + i;
            }
            if (i != expression.length() - 1 && expression.charAt(i) == '(' && expression.charAt(i + 1) == ')') {
                return false;
            }
        }

        if (countOpeningBrackets == 0 && countClosingBrackets == 0) {
            return true;
        }
        if (countOpeningBrackets == countClosingBrackets) {
            return sumIndexesOfOpeningBrackets < sumIndexesOfClosingBrackets;
        }
        return false;
    }

    private boolean checkOperators(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            if (getPriorityOperation(expression.charAt(i)) == Argument.NUMBER && getPriorityOperation(expression.charAt(i + 1)) == Argument.CLOSE_BRACKET) {
                continue;
            }
            if (getPriorityOperation(expression.charAt(i)) == Argument.NUMBER && getPriorityOperation(expression.charAt(i + 1)) == Argument.OPEN_BRACKET) {
                return false;
            }
        }
        return true;
    }

    private String getPreparedExpressionFrom(String expression) {
        StringBuilder preparedExpression = new StringBuilder();
        if (expression.length() == 1) {
            preparedExpression.append("0").append("+");
            preparedExpression.append(expression.charAt(0));
            return preparedExpression.toString();
        }

        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-') {
                if (i == 0) {
                    preparedExpression.append('0');
                } else if (expression.charAt(i - 1) == '(') {
                    preparedExpression.append('0');
                }
            }

            if (expression.charAt(i) == '-' && i > 0) {
                if (expression.charAt(i - 1) == '/' || expression.charAt(i - 1) == '*') {
                    preparedExpression.append('(').append('0');
                    preparedExpression.append(expression.charAt(i));
                    i++;
                    while (isNumber(Character.toString(expression.charAt(i))) || expression.charAt(i) == '.') {
                        preparedExpression.append(expression.charAt(i));
                        i = i + 1;
                        if (i == expression.length()) {
                            break;
                        }
                    }
                    preparedExpression.append(')');
                    if (i == expression.length()) {
                        return preparedExpression.toString();
                    }
                }
            }
            preparedExpression.append(expression.charAt(i));
        }
        return preparedExpression.toString();
    }

    private int addArgumentsToReversePolishNotation(Stack<Character> stack, List<String> arguments, String preparedExpression, int index) {
        Argument operationPriority = getPriorityOperation(preparedExpression.charAt(index));

        switch (operationPriority) {
            case NUMBER:
                int start = index;
                do {
                    index++;
                }
                while (index < preparedExpression.length() && getPriorityOperation(preparedExpression.charAt(index)) == Argument.NUMBER);
                String number = preparedExpression.substring(start, index);
                if (!isNumber(number)) {
                    throw new IllegalArgumentException("Number " + number + " is not number");
                }
                arguments.add(number);
                return index;
            case OPEN_BRACKET:
                stack.push(preparedExpression.charAt(index));
                break;
            case CLOSE_BRACKET:
                while (!stack.isEmpty()) {
                    switch (getPriorityOperation(stack.peek())) {
                        case OPEN_BRACKET:
                            stack.pop();
                            return index + 1;
                        default:
                            arguments.add(stack.pop().toString());
                            break;
                    }
                }
                break;
            case PLUS_MINUS:
                while (!stack.isEmpty()) {
                    switch (getPriorityOperation(stack.peek())) {
                        case PLUS_MINUS:
                        case MULTIPLY_DIVIDE:
                            arguments.add(stack.pop().toString());
                            break;
                        default:
                            stack.push(preparedExpression.charAt(index));
                            return index + 1;
                    }
                }
                stack.push(preparedExpression.charAt(index));
                break;
            case MULTIPLY_DIVIDE:
                while (!stack.isEmpty()) {
                    switch (getPriorityOperation(stack.peek())) {
                        case MULTIPLY_DIVIDE:
                            arguments.add(stack.pop().toString());
                            break;
                        default:
                            stack.push(preparedExpression.charAt(index));
                            return index + 1;
                    }
                }
                stack.push(preparedExpression.charAt(index));
                break;
            default:
                throw new IllegalStateException("invalid character: " + preparedExpression.charAt(index));
        }
        return index + 1;
    }

    private List<String> getReversePolishNotationFrom(String preparedExpression) {
        List<String> arguments = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < preparedExpression.length(); ) {
            i = addArgumentsToReversePolishNotation(stack, arguments, preparedExpression, i);
        }
        while (!stack.isEmpty()) {
            arguments.add(stack.pop().toString());
        }
        return arguments;
    }

    private void runArithmeticOperations(Stack<Double> stack, char operation) {
        Argument arg = getPriorityOperation(operation);
        if (arg == Argument.PLUS_MINUS || arg == Argument.MULTIPLY_DIVIDE) {
            if (stack.size() < 2) {
                throw new IllegalStateException("Cannot calculate expression");
            }

            double a = stack.pop();
            double b = stack.pop();

            switch (operation) {
                case '+':
                    stack.push(b + a);
                    break;
                case '-':
                    stack.push(b - a);
                    break;
                case '/':
                    stack.push(b / a);
                    break;
                case '*':
                    stack.push(b * a);
                    break;
            }
        }
    }

    private double convertReversePolishNotationToNumber(List<String> arguments) {
        Stack<Double> numbers = new Stack<>();

        for (int i = 0; i < arguments.size(); i++) {
            while (isNumber(arguments.get(i))) {
                numbers.push(Double.parseDouble(String.valueOf(arguments.get(i++))));
                if (i == arguments.size()) {
                    break;
                }
            }
            runArithmeticOperations(numbers, arguments.get(i).charAt(0));
        }
        if (numbers.size() > 1) {
            throw new IllegalStateException("stack has more than 1 value: " + numbers.toString());
        }
        return numbers.pop();
    }

    private boolean isNumber(String s) {
        Pattern number = Pattern.compile("^[0-9]+(\\.[0-9]+)?$");
        return number.matcher(s).matches();
    }

    private Argument getPriorityOperation(char operator) {
        if (operator == '*' || operator == '/') {
            return Argument.MULTIPLY_DIVIDE;
        }
        if (operator == '+' || operator == '-') {
            return Argument.PLUS_MINUS;
        }
        if (operator == '(') {
            return Argument.OPEN_BRACKET;
        }
        if (operator == ')') {
            return Argument.CLOSE_BRACKET;
        }
        if (operator >= '0' && operator <= '9' || operator == '.') {
            return Argument.NUMBER;
        } else {
            return Argument.INVALID;
        }
    }
}