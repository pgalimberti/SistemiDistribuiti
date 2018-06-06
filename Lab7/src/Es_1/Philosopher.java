package Es_1;
class Philosopher extends Thread {
    private int identity;
    private boolean stopRequested = false;
    private Fork left, right;
    private Table table;
    
    Philosopher(int id, Fork left, Fork right, Table t) {
        this.identity = id;
        this.left = left;
        this.right = right;
        this.table = t;
    }
    public void run() {
        while (!stopRequested) {
            try {
                //thinking
                sleep(50*Math.round(Math.random()));
                //hungry
                right.get(this.getIdentity()%2);
                sleep(50*Math.round(Math.random()));
                left.get(this.getIdentity()%2);
                //eating
                System.out.println("Philosopher " + identity + " eating...");
                sleep(50*Math.round(Math.random()));
                right.put(this.getIdentity()%2);
                left.put(this.getIdentity()%2);
            } catch (InterruptedException e) {

            }
        }
    }
    public void stopRequested() {
        stopRequested = true;
    }
    public int getIdentity(){
        return identity;
    }
}