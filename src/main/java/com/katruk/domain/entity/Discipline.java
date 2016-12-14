package com.katruk.domain.entity;


public class Discipline extends Model {

  private String title;
  private Teacher teacher;


  public Discipline() {
    super();
  }

  public Discipline(int id) {
    super(id);
  }

  public Discipline(String title, Teacher teacher) {
    setTitle(title);
    setTeacher(teacher);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Teacher getTeacher() {
    return teacher;
  }

  public void setTeacher(Teacher teacher) {
    this.teacher = teacher;
  }

  @Override
  public String toString() {
    return getTitle();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Discipline discipline = (Discipline) o;

    if (getId() != discipline.getId()) {
      return false;
    }
    if (getTitle() != null ? !getTitle().equals(discipline.getTitle())
                           : discipline.getTitle() != null) {
      return false;
    }
    return !(getTeacher() != null ? !getTeacher().equals(discipline.getTeacher())
                                  : discipline.getTeacher() != null);
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
    result = 31 * result + (getTeacher() != null ? getTeacher().hashCode() : 0);
    return result;
  }
}
