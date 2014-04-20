package org.javaee7.movieplex7.batch;

import org.javaee7.movieplex7.entities.Sales;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by benoit on 19/04/2014.
 */
@Named
@Dependent
public class SalesWriter extends AbstractItemWriter {
    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void writeItems(List items) throws Exception {
        for (Sales s : (List<Sales>)items) {
            em.persist(s);
        }
    }
}
