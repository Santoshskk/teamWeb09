export class Offer {

    id;
    title;
    status;
    description;
    sellDate;
    valueHighestBid;

    constructor(id, title, status, description, sellDate, valueHighestBid) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
        this.sellDate = sellDate;
        this.valueHighestBid = valueHighestBid;
    }

    static status = {
        NEW: "NEW",
        FOR_SALE: "FOR_SALE",
        SOLD: "SOLD",
        PAID: "PAID",
        DELIVERED: "DELIVERED",
        CLOSED: "CLOSED",
        EXPIRED: "EXPIRED",
        WITHDRAWN: "WITHDRAWN"
    }

    static copyConstructor(offer) {
        if (!offer) return null; // Handle the case where 'offer' is null or undefined

        let clonedOffer = new Offer(
            offer.id,
            offer.title,
            offer.status,
            offer.description,
            new Date(offer.sellDate),
            offer.valueHighestBid
        );

        return clonedOffer;
    }

    static equals(offer1, offer2) {
        if (offer1 === null && offer2 === null) {
            return true;
        }

        if (offer1 === null || offer2 === null) {
            return false;
        }

        // Basic field comparisons
        if (offer1.id !== offer2.id ||
            offer1.title !== offer2.title ||
            offer1.status !== offer2.status ||
            offer1.description !== offer2.description ||
            offer1.valueHighestBid !== offer2.valueHighestBid) {
            return false;
        }

        // Deep comparison of Date objects
        if (offer1.sellDate instanceof Date && offer2.sellDate instanceof Date) {
            if (offer1.sellDate.getTime() !== offer2.sellDate.getTime()) {
                return false;
            }
        } else if (offer1.sellDate !== offer2.sellDate) {
            return false;
        }

        // All checks passed, objects are equal
        return true;
    }

    static createSampleOffer(pId = 0) {
        const allStatus = [
            this.status.NEW, this.status.FOR_SALE, this.status.SOLD, this.status.PAID,
            this.status.DELIVERED, this.status.CLOSED, this.status.EXPIRED, this.status.WITHDRAWN
        ];
        let randomStatus =  allStatus[Math.floor(Math.random() * allStatus.length)];
        const randomDays = Math.floor(Math.random() * 30) - 15; // Random number between -15 and 15
        const randomDate = new Date();
        randomDate.setDate(randomDate.getDate() + randomDays);
        let randomValue = Math.floor(Math.random() * 1000) ; // Random value between 0 and 999

        if(randomStatus === "NEW") {
            randomValue = 0;
        }
        return new Offer(pId, "testStatus" + pId,
            randomStatus, "description for offer" + pId, randomDate, randomValue);
    }

}
