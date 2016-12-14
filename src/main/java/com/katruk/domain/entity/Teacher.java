package com.katruk.domain.entity;

public class Teacher extends Human {

  private Position position;

  public enum Position {
    ASSISTANT_PROFESSOR, ASSOCIATE_PROFESSOR, PROFESSOR
  }


  public Teacher() {
    super();
  }

  public Teacher(int id) {
    super(id);
  }

  public Teacher(String login, String password, String name, String lastName, String patronymic) {
    setLogin(login);
    setPassword(encodePassword(password));
    setName(name);
    setLastName(lastName);
    setPatronymic(patronymic);
    setRole(Role.TEACHER);
  }

  public Position getPosition() {
    return position;
  }

  public void setPosition(Position position) {
    this.position = position;
  }

  @Override
  public String toString() {
    return super.toString();
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Teacher teacher = (Teacher) o;

    if (getId() != teacher.getId()) {
      return false;
    }
    if (getName() != null ? !getName().equals(teacher.getName()) : teacher.getName() != null) {
      return false;
    }
    if (getLastName() != null ? !getLastName().equals(teacher.getLastName())
                              : teacher.getLastName() != null) {
      return false;
    }
    if (getPatronymic() != null ? !getPatronymic().equals(teacher.getPatronymic())
                                : teacher.getPatronymic() != null) {
      return false;
    }
    if (getPassword() != null ? !getPassword().equals(teacher.getPassword())
                              : teacher.getPassword() != null) {
      return false;
    }
    if (getLogin() != null ? !getLogin().equals(teacher.getLogin()) : teacher.getLogin() != null) {
      return false;
    }
    if (getPosition() != null ? !getPosition().equals(teacher.getPosition())
                              : teacher.getPosition() != null) {
      return false;
    }
    return getRole() == teacher.getRole();
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
    result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
    return result;
  }

}
