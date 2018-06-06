package Es_2;

public class Folder {
	private int num_max_file; //numero massimo di file
	private int max_grandezza_file; //grandezza massima dei file contenuti
	private MyFile[] files;
	private int dimensione_occupata;
		
	public Folder(int num_max_file, int max_grandezza_file) {
		this.setNum_max_file(num_max_file);
		this.setMax_grandezza_file(max_grandezza_file);
		this.files = new MyFile[this.num_max_file];
	}
	
	public synchronized void salva(MyFile file) throws InterruptedException {
		int freeIndex = checkNumeroDiFile();
		if(checkDimensione(file) && (freeIndex > -1)) { //se ok , inserisco il file
			this.files[freeIndex] = file;
		}else {
			while(!checkDimensione(file) && (freeIndex == -1)) { //finchè non ho spazio e non c'è posto allora aspetta
				wait();
			}
		}			
	}	
	
	//controllo se il nuovo file ci sta come dimensione nella cartella
	private boolean checkDimensione(MyFile file) {
		int aux = this.getDimensione_occupata();
		int dimFile = file.getDimensione();
		aux += dimFile;
		if(aux > this.getMax_grandezza_file())
			return false;
		else
			return true;
	}
	//controllo se la cartella non ha già raggiunto il numero massimo di file
	private int checkNumeroDiFile() {
		MyFile[] aux = this.getFiles();
		for(int i=0;i<aux.length;i++) {
			if(aux[i] == null)
				return i;
		}
		return -1;
	}
	
	//getter & setter
	public int getNum_max_file() {
		return num_max_file;
	}
	public void setNum_max_file(int num_max_file) {
		this.num_max_file = num_max_file;
	}
	public int getMax_grandezza_file() {
		return max_grandezza_file;
	}
	public void setMax_grandezza_file(int max_grandezza_file) {
		this.max_grandezza_file = max_grandezza_file;
	}
	public int getDimensione_occupata() {
		return dimensione_occupata;
	}

	public void setDimensione_occupata(int dimensione_occupata) {
		this.dimensione_occupata += dimensione_occupata;
	}
	public MyFile[] getFiles() {
		return files;
	}
	public void setFiles(MyFile[] files) {
		this.files = files;
	}	
}