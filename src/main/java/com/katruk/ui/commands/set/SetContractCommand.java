package com.katruk.ui.commands.set;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.human.Human;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class SetContractCommand implements ICommand, PageAttribute {

	private static final Logger logger = Logger.getLogger(SetContractCommand.class);

	private String page;

    private DaoFactory daoFactory = DaoFactory.getDAOFactory();


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
            boolean contract = Boolean.valueOf(request.getParameter(CONTRACT));
            System.out.println(">>>>>>>>>>>> contract= " + contract);
            Human human = daoFactory.getHumanDAO().get(request.getParameter(LOGIN));
            System.out.println(">>>>>>>>>>>> student= " + human);

            daoFactory.getStudentDAO().setContractForStudent(human, contract);

            List<Human> studentList = daoFactory.getStudentDAO().getAllByRole(Human.Role.STUDENT);
            request.setAttribute("studentList", studentList);

			page = Config.getInstance().getValue(Config.ALL_STUDENTS);

			logger.info(String.format("set contract=%s for student= %s", contract, human));
		} catch (Exception e) {
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error("Unable set contract for student", e);
		}
		return page;
	}
}