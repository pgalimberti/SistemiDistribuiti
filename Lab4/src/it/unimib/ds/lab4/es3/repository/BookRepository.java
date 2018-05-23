package it.unimib.ds.lab4.es3.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unimib.ds.lab4.es3.model.Book;

/**
 * Questa classe rappresenta un'astrazione di un data store.
 * Il design pattern Repository esiste ed è un modo per 
 * liberare il programmatore da query SQL ed accessi diretti ad un database.
 * Esistono vari modi per realizzare questa idea, questo
 * pattern è uno dei tanti.
 */
public class BookRepository {
  // Dato che non abbiamo un vero e proprio database
  // utilizzeremo una mappa
  private Map<String, Book> books;
  // Questa è una semplice implementatione del pattern Singleton.
  // Esso garantisce la creazione di 1 solo oggetto di tipo
  // BookRepository
  private static BookRepository instance = null;

  public static BookRepository getInstance() {
    if (instance == null) {
      instance = new BookRepository();
    }
    return instance;
  }
  
  // Il costruttore è privato proprio per proibire
  // la creazione manuale dell'oggetto
  private BookRepository() {
    // Aggiungo alla mappa due libri
    this.books = new HashMap<>();
    this.books.put("1449374646", new Book("1449374646", "Josh Long - Kenny Bastani",
        "Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry"));
    this.books.put("1491924357",
        new Book("1491924357", "Kief Morris", "Infrastructure as Code: Managing Servers in the Cloud"));
  }
  
  // Ritorno i valori della mappa (quindi una collection di libri)
  public Collection<Book> findAll() {
    return this.books.values();
  }
  
  // Ritorno un singolo libro dato il suo isbn
  public Book find(String isbn) {
    return this.books.get(isbn);
  }
  
  // Aggiungo un nuovo libro alla mappa 
  public void save(Book book) {
    this.books.put(book.getIsbn(), book);
  }
  
  // Aggiorno un libro dato il suo isbn
  // e la nuova istanza
  public Book update(String isbn, Book book) {
    return this.books.replace(isbn, book);
  }
  
  // Elimino un libro dalla mappa
  public Book delete(String isbn) {
    return this.books.remove(isbn);
  }
}
