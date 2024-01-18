package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OffersRepositoryMock implements OffersRepository {

    private final List<Offer> offers = new ArrayList<>();


    public OffersRepositoryMock() {
        //loop to create 7 samples
        for (int i = 1; i <= 7; i++) {
            Offer sampleOffer = Offer.createSampleOffer(i);
            sampleOffer.setOfferId(i); // Stel een unieke ID in voor elke aanbieding
            offers.add(sampleOffer);
        }
    }

    /**
     * this methode finds all offers
     * @return all offers
     */
    @Override
    public List<Offer> findAll() {
        return offers;
    }

    @Override
    public Offer findById(long id) {
        for (Offer offer : offers) {
            if (offer.getOfferId() == id) {
                return offer;
            }
        }
        return null;
    }

    /**
     * This method saves or changes offer
     * if the id is 0 it will genarate a new one
     * or if the id does not exist it will add that one
     * if the id exists it will update that one
     * @param offer
     * @return the saved offer
     */
    @Override
    public Offer save(Offer offer) {
        if (offer.getOfferId() == 0) {
            int nextId = generateUniqueID();
            offer.setOfferId(nextId);
            offers.add(offer);
        } else {
            for (int i = 0; i < offers.size(); i++) {
                if (offers.get(i).getOfferId() == offer.getOfferId()) {
                    offers.set(i, offer);
                    return offer;
                }
            }
            offers.add(offer);
            return offer;
        }
        return offer;
    }

    private int generateUniqueID() {
        int nextId = 1;
        for (Offer existingOffer : offers) {
            if (existingOffer.getOfferId() >= nextId) {
                nextId = existingOffer.getOfferId() + 1;
            }
        }
        return nextId;
    }

    /**
     * this methode delets a given id
     * @param id given id
     * @return returns the deleted offer
     */
    @Override
    public Offer deleteById(long id) {
        for (Offer offer : offers) {
            if (offer.getOfferId() == id) {
                offers.remove(offer);
                return offer;
            }
        }
        return null;
    }

    @Override
    public List<Offer> findByQuery(String queryName, Object... params) {
        return null;
    }
}
