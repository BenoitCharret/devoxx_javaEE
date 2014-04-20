package org.javaee7.movieplex7.points;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.*;
import java.util.Enumeration;

/**
 * Created by benoit on 20/04/2014.
 */
@JMSDestinationDefinition(name = "java:global/jms/pointsQueue",
        interfaceName = "javax.jms.Queue")
@Named
@RequestScoped
public class ReceivePointsBean {
    @Inject
    JMSContext context;

    @Resource(lookup="java:global/jms/pointsQueue")
    Queue pointsQueue;

    public String receiveMessage() {
        try (JMSConsumer consumer = context.createConsumer(pointsQueue)) {
            String message = consumer.receiveBody(String.class);
            System.out.println("Received message: " + message);
            return message;
        }
    }

    public int getQueueSize() {
        int count = 0;
        try {
            QueueBrowser browser = context.createBrowser(pointsQueue);
            Enumeration elems = browser.getEnumeration();
            while (elems.hasMoreElements()) {
                elems.nextElement();
                count++;
            }
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
        return count;
    }
}
