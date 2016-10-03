package com.katruk.ui.commands.getAll;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.domen.entity.human.Teacher;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetAllTeachersCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(GetAllTeachersCommand.class);
	private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Human> teacherList = daoFactory.getHumanDAO().getAllByRole(Human.Role.TEACHER);
            for(Human human: teacherList){
                ((Teacher)human).setPosition(daoFactory.getTeacherDAO().getPositionForTeacher(human));
            }
            request.setAttribute(TEACHER_LIST, teacherList);

            page = Config.getInstance().getValue(Config.ALL_TEACHERS);
            logger.info(String.format("get all students = %d", teacherList.size()));
        } catch (Exception e) {
            page = Config.getInstance().getValue(Config.ERROR_PAGE);
            logger.error("Unable get all students", e);
        }
        return page;
	}
}
