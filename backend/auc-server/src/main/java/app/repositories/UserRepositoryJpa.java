package app.repositories;

import app.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class UserRepositoryJpa implements EntityRepository<User>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User save(User entity) {
        return null;
    }

    @Override
    public User deleteById(long id) {
        return null;
    }

    @Override
    public List<User> findByQuery(String queryName, Object... params) {
        return null;
    }
}
