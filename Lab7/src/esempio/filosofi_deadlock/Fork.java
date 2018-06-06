package esempio.filosofi_deadlock;

class Fork {
    private boolean taken=false;
    private int identity;
    Fork(int identity) {
        this.identity = identity;
    }
    synchronized void put() {
        taken=false;
        notify();
    }
    synchronized void get() throws java.lang.InterruptedException {
        while (taken)
            wait();
        taken=true;
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " taken by philosopher " + p.getIdentity());
    }
}
