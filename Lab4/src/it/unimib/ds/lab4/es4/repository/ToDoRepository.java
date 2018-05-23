package it.unimib.ds.lab4.es4.repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import it.unimib.ds.lab4.es4.model.ToDo;

public class ToDoRepository {

	private Map<String, ToDo> todo;

	private static ToDoRepository instance = null;

	public static ToDoRepository getInstance() {
		if (instance == null) {
			instance = new ToDoRepository();
		}
		return instance;
	}

	private ToDoRepository() {
		this.todo = new HashMap<>();
		this.todo.put("1", new ToDo(1, "Title1", "Text1"));
		this.todo.put("2", new ToDo(2, "Title2", "Text2"));
	}

	public Collection<ToDo> findAll() {
		return this.todo.values();
	}

	public ToDo find(String id) {
		return this.todo.get(id);
	}

	public void save(ToDo todo) {
		this.todo.put(String.valueOf(todo.getId()), todo);
	}

	public ToDo update(String id, ToDo todo) {
		return this.todo.replace(id, todo);
	}

	public ToDo delete(String id) {
		return this.todo.remove(id);
	}

}
