package Es_1;

public class Main{
    public static void main(String args[]){
        Fork forks[] = new Fork[5];
        Philosopher phils[] = new Philosopher[5];
        //setto un max di 2 filosofi che possono mangiare in contemporanea con 5 forchette 
        Table t = new Table(2,5);

        for(int c=0; c<5; forks[c] = new Fork(c,t), c++);
        for(int c=0; c<5; c++)
            phils[c] = new Philosopher(c, forks[c], forks[(c+1)%5]);
        
        for(int c=0; c<5; phils[c].start(), c++);
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {}
        for(int c=0; c<5; phils[c].stopRequested(),c++);
    }
}
