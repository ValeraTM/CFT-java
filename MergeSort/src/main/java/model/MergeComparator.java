package model;

import data.InputData;
import model.options.Order;

import java.util.Comparator;

public class MergeComparator implements Comparator<InputData> {
    public MergeComparator(Order order) {
        this.order = order;
    }

    @Override
    public int compare(InputData it1, InputData it2) {
        switch (order) {
            case DESCENDING:
                return -1*it1.compareTo(it2);
            case ASCENDING:
                return it1.compareTo(it2);
            default:
                return it1.compareTo(it2);
        }
    }

    private final Order order;
}
