package com.katruk.ui.commands.set.status;

import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.Evaluation;
import com.katruk.domen.entity.human.Student;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class RevokedCommand implements ICommand, PageAttribute {

	private static final String ERROR_LOGIN = "Can't revoked discipline";
	private static final Logger logger = Logger.getLogger(RevokedCommand.class);
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

	private String page;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession();
            String title = request.getParameter(TITLE);

            System.out.println("title="+title);

            Student student = (Student) session.getAttribute(USER);

            Discipline discipline = daoFactory.getDisciplineDAO().get(title);

            Evaluation.StatusInDiscipline status = daoFactory.getEvaluationDAO().getStatusForStudent(student, discipline);

            if (!status.equals(Evaluation.StatusInDiscipline.DELETED)) {
                daoFactory.getEvaluationDAO().setStatusForStudent(student, discipline,
                        Evaluation.StatusInDiscipline.REVOKED);
            } else {
                request.getSession().setAttribute(ERROR,"YOU Can't set status");
            }

            List<Discipline> disciplineList = daoFactory.getEvaluationDAO().getDisciplinesByStatus(student,
                    Evaluation.StatusInDiscipline.DECLARED);;
                if (disciplineList != null) {
                    request.setAttribute("allDeclaredDisciplines", disciplineList);
//                    logger.info(String.format("all declared Disciplines"));
                }

            page = Config.getInstance().getValue(Config.STUDENT_DECLARED_DISCIPLINES);
            System.out.println("OK set eval");
		} catch (Exception e) {
			request.setAttribute("error", ERROR_LOGIN);
			page = Config.getInstance().getValue(Config.ERROR_PAGE);
			logger.error(ERROR_LOGIN, e);
		}
		return page;
	}
}