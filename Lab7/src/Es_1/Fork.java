package Es_1;

class Fork {
    //private boolean taken=false;
    private int identity;
    private Table table;
    
    Fork(int identity, Table t) {
        this.identity = identity;
        this.table = t;
    }
    synchronized void put(int idPhil) {
    	table.dropForkTaken_i(idPhil,this.identity); //corrisponde al vecchio "taken"
       // taken=false;
        notify();
    }
    synchronized void get(int idPhil) throws java.lang.InterruptedException {
    	//finchè il filosofo X ha la forchetta Y , aspetta.
    	
        while (table.getForkTaken_i(idPhil, this.identity) > 0) //corrisponde al vecchio "taken"
            wait();
        table.setForkTaken_i(idPhil,this.identity); //set taken
        //taken=true;
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " taken by philosopher " + p.getIdentity());
    }
}
