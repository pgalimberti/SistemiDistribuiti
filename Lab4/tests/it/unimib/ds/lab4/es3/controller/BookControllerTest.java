package it.unimib.ds.lab4.es3.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import it.unimib.ds.lab4.es3.model.Book;

public class BookControllerTest {
  private Book book1, book2;
  Client client;

  @Before
  public void setUp() {
    this.client = ClientBuilder.newClient();
    this.book1 = new Book("1449374646", "Josh Long - Kenny Bastani",
        "Cloud Native Java: Designing Resilient Systems with Spring Boot, Spring Cloud, and Cloud Foundry");
    this.book2 = new Book("1491924357", "Kief Morris", "Infrastructure as Code: Managing Servers in the Cloud");
  }

  @Test
  public void testMetadata() {

    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books");

    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(200, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
  }

  @Test
  public void testGetBooks() {

    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books");

    List<Book> response = webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Book>>() {
    });
    assertEquals(2, response.size());

    assertEquals(book1, response.get(1));
    assertEquals(book1.getIsbn(), response.get(1).getIsbn());
    assertEquals(book1.getAuthor(), response.get(1).getAuthor());
    assertEquals(book1.getTitle(), response.get(1).getTitle());

    assertEquals(book2, response.get(0));
    assertEquals(book2.getIsbn(), response.get(0).getIsbn());
    assertEquals(book2.getAuthor(), response.get(0).getAuthor());
    assertEquals(book2.getTitle(), response.get(0).getTitle());
  }

  @Test
  public void testGetBook() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/" + book1.getIsbn());

    Book response = webTarget.request(MediaType.APPLICATION_JSON).get(Book.class);
    assertEquals(book1, response);
    assertEquals(book1.getIsbn(), response.getIsbn());
    assertEquals(book1.getAuthor(), response.getAuthor());
    assertEquals(book1.getTitle(), response.getTitle());
  }

  @Test
  public void testUpdateBook() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/" + book2.getIsbn());
    Book book = new Book();
    book.setTitle("Foo");
    book.setAuthor("Bar");
    Book response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(book, MediaType.APPLICATION_JSON),
        Book.class);
    assertEquals(book, response);
  }

  @Test
  public void testCreateBook() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books");

    Book book = new Book("1234567890", "Foo", "Bar");

    Response response = webTarget.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(book, MediaType.APPLICATION_JSON));
    assertEquals(201, response.getStatus());

    String path = response.getLocation().getPath();
    webTarget = client.target("http://localhost:8080").path(path);

    Book responseGet = webTarget.request(MediaType.APPLICATION_JSON).get(Book.class);
    assertEquals(responseGet, book);
  }

  @Test
  public void testDeleteBook() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/" + book1.getIsbn());
    Response response = webTarget.request().delete();
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testGetBookNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/0123456789");
    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }

  @Test
  public void testUpdateBookNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/0123456789");
    Book book = new Book();
    book.setTitle("Foo");
    book.setAuthor("Bar");

    Response response = webTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(book, MediaType.APPLICATION_JSON));

    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }

  @Test
  public void testDeleteNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es3/books/0123456789");
    Response response = webTarget.request().delete();
    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }
}
