package com.katruk.domain.entity.human;

public class Student extends Human {

  private boolean contract = false;

  public Student() {
    super();
  }

  public Student(int id) {
    super(id);
  }

  public Student(String login, String password, String name, String lastName, String patronymic) {
    setLogin(login);
    setPassword(encodePassword(password));
    setName(name);
    setLastName(lastName);
    setPatronymic(patronymic);
    setRole(Role.STUDENT);
  }

  public boolean isContract() {
    return contract;
  }

  public void setContract(boolean contract) {
    this.contract = contract;
  }

  @Override
  public String toString() {
    return String.format("%s %s", getLastName(), getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Student student = (Student) o;

    if (getId() != student.getId()) {
      return false;
    }
    if (getName() != null ? !getName().equals(student.getName()) : student.getName() != null) {
      return false;
    }
    if (getLastName() != null ? !getLastName().equals(student.getLastName())
                              : student.getLastName() != null) {
      return false;
    }
    if (getPatronymic() != null ? !getPatronymic().equals(student.getPatronymic())
                                : student.getPatronymic() != null) {
      return false;
    }
    if (getPassword() != null ? !getPassword().equals(student.getPassword())
                              : student.getPassword() != null) {
      return false;
    }
    if (getLogin() != null ? !getLogin().equals(student.getLogin()) : student.getLogin() != null) {
      return false;
    }
    if (getRole() != null ? !getRole().equals(student.getRole()) : student.getRole() != null) {
      return false;
    }
    return isContract() == student.isContract();
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
    result = 31 * result + (isContract() ? 13 : 0);
    return result;
  }

}
