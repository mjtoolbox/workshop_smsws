package com.workshop.service;

import com.workshop.bean.Student;
import com.workshop.dao.SequenceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mijo on 2016-07-05.
 */
@Service
public class StudentService {

    @Autowired
    private MongoTemplate mongoOperation;
    @Autowired
    private SequenceImpl sequence;

    public StudentService() {
    }

    public List<Student> findAll() {
        return mongoOperation.findAll(Student.class);
    }

    public Student findStudentById(String anId) {
        Query query = new Query(Criteria.where("id").is(anId));
        return mongoOperation.findOne(query, Student.class);
    }

    public Student findStudentByName(String aName) {
        Query query = new Query(Criteria.where("name").is(aName));
        return mongoOperation.findOne(query, Student.class);
    }

    public void removeStudentById(String anId) {
        Query query = new Query(Criteria.where("id").is(anId));
        mongoOperation.remove(query, Student.class);
    }

    public void saveOrUpdateStudent(Student aStudent) {
        if (aStudent.get_id() != null) {
            //Update
            Update update = new Update();
            update.set("name", aStudent.getName());
            update.set("age", aStudent.getAge());
            update.set("gender", aStudent.getGender());
            update.set("province", aStudent.getProvince());

            Query query = new Query(Criteria.where("id").is(aStudent.getId()));

            mongoOperation.updateFirst(query, update, Student.class);
        } else {
            //Insert - studentid is key for "sequence" collection that I created in MongoDB
            aStudent.setId( sequence.getNextSequenceId("studentid") );
            mongoOperation.save(aStudent);
        }

    }
}
