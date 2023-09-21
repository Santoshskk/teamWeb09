<template>

    <h1>Offers Overview</h1>
  <div class="container">
    <div class="left-column">
    <table>
      <thead>
      <tr>
        <th>Title</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="offer in offers" :key="offer.id">
        <td @click="getOffer(offer)" :class="{ 'selected': selectOffer === offer }" >{{ offer.title }}</td>

      </tr>
      <button @click="onNewOffer" style="margin-top: 20px; float: right;">New Offer</button>
      </tbody>
    </table>
    </div>
    <div class="right-column2">
      <offers-detail32/>
    </div>
  </div>
</template>

<style scoped>

.container {
  display: flex;
  flex-direction: row;
}

.left-column{
  flex: 1;
}
.right-column2{
  flex: 2;
}


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

.selected {
  background-color: #D2B48C; /* Light gray background */
  color: white;
  border: 1px solid #d1d1d1; /* Gray border */
}



</style>
<script>
import { Offer } from '@/models/offer.js';
import offersDetail32 from "@/components/offers/OffersDetail32";

export default {
  name: 'OffersOverview32',
  components: {offersDetail32},
  data() {
    return {
      selectOffer: null,
      offers: [],
      nextOfferId: 30000
    };
  },


  created() {
    this.generateOffers(8);
  },
  methods: {

    getOffer(selectedOffer) {
      this.selectOffer = selectedOffer;
    },
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
