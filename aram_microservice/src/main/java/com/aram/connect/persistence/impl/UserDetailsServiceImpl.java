package com.aram.connect.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aram.connect.persistence.UserDetailsService;
import com.aram.connect.persistence.dao.Staff;
import com.aram.connect.persistence.dao.Student;
import com.aram.connect.persistence.dao.UserLogin;
import com.aram.connect.persistence.dao.UserRole;

@Transactional
@Repository
public class UserDetailsServiceImpl implements UserDetailsService {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public UserLogin getUserLoginDetails(int userId) {

		UserLogin user = em.find(UserLogin.class, userId);
		
		return user;
	}

	@Override
	public Student getStudentDetails(int studentId) {

		Student student = em.find(Student.class, studentId);
		
		return student;
	}

	@Override
	public Staff getStaffDetails(int staffId) {
		
		Staff staff = em.find(Staff.class, staffId);
		
		return staff;
	}

	@Override
	public UserLogin createUserLogin(UserLogin userLogin) {
		
		if(getUserLoginDetails(userLogin.getUserLoginId()) == null) {
			em.persist(userLogin);
		}else {
			em.merge(userLogin); 
		}

		return userLogin;
	}

	@Override
	public UserLogin getUserByUsername(String username) {

		UserLogin userLogin = new UserLogin();
		try {
			TypedQuery<UserLogin> query = em.createNamedQuery("UserLogin.getUserByUsername", UserLogin.class).setParameter("username", username);
			userLogin = query.getSingleResult();
		} catch(NoResultException e) {
			// e.printStackTrace();
			return null;
		}

		return userLogin;
	}

	@Override
	public UserRole getUserRole(int roleId) {
		UserRole userRole = em.find(UserRole.class, roleId);
		return userRole;
	}

	@Override
	public Student isStudentExist(String emailId, String mobileNumber) {

		Student student = null;
		try {
			TypedQuery<Student> query = em.createNamedQuery("Student.findByEmailIdOrMobileNumber", Student.class).setParameter("emailId", emailId).setParameter("mobileNumber", mobileNumber);
			student = query.getSingleResult();
		} catch(Exception e) {

		}
		return student;
	}

	@Override
	public Student createStudent(Student student) {

		if(student.getStudentId() == null) {
			em.persist(student);
			em.flush();
		} else {
			em.merge(student);
			em.flush();
		}

		return student;

	}

	@Override
	public List<Student> getStudents() {
		TypedQuery<Student> query = em.createNamedQuery("Student.list", Student.class);
		return query.getResultList();
	}

	@Override
	public List<Student> getActiveStudents() {
		TypedQuery<Student> query = em.createNamedQuery("Student.activeList", Student.class);
		return query.getResultList();

	}

	@Override
	public List<Student> getInActiveStudents() {
		TypedQuery<Student> query = em.createNamedQuery("Student.inActiveList", Student.class);
		return query.getResultList();
		
	}

	@Override
	public Integer countActiveList(Boolean registeredWithUPSC) {
		TypedQuery<Long> query = em.createNamedQuery("Student.countActiveList", Long.class).setParameter("registeredWithUPSC", registeredWithUPSC);
		return (int) (long) (query.getSingleResult());
	}

	@Override
	public Integer countInActiveList(Boolean registeredWithUPSC) {
		TypedQuery<Long> query = em.createNamedQuery("Student.countInActiveList", Long.class).setParameter("registeredWithUPSC", registeredWithUPSC);
		return (int) (long) (query.getSingleResult());

	}

	@Override
	public List<Student> activeOffsetList(Integer from, Integer To, Boolean registeredWithUPSC) {
		TypedQuery<Student> query = em.createNamedQuery("Student.activeList", Student.class).setParameter("registeredWithUPSC", registeredWithUPSC);
		return query.setFirstResult(from).setMaxResults(To).getResultList();
	}

	@Override
	public List<Student> inActiveOffsetList(Integer from, Integer To, Boolean registeredWithUPSC) {
		TypedQuery<Student> query = em.createNamedQuery("Student.inActiveList", Student.class).setParameter("registeredWithUPSC", registeredWithUPSC);
		return query.setFirstResult(from).setMaxResults(To).getResultList();

	}

	@Override
	public List<Student> searchWithTxtActiveOffsetList(String searchTxt, Integer from, Integer To, Boolean registeredWithUPSC) {
		Integer studentId = -1;
		try {
			studentId = Integer.parseInt(searchTxt);
		} catch(Exception e) {

		}

		TypedQuery<Student> query = em.createNamedQuery("Student.searchWithTxtActiveOffsetList", Student.class)
		.setParameter("searchTxt", ("%" + searchTxt + "%"))
		.setParameter("studentId", studentId)
		.setParameter("registeredWithUPSC", registeredWithUPSC);
		return query.setFirstResult(from).setMaxResults(To).getResultList();

	}

	@Override
	public List<Student> searchWithTxtInActiveOffsetList(String searchTxt, Integer from, Integer To, Boolean registeredWithUPSC) {
		Integer studentId = -1;
		try {
			studentId = Integer.parseInt(searchTxt);
		} catch(Exception e) {

		}
		TypedQuery<Student> query = em.createNamedQuery("Student.searchWithTxtInActiveOffsetList", Student.class)
				.setParameter("searchTxt", ("%" + searchTxt + "%"))
				.setParameter("studentId", studentId)
				.setParameter("registeredWithUPSC", registeredWithUPSC);
		return query.setFirstResult(from).setMaxResults(To).getResultList();

	}

	@Override
	public Integer countOfSearchWithTxtActive(String searchTxt, Boolean registeredWithUPSC) {
		Integer studentId = -1;
		try {
			studentId = Integer.parseInt(searchTxt);
		} catch(Exception e) {

		}
		TypedQuery<Long> query = em.createNamedQuery("Student.countOfSearchWithTxtActive", Long.class)
				.setParameter("searchTxt", ("%" + searchTxt + "%"))
				.setParameter("studentId", studentId)
				.setParameter("registeredWithUPSC", registeredWithUPSC);
		return (int) (long) (query.getSingleResult());

	}

	@Override
	public Integer countOfSearchWithTxtInActive(String searchTxt, Boolean registeredWithUPSC) {
		Integer studentId = -1;
		try {
			studentId = Integer.parseInt(searchTxt);
		} catch(Exception e) {

		}
		TypedQuery<Long> query = em.createNamedQuery("Student.countOfSearchWithTxtInActive", Long.class)
					.setParameter("searchTxt", ("%" + searchTxt + "%"))
					.setParameter("studentId", studentId)
					.setParameter("registeredWithUPSC", registeredWithUPSC);
		return (int) (long) (query.getSingleResult());

	}
}
