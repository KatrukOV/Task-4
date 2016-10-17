package com.katruk.domain.dto;

public class StudentMark {

  private String titleDiscipline;
  private String mark;
  private String feedback;

  public StudentMark(String titleDiscipline, String mark, String feedback) {
    setTitleDiscipline(titleDiscipline);
    setMark(mark);
    setFeedback(feedback);
  }

  public String getTitleDiscipline() {
    return titleDiscipline;
  }

  public void setTitleDiscipline(String titleDiscipline) {
    this.titleDiscipline = titleDiscipline;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
}
