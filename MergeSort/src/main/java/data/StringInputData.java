package data;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StringInputData extends InputData {
    public StringInputData(InputStream in) throws NoSuchElementException {
        scanner = new Scanner(in);
        token = scanner.next();
    }

    @Override
    public int compareTo(InputData other) {
        if (this == other) {
            return 0;
        }
        int cmp = this.token.compareTo(other.token);
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
        if (scanner.hasNext()) {
            token = scanner.next();
        }
        else {
            token = null;
        }
        return res;
    }
}
