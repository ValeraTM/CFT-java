package model;

import data.InputData;
import model.options.DataType;
import model.options.Order;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class MergeSort {
    public MergeSort(List<InputStream> in, Order order, DataType dataType) throws IOException {
        Factory factory = new Factory();

        set = new TreeSet<>(new MergeComparator(order));
        for (InputStream it : in) {
            try {
                set.add(factory.createProductById(dataType, it));
            }
            catch (ClassNotFoundException ex) {
                System.err.println("Type: \"" + dataType.toString() + "\" not found");
                System.exit(-1);
            }
            catch (InvocationTargetException e) {
                try {
                    it.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public boolean hasNextToken() {
        return !set.isEmpty();
    }

    public String nextToken() throws NoSuchElementException {
        InputData it = set.pollFirst();
        if (it == null) {
            throw new NoSuchElementException();
        }

        String token = it.next();
        if (it.hasNext()) {
            set.add(it);
        }
        return token;
    }

    private TreeSet<InputData> set;
}