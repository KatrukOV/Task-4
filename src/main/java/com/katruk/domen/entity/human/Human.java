package com.katruk.domen.entity.human;

import com.katruk.domen.entity.Model;
import org.apache.commons.codec.digest.DigestUtils;

public abstract class Human extends Model {

    public enum Role{
        STUDENT,
        TEACHER,
		ADMIN
    }
    protected String login;
    protected String password;  //sha1Hex encrypted
    protected String name;
    protected String lastName;
	protected String patronymic;
	protected Role role;

    public Human() { super(); }

    public Human(int id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role){
        this.role = role;
    }

    public Role getRole(){
        return role;
    }

    //Sha1Hex encryption method
    protected String encodePassword(String password){
        return DigestUtils.sha1Hex(password);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", getLastName(), getName(), getPatronymic());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Human human = (Human) o;

        if (getId() != human.getId()) return false;
        if (getName() != null ? !getName().equals(human.getName()) : human.getName() != null) return false;
        if (getLastName() != null ? !getLastName().equals(human.getLastName()) : human.getLastName() != null)
            return false;
		if (getPatronymic() != null ? !getPatronymic().equals(human.getPatronymic()) : human.getPatronymic() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(human.getPassword()) : human.getPassword() != null)
            return false;
        if (getLogin() != null ? !getLogin().equals(human.getLogin()) : human.getLogin() != null)
            return false;
        return getRole() == human.getRole();

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getPatronymic() != null ? getPatronymic().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

}
