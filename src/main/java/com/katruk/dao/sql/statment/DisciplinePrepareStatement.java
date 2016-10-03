package com.katruk.dao.sql.statment;

public interface DisciplinePrepareStatement {

	String GET_ALL = "SELECT * FROM discipline;";
	String GET_BY_TITLE = "SELECT * FROM discipline WHERE title = ?;";
	String GET_BY_ID = "SELECT * FROM discipline WHERE id = ?;";

	String CREATE = "INSERT INTO discipline (title, teacher_human_id) VALUES (?, ?);";
	String REMOVE = "DELETE FROM discipline WHERE id = ?;";
	String UPDATE = "UPDATE discipline SET id = ?, title = ?, teacher_human_id = ? WHERE id = ?;";

	String GET_ALL_DISCIPLINE_TEACHER = "SELECT d.id FROM discipline as d INNER JOIN human as h ON h.id = d.teacher_human_id WHERE h.login = ?;";
}
