package it.unimib.ds.lab4.es3.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import it.unimib.ds.lab4.es3.model.Book;
import it.unimib.ds.lab4.es3.repository.BookRepository;
/**
 * Questa classe rappresenta la nostra risorsa Book.
 * Il path è da intendersi relativo a quello definito
 * all'application path.
 * Quindi questa classe risponde per tutte le chiamate
 * agli URL del tipo /api/es3/books
 */
@Path("/es3/books")
public class BookController {
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es3/books
   * 
   * @Produces definisce il MIME type della risposta e quindi la serializzazione
   * in questo caso JSON
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBooks() {
    // Ritorniamo la risposta:
    // Response.ok setta automaticamente lo status code a 200
    // ed accetta anche il payload, in questo caso la nostra lista di libri
    // recuperata con il BookRepository
    return Response.ok(BookRepository.getInstance().findAll()).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es3/books/{isbn}
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta e quindi la serializzazione
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta l'isbn del libro e lo inserisce nella
   * variabile String isbn
   */
  @GET
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getBook(@PathParam("isbn") String isbn) {
    // Recupero tramite il repository il libro
    Book book = BookRepository.getInstance().find(isbn);
    // Se è null, allora ritorno una risposta con status code 404
    // e come payload del JSON che ne indica l'errore
    if (null == book) {
      return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
    }
    // Altrimenti ritorno il libro
    return Response.ok(book).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una POST su /api/es3/books
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta e quindi la serializzazione
   * @Consumes definisce il MIME type del payload della richiesta in ingresso
   * 
   * Come parametri del metodo vengono passati un oggetto di tipo Book
   * ed uno di tipo UriInfo che viene iniettato sempre grazie all'annotazione
   * @Context come nell'esercizio 2.
   * 
   * Qui dovrebbe sorgere spontanea la domanda: come viene inizializzato 
   * l'oggetto book?
   * Avendo definito il tipo MIME type in ingresso tramite @Consumes
   * il framework riesce a deserializzare il JSON presente nel payload
   * della richiesta direttamente nell'oggetto di tipo Book.
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response create(Book book, @Context UriInfo uriInfo) {
    // Salvo il nuovo libro tramite il repository
    BookRepository.getInstance().save(book);

    // Creo l'URI che punta alla nuova risorsa creata
    UriBuilder builder = uriInfo.getAbsolutePathBuilder();
    builder.path(book.getIsbn());
    
    // Ritorniamo la risposta:
    // Response.created setta automaticamente lo status code a 201
    // ed accetta anche l'URI della nuova risorsa creata
    // che finirà nell'header come valore della chiave Location
    return Response.created(builder.build()).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una PUT su /api/es3/books
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta e quindi la serializzazione
   * @Consumes definisce il MIME type del payload della richiesta in ingresso
   * 
   * Come parametri del metodo vengono passati l'isbn del libro
   * un oggetto di tipo Book
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta l'isbn del libro e lo inserisce nella
   * variabile String isbn
   * 
   * L'oggetto book viene inizializzato nello stesso modo
   * spiegato precedentemente per il metodo create.
   */
  @PUT
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("isbn") String isbn, Book book) {
    // Aggiorno il libro per mezzo del repository
    Book updatedBook = BookRepository.getInstance().update(isbn, book);
    // Nel caso in cui non fosse presente il libro da aggiornare
    // allora ritorno una risposta con status code 404
    // e come payload del JSON che ne indica l'errore
    if (null == updatedBook) {
      return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
    }
    // Altrimenti ritorno il libro aggiornato
    return Response.ok(book).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una DELETE su /api/es3/books
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta e quindi la serializzazione
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta l'isbn del libro e lo inserisce nella
   * variabile String isbn
   */
  @DELETE
  @Path("/{isbn}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response delete(@PathParam("isbn") String isbn) {
    // Elimino il libro per mezzo del repository
    Book deletedBook = BookRepository.getInstance().delete(isbn);
    // Nel caso in cui non fosse presente il libro da eliminare
    // allora ritorno una risposta con status code 404
    // e come payload del JSON che ne indica l'errore
    if (null == deletedBook) {
      return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
    }
    // Altrimenti ritorno una risposta
    // senza payload e con status code 204 NO CONTENT
    return Response.noContent().build();
  }
}
