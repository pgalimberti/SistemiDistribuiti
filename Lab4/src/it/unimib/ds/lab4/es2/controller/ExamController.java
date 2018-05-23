package it.unimib.ds.lab4.es2.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import it.unimib.ds.lab4.es2.model.Exam;
import it.unimib.ds.lab4.es2.repository.ExamRepository;
/**
 * Questa classe rappresenta la nostra risorsa Exam.
 * Il path è da intendersi relativo a quello definito
 * all'application path.
 * Quindi questa classe risponde per tutte le chiamate
 * agli URL del tipo /api/es2/exams
 */
@Path("/es2/exams")
public class ExamController {
  /**
   * L'annotazione @Context inietta a runtime un'instanza
   * dell'oggetto UriInfo
   * uriInfo conterrà informazioni utili sull'URI richiesto come
   * query parameters ed altro
   */
  @Context
  UriInfo uriInfo;
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es2/exams
   * 
   * @Produces definisce il MIME type della risposta
   * in questo caso JSON
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getExams() {
    
    // Generiamo un link self alla collezione di esami.
    // uriInfo.getAbsolutePath() = http://localhost:8080/Lab4/api/es2/exams
    Link collection = Link.fromUri(uriInfo.getAbsolutePath())
        .title("exams")
        .rel("self")
        .type("GET")
        .build();
    
    // Accediamo al repository e prendiamo tutti gli esami in esso contenuti
    Collection<Exam> exams = ExamRepository.getInstance().findAll();
    
    // Per ogni esame presente settiamo il link self all'esame
    for (Exam e : exams) {
      List<Link> links = new ArrayList<>();
      links.add(Link.fromUri(uriInfo.getAbsolutePathBuilder().path(e.getCode()).build())
          .title("exam")
          .rel("self")
          .type("GET")
          .build());
      e.setLinks(links);
    }
    // Ritorniamo la risposta:
    // Response.ok setta automaticamente lo status code a 200
    // ed accetta anche il payload, in questo caso la nostra lista di esami
    // .links() accetta un numero infinito di parametri di tipo Link
    // Essi vengono inseriti dell'header della risposta
    //
    // N.B.: in questo caso il link all'intera collezione dovrebbe essere inserito
    // anche nel payload della risposta (come i link ai singoli esami)
    // questo però richiederebbe l'ulteriore sforzo di definire una nostra implementazione
    // di una collezione di esami con un attributo links.
    // Per semplicità questo passaggio è stato omesso.
    return Response.ok(exams).links(collection).build();
  }
  
  /**
   * Questo metodo viene chiamato quando viene effettuata
   * una GET su /api/es2/exams/{code}
   * 
   * @Path definisce un nuovo percorso relativo quello definito
   * a livello di classe
   * @Produces definisce il MIME type della risposta
   * @PathParam permette di accedere all'ultimo fragment dell'URI
   * quello che per noi rappresenta il code dell'esame e lo inserisce nella
   * variabile String code
   */
  @Path("/{code}")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getExam(@PathParam("code") String code) {
    // Recuperiamo l'esame grazie al repository
    Exam exam = ExamRepository.getInstance().find(code);
    
    // Generiamo i link self all'esame ed il link collection all'intera collezione
    Link self = Link.fromUri(uriInfo.getAbsolutePath())
        .title("exam")
        .rel("self")
        .type("GET")
        .build();
    String absolutePath = uriInfo.getAbsolutePath().toString();
    Link collection = Link.fromUri(URI.create(absolutePath.substring(0, absolutePath.lastIndexOf('/'))))
        .title("exams")
        .rel("collection")
        .type("GET")
        .build();
    
    // Aggiungiamo i link generati all'oggetto esame
    List<Link> links = new ArrayList<>();
    links.add(collection);
    links.add(self);
    exam.setLinks(links);
    
    // Ritorniamo la risposta:
    // Response.ok setta automaticamente lo status code a 200
    // ed accetta anche il payload, in questo caso l'esame richiesto
    // .links() accetta un numero infinito di parametri di tipo Link
    // Essi vengono inseriti dell'header della risposta ma in questo caso
    // sono anche presenti nel payload perchè sono stati aggiunti
    // all'oggetto esame
    return Response.ok(exam).links(self, collection).build();
  }
}
