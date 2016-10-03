package com.katruk.domen.entity.human;

public class Admin extends Human {

	public Admin() {super();}

	public Admin(int id){ super(id); }

	public Admin(String login, String password, String name, String lastName, String patronymic){
		setLogin(login);
		setPassword(encodePassword(password));
		setName(name);
		setLastName(lastName);
		setPatronymic(patronymic);
		setRole(Role.ADMIN);
	}

	@Override
	public String toString(){
		return super.toString();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}
}
