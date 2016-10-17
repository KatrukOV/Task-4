package com.katruk.dao.sql.statment;

public interface HumanPrepareStatement {

  String GET_ALL =
      "SELECT id, login, password, name, last_name, patronymic, role "
      + "FROM human";

  String GET_BY_LOGIN =
      "SELECT id, login, password, name, last_name, patronymic, role "
      + "FROM human "
      + "WHERE login = ?;";

  String GET_BY_ID =
      "SELECT id, login, password, name, last_name, patronymic, role "
      + "FROM human "
      + "WHERE id = ?;";

  String CREATE =
      "INSERT INTO human (login, password, name, last_name, patronymic, role) "
      + "VALUES (?, ?, ?, ?, ?, ?);";

  String REMOVE =
      "DELETE "
      + "FROM human "
      + "WHERE id = ?;";

  String UPDATE =
      "UPDATE human SET "
      + "id = ?, login = ?, password = ?, name = ?, last_name = ?, patronymic = ?, role = ? "
      + "WHERE id = ?;";

  String SET_ROLE =
      "UPDATE human SET role = ? "
      + "WHERE login = ?;";

  String GET_ALL_BY_ROLE =
      "SELECT id, login, password, name, last_name, patronymic, role "
      + "FROM human "
      + "WHERE role = ?;";
}
