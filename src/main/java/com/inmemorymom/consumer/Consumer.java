package com.inmemorymom.consumer;

import com.inmemorymom.model.Message;
import com.inmemorymom.queue.MessageQueue;

public class Consumer implements Runnable {
    private final MessageQueue messageQueue;
    private final String topic;

    public Consumer(MessageQueue messageQueue, String topic) {
        this.messageQueue = messageQueue;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            // Check if the queue is empty before consuming
            if (messageQueue.isQueueEmpty()) {
                System.out.println("Queue is empty. Consumer is waiting...");
                try {
                    Thread.sleep(1000); // Wait for 1 second before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                // Consume a message if available
                Message message = messageQueue.consume();
                if (message != null) {
                    System.out.println("Consumed from " + topic + ": " + message);
                }
            }
        }
    }
}
