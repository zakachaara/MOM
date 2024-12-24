package com.inmemorymom.queue;

import com.inmemorymom.model.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {
    private final BlockingQueue<Message> queue = new LinkedBlockingQueue<>(10); // Set capacity to 10

    // Publish a message to the queue (producer will wait if the queue is full)
    public void publish(Message message) {
        try {
            queue.put(message);  // Blocks if the queue is full
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Consume a message from the queue (consumer will block if the queue is empty)
    public Message consume() {
        try {
            return queue.take();  // Blocks until a message is available
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    // Check if the queue is empty (consumer logic)
    public boolean isQueueEmpty() {
        return queue.isEmpty();
    }
}
