package com.inmemorymom;

import com.inmemorymom.producer.Producer;
import com.inmemorymom.consumer.Consumer;
import com.inmemorymom.queue.MessageQueue;

public class Main {
    public static void main(String[] args) {
        // Initialize the message queue
        MessageQueue messageQueue = new MessageQueue();

        // Create and start a consumer thread for topic1
        Consumer consumer1 = new Consumer(messageQueue, "topic1");
        Thread consumerThread1 = new Thread(consumer1);
        consumerThread1.start();

        // Create a producer
        Producer producer = new Producer(messageQueue);

        // Produce some messages to "topic1"
        for (int i = 1; i <= 12; i++) { // Trying to produce more than queue's capacity (10)
            producer.produce("topic1", "Message " + i);
            try {
                Thread.sleep(500); // Simulate a delay between producing messages
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Optionally, wait for the consumer thread to finish consuming
        try {
            Thread.sleep(10000);  // Wait for 10 seconds or you can use other mechanisms to stop the consumer
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
