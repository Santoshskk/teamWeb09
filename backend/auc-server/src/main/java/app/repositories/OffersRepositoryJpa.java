package app.repositories;

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
public class OffersRepositoryJpa implements EntityRepository<Offer> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Offer> findAll() {
        return em.createQuery("SELECT o FROM Offer o", Offer.class).getResultList();
    }

    @Override
    public Offer findById(long id) {
        return em.find(Offer.class, id);
    }

    @Override
    public Offer save(Offer offer) {
        if (offer == null || offer.getId() ==0) {
            // If the ID is not set, it's a new offer, so persist it
            em.persist(offer);
        } else {
            // If the ID is set, it's an existing offer, so merge it
            offer = em.merge(offer);
        }
        return offer;
    }

    @Override
    public Offer deleteById(long id) {
        Offer offer = this.findById(id);
        if (offer != null) {
            em.remove(offer);
        }
        return offer;
    }

    @Override
    public List<Offer> findByQuery(String queryName, Object... params) {
        var query = em.createNamedQuery(queryName, Offer.class);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

}
