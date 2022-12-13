package stackcalculator;

import java.util.LinkedList;

// Stack calculator class creates LinkedList of doubles and performs all functions on private LinkedList.
public class StackCalculator {

    private LinkedList<Double> stack = new LinkedList<>();

    public void push(double x) {
        stack.addFirst(x);
    }

    // I was confused what you meant by this function since in the pdf it says it returns a double
    // but what I thought you meant is for it to remove the first element from the stack so I set
    // it to void.
    public void pop() {
        stack.removeFirst();
    }

    public void add() {
        double num1 = stack.removeFirst();
        double num2 = stack.removeFirst();
        double newValue = num1 + num2;
        stack.addFirst(newValue);
    }

    public void subtract() {
        double num1 = stack.removeFirst();
        double num2 = stack.removeFirst();
        double newValue = num1 - num2;
        stack.addFirst(newValue);
    }

    public void multiply() {
        double num1 = stack.removeFirst();
        double num2 = stack.removeFirst();
        double newValue = num1 * num2;
        stack.addFirst(newValue);
    }

    public void divide() {
        double num1 = stack.removeFirst();
        double num2 = stack.removeFirst();
        double newValue = num1 / num2;
        stack.addFirst(newValue);
    }

    public void clear() {
        stack.clear();
    }

    public double[] getValues() {
        int arraySize = stack.size();
        double[] stackArray = new double[arraySize];
        for (int i = 0; i < stack.size(); i++) {
            stackArray[i] = stack.get(i);
        }
        return stackArray;
    }

    public int size() {
        return stack.size();
    }

    public boolean isStackEmpty() {
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

}
