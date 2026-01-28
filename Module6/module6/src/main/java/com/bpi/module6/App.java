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
	
    public static void main( String[] args )
    {
    	EntityManager em = EntityManagerUtil.getInstance().createEntityManager();

    	try {
    				persistOnetoMany(em);
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
    				newStudent.setEmail("juandelacruz@gmail.com");

    				em.persist(newStudent);
    				em.getTransaction().commit();
    			} finally {
    				
    			}
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
