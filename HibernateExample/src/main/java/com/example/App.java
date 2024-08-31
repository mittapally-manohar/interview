package com.example;

import com.example.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        // Step 2: Configure Hibernate settings directly in code
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hibernate");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.current_session_context_class", "thread");

        // Step 3: Add the annotated class
        configuration.addAnnotatedClass(Employee.class);

        // Step 4: Build the SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Step 5: Obtain a session and begin transaction
        Session session = sessionFactory.getCurrentSession();

        try {
            // Start transaction
            session.beginTransaction();

            // Create a new Employee object
            Employee employee = new Employee(3,"John", "Doe", "john.doe@example.com");

            // Save the Employee object
            session.save(employee);

            // Commit transaction
            session.getTransaction().commit();
        } finally {
            // Clean up by closing the session factory
            sessionFactory.close();
        }
    }

    public static void hibernateUsingConfigurationFile(){
        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();

        // Create Session
        Session session = factory.getCurrentSession();

        try {
            // Create a new employee object
            Employee tempEmployee = new Employee(1, "John", "Doe", "john.doe@example.com");

            // Start a transaction
            session.beginTransaction();

            // Save the employee object
            session.save(tempEmployee);

            // Commit the transaction
            session.getTransaction().commit();

            System.out.println("Employee saved successfully!");
        } finally {
            factory.close();
        }
    }
}
