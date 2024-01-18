package app.models;

import app.repositories.Identifiable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Component

@Entity
public class Bid implements Identifiable {
    @Id
    @GeneratedValue
    private long bidId;
    private double offerBid;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonBackReference
    private Offer offer;


    public Bid(double offerBid, Offer offer) {
        this.offerBid = offerBid;
        this.associateOffer(offer);
    }

    public Bid() {

    }

    public boolean associateOffer(Offer offer) {
        if (offer != null && this.getOffer() == null) {
                offer.addBid(this);
                this.setOffer(offer);
                return true;
        }
        return false;
    }



    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public long getOfferId() {
        return bidId;
    }

    public void setBidId(long id) {
        this.bidId = id;
    }

    public double getOfferBid() {
        return offerBid;
    }

    public void setOfferBid(double offerBid) {
        this.offerBid = offerBid;
    }

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public void setId(long id) {

    }
}
