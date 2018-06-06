package Es_1;

public class Table {
	private int maxForkOnTable;//
	private int maxPhilsCanEatAtSameTime; //2
	private int forkTaken[][]; //[0-1][0-4] possono mangiare max 2 filosofi alla volta con 5 forchette sul tavolo da smazzarsi
	
	public Table(int maxPhilsCanEatAtSameTime, int maxForkOnTable){ 
		//init
		this.setMaxForkOnTable(maxForkOnTable);
		this.setMaxPhilsCanEatAtSameTime(maxPhilsCanEatAtSameTime);		
		this.forkTaken = new int [this.maxPhilsCanEatAtSameTime][this.getMaxForkOnTable()];
		
		for(int j=0;j<this.getMaxPhilsCanEatAtSameTime(); j++)
			for(int i=0;i<this.getMaxForkOnTable(); i++)
				this.forkTaken[j][i] = -1;
	}
	
	//getter & setter
	public void setForkTaken_i(int phil,int forkNumber,int fork) {
		this.forkTaken[phil][forkNumber] = fork;
	}
	public void dropForkTaken_i(int phil,int forkNumber) {
		this.forkTaken[phil][forkNumber] = -1;
	}
	
	
	public int getMaxPhilsCanEatAtSameTime() {
		return maxPhilsCanEatAtSameTime;
	}
	public void setMaxPhilsCanEatAtSameTime(int maxPhilsCanEatAtSameTime) {
		this.maxPhilsCanEatAtSameTime = maxPhilsCanEatAtSameTime;
	}
	public int getForkTaken_i(int phil,int fork) {
		return this.forkTaken[phil][fork];
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