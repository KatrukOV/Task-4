package com.katruk.domain.entity;

import java.io.Serializable;

public class Model implements Serializable {

	protected int id;

	public Model() {}

	public Model(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
