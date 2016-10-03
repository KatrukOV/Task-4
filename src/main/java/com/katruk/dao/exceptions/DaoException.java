package com.katruk.dao.exceptions;

import java.sql.SQLException;

public class DaoException extends SQLException {

	public DaoException() {}

	public DaoException(String reason, Throwable cause) {
		super(reason, cause);
	}

	public DaoException(String reason) {
		super(reason);
	}
}
