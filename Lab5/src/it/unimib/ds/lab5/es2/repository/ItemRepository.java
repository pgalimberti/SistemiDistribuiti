package it.unimib.ds.lab5.es2.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unimib.ds.lab5.es2.model.Item;

public class ItemRepository {

	/**
	 * Questa classe rappresenta un'astrazione di un data store. Il design pattern
	 * Repository esiste ed è un modo per liberare il programmatore da query SQL ed
	 * accessi diretti ad un database. Esistono vari modi per realizzare questa
	 * idea, questo pattern è uno dei tanti.
	 */
	// Dato che non abbiamo un vero e proprio database
	// utilizzeremo una mappa
	private Map<Integer, Item> items;
	// Questa è una semplice implementatione del pattern Singleton.
	// Esso garantisce la creazione di 1 solo oggetto di tipo
	// ExamRepository
	private static ItemRepository instance = null;

	public static ItemRepository getInstance() {
		if (instance == null) {
			instance = new ItemRepository();
		}
		return instance;
	}

	// Il costruttore è privato proprio per proibire
	// la creazione manuale dell'oggetto
	private ItemRepository() {
		// Aggiungo alla mappa due esami
		this.items = new HashMap<>();
		this.items.put(1, new Item("Distributed Systems"));
		this.items.put(2, new Item("Software Engineering"));
	}

	// Ritorno i valori della mappa (quindi una collection di esami)
	public Collection<Item> findAll() {
		return this.items.values();
	}

	public void save(Item item) {
		this.items.put(this.items.size() + 1, item);
	}
}
