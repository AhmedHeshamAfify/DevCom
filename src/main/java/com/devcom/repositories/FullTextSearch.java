package com.devcom.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import com.devcom.models.Question;
import org.hibernate.search.jpa.FullTextQuery;

@Repository
public class FullTextSearch {

	@PersistenceContext
	private EntityManager em;

	public List<Question> searchByKeyword(String keyword) {
		try {
			FullTextEntityManager fullTextEm = Search.getFullTextEntityManager(em);
			fullTextEm.createIndexer().startAndWait();
			QueryBuilder postQb = fullTextEm.getSearchFactory().buildQueryBuilder().forEntity(Question.class).get();
			Query fullTextQuery = postQb.keyword().onFields("title", "text").matching(keyword).createQuery();
			FullTextQuery jpaQuery = fullTextEm.createFullTextQuery(fullTextQuery, Question.class);
			return jpaQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
