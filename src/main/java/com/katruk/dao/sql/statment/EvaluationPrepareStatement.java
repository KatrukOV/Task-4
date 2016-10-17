package com.katruk.dao.sql.statment;

public interface EvaluationPrepareStatement {

  String GET_ALL =
      "SELECT * "
      + "FROM evaluation;";

  String GET_BY_DISCIPLINE_AND_STUDENT =
      "SELECT * "
      + "FROM evaluation "
      + "WHERE discipline_id = ? AND student_human_id = ?;";

  String GET_BY_ID =
      "SELECT * "
      + "FROM evaluation "
      + "WHERE id = ?;";

  String CREATE =
      "INSERT INTO "
      + "evaluation (discipline_id, student_human_id, statusInDiscipline, mark, feedback) "
      + "VALUES (?, ?, ?, ?, ?);";

  String REMOVE =
      "DELETE FROM evaluation "
      + "WHERE id = ?;";

  String UPDATE =
      "UPDATE evaluation SET "
      + "statusInDiscipline = ?, mark = ?, feedback = ? "
      + "WHERE discipline_id = ? AND student_human_id = ?;";

  String SET_STATUS_FOR_STUDENT_IN_DISCIPLINE =
      "UPDATE evaluation SET statusInDiscipline = ? "
      + "WHERE discipline_id = ? AND student_human_id = ?;";

  String GET_STATUS_OF_STUDENT_IN_DISCIPLINE =
      "SELECT statusInDiscipline "
      + "FROM evaluation "
      + "WHERE discipline_id = ? AND student_human_id = ?;";

  String GET_DISCIPLINES_BY_STATUS =
      "SELECT discipline_id "
      + "FROM evaluation "
      + "WHERE student_human_id = ? AND statusInDiscipline = ?;";

  String GET_BY_STUDENT_AND_STATUS =
      "SELECT id, discipline_id, student_human_id, statusInDiscipline, mark, feedback "
      + "FROM evaluation "
      + "WHERE student_human_id = ? AND statusInDiscipline = ?;";

  String GET_BY_DISCIPLINE_AND_STATUS =
      "SELECT id, discipline_id, student_human_id, statusInDiscipline, mark, feedback "
      + "FROM evaluation "
      + "WHERE discipline_id = ? AND statusInDiscipline = ?;";
}
