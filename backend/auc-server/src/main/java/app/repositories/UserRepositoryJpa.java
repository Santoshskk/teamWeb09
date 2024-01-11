package app.repositories;

import app.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Primary
@Repository("USERS.JPA")
@Transactional
public class UserRepositoryJpa implements EntityRepository<User>{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        TypedQuery<User> query =
                this.em.createQuery(
                        "select a from User a", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        return this.em.find(User.class, id);
    }

    @Override
    public User save(User entity) {
        return this.em.merge(entity);
    }

    @Override
    public User deleteById(long id) {
        User user = this.findById(id);

        if (user != null) {
            //Use EntityManager to remove the user
            this.em.remove(user);
            //Flush changes the database
            this.em.flush();
        }

        return user;
    }


    @Override
    public List<User> findByQuery(String jpqlName, Object... params) {
        TypedQuery<User> query = this.em.createNamedQuery(jpqlName, User.class);

        // Resolve all parameter values into the query
        for (int i = 0; i < params.length; i++) {
            // The following block handles the special case of collections
            Object paramValue = params[i];
            if (paramValue instanceof Collection<?>) {
                query.setParameter(i + 1, (Collection<?>) paramValue);
            } else {
                query.setParameter(i + 1, paramValue);
            }
        }

        return query.getResultList();
    }

}
