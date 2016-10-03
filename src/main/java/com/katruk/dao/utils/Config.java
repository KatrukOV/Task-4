package com.katruk.dao.utils;

import java.util.ResourceBundle;

public class Config {

	private static Config instance;
	private ResourceBundle configFile;
	private static final String BUNDLE_NAME = "config";

	public static final String DRIVER = "DRIVER";
	public static final String URL = "URL";

	public static final String INDEX = "INDEX";
	public static final String REGISTRATION = "REGISTRATION";
	public static final String REGISTRATION_OK = "REGISTRATION_OK";
	public static final String REGISTRATION_ERROR = "REGISTRATION_ERROR";

    public static final String ADMIN_PROFILE = "ADMIN_PROFILE";
    public static final String ALL_HUMANS = "ALL_HUMANS";
    public static final String ALL_STUDENTS = "ALL_STUDENTS";
    public static final String ALL_TEACHERS = "ALL_TEACHERS";

    public static final String TEACHER_PROFILE = "TEACHER_PROFILE";
    public static final String TEACHER_DISCIPLINES = "TEACHER_DISCIPLINES";
    public static final String TEACHER_EVALUATION = "TEACHER_EVALUATION";
    public static final String TEACHER_CONFIRMED = "TEACHER_CONFIRMED";

    public static final String STUDENT_PROFILE = "STUDENT_PROFILE";
	public static final String STUDENT_MARKS = "STUDENT_MARKS";
	public static final String STUDENT_DECLARED_DISCIPLINES = "STUDENT_DECLARED_DISCIPLINES";

    public static final String DISCIPLINES = "DISCIPLINES";

	public static final String ERROR_PAGE = "ERROR";

	private Config() {
		configFile = ResourceBundle.getBundle(BUNDLE_NAME);
	}


	public static Config getInstance() {
		if (instance == null) {
	        instance = new Config();
		}
		return instance;
	}

	public String getValue(String key) {
		return configFile.getString(key);
	}

}
