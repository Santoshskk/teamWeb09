package app.rest;

import app.models.Offer;
import app.repositories.OffersRepositoryMock;
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

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable long id) {
        Offer offer = offersRepository.findById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        if (offer != null) {
            if (offer.getId() == 0) {
                offer = offersRepository.save(offer);
            } else {
                // Controleert of het opgegeven ID al bestaat in de repository.
                if (offersRepository.findById(offer.getId()) == null) {
                    return ResponseEntity.badRequest().build();
                }
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
        if (offersRepository.findById(id) != null) {
            offer.setId((int) id);
            offer = offersRepository.save(offer);
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
        Offer offer = offersRepository.deletedById(id);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/all")
    public List<Offer> getTestOffers() {
        return offersRepository.findAll();
    }
}
