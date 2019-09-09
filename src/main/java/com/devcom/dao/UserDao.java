package com.devcom.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.devcom.models.User;

@Repository
@Transactional
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public User getUserByEmail(String email) {
		Query q = em.createQuery("From User where email =:email");
		q.setParameter("email", email);
		return (User) q.getSingleResult();
	}

	public void register(User user) {
		em.persist(user);
	}

	public User login(String email, String password) {
		User user = null;
		Query q = em.createQuery("from User where email=:email and password=:password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		List<User> users = q.getResultList();
		if (users != null && users.size() > 0) {
			user = users.get(0);
		}
		return user;
	}
}
