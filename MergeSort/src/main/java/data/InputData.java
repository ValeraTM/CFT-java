package data;

import java.util.Scanner;

public abstract class InputData implements Comparable<InputData> {
    public abstract String next();
    public abstract boolean hasNext();

    protected Scanner scanner;
    protected String token;
}