package ru.pb.hibernate.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pb.hibernate.SessionFactoryUtils;
import ru.pb.hibernate.entity.Person;

import java.util.List;

@Component
public class PersonDao {

    @Autowired
    private SessionFactoryUtils sessionFactoryUtils;

    public PersonDao(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }


    public Person findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Person person = session.get(Person.class, id);
            session.getTransaction().commit();
            return person;
        }
    }


    public List<Person> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Person> persons = session.createQuery("select u from Person u").getResultList();
            session.getTransaction().commit();
            return persons;
        }
    }


    public Person findByTitle(String name) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Person persons = session.createQuery("select u from Person u where u.name = :name", Person.class)
                    .setParameter("name", name)
                    .getSingleResult();
            session.getTransaction().commit();
            return persons;
        }
    }


    public void saveOrUpdate(Person person) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            session.saveOrUpdate(person);
            session.getTransaction().commit();
        }
    }



    public void delete(long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            Person p = session.get(Person.class, id);
            if (p != null)
                session.delete(p);

            session.getTransaction().commit();
        }
    }


}
