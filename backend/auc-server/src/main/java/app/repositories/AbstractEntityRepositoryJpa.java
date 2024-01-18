package app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractEntityRepositoryJpa<E> implements EntityRepository<E> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<E> theEntityClass;

    public AbstractEntityRepositoryJpa(Class<E> entityClass) {
        this.theEntityClass = entityClass;
    }

    @Override
    public List<E> findAll() {
        TypedQuery<E> query = this.entityManager.createQuery(
                "select e from " + this.theEntityClass.getSimpleName() + " e",
                this.theEntityClass);
        return query.getResultList();
    }

    @Override
    public E findById(long id) {
        // Use the entity manager to find the identified entity
        return entityManager.find(theEntityClass, id);
    }

    @Override
    public E save(E entity) {
        // Use the entity manager to persist or update the entity
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public E deleteById(long id) {
        // Use the entity manager to delete the entity
        E entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    @Override
    public List<E> findByQuery(String jpqlName, Object... params) {
        // Use the entity manager to build the named query
        TypedQuery<E> query = entityManager.createNamedQuery(jpqlName, theEntityClass);

        // Resolve all parameter values into the query
        int i = 1;
        for (Object param : params) {
            query.setParameter(i++, param);
        }

        // Return the query result
        return query.getResultList();
    }
}
