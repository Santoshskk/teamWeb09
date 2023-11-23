package app.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Bid {
    @Id
    @GeneratedValue
    private long id;
    private double offerBid;

    @ManyToOne
    private Offer offer;



    public boolean associateOffer(Offer offer) {
        //if the bid size is higher than 1 the recursion will be interfered
        if(offer.getBids().size()>0){
            return true;
        }
        if (offer != null && this.getOffer() == null) {
            if (!offer.associateBid(this)) {
                offer.associateBid(this);
                this.setOffer(offer);
                return true;
            }
        }
        return false;
    }

    public static Bid generateBid(long id) {
        Bid bid = new Bid();
        bid.setOfferBid(Math.random() * 10000);
        bid.setId(id);
        return bid;
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
