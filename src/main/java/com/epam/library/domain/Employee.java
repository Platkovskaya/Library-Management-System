package com.epam.library.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee extends Entity {

	private String name;
	private String email;
	private Date dateOfBirth;
	private List<Book> rentedBooks = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(int id, String name, String email, Date dateOfBirth) {
		super(id);
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public Employee(String name, String email, Date dateOfBirth) {
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<Book> getRentedBooks() {
		return rentedBooks;
	}

	public void addRentedBook(Book book) {
		this.rentedBooks.add(book);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Employee employee = (Employee) o;

		if (!name.equals(employee.name)) return false;
		if (!email.equals(employee.email)) return false;
		return dateOfBirth.equals(employee.dateOfBirth);
	}

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + email.hashCode();
		result = 31 * result + dateOfBirth.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "★ id: " + getId() + ", name = " + name + ", email = " + email + ", dateOfBirth = " + dateOfBirth + " ★";
	}

}
