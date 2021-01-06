package net.javaguides.usermanagement.model;

public class School {
	private int id, yearOfFoundation;
	private String name, adres;

	public School(int yearOfFoundation, String name, String adres) {
		super();
		this.yearOfFoundation = yearOfFoundation;
		this.name = name;
		this.adres = adres;
	}

	public School(int id, int yearOfFoundation, String name, String adres) {
		super();
		this.id = id;
		this.yearOfFoundation = yearOfFoundation;
		this.name = name;
		this.adres = adres;
	}

	public School() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getYearOfFoundation() {
		return yearOfFoundation;
	}

	public void setYearOfFoundation(int yearOfFoundation) {
		this.yearOfFoundation = yearOfFoundation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

}
