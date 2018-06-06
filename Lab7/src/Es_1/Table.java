package Es_1;

public class Table {
	private int phils_number; //5
	private int forks_number;
	private int philosophers []; 
	private int forks[];
	
	public Table(int phils_number, int forks_number){ 
		this.setPhils_number(phils_number);
		this.philosophers = new int[phils_number];
		this.forks = new int[forks_number];
		
		for(int i=0;i<this.getPhils_number()-1; i++)
			this.philosophers[i] = 0; // 0 = non ha forchette
		for(int i=0;i<this.getForks_number()-1; i++)
			this.forks[i] = -1; // 0 = non ha forchette
	}
	
	public synchronized void setForkAndPhilosopher(int phil, int fork) {
		this.forks[fork] = phil;
		if (fork == 0 ) {
			if(phil == 0 && this.forks[fork+1] == -1)
				this.philosophers[1] = 1;
			if(phil == 4 && this.forks[4] == -1)
				this.philosophers[3] = 1;
		}
		else if (fork == 1 ) {
			if(phil == 1 && this.forks[fork+1] == -1)
				this.philosophers[2] = 1;
			else if(phil == 0 && this.forks[0] == -1)
				this.philosophers[4] = 1;
		}
		else if (fork == 2 ) {
			if(phil == 2 && this.forks[fork+1] == -1)
				this.philosophers[3] = 1;
			else if(phil == 1 && this.forks[fork-1] == -1)
				this.philosophers[0] = 1;
		}
		else if (fork == 3 ) {
			if(phil == 3 && this.forks[fork+1] == -1)
				this.philosophers[4] = 1;
			else if(phil == 2 && this.forks[fork-1] == -1)
				this.philosophers[1] = 1;
		}
		else if (fork == 4) {
			if(phil == 4 && this.forks[0] == -1)
				this.philosophers[0] = 1;
			else if(phil == 3 && this.forks[fork-1] == -1)
				this.philosophers[2] = 1;
		}	
	}

	public boolean checkIfCanEat(int index) {
		System.out.print("filosofo : "+index+ "  - è : "+this.philosophers[index]+" | ");
		if(this.philosophers[index] == 0)
			return true;
		else
			return false;
	}
	
	public synchronized void unlockForkAndPhilosopher(int phil,int fork) {
		this.forks[fork] = -1;
		if (fork == 0 ) {
			if(phil == 0 && this.forks[fork+1] == phil) {
				this.philosophers[0] = 0;
				this.forks[fork+1] = -1;
			}
			if(phil == 4 && this.forks[4] == phil) {
				this.philosophers[4] = 0;
				this.forks[4] = -1;
			}
		}
		else if (fork == 1 ) {
			if(phil == 1 && this.forks[fork+1] == phil) {
				this.philosophers[1] = 0;
				this.forks[fork+1] = -1;
			}
			if(phil == 0 && this.forks[0] == phil) {
				this.philosophers[0] = 0;
				this.forks[0] = -1;
			}
		}
		else if (fork == 2 ) {
			if(phil == 2 && this.forks[fork+1] == phil) {
				this.philosophers[2] = 0;
				this.forks[fork+1] = -1;
			}
			else if(phil == 1 && this.forks[fork-1] == phil) {
				this.philosophers[1] = 0;
				this.forks[fork-1] = -1;
			}
		}
		else if (fork == 3 ) {
			if(phil == 3 && this.forks[fork+1] == phil) {
				this.philosophers[3] = 0;
				this.forks[fork+1] = -1;
			}
			if(phil == 2 && this.forks[fork-1] == phil) {
				this.philosophers[2] = 0;
				this.forks[fork-1] = -1;
			}
		}
		else if (fork == 4) {
			if(phil == 4 && this.forks[0] == phil) {
				this.philosophers[4] = 0;
				this.forks[0] = -1;
			}
			else if(phil == 3 && this.forks[fork-1] == phil) {
				this.philosophers[3] = 0;
				this.forks[0] = -1;
			}
		}
	}
	
	public int getPhils_number() {
		return phils_number;
	}
	public void setPhils_number(int phils_number) {
		this.phils_number = phils_number;
	}

	public int getForks_number() {
		return forks_number;
	}

	public void setForks_number(int forks_number) {
		this.forks_number = forks_number;
	}
	
	
}