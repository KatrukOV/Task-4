package com.katruk.domain.entity;

import com.katruk.domain.entity.human.Student;

public class Evaluation extends Model {

  Discipline discipline;
  Student student;
  StatusInDiscipline status;
  Mark mark;
  String feedback;


  public enum StatusInDiscipline {
    DECLARED,
    REVOKED,
    CONFIRMED,
    DELETED
  }

  public enum Mark {
    A, B, C, D, E, Fx, F
  }

  public Evaluation(Discipline discipline, Student student, StatusInDiscipline status, Mark mark,
                    String feedback) {
    this.discipline = discipline;
    this.student = student;
    this.status = status;
    this.mark = mark;
    this.feedback = feedback;
  }

  public Evaluation() {
    super();
  }

  public Evaluation(int id) {
    super(id);
  }

  public Discipline getDiscipline() {
    return discipline;
  }

  public void setDiscipline(Discipline discipline) {
    this.discipline = discipline;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public StatusInDiscipline getStatus() {
    return status;
  }

  public void setStatus(StatusInDiscipline status) {
    this.status = status;
  }

  public Mark getMark() {
    return mark;
  }

  public void setMark(Mark mark) {
    this.mark = mark;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }

//	@Override
//	public String toString() {
//		return String.format("discipline=%s, student=%s %s, status =%s, mark=%s , feedback=%s",
//				getDiscipline().getTitle(), getStudent().getLastName(), getStudent().getName(),
//                getStatus().name(), getMark().name(), getFeedback());
//	}

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Evaluation evaluation = (Evaluation) o;

    if (getId() != evaluation.getId()) {
      return false;
    }
    if (getDiscipline() != null ? !getDiscipline().equals(evaluation.getDiscipline())
                                : evaluation.getDiscipline() != null) {
      return false;
    }
    if (getStatus() != null ? !getStatus().equals(evaluation.getStatus())
                            : evaluation.getStatus() != null) {
      return false;
    }
    if (getMark() != null ? !getMark().equals(evaluation.getMark())
                          : evaluation.getMark() != null) {
      return false;
    }
    if (getFeedback() != null ? !getFeedback().equals(evaluation.getFeedback())
                              : evaluation.getFeedback() != null) {
      return false;
    }
    return (getStudent() != null ? !getStudent().equals(evaluation.getStudent())
                                 : evaluation.getStudent() != null);
  }

  @Override
  public int hashCode() {
    int result = getId();
    result = 31 * result + (getDiscipline() != null ? getDiscipline().hashCode() : 0);
    result = 31 * result + (getStudent() != null ? getStudent().hashCode() : 0);
    result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
    result = 31 * result + (getMark() != null ? getMark().hashCode() : 0);
    result = 31 * result + (getFeedback() != null ? getFeedback().hashCode() : 0);
    return result;
  }
}
