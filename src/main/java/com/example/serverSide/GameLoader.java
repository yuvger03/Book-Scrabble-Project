package com.example.serverSide;
import java.sql.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class GameLoader {
     public static void main(String[] args) {
         // Build the Hibernate session factory
         SessionFactory sessionFactory = new Configuration()
                 .configure("hibernate.cfg.xml")
                 .buildSessionFactory();

         // Create a new person
//         Person person = new Person();
//         person.setName("John Doe");

         // Save the person to the database
         try (Session session = sessionFactory.openSession()) {
             session.beginTransaction();
             int a =0;
             session.save(a);
             session.getTransaction().commit();
         }

         // Close the session factory
         sessionFactory.close();
     }
 }