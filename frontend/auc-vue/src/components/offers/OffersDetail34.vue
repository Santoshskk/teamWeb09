
<template>
  <div>
    <table>
      <tr>
        <td>Title:</td>
        <td><input type="text" v-model="clonedOffer.title" /></td>
      </tr>
      <tr>
        <td>Status:</td>
        <td><input type="text" v-model="clonedOffer.status" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><input type="text" v-model="clonedOffer.description" /></td>
      </tr>
      <tr>
        <td>Sell Date:</td>
        <td><input type="date" v-model="sellDateValue" /></td>
      </tr>
      <tr>
        <td>Highest Bid:</td>
        <td><input type="text" v-model="clonedOffer.valueHighestBid" /></td>
      </tr>
    </table>

    <button @click="save">Save</button>
    <button @click="reset">Reset</button>
    <button @click="clear">Clear</button>
    <button @click="cancel">Cancel</button>
    <button @click="deleteOffer">Delete</button>
  </div>
</template>

<script>
import Offer from "@/models/offer.js";

export default {
  name: "OffersDetail34",
  props: {
    offer: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      clonedOffer: null,
    };
  },
  created() {
    this.clonedOffer = Offer.copyConstructor(this.offer);
  },
  watch: {
    offer: {
      handler(newValue) {
        this.clonedOffer = Offer.copyConstructor(newValue);
      },
      deep: true,
    },
  },
  computed: {
    sellDateValue: {
      get() {
        return this.formatDate(this.clonedOffer.sellDate);
      },
      set(value) {
        this.clonedOffer.sellDate = value;
      }
    }
  },
  methods: {
    formatDate(date) {
      if (!date) return null;
      if (typeof date === 'string' && /^\d{4}-\d{2}-\d{2}$/.test(date)) {
        return date;
      }
      if (date instanceof Date) {
        const year = date.getFullYear();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        return `${year}-${month}-${day}`;
      }
      return null;
    },
    save() {
      this.$emit("updateOffer", this.clonedOffer);
      this.$router.push("/offers"); // Navigate back to the offers overview
    },
    reset() {
      this.clonedOffer = Offer.copyConstructor(this.offer);
    },
    clear() {
      for (let key in this.clonedOffer) {
        this.clonedOffer[key] = key === "sellDate" ? null : "";
      }
    },
    cancel() {
      this.$router.push("/offers"); // Navigate back to the offers overview
    },
    deleteOffer() {
      this.$emit("deleteOffer", this.clonedOffer.id);
      this.$router.push("/offers"); // Navigate back to the offers overview
    }
  }
};
</script>

<style scoped>
table {
  width: 100%;
  border-collapse: collapse;
}

td {
  padding: 10px;
  border: 1px solid #ccc;
}

input[type="text"] {
  width: 100%;
  padding: 5px;
  box-sizing: border-box;
}
</style>
