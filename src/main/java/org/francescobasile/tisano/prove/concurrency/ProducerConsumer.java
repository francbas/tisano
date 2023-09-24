package org.francescobasile.tisano.prove.concurrency;

import java.util.Random;

public class ProducerConsumer {

    public static class SharedObject {

        private String sharedRisorsa;
        private STATUS status;

        public static enum STATUS {
            BUFFER_EMPTY,
            BUFFER_FULL,
            END_PROGRAM,
        }

        public SharedObject() {
            this(null, STATUS.BUFFER_EMPTY);
        }

        public SharedObject(String sharedRisorsa, STATUS status) {
            this.sharedRisorsa = sharedRisorsa;
            this.status = status;
        }

        public synchronized String consume() {
            while (status == STATUS.BUFFER_EMPTY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            status = STATUS.BUFFER_EMPTY;
            notifyAll();
            return sharedRisorsa;
        }

        public synchronized void produce(String msg) {
            while (status == STATUS.BUFFER_FULL) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            status = STATUS.BUFFER_FULL;
            this.sharedRisorsa = msg;
            notifyAll();
        }

        public STATUS getStatus() {
            return status;
        }

        public void setStatus(STATUS status) {
            this.status = status;
        }
    }

    public static class Producer implements Runnable {
        private SharedObject sharedObject;

        public Producer(SharedObject sharedObject) {
            this.sharedObject = sharedObject;
        }

        @Override
        public void run() {
            Random rnd = new Random();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": Producing Message!!");
                this.sharedObject.produce("Messaggio dal thread " + Thread.currentThread().getName() + " ID: " + rnd.nextInt(100));
                try {
                    System.out.println(Thread.currentThread().getName() + ": going to sleep!!");
                    Thread.sleep(rnd.nextInt(5000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.sharedObject.produce("-------Fine esecuzione!-------");
            this.sharedObject.setStatus(SharedObject.STATUS.END_PROGRAM);
        }
    }

    public static class Consumer implements Runnable {
        private SharedObject sharedObject;

        public Consumer(SharedObject sharedObject) {
            this.sharedObject = sharedObject;
        }

        @Override
        public void run() {
            Random rnd = new Random();
            while (this.sharedObject.getStatus() != SharedObject.STATUS.END_PROGRAM) {
                System.out.println(Thread.currentThread().getName() + ": Consuming Message!!");
                String msg;
                msg = this.sharedObject.consume();
                System.out.println(Thread.currentThread().getName() + ": " + msg);
                try {
                    System.out.println(Thread.currentThread().getName() + ": going to sleep!!");
                    Thread.sleep(rnd.nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        SharedObject sharedObject = new SharedObject();
        Thread tp = new Thread(new Producer(sharedObject));
        Thread tc = new Thread(new Consumer(sharedObject));
        tp.setName("TH-PRODUCER");
        tc.setName("TH-CONSUMER");
        tc.start();
        tp.start();
        try {
            tp.join();
            tc.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
