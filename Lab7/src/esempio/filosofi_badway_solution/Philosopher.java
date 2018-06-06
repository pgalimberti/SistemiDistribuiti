package esempio.filosofi_badway_solution;

class Philosopher extends Thread {
    private int identity;
    private boolean stopRequested = false;
    private Fork left, right;

    Philosopher(int id, Fork left, Fork right) {
        this.identity = id;
        this.left = left;
        this.right = right;
    }
    public void run() {
        while (!stopRequested) {
            try {
                //thinking
                sleep(50*Math.round(Math.random()));
                //hungry
                if (identity != 4) {
                    left.get();
                    sleep(50*Math.round(Math.random()));
                    right.get();
                } else {
                    right.get();
                    sleep(50*Math.round(Math.random()));
                    left.get();
                }
                //eating
                System.out.println("Philosopher " + identity + " eating...");
                sleep(50*Math.round(Math.random()));
                right.put();
                left.put();
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