package com.icesi.DAO;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;
import com.icesi.model.Person;

@Repository
public class PersonDAO implements Dao<Person>{
	
	
	@PersistenceContext
	private EntityManager entityManager;
	   

	@Override
	@Transactional
	public Optional<Person> get(Integer id) {
		return Optional.ofNullable(entityManager.find(Person.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getTittle(String title) {
		Query query = entityManager.createQuery("SELECT e FROM Person e WHERE title = "+ "\'"+title+"\'");
        return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getByInterval(String date1,String date2) {
		Query query = entityManager.createQuery("SELECT e FROM Person e WHERE modifieddate BETWEEN " + "\'"+date1+"\'" 
				+ " AND " + "\'"+date2+"\'" + " AND counter >= 1 ORDER BY lastname");
        return query.getResultList();
	}

	@Override
	@Transactional
	public Optional<Person> get1(long id) {
		return Optional.ofNullable(entityManager.find(Person.class, id));
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Person> getAll() {
		Query query = entityManager.createQuery("SELECT e FROM Person e");
        return query.getResultList();
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Person> getAllByID(long id) {
		Query query = entityManager.createQuery("SELECT e FROM Person e WHERE personid="+id);
        return query.getResultList();
	}

	@Override
	@Transactional
	public void save(Person t) {
	    entityManager.persist(t);
		
	}

	@Override
	@Transactional
	public void update(Person t) {
	    entityManager.merge(t);	
	}
}
 