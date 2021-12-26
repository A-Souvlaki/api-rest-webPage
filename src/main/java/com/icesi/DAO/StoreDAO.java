package com.icesi.DAO;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.icesi.model.Store;

@Repository
@Transactional
public class StoreDAO implements Dao<Store>{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Store> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Store.class, id));
	}

	@Override
	public Optional<Store> get1(long id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Store> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Store e");
        return query.getResultList();
	}

	@Override
	public void save(Store t) {
		entityManager.persist(t);
		
	}

	@Override
	public void update(Store t) {
		entityManager.merge(t);
		
	}
	
	
	public void delete(Store t) {
		entityManager.remove(t);
	}
}
