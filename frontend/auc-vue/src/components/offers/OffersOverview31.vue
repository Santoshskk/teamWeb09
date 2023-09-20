

<template>
  <div>
    <h1>Offers Overview</h1>
    <ul>
      <li v-for="offer in offers" :key="offer.id">
        {{ offer.id }} - {{ offer.title }} - {{ offer.status }} - {{ offer.sellDate }} - {{ offer.valueHighestBid }}
      </li>
    </ul>
  </div>
</template>

<script>
import { Offer } from '@/models/offer.js'; // Adjust the path if necessary

export default {
  name: 'OffersOverview31',
  data() {
    return {
      offers: [],
      nextOfferId: 30000
    };
  },
  created() {
    for (let i = 0; i < 8; i++) {
      this.offers.push(Offer.createSampleOffer(this.nextOfferId));
      this.nextOfferId += Math.floor(Math.random() * 3) + 1;
        // Random increment of 1, 2, or 3
    }
  }
}
</script>


