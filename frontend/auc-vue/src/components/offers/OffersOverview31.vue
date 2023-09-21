<template>
  <div>
    <h1>Offers Overview</h1>
    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Status</th>
        <th>Description</th>
        <th>Sell Date</th>
        <th>Value of Highest Bid</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="offer in offers" :key="offer.id">
        <td>{{ offer.id }}</td>
        <td>{{ offer.title }}</td>
        <td>{{ offer.status }}</td>
        <td>{{ offer.description }}</td>
        <td>{{ offer.sellDate }}</td>
        <td v-if="offer.status !== 'NEW'">{{ offer.valueHighestBid }}</td>
        <td v-else></td>

      </tr>
      <button @click="onNewOffer" style="margin-top: 20px; float: right;">New Offer</button>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f5f5f5;
}

tr:hover {
  background-color: #ddd;
}
</style>
<script>
import { Offer } from '@/models/offer.js';

export default {
  name: 'OffersOverview31',
  data() {
    return {
      offers: [],
      nextOfferId: 30000
    };
  },
  created() {
    this.generateOffers(8);
  },
  methods: {

    generateOffers(count) {
      for (let i = 0; i < count; i++) {
        this.offers.push(Offer.createSampleOffer(this.nextOfferId));
        this.nextOfferId += Math.floor(Math.random() * 3) + 1; // Random increment of 1, 2, or 3
      }
    },
    onNewOffer() {
      this.generateOffers(1); // Generate a single new offer
    }
  }
}
</script>
