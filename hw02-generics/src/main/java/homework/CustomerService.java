package homework;

import java.util.*;

public class CustomerService {

    private final NavigableMap<Long, Map.Entry<Customer, String>> sorted = new TreeMap<>();

    public void add(Customer customer, String data) {
        Customer customer1 = new Customer(customer.getId(), customer.getName(), customer.getScores());
        sorted.put(customer.getScores(), new AbstractMap.SimpleEntry<>(customer1, data));
    }

    public Map.Entry<Customer, String> getSmallest() {
        return Optional.ofNullable(sorted.firstEntry())
                .map(Map.Entry::getValue)
                .map(this::makeCopy)
                .orElse(null);
    }

    public Map.Entry<Customer, String> getNext(Customer key) {
        return Optional.ofNullable(sorted.higherEntry(key.getScores()))
                .map(Map.Entry::getValue)
                .map(this::makeCopy)
                .orElse(null);
    }

    private Map.Entry<Customer, String> makeCopy(Map.Entry<Customer, String> e) {
        Customer stored = e.getKey();
        Customer copy = new Customer(stored.getId(), stored.getName(), stored.getScores());
        return new AbstractMap.SimpleEntry<>(copy, e.getValue());
    }
}
