package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Component;
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
            sampleOffer.setId(i); // Stel een unieke ID in voor elke aanbieding
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
            if (offer.getId() == id) {
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
            offers.add(offer);
            return offer;
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

    /**
     * this methode delets a given id
     * @param id given id
     * @return returns the deleted offer
     */
    @Override
    public Offer deleteById(long id) {
        for (Offer offer : offers) {
            if (offer.getId() == id) {
                offers.remove(offer);
                return offer;
            }
        }
        return null;
    }
}
