package Es_1;

public class Table {
	private int maxFork;
	private int maxForkOnTable;
	private int maxPhilsCanEatAtSameTime;
	private int forkTaken[][];
	
	public Table(int maxPhilsCanEatAtSameTime, int maxForkOnTable){
		this.setMaxFork(4);
		this.setMaxForkOnTable(maxForkOnTable);
		this.setMaxPhilsCanEatAtSameTime(maxPhilsCanEatAtSameTime);		
		this.forkTaken = new int [this.maxPhilsCanEatAtSameTime][this.getMaxForkOnTable()];
		
		for(int j=0;j<this.getMaxPhilsCanEatAtSameTime(); j++)
			for(int i=0;i<this.getMaxForkOnTable(); i++)
				this.forkTaken[j][i] = 0;
		//for(int i=0;i<this.getMaxFork(); System.out.println(this.forkTaken[i]), i++);		
	}
	
	public int getMaxPhilsCanEatAtSameTime() {
		return maxPhilsCanEatAtSameTime;
	}
	public void setMaxPhilsCanEatAtSameTime(int maxPhilsCanEatAtSameTime) {
		this.maxPhilsCanEatAtSameTime = maxPhilsCanEatAtSameTime;
	}

	public void setForkTaken_i(int phil,int fork) {
		this.forkTaken[phil][fork] = 1;
	}
	public void dropForkTaken_i(int phil,int fork) {
		this.forkTaken[phil][fork] = 0;
	}
	
	public int getForkTaken_i(int phil,int fork) {
		return this.forkTaken[phil][fork];
	}
	
	public int getMaxFork() {
		return maxFork;
	}
	public void setMaxFork(int maxFork) {
		this.maxFork = maxFork;
	}
	public int[][] getForkTaken() {
		return forkTaken;
	}
	public int getMaxForkOnTable() {
		return maxForkOnTable;
	}

	public void setMaxForkOnTable(int maxForkOnTable) {
		this.maxForkOnTable = maxForkOnTable;
	}
		
}