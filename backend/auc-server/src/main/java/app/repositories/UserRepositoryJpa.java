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
public class UserRepositoryJpa extends AbstractEntityRepositoryJpa<User>{

    public UserRepositoryJpa() {
        super(User.class);
    }
}
