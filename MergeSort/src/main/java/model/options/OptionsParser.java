package model.options;

import java.util.Arrays;

public class OptionsParser {
    private static final char ASCENDING_ORDER = 'a';
    private static final char DESCENDING_ORDER = 'd';
    private static final char INTEGER_TYPE = 'i';
    private static final char STRING_TYPE = 's';

    public OptionsParser(String[] options) {
        this.options = options;
    }

    public Order getSortOrder() throws IllegalArgumentException {
        String arg = options[0];
        if (!verifyLegalArg(arg)) {
            throw new IllegalArgumentException();
        }
        switch (arg.charAt(1)) {
            case ASCENDING_ORDER:
                return Order.ASCENDING;
            case DESCENDING_ORDER:
                return Order.DESCENDING;
            default:
                return Order.ASCENDING;
        }
    }

    public DataType getDataType() throws IllegalArgumentException {
        String arg = options[getIdxArgAsDataType()];
        if (!verifyLegalArg(arg)) {
            throw new IllegalArgumentException();
        }
        switch (arg.charAt(1)) {
            case INTEGER_TYPE:
                return DataType.INTEGER;
            case STRING_TYPE:
                return DataType.STRING;
            default:
                throw new IllegalArgumentException();
        }
    }

    public String getNameOutputFile() {
        return options[getIdxArgAsDataType() + 1];
    }

    public String[] getNamesInputFiles() {
        int idx = getIdxArgAsDataType() + 2;
        return Arrays.copyOfRange(options, idx, options.length);
    }

    private int getIdxArgAsDataType() throws IllegalArgumentException {
        String arg = options[0];
        if (!verifyLegalArg(arg)) {
            throw new IllegalArgumentException();
        }
        switch (arg.charAt(1)) {
            case INTEGER_TYPE:
            case STRING_TYPE:
                return 0;
            default:
                return 1;
        }
    }

    private boolean verifyLegalArg(String arg) {
        return arg.length() == 2 && arg.charAt(0) == '-';
    }

    private final String[] options;
}
