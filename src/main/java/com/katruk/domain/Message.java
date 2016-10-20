package com.katruk.domain;

import java.util.ResourceBundle;

public class Message {

  private static final Message INSTANCE = new Message();
  private ResourceBundle resourceBundle;
  private static final String BUNDLE_NAME = "message";

  public static final String EMPTY_RESULTSET = "EMPTY_RESULTSET";

  // message for DisciplineMySql
  public static final String ERROR_GET_ALL_DISCIPLINE = "ERROR_GET_ALL_DISCIPLINE";
  public static final String ERROR_GET_DISCIPLINE_BY_TITLE = "ERROR_GET_DISCIPLINE_BY_TITLE";
  public static final String ERROR_GET_DISCIPLINE_BY_ID = "ERROR_GET_DISCIPLINE_BY_ID";
  public static final String ERROR_CREATE_DISCIPLINE = "ERROR_CREATE_DISCIPLINE";
  public static final String ERROR_REMOVE_DISCIPLINE = "ERROR_REMOVE_DISCIPLINE";
  public static final String ERROR_UPDATE_DISCIPLINE = "ERROR_UPDATE_DISCIPLINE";
  public static final String ERROR_GET_DISCIPLINE_OF_TEACHER = "ERROR_GET_DISCIPLINE_OF_TEACHER";

  // message for EvaluationMySql
  public static final String ERROR_GET_ALL_EVALUATION = "ERROR_GET_ALL_EVALUATION";
  public static final String ERROR_GET_ALL_EVALUATION_BY_DISCIPLINE_AND_STUDENT =
      "ERROR_GET_ALL_EVALUATION_BY_DISCIPLINE_AND_STUDENT";
  public static final String ERROR_GET_EVALUATION_BY_ID = "ERROR_GET_EVALUATION_BY_ID";
  public static final String ERROR_CREATE_EVALUATION = "ERROR_CREATE_EVALUATION";
  public static final String ERROR_REMOVE_EVALUATION = "ERROR_REMOVE_EVALUATION";
  public static final String ERROR_UPDATE_EVALUATION = "ERROR_UPDATE_EVALUATION";
  public static final String ERROR_SET_STATUS_OF_STUDENT = "ERROR_SET_STATUS_OF_STUDENT";
  public static final String ERROR_GET_STATUS_OF_STUDENT = "ERROR_GET_STATUS_OF_STUDENT";
  public static final String ERROR_GET_DISCIPLINE_OF_STUDENT_BY_STATUS =
      "ERROR_GET_DISCIPLINE_OF_STUDENT_BY_STATUS";
  public static final String ERROR_GET_EVALUATION_OF_STUDENT_BY_STATUS =
      "ERROR_GET_EVALUATION_OF_STUDENT_BY_STATUS";
  public static final String ERROR_GET_EVALUATION_OF_DISCIPLINE_BY_STATUS =
      "ERROR_GET_EVALUATION_OF_DISCIPLINE_BY_STATUS";

  // message for TeacherMySql
  public static final String ERROR_SET_POSITION_FOR_TEACHER = "ERROR_SET_POSITION_FOR_TEACHER";
  public static final String
      ERROR_CREATE_POSITION_FOR_TEACHER =
      "ERROR_CREATE_POSITION_FOR_TEACHER";
  public static final String CREATE_POSITION_FOR_TEACHER = "CREATE_POSITION_FOR_TEACHER";
  public static final String ERROR_GET_POSITION_FOR_TEACHER = "ERROR_GET_POSITION_FOR_TEACHER";
  public static final String HAVE_NOT_TEACHER = "HAVE_NOT_TEACHER";


  private Message() {
    resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME);
  }

  public static Message getInstance() {
    return INSTANCE;
  }

  public String getMessage(String key) {
    return resourceBundle.getString(key);
  }
}
