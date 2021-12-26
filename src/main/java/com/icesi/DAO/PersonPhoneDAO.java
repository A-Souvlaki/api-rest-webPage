package com.icesi.DAO;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.icesi.model.Personphone;

@Repository
@Transactional
public class PersonPhoneDAO implements Dao<Personphone> {
	
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Personphone> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Personphone.class, id));
	}

	@Override
	public Optional<Personphone> get1(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Personphone> getByPref(String pref) {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e WHERE phone LIKE " + "\'"+pref+"%\'");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Personphone> getAllTypes() {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e WHERE person.counter1 >= 1 AND person.counter2 >= 1");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Personphone> getByType(String type) {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e WHERE phonenumbertype.name = " +"\'"+type+"\'");
        return query.getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Personphone> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Personphone e");
        return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Personphone t) {
	    entityManager.persist(t);
		
	}

	@Override
	@Transactional
	public void update(Personphone t) {
	    entityManager.merge(t);
		
	}

}
