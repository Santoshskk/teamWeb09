<template>
  <h1>Offers Overview 37</h1>
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
          <td @click="getOffer(offer)" :class="{ 'selected': selectOffer === offer }">{{ offer.title }}</td>
        </tr>
        <button @click="onNewOffer" style="margin-top: 20px; float: right;">New Offer</button>
        <button @click="deleteoffer">Delete</button>
        </tbody>
      </table>
    </div>
    <div class="right-column2">
      <offers-detail37 v-if="selectOffer" :offer="selectOffer"   @updateOffer="updateOffer"
                       @deleteOffer="deleteoffer"/>
      <p v-else>Please select an offer to view its details.</p>
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
import offersDetail37 from "@/components/offers/OffersDetail37";
import {router} from "@/router";

export default {
  name: 'OffersOverview37',
  inject: ['offersServices'],
  components: {offersDetail37},
  data() {
    return {
      selectOffer: null,
      offers: [],
      nextOfferId: 30000
    };
  },
  async created() {
    this.offers = await this.offersServices.asyncFindAll();
    this.selectOffer = this.findSelectedRouteParam(this.$route);
  },

  watch: {
    '$route': {
      immediate: true,
      handler: 'findSelectedRouteParam'
    }
  },

  methods: {

    findSelectedRouteParam(route) {
      if (route && route.params && route.params.id) {
        for (let i = 0; i < this.offers.length; i++) {
          console.log(this.offers[i]);
          if (this.offers[i].id.toString() === route.params.id) {
            this.getOffer(this.offers[i]);
          }
        }
      } else {
        console.error('Route params or ID not available');
      }
    },
    getOffer(selectedOffer) {
      this.selectOffer = selectedOffer;
      router.push({
        path: `/offers/overview37/${this.selectOffer.id}`
      });
    },
    generateOffers(count) {
      for (let i = 0; i < count; i++) {
        this.offers.push(Offer.createSampleOffer(this.nextOfferId));
        this.nextOfferId += Math.floor(Math.random() * 3) + 1; // Random increment of 1, 2, or 3
      }
    },
    onNewOffer() {
      const newOffer = Offer.createSampleOffer(0); // Create a new offer with ID 0

      // Add the new offer to the list
      this.offers.push(newOffer);

      // Select the newly added offer
      this.selectOffer = newOffer;

      // Save the new offer to the backend
      this.offersServices.asyncSave(newOffer)
          .then(savedOffer => {
            // Once the offer is saved and you receive the response with the updated ID,
            // update the ID of the selected offer and its position in the list
            newOffer.id = savedOffer.id;
            this.offers[this.offers.length - 1] = newOffer;

            // Navigate to the new route
            router.replace({
              path: `/offers/overview37/${newOffer.id}`
            });
          })
          .catch(error => {
            console.error("Failed to save the new offer:", error);
          });
    },
    removeOffer(offerId) {
      this.offers = this.offers.filter(offer => offer.id !== offerId);
      this.selectOffer = null;  // Unselect the offer
    },
    deleteoffer() {
      if (this.selectOffer) {
        this.removeOffer(this.selectOffer.id);
      }
    },
    updateOffer(updatedOffer) {
      console.log("update")
      const index = this.offers.findIndex(o => o.id === updatedOffer.id);
      if (index !== -1) {
        this.offers.splice(index, 1, updatedOffer);
      }
      this.selectOffer = null;  // Unselect the offer
    },
    deleteSelectedOffer(offerId) {
      const index = this.offers.findIndex(o => o.id === offerId);
      if (index !== -1) {
        this.offers.splice(index, 1);
      }
      this.selectOffer = null;  // Unselect the offer
    }
  }
};
</script>
