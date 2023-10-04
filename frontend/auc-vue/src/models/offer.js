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

        console.log(randomValue)
        return new Offer(pId, "testStatus" + pId,
            randomStatus, "description for offer" + pId, randomDate, randomValue);
    }

}