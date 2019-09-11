import model.MergeSort;
import model.options.DataType;
import model.options.Order;

import java.io.*;
import java.util.*;

public class Controller {
    public Controller(Order order, DataType dataType, OutputStream out, List<InputStream> in) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            MergeSort sort = new MergeSort(in, order, dataType);
            while (sort.hasNextToken()) {
                writer.write(sort.nextToken() + '\n');
                writer.flush();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            for (InputStream it : in) {
                try {
                    it.close();
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}