package com.katruk.domain.commands.getAll;


import com.katruk.dao.utils.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.entity.Human;
import com.katruk.domain.entity.Student;
import com.katruk.ui.PageAttribute;

import org.apache.log4j.Logger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllStudentsCommand implements ICommand, PageAttribute {

  private static final Logger logger = Logger.getLogger(GetAllStudentsCommand.class);

  private String page;
  private DaoFactory daoFactory = DaoFactory.getDAOFactory();

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) {

    try {
      List<Human> studentList = daoFactory.getHumanDAO().getAllByRole(Human.Role.STUDENT);
      for (Human human : studentList) {
        ((Student) human).setContract(daoFactory.getStudentDAO().getContractForStudent(human));
      }

      request.setAttribute(STUDENT_LIST, studentList);

      page = Config.getInstance().getValue(Config.ALL_STUDENTS);
      logger.info(String.format("get all students = %d", studentList.size()));
    } catch (Exception e) {
      page = Config.getInstance().getValue(Config.ERROR_PAGE);
      logger.error("Unable get all students", e);
    }
    return page;
  }
}
