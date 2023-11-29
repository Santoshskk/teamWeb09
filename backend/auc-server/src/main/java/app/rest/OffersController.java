package app.rest;

import app.models.Bid;
import app.models.Offer;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepository;
import app.repositories.OffersRepositoryJpa;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    private final OffersRepositoryJpa offersRepository;
    private final BidsRepositoryJpa bidsRepositoryJpa;


    @Autowired
    public OffersController(OffersRepositoryJpa offersRepository, BidsRepositoryJpa bidsRepositoryJpa) {
        this.offersRepository = offersRepository;
        this.bidsRepositoryJpa = bidsRepositoryJpa;
    }

    /**
     * this api is to show a summary of all offers
     * @return summary the offers
     */
    @RequestMapping("/summary")
    @JsonView(OffersRepository.class)
    public List<Offer> getOfferSummary(){
        return offersRepository.findAll();
    }

    /**
     * this api is for getting the given ids offer
     * @param id the id given in the url
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable long id) {
        Offer offer = offersRepository.findById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    /**
     * this api is for creating an offer
     * @param offer this is the given offer that is going to be added
     * @return bad request
     */
    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        if (offer != null) {
            //this can be optemised because the outcome is the same in both cases
            if (offer.getId() == 0) {
                offer = offersRepository.save(offer);
            }

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(offer.getId()).toUri();

            return ResponseEntity.created(location).body(offer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * Update an offer with the specified ID by replacing it with the provided Offer object.
     * @param id    The id of the offer that is going to be updated.
     * @param offer the offer with updated information.
     * @return A ResponseEntity containing the updated Offer if the update is successful.
     * @throws PreConditionFailedException if the provided ID in the path does not match the ID in the request body.
     * @throws ResourceNotFoundException    if the offer with the specified ID is not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Offer> updateOffer(@PathVariable long id, @RequestBody Offer offer) {
        if (id != offer.getId()) {
            throw new PreConditionFailedException("ID in the path does not match ID in the request body");
        }
        if (offersRepository.findById(id) != null) {
            offer.setId((int) id);
            offer = offersRepository.save(offer);
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    /**
     * Delete an offer with the specified ID.
     * @param id id of the offer to be deleted.
     * @return A ResponseEntity containing the deleted Offer if the deletion is successful.
     * @throws ResourceNotFoundException if the offer with the specified ID is not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offer = offersRepository.deleteById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    /**
     * getting a list of all offers.
     * @return A List of Offer representing all the available offers.
     */
    @GetMapping("/all")
    public List<Offer> getTestOffers() {
        return offersRepository.findAll();
    }

    /**
     * Add a new Bid to the specified Offer.
     * @param offerId The ID of the Offer to which the Bid will be added.
     * @param bid The Bid object to be added.
     * @return A ResponseEntity containing the updated Offer with the new Bid if the addition is successful.
     * @throws ResourceNotFoundException if the Offer with the specified ID is not found.
     * @throws PreConditionFailedException if the offer does not have status 'FOR_SALE' or if the bid value is not higher than the latest bid on the offer.
     */
    @PostMapping("/{offerId}/bids")
    public ResponseEntity<Offer> addBidToOffer(@PathVariable long offerId, @RequestBody Bid bid) {
        Offer offer = offersRepository.findById(offerId);

        if (offer != null) {
            // Check if the offer has status 'FOR_SALE'
            if (offer.getStatus() != offer.getStatus().FOR_SALE) {
                throw new PreConditionFailedException("Offer does not have status 'FOR_SALE'");

            }

            if (offer.getValueHighestBid() >= bid.getOfferBid()){
                throw new PreConditionFailedException("Bid: "+ bid.getOfferBid()+ " is lower than the current highest bid "+
                        offer.getValueHighestBid());
            }

            // Further check and modification of the Bid can be done here before saving it
            // For example, you can set the Offer reference in the Bid
            bid.setOffer(offer);

            // Save the Bid to the repository
            Bid savedBid = bidsRepositoryJpa.save(bid);

            // Add the Bid to the Offer
            offer.addBid(savedBid);

            // Update the Offer in the repository
            offer = offersRepository.save(offer);

            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + offerId);
        }

        }
    }





