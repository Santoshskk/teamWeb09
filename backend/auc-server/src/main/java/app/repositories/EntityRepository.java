package app.repositories;

import app.models.Offer;

import java.util.List;

public interface EntityRepository<E> {
    List<E> findAll();
    E findById(long id);
    E save(E entity);
    E deleteById(long id);

    List<E> findByQuery(String queryName, Object... params);

}
