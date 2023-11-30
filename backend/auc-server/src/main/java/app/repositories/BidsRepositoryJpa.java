package app.repositories;

import app.models.Bid;
import app.models.Offer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@Primary
public class BidsRepositoryJpa implements EntityRepository<Bid> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Bid> findAll() {
        return em.createQuery("SELECT o FROM Bid o", Bid.class).getResultList();
    }

    @Override
    public Bid findById(long id) {
        return em.find(Bid.class, id);
    }

    @Override
    public Bid save(Bid bid ) {
        if (bid == null || bid.getId() ==0) {
            // If the ID is not set, it's a new offer, so persist it
            em.persist(bid);
        } else {
            // If the ID is set, it's an existing offer, so merge it
            bid = em.merge(bid);
        }
        return bid;
    }

    @Override
    public Bid deleteById(long id) {
        Bid bid = this.findById(id);
        if (bid != null) {
            em.remove(bid);
        }
        return bid;
    }

    @Override
    public List<Bid> findByQuery(String queryName, Object... params) {
        var query = em.createNamedQuery(queryName, Bid.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }
}

