package org.francescobasile.tisano.prove.concurrency;

public class SimpleThread {

    static void threadMessage(String msg) {
        String threadName = Thread.currentThread().getName();
        System.out.println("threadName: " + threadName + " --- msg: " + msg);
    }

    private static class MessaggeLooper implements Runnable {
        @Override
        public void run() {
            String[] info = {
                    "messaggio 01",
                    "messaggio 02",
                    "messaggio 03",
                    "messaggio 04",
            };
            try {
                for (int i = 0; i < info.length; i++) {
                    Thread.sleep(4000);
                    threadMessage(info[i]);
                }
            } catch (InterruptedException e) {
                threadMessage(" --- Attenzione -----Interrotto prima di aver finito!!!!");
//                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long pazienza = 1000 * 10;

        threadMessage("Avvio Thread");
        Thread t1 = new Thread(new MessaggeLooper());
//        Thread t2 = new Thread(new MessaggeLooper());
//        t2.start();
        long startTime = System.currentTimeMillis();
        t1.start();
        threadMessage("Waiting MessageLooper ... ");
        while (t1.isAlive()) {
            threadMessage("... Still Waiting MessageLooper to finish ... ");
            t1.join(1000);
            if (((System.currentTimeMillis() - startTime) > pazienza) && t1.isAlive()) {
                threadMessage("... max time out raggiunto ... ");
                t1.interrupt();
                t1.join();
            }
        }
//        t1.join();
//        t2.join();
        threadMessage("Termine programma!!");
    }
}
