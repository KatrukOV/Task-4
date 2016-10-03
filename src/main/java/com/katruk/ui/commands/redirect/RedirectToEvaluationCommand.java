package com.katruk.ui.commands.redirect;


import com.katruk.dao.DaoFactory;
import com.katruk.dao.utils.Config;
import com.katruk.domen.entity.Discipline;
import com.katruk.domen.entity.Evaluation;
import com.katruk.ui.PageAttribute;
import com.katruk.ui.commands.ICommand;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectToEvaluationCommand implements ICommand, PageAttribute {

    private static final Logger logger = Logger.getLogger(RedirectToEvaluationCommand.class);

    private String page;
    private DaoFactory daoFactory = DaoFactory.getDAOFactory();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Discipline discipline = daoFactory.getDisciplineDAO().get(request.getParameter(TITLE));
            System.out.println("title in eval"+request.getParameter(TITLE));
//            HttpSession session = request.getSession();
            System.out.println("dis in exec="+discipline);
            List<Evaluation> evaluationList = daoFactory.getEvaluationDAO().getByDisciplineAndStatus(
                    discipline, Evaluation.StatusInDiscipline.CONFIRMED);

            if (evaluationList != null) {
                request.setAttribute(EVALUATION_LIST, evaluationList);
                logger.info(String.format("show all CONFIRMED evaluations"));
            }

            page = Config.getInstance().getValue(Config.TEACHER_EVALUATION);
        } catch (Exception e) {
            page = Config.getInstance().getValue(Config.ERROR_PAGE);
            logger.error("show all declared Disciplines", e);
        }
        return page;
    }
}
