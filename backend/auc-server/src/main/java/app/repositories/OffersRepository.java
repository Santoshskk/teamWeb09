package app.repositories;

import app.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersRepository {
    //finds all available offers
    List<Offer> findAll();

    //finds one offer identified by id. returns null if the offer does not exist.
    Offer findById(long id);

    //updates the offer in the repository identified bu offer.id
    //insert a new offer if offer.id==0
    //return the updated or inserted offer with new offer.id
    Offer save(Offer offer);

    //deletes the offer fro the repository identified by offer.id;
    //returns the instance that has been deleted
    Offer deletedById(long id);
}
