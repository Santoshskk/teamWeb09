package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    private long id;
    private double offerBid;

    @ManyToOne

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

    public long getId() {
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
}
