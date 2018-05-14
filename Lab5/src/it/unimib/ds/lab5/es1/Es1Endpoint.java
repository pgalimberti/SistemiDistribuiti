package it.unimib.ds.lab5.es1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/es1")
public class Es1Endpoint {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String getContent() {
    return "Server-side content";
  }
}
