package com.bpi.module6;

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
    				runM6Activity2(em);
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

}
