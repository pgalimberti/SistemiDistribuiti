package it.unimib.ds.lab4.es1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Questa classe rappresenta la nostra risorsa People.
 * Il path è da intendersi relativo a quello definito
 * all'application path.
 * Quindi questa classe risponde per tutte le chiamate
 * agli URL del tipo /api/es1/people
 */
@Path("/es1/people")
public class PeopleEndpoint {
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es1/people
   * 
   * @Produces definisce il MIME type della risposta
   */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getPeople() {
    return "This is a list of people";
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es1/people/{id}
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta l'id e lo inserisce nella
   * variabile String id
   */
  @Path("/{id}")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getPerson(@PathParam("id") String id) {
    return "This is the person with id: " + id;
  }
}
