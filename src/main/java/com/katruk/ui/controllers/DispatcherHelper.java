package com.katruk.ui.controllers;

import com.katruk.domain.commands.AddDisciplineCommand;
import com.katruk.domain.commands.FeedbackCommand;
import com.katruk.domain.commands.ICommand;
import com.katruk.domain.commands.LoginCommand;
import com.katruk.domain.commands.LogoutCommand;
import com.katruk.domain.commands.RegistrationCommand;
import com.katruk.domain.commands.UnknownCommand;
import com.katruk.domain.commands.getAll.GetAllDisciplinesCommand;
import com.katruk.domain.commands.getAll.GetAllHumansCommand;
import com.katruk.domain.commands.getAll.GetAllStudentsCommand;
import com.katruk.domain.commands.getAll.GetAllTeachersCommand;
import com.katruk.domain.commands.redirect.RedirectToConfirmedCommand;
import com.katruk.domain.commands.redirect.RedirectToDeclaredDisciplinesCommand;
import com.katruk.domain.commands.redirect.RedirectToEvaluationCommand;
import com.katruk.domain.commands.redirect.RedirectToMarksCommand;
import com.katruk.domain.commands.redirect.RedirectToProfileCommand;
import com.katruk.domain.commands.redirect.RedirectToTeacherDisciplinesCommand;
import com.katruk.domain.commands.set.SetContractCommand;
import com.katruk.domain.commands.set.SetPositionCommand;
import com.katruk.domain.commands.set.SetRoleCommand;
import com.katruk.domain.commands.set.status.ConfirmedCommand;
import com.katruk.domain.commands.set.status.DeclaredCommand;
import com.katruk.domain.commands.set.status.DeletedCommand;
import com.katruk.domain.commands.set.status.RevokedCommand;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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