package com.katruk.ui.commands.getAll;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Student;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetAllStudentsCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(GetAllStudentsCommand.class);

	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            List<Human> studentList = daoFactory.getHumanDAO().getAllByRole(Human.Role.STUDENT);
            for(Human human: studentList){
                ((Student)human).setContract(daoFactory.getStudentDAO().getContractForStudent(human));
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
