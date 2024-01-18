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
    private long id;
    private double offerBid;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "offer_id")
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
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getOfferBid() {
        return offerBid;
    }

    public void setOfferBid(double offerBid) {
        this.offerBid = offerBid;
    }

    @Override
    public long getIdentifiableId() {
        return 0;
    }

    @Override
    public void setIdentifiableId(long id) {

    }
}
