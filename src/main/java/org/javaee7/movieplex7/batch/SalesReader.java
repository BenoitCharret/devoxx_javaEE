package org.javaee7.movieplex7.batch;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.api.chunk.ItemReader;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Created by benoit on 19/04/2014.
 */
@Named
@Dependent
public class SalesReader extends AbstractItemReader {

    private BufferedReader reader;

    public void open(Serializable checkpoint) throws Exception {
        reader = new BufferedReader(
                new InputStreamReader(
                        Thread.currentThread()
                                .getContextClassLoader()
                                .getResourceAsStream("META-INF/sales.csv")));
    }

    @Override
    public Object readItem() throws Exception {
        String string = null;
        try {
            string = reader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return string;
    }
}
