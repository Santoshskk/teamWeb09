package app.repositories;

import app.models.Offer;


import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;



@Repository("OFFERS.JPA")
@Transactional
@Primary
public class OffersRepositoryJpa extends AbstractEntityRepositoryJpa<Offer> {

    public OffersRepositoryJpa() {
        super(Offer.class);
    }

}
