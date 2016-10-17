package com.katruk.dao.sql.statment;

public interface TeacherPrepareStatement {

  String SET_POSITION_FOR_TEACHER =
      "UPDATE teacher SET position = ? "
      + "WHERE human_id = (SELECT id FROM human WHERE login = ?);";

  String IS_POSITION_FOR_TEACHER =
      "SELECT human_id "
      + "FROM teacher "
      + "WHERE human_id = (SELECT id FROM human WHERE login = ?);";

  String INSERT_POSITION_FOR_TEACHER =
      "INSERT INTO teacher (position, human_id) "
      + "VALUES (?, (SELECT id FROM human WHERE login = ?));";

  String GET_POSITION_OF_TEACHER =
      "SELECT position "
      + "FROM teacher "
      + "WHERE human_id = (SELECT id FROM human WHERE login = ?);";
}
