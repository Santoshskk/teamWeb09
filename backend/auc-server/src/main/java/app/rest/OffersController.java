package app.rest;

import app.models.Offer;
import app.repositories.OffersRepository;
import app.repositories.OffersRepositoryJpa;
import app.repositories.OffersRepositoryMock;
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

    @Autowired
    public OffersController(OffersRepositoryJpa offersRepository) {
        this.offersRepository = offersRepository;
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
            } else {
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
}



