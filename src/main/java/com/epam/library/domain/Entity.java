package com.epam.library.domain;

public abstract class Entity {

	private int id;

	public Entity() {}

	protected Entity(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
