package com.katruk.domain.logic;

import com.katruk.domain.entity.Admin;
import com.katruk.domain.entity.Human;
import com.katruk.domain.entity.Student;
import com.katruk.domain.entity.Teacher;

public class HumanFactory {

  /**
   * this single factory
   */
  private static HumanFactory humanFactory;

  /**
   * hidden constructor
   */
  private HumanFactory() {
  }

  /**
   * for get single HumanFactory
   *
   * @return HumanFactory
   */
  public static HumanFactory getInstance() {
    if (humanFactory == null) {
      synchronized (HumanFactory.class) {
        if (humanFactory == null) {
          humanFactory = new HumanFactory();
        }
      }
    }
    return humanFactory;
  }

  /**
   * create different human
   *
   * @param role type of human
   * @return one of human
   */
  public Human create(Human.Role role) {
    Human human = null;
//		System.out.println(">>>>>>>>>>>>>>>>>>factory role"+role);
    switch (role) {
      case ADMIN: {
        human = new Admin();
        break;
      }
      case TEACHER: {
        human = new Teacher();
        break;
      }
      case STUDENT: {
        human = new Student();
        break;
      }
    }
    return human;
  }
}
