package br.com.cesupa.agenda.persistence;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

public abstract class GenericDAO<T, K extends java.io.Serializable> {
	
	@Logger
	private Log logger;
	
	@In("agendaEntityManager")
	private EntityManager entityManager;
	
	protected Class<T> _class;
	
	@SuppressWarnings("unchecked" )
	protected void create() {
		Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		_class = (Class<T>) types[0];                                                              
	}
	
	public void closeConnection() {
		this.entityManager.close();
	}
	
	public void save(T obj) throws Exception {
		try {
			entityManager.persist(obj);
		} catch (Exception ex) {
			logger.error("Erro ao tentar salvar: " + obj, ex);
			throw new Exception();
		}
	}
	
	public void update(T obj) throws Exception  {
		try {
			entityManager.merge(obj);
		} catch (Exception ex) {
			logger.error("Erro ao tentar atualizar: " + obj, ex);
			throw new Exception();
		}
	}

	public void delete(T obj) throws Exception  {
		try {
			entityManager.remove(obj);
		} catch (Exception ex) {
			logger.error("Erro ao tentar excluir: " + obj, ex);
			throw new Exception();
		}
	}

	public T find(K id) {  
        return ((T)entityManager.find(_class, id));  
    }
	
	@SuppressWarnings("unchecked")
	public List<T> findByField(String fieldName, Object value) {
		Query query = entityManager.createNamedQuery("find"+_class.getSimpleName()+"By"+fieldName);
		query.setParameter(fieldName.toLowerCase(), value);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked") 
	public List<T> findAll() {
		return entityManager.createNamedQuery("findAll"+_class.getSimpleName()).getResultList();
	}
	
	
	
}
