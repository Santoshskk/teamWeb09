package app.rest;

import app.models.Offer;
import app.models.Views;
import app.repositories.OffersRepository;
import app.repositories.OffersRepositoryMock;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/offers")
public class OffersController {

    private final OffersRepositoryMock offersRepository;

    @Autowired
    public OffersController(OffersRepositoryMock offersRepository) {
        this.offersRepository = offersRepository;
    }

    @JsonView(Views.Summary.class)
    @GetMapping("/summary")
    public List<Offer> getOfferSummary(){
        return offersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable long id) {
        Offer offer = offersRepository.findById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        if (offer != null) {
            if (offer.getId() == 0) {
                offer = offersRepository.save(offer);
            } else {
//                if (offersRepository.findById(offer.getId()) == null) {
//                    return ResponseEntity.badRequest().build();
//                }
                offer = offersRepository.save(offer);
            }

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(offer.getId())
                    .toUri();

            return ResponseEntity.created(location).body(offer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



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

    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offer = offersRepository.deletedById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            throw new ResourceNotFoundException("Offer not found with ID: " + id);
        }
    }

    @GetMapping("/all")
    public List<Offer> getTestOffers() {
        return offersRepository.findAll();
    }
}



