package app;

import app.models.Bid;
import app.models.Offer;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepositoryJpa;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class AucServerApplication implements CommandLineRunner {

    @Autowired
    private OffersRepositoryJpa offersRepositoryJpa;
    private BidsRepositoryJpa bidsRepositoryJpa;

    public static void main(String[] args) {
        SpringApplication.run(AucServerApplication.class, args);
    }

    public AucServerApplication(OffersRepositoryJpa offersRepositoryJpa, BidsRepositoryJpa bidsRepositoryJpa) {
        this.offersRepositoryJpa = offersRepositoryJpa;
        this.bidsRepositoryJpa = bidsRepositoryJpa;
    }

    private void createInitialOfferWithBids() {
        List<Offer> offers = this.offersRepositoryJpa.findAll();
        if (offers.size() > 0) return;

        System.out.println("Configuring some initial data");

        for (int i = 0; i < 9; i++) {
            Offer offer = Offer.createSampleOffer(i);

            // Add a few bids to every offer
            for (int j = 0; j < 3; j++) {
                Bid bid = Bid.generateBid(j); // Assume a method to generate a Bid
                offer.associateBid(bid);
                this.bidsRepositoryJpa.save(bid);
                offer.getBids().add(bid);
            }
            offer = this.offersRepositoryJpa.save(offer);
        }
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running CommandLine StartUp");
        this.createInitialOfferWithBids();

   }
}
