package com.katruk.domain.logic;
import com.katruk.domain.entity.human.Admin;
import com.katruk.domain.entity.human.Human;
import com.katruk.domain.entity.human.Student;
import com.katruk.domain.entity.human.Teacher;

public class HumanFactory {

	/**
	 * this single factory
	 */
	private static HumanFactory humanFactory;

	/**
	 * hidden constructor
	 */
	private HumanFactory() {}

	/**
	 * for get single HumanFactory
	 * @return 	HumanFactory
	 */
	public static HumanFactory getInstance(){
		if (humanFactory == null) {
			synchronized (HumanFactory.class) {
				if (humanFactory == null) { humanFactory = new HumanFactory(); }
			}
		}
		return humanFactory;
	}

	/**
	 * create different human
	 * @param role		type of human
	 * @return			one of human
	 */
	public Human create(Human.Role role){
		Human human = null;
//		System.out.println(">>>>>>>>>>>>>>>>>>factory role"+role);
		switch (role){
			case ADMIN:		{ human = new Admin();  	break;}
			case TEACHER:	{ human = new Teacher();	break;}
			case STUDENT:	{ human = new Student();	break;}
		}
		return human;
	}
}
