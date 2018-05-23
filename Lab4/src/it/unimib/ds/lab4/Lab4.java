package it.unimib.ds.lab4;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Questa classe permette al framework di creare
 * al posto nostro una servlet che mapperà tutti gli URL
 * con il pattern /api/*
 * 
 * Inoltre, indicando i packages ci assicuriamo che tutte
 * le classi ed i metodi in esso contenute (ed in tutti i sotto packages)
 * annotate con @Path vengano gestiti da questa servlet.
 * 
 * Lo stesso risultato si poteva ottenere andando a configurare
 * la servlet dentro il file web.xml.
 */
@ApplicationPath("/api")
public class Lab4 extends ResourceConfig {
  public Lab4() {
    packages("it.unimib.ds.lab4");
  }
}
