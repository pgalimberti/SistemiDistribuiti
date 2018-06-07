package Es_1;

class Fork {
    private boolean taken=false;
    private int identity;
    private Table table;
    
    Fork(int identity, Table t) {
        this.identity = identity;
        this.table = t;
    }
    synchronized void put(int idPhil) {
    	table.unlockForkAndPhilosopher(idPhil,this.identity, this); //corrisponde al vecchio "taken"
        taken=false;
        notify();
    }
    synchronized void get(int idPhil) throws java.lang.InterruptedException {        
    	while (taken) //corrisponde al vecchio "taken"
            wait();
    	table.setWhoCanEat(idPhil, this.identity, this);
        taken=true;
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " taken by philosopher " + p.getIdentity());
    }
}
