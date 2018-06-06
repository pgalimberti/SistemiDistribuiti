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
    	table.dropForkTaken_i(idPhil,this.identity);
        taken=false;
        notify();
    }
    synchronized void get(int idPhil) throws java.lang.InterruptedException {
    	//MIO EDIT
//    	if(table.getForkTaken_i(this.identity) > 0)
//    		wait();
//    	table.setForkTaken_i(this.identity);
    	//END MIO EDIT    	
        while (table.getForkTaken_i(idPhil, this.identity) > 0)
            wait();
        table.setForkTaken_i(idPhil,this.identity);
        taken=true;
        Philosopher p = (Philosopher)(Thread.currentThread());
        System.out.println("Fork " + identity + " taken by philosopher " + p.getIdentity());
    }
}
