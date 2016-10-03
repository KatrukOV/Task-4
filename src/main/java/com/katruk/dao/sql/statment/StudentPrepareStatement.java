package com.katruk.dao.sql.statment;

public interface StudentPrepareStatement {
	String SET_CONTRACT_FOR_STUDENT = "UPDATE student SET contract = ? WHERE human_id = (SELECT id FROM human WHERE login = ?);";
	String INSERT_CONTRACT_FOR_STUDENT = "INSERT INTO student (contract, human_id) VALUES (?, (SELECT id FROM human WHERE login = ?));";
	String IS_CONTRACT_FOR_STUDENT = "SELECT human_id FROM student WHERE human_id = (SELECT id FROM human WHERE login = ?);";
    String GET_CONTRACT_OF_STUDENT = "SELECT contract FROM student WHERE human_id = ?;";
    String GET_ALL_STUDENT_FOR_DISCIPLINE = "SELECT student_human_id FROM evaluation WHERE discipline_id = ?;";
}
