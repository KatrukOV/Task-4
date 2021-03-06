package com.katruk.dao;

import com.katruk.dao.exceptions.DaoException;
import com.katruk.domain.entity.Human;

import java.util.List;

public interface HumanDAO extends IDAO<Human> {

  public Human get(String login) throws DaoException;

  public void setRole(Human human, Human.Role role) throws DaoException;

  public List<Human> getAllByRole(Human.Role role) throws DaoException;

}
