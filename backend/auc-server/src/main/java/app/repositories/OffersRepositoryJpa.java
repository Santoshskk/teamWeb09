package app.repositories;

import app.models.Offer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository("OFFERS.JPA")

@Transactional
@Primary
public class OffersRepositoryJpa extends AbstractEntityRepositoryJpa<Offer> {

    public OffersRepositoryJpa() {
        super(Offer.class);
    }

}
