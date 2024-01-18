package app.repositories;

import app.models.Bid;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BIDS.JPA")
@Transactional
@Primary
public class BidsRepositoryJpa extends AbstractEntityRepositoryJpa<Bid> {

    public BidsRepositoryJpa() {
        super(Bid.class);
    }
}

