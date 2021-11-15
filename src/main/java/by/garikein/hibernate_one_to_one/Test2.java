package by.garikein.hibernate_one_to_one;

import by.garikein.hibernate_one_to_one.entity.Detail;
import by.garikein.hibernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test2 {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {

            session = factory.getCurrentSession();

            Employee employee = new Employee("Nicolya", "Ivanov", "HR", 850);
            Detail detail = new Detail("New-York", "31283911512", "nikolya@gmail.com");

            employee.setEmpDetail(detail);
            detail.setEmployee(employee);

            session.beginTransaction();
            session.save(detail);
            session.getTransaction().commit();

            System.out.println("Done!");

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//
//            Detail detail = session.get(Detail.class, 2);
//            detail.getEmployee().setEmpDetail(null);
//            session.delete(detail);
//
//            session.getTransaction().commit();
//
//            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
