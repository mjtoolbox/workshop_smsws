import com.mongodb.*;
import com.workshop.bean.Student;
import com.workshop.config.SpringMongoConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by mijo on 2016-10-12.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations="/applicationContext.xml")
public class TestStudentService {

    @Autowired
    private MongoTemplate mongoOperation;


    @BeforeClass
    public static void initStudentService() {
    }


    @Test
    public void testDisplayStudents() {
/*
        DBCollection table = mongoDbFactory.getDb().getCollection("students");
        DBCursor cursor =  table.find().sort( new BasicDBObject("id", 1));
        while (cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
        cursor.close();
*/
        List<Student> studentList = mongoOperation.findAll(Student.class);
        for (Student aStudent: studentList){
            System.out.println(aStudent);
        }
    }


    @Test
    public void testFindById() {
        Query query = new Query(Criteria.where("id").is("1008"));
        Student student = (Student) mongoOperation.findOne(query, Student.class);
        System.out.println("1008 student is " + student);
    }


/*
    @Test
    public void testSave(){
        // Insert new record

        BasicDBObject document = new BasicDBObject();
        document.put("id", "1008");
        document.put("name", "Jim");
        document.put("age", 37);
        document.put("gender", "M");
        document.put("province", "BC");
        DBCollection table = mongoDbFactory.getDb().getCollection("students");
        table.save(document);
        Assert.assertEquals(table.find().size(),9 );
//        Student aStudent = new Student( 1009, "Donna", 31, "F", "ON");
//        mongoOperation.save(aStudent);
    }
*/








// JDK 8 demon below
/*
    @Test
    public void testFindStudents() {

        System.out.println("********** Before JDK 8 : Find over 30 BC Male count ***********");
        List<Student> studentList = studentService.findAll();
        ArrayList<Student> newList = new ArrayList<Student>();

        for (Student aStudent: studentList){
            if ( aStudent.getAge() > 30 && aStudent.getGender().equals("M") && aStudent.getProvince().equals("BC") )
            {
                newList.add(aStudent);
                System.out.println("Adding to new list: " + aStudent);
            }
        }

        System.out.println("Total list size: " + newList.size());

        Assert.assertNotNull(newList.size());
    }


    @Test
    public void testFindStudentsWithStream() {

        System.out.println("********** JDK 8 : Find over 30 BC Male count ***********");
        long size = studentService.findAll().stream()
                .filter(aStudent -> aStudent.getAge() > 30)
                .filter(aStudent -> aStudent.getGender().equals("M"))
                .filter(aStudent -> aStudent.getProvince().equals("BC"))
                .count();

        System.out.println("Total list size: " + size);
        Assert.assertNotNull(size);
    }

    @Test
    public void testAverageAgeWithStream() {

        System.out.println("********** JDK 8 : Find average age of Females ***********");
        double avgAge = studentService.findAll().stream()
                .filter(aStudent -> aStudent.getGender().equals("F"))
                .mapToInt(aStudent-> aStudent.getAge())
                .average()
                .getAsDouble();

        System.out.println("Average age of Female is : " + avgAge);
        Assert.assertNotNull(avgAge);
    }

    @Test
    public void testSortedByAgeGroupByStateStream() {

        System.out.println("********** JDK 8 : Group by Province and sort by age within the group ***********");
        Map<String, List<Student>> newList = studentService.findAll().stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.groupingBy(Student::getProvince));

        newList.entrySet().stream()
                .forEach( aList -> aList.getValue()
                        .forEach( aStudent -> System.out.println(aStudent)));

        Assert.assertNotNull(newList);
    }
*/
}
