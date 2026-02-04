package com.bpi.module6;

import java.util.ArrayList;
import java.util.List;

import com.bpi.module6.model.Courses;
import com.bpi.module6.model.Students;
import com.bpi.module6.util.EntityManagerUtil;

import jakarta.persistence.EntityManager;

/**
 * Hello world!	
 *
 */
public class App 
{
	
    public static void main( String[] args ){
    	EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

    	try {
			M6Activity5.selectAllStudents(em);
			M6Activity5.countCoursesByStudentId(em);
			M6Activity5.findStudentsByAgeGreaterThan(em);

    			} finally {
    				EntityManagerUtil.getInstance().closeEntityManager(em);
    				EntityManagerUtil.getInstance().shutdownFactory();
    			}
    		}

    		static void runM6Activity2(EntityManager em) {
    			
    			try {
    				em.getTransaction().begin();

    				Students newStudent = new Students();
    				newStudent.setName("Juan Dela Cruz");
    				newStudent.setAge(50);
    				newStudent.setEmail("juandelacruz1@gmail.com");

    				em.persist(newStudent);
    				em.flush();
    				System.out.println("is newStudent inside the persistence context: " + em.contains(newStudent));

    			} finally {
    				
    			}
    		}	
    		
    		static void runM6Activity4(EntityManager em) {
    			
    			try {
    				em.getTransaction().begin();

    				Students newStudent = new Students();
    				newStudent.setName("Juan Dela Cruz");
    				newStudent.setAge(50);
    				newStudent.setEmail("juandelacruz4@gmail.com");

    				em.persist(newStudent);
    				em.flush();
    				em.detach(newStudent);
    				System.out.println("is newStudent inside the persistence context: " + em.contains(newStudent));
    				em.merge(newStudent);
    				
    				Students studentUpdate = em.merge(newStudent);
    				studentUpdate.setAge(25);
    				em.flush();
    				System.out.println("is newStudent inside the persistence context: " + em.contains(studentUpdate));
    				em.remove(studentUpdate);    				
    				em.flush();
    				System.out.println("is newstudent inside the persistence context: " + em.contains(studentUpdate)); 
//    				em.getTransaction().commit();
    			} finally {
    				
    			}
    		}	
    		
    		static void detachSample(EntityManager em) {
    			
    			em.getTransaction().begin();
    			
    			Students student = em.find(Students.class, 1L); //managed
    			
    			em.detach(student); // detached
    			
    			student.setAge(100);
    			
    			em.getTransaction().commit(); // no update statement generated because student is detached
    			
    		}
    		
    		static void persistOnetoMany(EntityManager em) {
    			em.getTransaction().begin();
    			Students student = em.find(Students.class, 1L);
    			
    			Courses newCourse = new Courses();
    			newCourse.setCourseName("Superhero Course");
    			newCourse.setGrade("87");
    			newCourse.setStudent(student);
    			
    			em.persist(newCourse);
    			
    			List<Courses> student1Courses = new ArrayList<>();
    			student1Courses.add(newCourse);
    			
    			student.setCourses(student1Courses);
    			em.getTransaction().commit();
    		}
    		
    		static void runBidirectional(EntityManager em) {

    				em.getTransaction().begin();
    				
    				Students student = em.find(Students.class, 1L);
    				
    				student.getCourses().forEach(course -> System.out.print(course.getCourseName()));
    				
    				em.getTransaction().commit();
    		}
  	
}
