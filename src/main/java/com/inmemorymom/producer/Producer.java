package com.inmemorymom.producer;

import com.inmemorymom.model.Message;
import com.inmemorymom.queue.MessageQueue;

public class Producer {
    private final MessageQueue messageQueue;

    public Producer(MessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    // Method to produce a message and add to the queue
    public void produce(String topic, String payload) {
        Message message = new Message(topic, payload);
        messageQueue.publish(message);
        System.out.println("Produced: " + message);
    }
}
