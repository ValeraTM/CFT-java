package data;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IntegerInputData extends InputData {
    public IntegerInputData(InputStream in) throws NoSuchElementException {
        scanner = new Scanner(in);
        token = getFirstInt();
        if (token == null) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int compareTo(InputData other) {
        if (this == other) {
            return 0;
        }
        int cmp = Integer.compare(Integer.parseInt(this.token), Integer.parseInt(other.token));
        if (cmp == 0) {
            return 1;
        }
        return cmp;
    }

    @Override
    public boolean hasNext() {
        return token != null;
    }

    @Override
    public String next() throws NoSuchElementException {
        if (token == null) {
            throw new NoSuchElementException();
        }
        String res = token;
        token = getFirstInt();
        return res;
    }

    private String getFirstInt() {
        String res = null;
        while (scanner.hasNext()) {
            try {
                res = Integer.toString(scanner.nextInt());
                break;
            }
            catch (InputMismatchException ex) {
                System.err.println("Unexpected token type. \""+ scanner.next() + "\" will be skipped");
            }
        }
        return res;
    }

    private Scanner scanner;
}
