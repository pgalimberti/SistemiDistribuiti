package it.unimib.ds.lab4.es4.controller;

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

import it.unimib.ds.lab4.es4.model.ToDo;
import it.unimib.ds.lab4.es4.repository.ToDoRepository;

@Path("/es4/todos")
public class ToDoController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodo() {
		return Response.ok(ToDoRepository.getInstance().findAll()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodo(@PathParam("id") String id) {
		ToDo todo = ToDoRepository.getInstance().find(id);
		if (null == todo) {
			return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
		}
		return Response.ok(todo).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(ToDo todo, @Context UriInfo uriInfo) {
		ToDoRepository.getInstance().save(todo);

		UriBuilder builder = uriInfo.getAbsolutePathBuilder();
		builder.path(String.valueOf(todo.getId()));

		return Response.created(builder.build()).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") String id, ToDo todo) {
		ToDo updatedtodo = ToDoRepository.getInstance().update(id, todo);
		if (null == updatedtodo) {
			return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
		}
		return Response.ok(todo).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") String id) {

		ToDo deletedTodo = ToDoRepository.getInstance().delete(id);

		if (null == deletedTodo) {
			return Response.status(404).entity("{\"error\": \"NOT FOUND\"}").build();
		}

		return Response.noContent().build();
	}

}
