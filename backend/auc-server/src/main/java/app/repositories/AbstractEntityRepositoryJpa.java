package app.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public abstract class AbstractEntityRepositoryJpa<E extends Identifiable> implements EntityRepository<E> {

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
    @Transactional
    public E save(E entity) {
        if (entity.getIdentifiableId() == 0) {
            entityManager.persist(entity);
        } else {
            if (!entityManager.contains(entity)) {
                entity = entityManager.merge(entity);
            }
        }
        return entity;
    }


    @Override
    @Transactional
    public E deleteById(long id) {
        //delete the entity
        E entity = findById(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
        return entity;
    }

    @Override
    public List<E> findByQuery(String jpqlName, Object... params) {
        // build the named query
        TypedQuery<E> query = entityManager.createNamedQuery(jpqlName, theEntityClass);

        // Resolve all parameter values into the query
        int i = 1;
        for (Object param : params) {
            query.setParameter(i++, param);
        }

        return query.getResultList();
    }
}
