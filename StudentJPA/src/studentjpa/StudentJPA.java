/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentjpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Student;

/**
 *
 * @author Thorny
 */
public class StudentJPA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student sd = new Student(64050156, "Pongsathorn",0.59);
        Student sd2 = new Student(64050155, "KUYA",4.00);
        Student sd3 = new Student(64050155, "ariSuSan",3.35);
        StudentTable.insertStudent(sd);
        StudentTable.insertStudent(sd2);
        
        System.out.println("Basic Data (Insert) \n");
        List<Student> stdList = StudentTable.findAllStudent();
        printAllStudent(stdList);
        
        StudentTable.removeStudent(sd);
        
        System.out.println("Changed Data (Delete) \n");
        stdList = StudentTable.findAllStudent(); // After Delete
        printAllStudent(stdList);
        
        StudentTable.updateStudent(sd3);
        
        System.out.println("Changed Data (Update) \n");
        stdList = StudentTable.findAllStudent(); // After Delete
        printAllStudent(stdList);
        
        
    }
        public static void printAllStudent(List<Student> employeeList) {
        for(Student std : employeeList) {
           System.out.print(std.getId() + " ");
           System.out.print(std.getName() + " ");
           System.out.println(std.getGpa() + " ");
       }
    }

    public static void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("StudentJPAPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
}
