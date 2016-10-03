package com.katruk.ui.controllers;

import com.katruk.ui.commands.*;
import com.katruk.ui.commands.getAll.GetAllDisciplinesCommand;
import com.katruk.ui.commands.getAll.GetAllHumansCommand;
import com.katruk.ui.commands.getAll.GetAllStudentsCommand;
import com.katruk.ui.commands.getAll.GetAllTeachersCommand;
import com.katruk.ui.commands.redirect.*;
import com.katruk.ui.commands.set.*;
import com.katruk.ui.commands.set.status.ConfirmedCommand;
import com.katruk.ui.commands.set.status.DeclaredCommand;
import com.katruk.ui.commands.set.status.DeletedCommand;
import com.katruk.ui.commands.set.status.RevokedCommand;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public final class DispatcherHelper {

	private static DispatcherHelper instance;
	private HashMap<String, ICommand> commands;

	private DispatcherHelper() {
		commands = new HashMap<String, ICommand>();

		commands.put("login", new LoginCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("logout", new LogoutCommand());
        commands.put("redirectToProfile", new RedirectToProfileCommand());

        commands.put("getAllHumans", new GetAllHumansCommand());
        commands.put("getAllStudents", new GetAllStudentsCommand());
        commands.put("getAllTeachers", new GetAllTeachersCommand());
        commands.put("getAllDisciplines", new GetAllDisciplinesCommand());

        commands.put("setRole", new SetRoleCommand());
        commands.put("setPosition", new SetPositionCommand());
        commands.put("setContract", new SetContractCommand());

        commands.put("redirectToTeacherDisciplines", new RedirectToTeacherDisciplinesCommand());
        commands.put("redirectToConfirmed", new RedirectToConfirmedCommand());
        commands.put("redirectToEvaluation", new RedirectToEvaluationCommand());
        commands.put("feedback", new FeedbackCommand());
        commands.put("addDiscipline", new AddDisciplineCommand());

		commands.put("redirectToMarks", new RedirectToMarksCommand());
		commands.put("redirectToDeclaredDisciplines", new RedirectToDeclaredDisciplinesCommand());

		commands.put("declared", new DeclaredCommand());
		commands.put("revoked", new RevokedCommand());
		commands.put("confirmed", new ConfirmedCommand());
		commands.put("deleted", new DeletedCommand());
	}

	public static DispatcherHelper getInstance() {
		if (instance == null) {
			synchronized (DispatcherHelper.class) {
				if (instance == null) {
					instance = new DispatcherHelper();
				}
			}
		}
		return instance;
	}

	public ICommand getCommand(HttpServletRequest request) {
		ICommand resultCommand = commands.get(request.getParameter("command"));
		if (resultCommand == null) {
			resultCommand = new UnknownCommand();
		}
		return resultCommand;
	}
}