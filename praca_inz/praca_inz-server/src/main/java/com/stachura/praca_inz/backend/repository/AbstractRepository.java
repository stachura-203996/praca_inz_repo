package com.stachura.praca_inz.backend.repository;

import com.stachura.praca_inz.backend.exception.EntityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class AbstractRepository<T> {

    private Class<T> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    protected AbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metoda tworząca nową encję w bazie
     *
     * @param entity nowa encja
     * @throws EntityException jeżeli tworzenie encji zostanie zakończone niepowodzeniem
     */
    public void create(T entity) throws EntityException {
        getEntityManager().persist(entity);
    }

    /**
     * Metoda edytująca encję w bazie
     *
     * @param entity encja do edycji
     * @throws EntityException jeżeli edycja encji zostanie zakończone niepowodzeniem
     */
    public void edit(T entity) throws EntityException {
        getEntityManager().merge(entity);
    }

    public void remove(Long id){getEntityManager().remove(getEntityManager().find(entityClass,id));}

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public T update(T entity) throws EntityException{
       return getEntityManager().merge(entity);
    }
}
