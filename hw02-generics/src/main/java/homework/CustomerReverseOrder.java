package homework;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class CustomerReverseOrder {

    // todo: 2. надо реализовать методы этого класса
    // надо подобрать подходящую структуру данных, тогда решение будет в "две строчки"
    private final Deque<Customer> stack = new ArrayDeque<>();

    public void add(Customer customer) {
        stack.push(customer);
    }

    public Customer take() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException("No customers available");
        }
        return stack.pop();
    }
}
