package model;

import data.InputData;
import model.options.DataType;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Factory {
    private final Properties creators = new Properties();

    public Factory() throws IOException {
        InputStream in = Factory.class.getClassLoader().getResourceAsStream("FactoryConfig.properties");
        if (in == null) {
            throw new IOException("FactoryConfig.properties not found");
        }
        creators.load(in);
    }

    public InputData createProductById(DataType id, InputStream input) throws ClassNotFoundException, InvocationTargetException {
        Class<?> product = Class.forName(creators.getProperty(id.toString()));
        try {
            return (InputData) product.getConstructor(InputStream.class).newInstance(input);
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
            System.exit(-1);
            return null;
        }
    }
}
