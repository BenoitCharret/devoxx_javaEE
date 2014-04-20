package org.javaee7.movieplex7.points;

import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by benoit on 20/04/2014.
 */
@Named
@RequestScoped
public class SendPointsBean {

    @NotNull
    @Pattern(regexp = "^\\d{2},\\d{2}",
            message = "Message format must be 2 digits, comma, 2 digits, e.g.12,12")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Inject
    JMSContext context;

    @Resource(lookup = "java:global/jms/pointsQueue")
    Queue pointsQueue;

    public void sendMessage() {
        System.out.println("Sending message: " + message);
        context.createProducer().send(pointsQueue, message);
    }

}
