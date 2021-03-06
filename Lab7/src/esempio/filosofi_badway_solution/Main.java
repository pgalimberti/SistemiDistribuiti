package esempio.filosofi_badway_solution;

public class Main{
    public static void main(String args[]){
        Fork forks[] = new Fork[5];
        Philosopher phils[] = new Philosopher[5];
        for(int c=0; c<5; forks[c] = new Fork(c), c++);
        for(int c=0; c<5; c++)
            phils[c] = new Philosopher(c, forks[c], forks[(c+1)%5]);
        for(int c=0; c<5; phils[c].start(), c++);
        try {
            Thread.sleep(50);
        }
        catch (InterruptedException e) {}
        for(int c=0; c<5; phils[c].stopRequested(),c++);
    }
}
