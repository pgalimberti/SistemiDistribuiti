package it.unimib.ds.lab4.es4.controller;

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

import it.unimib.ds.lab4.es4.model.ToDo;

public class ToDoControllerTest {
  private ToDo todo1, todo2;
  Client client;

  @Before
  public void setUp() {
    this.client = ClientBuilder.newClient();
    this.todo1 = new ToDo(1, "Title1", "Text1");
    this.todo2 = new ToDo(2, "Title2", "Text2");
  }

  @Test
  public void testMetadata() {

    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos");

    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(200, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
  }

  @Test
  public void testGetToDos() {

    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos");

    List<ToDo> response = webTarget.request(MediaType.APPLICATION_JSON).get(new GenericType<List<ToDo>>() {
    });
    assertEquals(2, response.size());

    assertEquals(todo1, response.get(0));
    assertEquals(todo1.getId(), response.get(0).getId());
    assertEquals(todo1.getTitle(), response.get(0).getTitle());
    assertEquals(todo1.getText(), response.get(0).getText());

    assertEquals(todo2, response.get(1));
    assertEquals(todo2.getId(), response.get(1).getId());
    assertEquals(todo2.getTitle(), response.get(1).getTitle());
    assertEquals(todo2.getText(), response.get(1).getText());
  }

  @Test
  public void testGetToDo() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/" + todo1.getId());

    ToDo response = webTarget.request(MediaType.APPLICATION_JSON).get(ToDo.class);
    assertEquals(todo1, response);
    assertEquals(todo1.getId(), response.getId());
    assertEquals(todo1.getTitle(), response.getTitle());
    assertEquals(todo1.getText(), response.getText());
  }

  @Test
  public void testUpdateToDo() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/" + todo2.getId());
    ToDo todo = new ToDo();
    todo.setTitle("Foo");
    todo.setText("Bar");
    ToDo response = webTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(todo, MediaType.APPLICATION_JSON),
        ToDo.class);
    assertEquals(todo, response);
  }

  @Test
  public void testCreateTodo() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos");

    ToDo todo = new ToDo(12, "Foo", "Bar");

    Response response = webTarget.request(MediaType.APPLICATION_JSON)
        .post(Entity.entity(todo, MediaType.APPLICATION_JSON));
    assertEquals(201, response.getStatus());

    String path = response.getLocation().getPath();
    webTarget = client.target("http://localhost:8080").path(path);

    ToDo responseGet = webTarget.request(MediaType.APPLICATION_JSON).get(ToDo.class);
    assertEquals(responseGet, todo);
  }

  @Test
  public void testDeleteToDo() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/" + todo1.getId());
    Response response = webTarget.request().delete();
    assertEquals(204, response.getStatus());
  }

  @Test
  public void testGetToDoNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/1234");
    Response response = webTarget.request(MediaType.APPLICATION_JSON).get();
    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }

  @Test
  public void testUpdateToDoNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/1234");
    ToDo todo = new ToDo();
    todo.setTitle("Foo");
    todo.setText("Bar");

    Response response = webTarget.request(MediaType.APPLICATION_JSON)
        .put(Entity.entity(todo, MediaType.APPLICATION_JSON));

    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }

  @Test
  public void testDeleteNotFound() {
    WebTarget webTarget = client.target("http://localhost:8080").path("Lab4/api/es4/todos/1234");
    Response response = webTarget.request().delete();
    assertEquals(404, response.getStatus());
    assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    assertEquals("{\"error\": \"NOT FOUND\"}", response.readEntity(String.class));
  }
}
