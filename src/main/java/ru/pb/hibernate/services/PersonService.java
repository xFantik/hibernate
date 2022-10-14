package ru.pb.hibernate.services;

import org.springframework.stereotype.Component;
import ru.pb.hibernate.dao.PersonDao;
import ru.pb.hibernate.entity.Person;

import java.util.List;

@Component
public class PersonService {


    private PersonDao personDao;


    //Вместо @Autowired
    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public String getNameById(long id) {
        return personDao.findById(id).getName();
    }

    public Person getProduct(long id){
        return personDao.findById(id);
    }
//    @Autowired
//    public void setRepository(ProductRepository inMemoryRepository) {
//        this.inMemoryRepository = inMemoryRepository;
//    }

    public List<Person> findAll(){
        return personDao.findAll();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Доступные товары:\n");
        Person p;
        List<Person> products =personDao.findAll();
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            sb.append(p.getId()).append(" ").append(p.getName()).append(", ");
        }
        sb.append("\n");

        return sb.toString();
    }


}
