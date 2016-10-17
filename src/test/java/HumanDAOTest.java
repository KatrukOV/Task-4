import com.katruk.dao.interfase.DisciplineDAO;
import com.katruk.dao.interfase.HumanDAO;
import com.katruk.domain.entity.Discipline;
import com.katruk.domain.entity.human.Human;

public class HumanDAOTest {


    private DisciplineDAO disciplineDAO;
    private HumanDAO humanDAO;
    private Discipline tempDiscipline;
    private Human student;
/*
    @Before
    public void init() throws SQLException {
        disciplineDAO = new DisciplineDataBaseDAO();
        humanDAO = new HumanDataBaseDAO();
        student = new Student("Student4Test", "student", "Johny", "Doe", "Djorich");
        Teacher teacher = new Teacher("username", "password", "JohnT", "DoeT", "DjorichT");
		teacher.setId(0);
        tempDiscipline = new Discipline("Test Discipline", teacher.getId());
    }

    @Test
    public void getAllTest(){
        int size = humanDAO.getAll().size();
        Assert.assertEquals(humanDAO.getAll().size(), size);
        Assert.assertTrue(humanDAO.create(student));
        Assert.assertEquals(humanDAO.getAll().size(), size + 1);
        student = humanDAO.get(student.getLogin());
        Assert.assertTrue(humanDAO.remove(student));
    }

//    @Test
//    public void getAllStudentsForTest() {
//        Assert.assertEquals(humanDAO.getAllStudentsFor(tempDiscipline).size(), 0);
//    }

    @Test
    public void  getTest(){
        Assert.assertEquals(humanDAO.get(student.getLogin()), null);
        Assert.assertTrue(humanDAO.create(student));
        Assert.assertNotEquals(humanDAO.get(student.getLogin()).getId(), 0);
        student = humanDAO.get(student.getLogin());
        Assert.assertTrue(humanDAO.remove(student));
    }

    @Test
    public void createTest(){
        Assert.assertEquals(humanDAO.get(student.getLogin()), null);
        Assert.assertTrue(humanDAO.create(student));
        Assert.assertNotNull(humanDAO.get(student.getLogin()));
        Assert.assertNotEquals(humanDAO.get(student.getLogin()).getId(), 0);
        student = humanDAO.get(student.getLogin());
        Assert.assertTrue(humanDAO.remove(student));
    }

    @Test
    public void removeTest() {
        Assert.assertTrue(humanDAO.remove(student));
        Assert.assertTrue(humanDAO.create(student));
        student = humanDAO.get(student.getLogin());
        Assert.assertTrue(humanDAO.remove(student));
        Assert.assertTrue(humanDAO.remove(student));
    }

    @Test
    public void updateTest(){
        Assert.assertTrue(humanDAO.create(student));
        student = humanDAO.get(student.getLogin());
        student.setName("Alice");
        Assert.assertTrue(humanDAO.update(student));
        Assert.assertEquals(humanDAO.get(student.getLogin()).getName(), "Alice");
        Assert.assertTrue(humanDAO.remove(student));
    }
*/
}
