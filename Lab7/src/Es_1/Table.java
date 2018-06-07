package Es_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Table {
	@SuppressWarnings("unused")
	private int phils_number; //5
	private int fork_taken;
	Map<Integer,Integer> whoEat;
	private Set<Integer> coda;
	
	public Table(){ 
		this.phils_number = 5;
		this.fork_taken = 0;
		this.whoEat = new  HashMap<Integer,Integer>();
		this.coda = new HashSet<Integer>();
	}
	
	public synchronized boolean setWhoCanEat(int phil,int forkTaken) throws InterruptedException {		
		if(check() < 2 && fork_taken < 4) {
			if(!whoEat.containsKey(forkTaken)) {
				//int counted = check(phil);
					whoEat.put(forkTaken, phil);
					coda.add(phil);
					fork_taken++;
					return false;
			}
		}else {
			//System.out.println("coda >= 2 . whoEat.containsValue("+phil+") : " + whoEat.containsValue(phil));
			if(whoEat.containsValue((Object)phil)) {
				whoEat.put(forkTaken, phil);
				fork_taken++;
				return false;
				//coda.add(phil);
			}
			else {
				if(fork_taken == 4)
					return true;
			}
		}
		return true;
	}

	public int check() {
		/*int count = -1;
		
		if(!coda.isEmpty()) {
			for(Integer i : coda) {
				if(coda.contains(phil)) {
					for(Integer key : whoEat.keySet()) {
					      if(whoEat.containsKey(key))
					    	  count++;
					}				
				}else {
					count = 0;
				}
			}
		}*/
		return coda.size();
	}
	
	public synchronized void unlockForkAndPhilosopher(int phil,int forkTaken) {
		if(whoEat.containsKey(forkTaken)) {
			whoEat.remove(forkTaken);
			if(whoEat.containsValue(phil)) {
				coda.remove((Object)phil);
				fork_taken--;
				notify();
			}				
		}
	}
	
}