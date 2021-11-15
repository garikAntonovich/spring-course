package by.garikein.hibernate_one_to_one;

import by.garikein.hibernate_one_to_one.entity.Detail;
import by.garikein.hibernate_one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();

            Employee employee = new Employee("Ivan", "Ivanov", "IT", 500);
            Detail detail = new Detail("Baku", "123456789", "ivan_ivanov@gmail.com");
            employee.setEmpDetail(detail);

            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();


//            Session session = factory.getCurrentSession();
//
//            Employee employee = new Employee("Oleg", "Smirnov", "Sales", 700);
//            Detail detail = new Detail("Moscow", "987654321", "olejka@gmail.com");
//            employee.setEmpDetail(detail);
//
//            session.beginTransaction();
//            session.save(employee);
//            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee employee2 = session.get(Employee.class, 1);
            System.out.println(employee2.getEmpDetail());

            session.getTransaction().commit();


            session = factory.getCurrentSession();
            session.beginTransaction();

            Employee employee3 = session.get(Employee.class, 1);

            session.delete(employee3);
            session.getTransaction().commit();
            System.out.println("Done!");
        } finally {
            session.close();
            factory.close();
        }
    }
}
