package com.bpi.module6;



import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class M6Activity5 {

	static void selectAllStudents(EntityManager em) {
		em.getTransaction().begin();
		
		String jpql = "Select s.name FROM Students s";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		List<String> studentNames = query.getResultList();
		
		studentNames.forEach(name -> System.out.println(name));
		
		em.getTransaction().commit();		
	}
	
	static Long countCoursesByStudentId(EntityManager em) {
		
//		Long courseCount = em.createQuery("Select COUNT(s) from Courses JOIN s.Students c", Long.class).getSingleResult();
//		Long studentsWithCourses = em.createQuery("SELECT COUNT(c) FROM Students s JOIN s.courses c where s.id = 1", Long.class).getSingleResult();
//		System.out.println(studentsWithCourses);
		Long studentsWithCourses = em.createQuery("SELECT COUNT(c) FROM Courses c JOIN c.student s WHERE s.id = 1", Long.class).getSingleResult();
		System.out.println("Number of Courses by Student with ID 1: "+studentsWithCourses);
		return studentsWithCourses;
		
//		return courseCount;
		
	}
	static Long findStudentsByAgeGreaterThan(EntityManager em) {
		
		Long studentAge = em.createQuery("Select COUNT(s) FROM Students s WHERE s.age >= 23", Long.class).getSingleResult();
		System.out.println("Students that are age 23 and above: " + studentAge);
		return studentAge;
		
	}
}
