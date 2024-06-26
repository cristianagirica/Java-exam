package eu.airplane.classes;

import java.io.Serializable;

public abstract class Airplane implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	
	public Airplane() {}
	
	public Airplane(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}
}
