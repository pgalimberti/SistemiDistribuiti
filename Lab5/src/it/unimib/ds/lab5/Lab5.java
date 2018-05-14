package it.unimib.ds.lab5;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
@ApplicationPath("/api")
public class Lab5 extends ResourceConfig {
  public Lab5() {
    packages("it.unimib.ds.lab5");
  }
}
