package ru.pb.hibernate;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.pb.hibernate.entity.Order;
import ru.pb.hibernate.entity.Person;
import ru.pb.hibernate.services.OrderService;
import ru.pb.hibernate.services.PersonService;

import java.util.List;


public class Main {

    static private PersonService personService;
    private static OrderService orderService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("ru.pb.hibernate");

        personService = context.getBean(PersonService.class);
        orderService = context.getBean(OrderService.class);




        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
            try {
//                PersonDao personDao = new PersonDao(sessionFactoryUtils);
//                OrderDao orderDao = new OrderDao((sessionFactoryUtils));

//                List<Person> personList=personDao.findAll();
                List<Person> personList=personService.findAll();
                for (Person person : personList) {
//                    List<Order> orders = orderDao.findByPersonId(person.getId());
                    List<Order> orders = orderService.findByPersonId(person.getId());
                    System.out.println();
                    System.out.println(person.getName() + ", заказы:");
                    for (Order order : orders) {
                        System.out.println(order.getName()+": ");
                        System.out.println("     "+order.getProducts());
                    }
                    System.out.println("\n\n");
                }
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                sessionFactoryUtils.shotdown();
            }
    }
}
