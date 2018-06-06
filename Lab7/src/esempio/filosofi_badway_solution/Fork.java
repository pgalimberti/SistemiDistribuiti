package esempio.filosofi_badway_solution;

class Fork {
    private boolean taken=false;
    private int identity;
    Fork(int identity) {
        this.identity = identity;
    }
    synchronized void put() {
        taken=false;
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " released by philosopher " + p.getIdentity());
        notify();
    }
    synchronized void get() throws InterruptedException {
        while (taken)
            wait();
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " taken by philosopher " + p.getIdentity());
        taken=true;
    }
}
