package app.models;

import app.repositories.OffersRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDate;
import java.util.Random;

public class Offer {
    @JsonView(OffersRepository.class)
    private int id;
    @JsonView(OffersRepository.class)
    private String title;
    @JsonView(OffersRepository.class)
    private Status status;
    @JsonProperty
    private String description;
    @JsonProperty
    private LocalDate sellDate;
    @JsonProperty
    private double valueHighestBid;

    public Offer(int id, String title, Status status, String description, LocalDate sellDate, double valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
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
    }

    public Offer() {
        // Lege standaardconstructor
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
}
