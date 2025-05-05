package homework;

import java.util.*;

public class CustomerService {

    // todo: 3. надо реализовать методы этого класса
    // важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны
    private final Map<Customer, String> map = new HashMap<>();

    public Map.Entry<Customer, String> getSmallest() {
        // Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
        return map.entrySet().stream()
                .min(Comparator.comparingInt(e -> (int) e.getKey().getScores()))
                .map(this::cloneEntry)
                .orElseThrow(() -> new NoSuchElementException("No customers in service"));
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        int score = (int) customer.getScores();
        return map.entrySet().stream()
                .filter(e -> e.getKey().getScores() > score)
                .min(Comparator.comparingInt(e -> (int) e.getKey().getScores()))
                .map(this::cloneEntry)
                //                .map(e -> {
                //                    Customer orig = e.getKey();
                //                    Customer clone = new Customer(
                //                            orig.getId(),
                //                            orig.getName(),
                //                            originalScores.get(orig)
                //                      );
                //                      return new AbstractMap.SimpleEntry<>(clone, e.getValue());
                .orElse(null);
    }

    private Map.Entry<Customer, String> cloneEntry(Map.Entry<Customer, String> e) {
        Customer orig = e.getKey();
        Customer copy = new Customer(orig.getId(), orig.getName(), orig.getScores());
        return new AbstractMap.SimpleEntry<>(copy, e.getValue());
    }

    public void add(Customer customer, String data) {
        map.put(customer, data);
    }
}
