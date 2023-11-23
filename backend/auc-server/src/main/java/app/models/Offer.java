package app.models;

import app.repositories.OffersRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@Entity

public class Offer {
    @JsonView(OffersRepository.class)
    @Id
    @GeneratedValue
    private int id;
    @JsonView(OffersRepository.class)
    private String title;
    @JsonView(OffersRepository.class)
    @Enumerated(EnumType.STRING)
    private Status status;
    @JsonProperty
    private String description;
    @JsonProperty
    private LocalDate sellDate;
    @JsonProperty
    private double valueHighestBid;

    @OneToMany(mappedBy = "offer")
    @JsonBackReference
    private List<Bid> bids= new ArrayList<>();

    public Offer(int id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    public Offer(int id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid, List<Bid> bid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
        this.bids=new ArrayList<>();
    }



    public enum Status {
        NEW,
        FOR_SALE,
        SOLD,
        PAID,
        DELIVERED,
        CLOSED,
        EXPIRED,
        WITHDRAWN
    }

    public Offer(String title) {
        this.title = title;
        Status status = Status.values()[(int)(Math.random() * Status.values().length)];
        this.status = status;
        this.bids= new ArrayList<>();
    }

    public Offer() {
        this.bids= new ArrayList<>();
    }

    public static Offer createSampleOffer(int id) {
        String title = "Sample Offer " + id;
        String description = "Description for Offer id: " + id;
        Status status = Status.values()[new Random().nextInt(Status.values().length)];
        LocalDate startDate = LocalDate.now().plusDays(id);
        double price = Math.random() * 100;
        String priceString= String.format("%.1f", price).replace(',', '.');
        double formattedPrice = Double.parseDouble(priceString);
        return new Offer(id, title, status, description, startDate, formattedPrice);
    }
//    public boolean associateBid(Bid bid) {
//        if(bid.getOffer() != null){
//            return bid.associateOffer(this);
//        }
//        if(bid.getOfferBid()<= this.getValueHighestBid()){
//            return false;
//        }
//        this.getBids().add(bid);
//        bid.setOffer(this);
//        return true;
//    }

    public void addBid(Bid bid){
        bids.add(bid);
        bid.setOffer(this);
    }

    /**
     * Disassociate the given bid from the this offer, if associated
     * @param bid
     * @return whether an existing association has been removed
     */
    public boolean dissociateBid(Bid bid) {
        if (bid != null && this.getBids().contains(bid)) {
            // Remove the bid association from both sides
            boolean removedFromOffer = this.getBids().remove(bid);
            boolean removedFromBid = bid.associateOffer(null);

            // Return true if the association was successfully removed from both sides
            return removedFromOffer && removedFromBid;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public double getValueHighestBid() {
        return valueHighestBid;
    }

    public void setValueHighestBid(double valueHighestBid) {
        this.valueHighestBid = valueHighestBid;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }
}
