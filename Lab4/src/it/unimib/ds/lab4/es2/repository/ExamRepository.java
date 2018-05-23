package it.unimib.ds.lab4.es2.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unimib.ds.lab4.es2.model.Exam;

/**
 * Questa classe rappresenta un'astrazione di un data store.
 * Il design pattern Repository esiste ed è un modo per 
 * liberare il programmatore da query SQL ed accessi diretti ad un database.
 * Esistono vari modi per realizzare questa idea, questo
 * pattern è uno dei tanti.
 */
public class ExamRepository {
  // Dato che non abbiamo un vero e proprio database
  // utilizzeremo una mappa
  private Map<String, Exam> exams;
  // Questa è una semplice implementatione del pattern Singleton.
  // Esso garantisce la creazione di 1 solo oggetto di tipo
  // ExamRepository
  private static ExamRepository instance = null;
  
  public static ExamRepository getInstance() {
    if (instance == null) {
      instance = new ExamRepository();
    }
    return instance;
  }
  
  // Il costruttore è privato proprio per proibire
  // la creazione manuale dell'oggetto
  private ExamRepository() {
    // Aggiungo alla mappa due esami
    this.exams = new HashMap<>();
    this.exams.put("E3101Q112", new Exam("Distributed Systems", "E3101Q112"));
    this.exams.put("E3101Q119", new Exam("Software Engineering", "E3101Q119"));
  }
  
  // Ritorno i valori della mappa (quindi una collection di esami)
  public Collection<Exam> findAll() {
    return this.exams.values();
  }
  
  // Ritorno un singolo esame dato il suo codice
  public Exam find(String code) {
    return this.exams.get(code);
  }
}
