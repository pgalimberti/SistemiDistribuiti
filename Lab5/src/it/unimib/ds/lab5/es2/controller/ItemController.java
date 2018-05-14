package it.unimib.ds.lab5.es2.controller;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import it.unimib.ds.lab5.es2.model.Item;
import it.unimib.ds.lab5.es2.repository.ItemRepository;

@Path("/es2/items")
public class ItemController {
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Collection<Item> getItems() {
    return ItemRepository.getInstance().findAll();
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Collection<Item> addItem(@FormParam("name") String name) {
    ItemRepository.getInstance().save(new Item(name));
    return ItemRepository.getInstance().findAll();
  }
}
