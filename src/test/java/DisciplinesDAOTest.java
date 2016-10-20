import com.katruk.dao.DisciplineDAO;
import com.katruk.dao.HumanDAO;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;

public class DisciplinesDAOTest {


  private DisciplineDAO disciplineDAO;
  private HumanDAO humanDAO;
  private Discipline tempDiscipline;
  private Human student;
  private Human teacher;
/*
    @Before
    public void init() throws SQLException {
        disciplineDAO = new DisciplineMySql();
        humanDAO = new HumanMySql();
        student = new Student("Student4Test", "student", "Johny", "Doe", "Djorich");
		student.setId(-1);
        teacher = new Teacher("login", "password", "John", "Doe", "Djorich");
		teacher.setId(-2);
        tempDiscipline = new Discipline("Test course", teacher.getId());
    }

    @Test
    public void getAllTest(){
        int size = disciplineDAO.getAll().size();
        Assert.assertEquals(disciplineDAO.getAll().size(), size);
        Assert.assertTrue(disciplineDAO.create(tempDiscipline));
        Assert.assertEquals(disciplineDAO.getAll().size(), size + 1);
        tempDiscipline = disciplineDAO.get(tempDiscipline.getTitle());
        Assert.assertTrue(disciplineDAO.remove(tempDiscipline));
        Assert.assertEquals(disciplineDAO.getAll().size(), size);
    }

    @Test
    public void collectFeedbackAndMarksForTest(){
        Assert.assertEquals(disciplineDAO.collectMarksAndFeedbackFor(student).size(), 0);
    }

    @Test
    public void getAllCoursesOfTest(){
        Assert.assertEquals(disciplineDAO.getAllDisciplinesOfTeacher(teacher).size(), 0);
    }

    @Test
    public void getAllCoursesForTest(){
        int size = disciplineDAO.getAll().size() - disciplineDAO.getAllAvailableDisciplinesFor(student).size();
        Assert.assertEquals(disciplineDAO.getAllDisciplinesForStudent(student).size(), size);
    }

    @Test
    public void getAllAvailableCoursesForTest(){
        int size = disciplineDAO.getAll().size() - disciplineDAO.getAllDisciplinesForStudent(student).size();
        Assert.assertEquals(disciplineDAO.getAllAvailableDisciplinesFor(student).size(), size);
    }

    @Test
    public void getFeedbackForStudentTest(){
        Assert.assertNull(disciplineDAO.getFeedBackForStudent(student, tempDiscipline, null));
    }

    @Test
    public void getMarkForStudentTest(){
        Assert.assertNull(disciplineDAO.getFeedBackForStudent(student, tempDiscipline, null));
    }

    @Test
    public void getTest(){
        Assert.assertTrue(disciplineDAO.create(tempDiscipline));
        Assert.assertNotNull(disciplineDAO.get(tempDiscipline.getTitle()));
        Assert.assertNotEquals(disciplineDAO.get(tempDiscipline.getTitle()).getId(), 0);
        tempDiscipline = disciplineDAO.get(tempDiscipline.getTitle());
        Assert.assertEquals(tempDiscipline, disciplineDAO.get(tempDiscipline.getId()));
        Assert.assertTrue(disciplineDAO.remove(tempDiscipline));
    }

    @Test
    public void createTest(){
        Assert.assertTrue(disciplineDAO.create(tempDiscipline));
        Assert.assertNotNull(disciplineDAO.get(tempDiscipline.getTitle()));
        tempDiscipline = disciplineDAO.get(tempDiscipline.getTitle());
        Assert.assertTrue(disciplineDAO.remove(tempDiscipline));
    }

    @Test
    public void removeTest(){
        int size = disciplineDAO.getAll().size();
        Assert.assertTrue(disciplineDAO.create(tempDiscipline));
        Assert.assertTrue(disciplineDAO.remove(disciplineDAO.get(tempDiscipline.getTitle())));
        Assert.assertEquals(disciplineDAO.getAll().size(), size);
    }

    @Test
    public void updateTest(){
        Assert.assertTrue(humanDAO.create(teacher));
        tempDiscipline.setTeacher(humanDAO.get(teacher.getLogin()).getId());
        Assert.assertTrue(disciplineDAO.create(tempDiscipline));
        Discipline savedDiscipline = disciplineDAO.get(tempDiscipline.getTitle());
        savedDiscipline.setTitle("Modified discipline");
        Assert.assertTrue(disciplineDAO.update(savedDiscipline));
        Discipline updatedDiscipline = disciplineDAO.get(savedDiscipline.getTitle());
        Assert.assertNotSame(tempDiscipline, updatedDiscipline);
        Assert.assertEquals("Modified discipline", updatedDiscipline.getTitle());
        Assert.assertTrue(disciplineDAO.remove(updatedDiscipline));
        Assert.assertTrue(humanDAO.remove(humanDAO.get(teacher.getLogin())));
    }

    @Test
    public void enrollTest(){
        Assert.assertTrue(disciplineDAO.enroll(tempDiscipline, student));
        Assert.assertTrue(disciplineDAO.unEnroll(tempDiscipline, student));
    }
*/
}
