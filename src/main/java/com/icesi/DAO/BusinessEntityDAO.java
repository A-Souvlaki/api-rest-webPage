package com.icesi.DAO;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.icesi.model.Businessentity;

@Repository
public class BusinessEntityDAO implements Dao<Businessentity>{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional
	public Optional<Businessentity> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Businessentity.class, id));
	}

	@Override
	@Transactional
	public Optional<Businessentity> get1(long id) {
		return Optional.ofNullable(entityManager.find(Businessentity.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Businessentity> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Businessentity e");
        return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Businessentity t) {
		entityManager.persist(t); 
	}
	
	@Override
	@Transactional
	public void update(Businessentity t) {
	    entityManager.merge(t);	
	}
}
