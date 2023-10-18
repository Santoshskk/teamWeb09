package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class OffersRepositoryMock implements OffersRepository {

    private final List<Offer> offers = new ArrayList<>();

    public OffersRepositoryMock() {
        for (int i = 1; i <= 7; i++) {
            Offer sampleOffer = Offer.createSampleOffer(i);
            sampleOffer.setId(i); // Stel een unieke ID in voor elke aanbieding
            offers.add(sampleOffer);
        }
    }

    @Override
    public List<Offer> findAll() {
        return offers;
    }

    @Override
    public Offer findById(long id) {
        for (Offer offer : offers) {
            if (offer.getId() == id) {
                return offer;
            }
        }
        return null;
    }

    @Override
    public Offer save(Offer offer) {
        if (offer.getId() == 0) {
            int nextId = generateUniqueID();
            offer.setId(nextId);
            offers.add(offer);
        } else {
            for (int i = 0; i < offers.size(); i++) {
                if (offers.get(i).getId() == offer.getId()) {
                    offers.set(i, offer);
                    return offer;
                }
            }
        }
        return offer;
    }

    private int generateUniqueID() {
        int nextId = 1;
        for (Offer existingOffer : offers) {
            if (existingOffer.getId() >= nextId) {
                nextId = existingOffer.getId() + 1;
            }
        }
        return nextId;
    }

    @Override
    public Offer deletedById(long id) {
        for (Offer offer : offers) {
            if (offer.getId() == id) {
                offers.remove(offer);
                return offer;
            }
        }
        return null;
    }
}
