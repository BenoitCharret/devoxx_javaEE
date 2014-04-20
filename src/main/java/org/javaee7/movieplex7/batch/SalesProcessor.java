package org.javaee7.movieplex7.batch;

import org.javaee7.movieplex7.entities.Sales;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.util.StringTokenizer;

/**
 * Created by benoit on 19/04/2014.
 */
@Named
@Dependent
public class SalesProcessor implements ItemProcessor{
    @Override
    public Sales processItem(Object s) {
        Sales sales = new Sales();
        StringTokenizer tokens = new StringTokenizer((String)s, ",");
        sales.setId(Integer.parseInt(tokens.nextToken()));
        sales.setAmount(Float.parseFloat(tokens.nextToken()));

        return sales;
    }
}
