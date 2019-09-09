package com.devcom.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.devcom.dao.UserDao;
import com.devcom.models.User;
import com.devcom.repositories.UserRepository;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UserRepository userRepository;

	public User getUserByEmail(String email) {
		Query q = em.createQuery("From User where email =:email");
		q.setParameter("email", email);
		return (User) q.getSingleResult();
	}
	
	public User saveNewUser(User user) {
		return userRepository.save(user);

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

	public UserRepository getUserRepository() {
		return userRepository;
	}


	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
}
