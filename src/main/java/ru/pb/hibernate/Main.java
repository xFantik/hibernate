package ru.pb.hibernate;

public class Main {

    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
            try {
                ProductDao productDao =  new ProductDao(sessionFactoryUtils);

                System.out.println(productDao.findById(2l));

                System.out.println(productDao.findAll());
                productDao.delete(5l);


                System.out.println(productDao.findAll());



            } catch (Exception e){
                e.printStackTrace();
            } finally {
                sessionFactoryUtils.shotdown();
            }
    }
}
