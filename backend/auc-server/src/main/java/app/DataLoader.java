package app;

import app.models.Bid;
import app.models.Offer;
import app.repositories.BidsRepositoryJpa;
import app.repositories.OffersRepositoryJpa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DataLoader  implements CommandLineRunner {
    private OffersRepositoryJpa offersRepositoryJpa;
    private BidsRepositoryJpa bidsRepositoryJpa;

    @Autowired
    public DataLoader(OffersRepositoryJpa offersRepositoryJpa, BidsRepositoryJpa bidsRepositoryJpa) {
        this.offersRepositoryJpa = offersRepositoryJpa;
        this.bidsRepositoryJpa = bidsRepositoryJpa;
    }

    private void createInitialOfferWithBids() {
        List<Offer> offers = this.offersRepositoryJpa.findAll();
        if (offers.size() > 0) return;

        System.out.println("Configuring some initial data");
        for (int i = 0; i < 9; i++) {
            Offer offer = Offer.createSampleOffer(i);
            offer = this.offersRepositoryJpa.save(offer);


            // Add a few bids to every offer
            for (int j = 0; j < 3; j++) {
                double randomOfferBid = (Math.random() * 10000);
                String formattedRandomOfferBid = String.format("%.2f", randomOfferBid);
                formattedRandomOfferBid = formattedRandomOfferBid.replace(',', '.');
                Bid bid = new Bid(Double.parseDouble(formattedRandomOfferBid),offer);
                this.bidsRepositoryJpa.save(bid);
            }
        }
    }
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running CommandLine StartUp");
        this.createInitialOfferWithBids();

    }
}