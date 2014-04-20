package org.javaee7.movieplex7.batch;

import org.javaee7.movieplex7.entities.Sales;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;
import java.util.Properties;

/**
 * Created by benoit on 19/04/2014.
 */
@Named
@RequestScoped
public class SalesBean {

    @PersistenceUnit
    EntityManagerFactory emf;

    public void runJob() {
        try {
            JobOperator jo = BatchRuntime.getJobOperator();
            long jobId = jo.start("eod-sales", new Properties());
            System.out.println("Started job: with id: " + jobId);
        } catch (JobStartException ex) {
            ex.printStackTrace();
        }
    }

    public List<Sales> getSalesData() {
        return emf.
                createEntityManager().
                createNamedQuery("Sales.findAll", Sales.class).
                getResultList();
    }
}
