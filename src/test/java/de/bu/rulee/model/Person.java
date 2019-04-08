package de.bu.rulee.model;

public class Person {

	private String firstName;
	private String surname;

	@Dimensional(dimension = "marketing-channels")
	private boolean readsNewsletter;

	@Dimensional(dimension = "age-groups")
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public boolean isReadsNewsletter() {
		return readsNewsletter;
	}

	public void setReadsNewsletter(boolean readsNewsletter) {
		this.readsNewsletter = readsNewsletter;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Person() {
		/* Default constructor. */
	}

	public Person(String firstName, String surname, int age, boolean readsNewsletter) {
		this.firstName = firstName;
		this.surname = surname;
		this.age = age;
		this.readsNewsletter = readsNewsletter;
	}

}
