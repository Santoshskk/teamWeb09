
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

    <button @click="save" :disabled="!hasChanged">Save</button>
    <button @click="reset" :disabled="!hasChanged">Reset</button>
    <button @click="clear">Clear</button>
    <button @click="cancel">Cancel</button>
    <button @click="deleteOffer" :disabled="hasChanged">Delete</button>
  </div>
</template>

<script>
import {Offer} from "@/models/offer.js";
import {router} from "@/router";

export default {
  name: "OffersDetail37",
  inject: ['offersServices'],
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
    },
    hasChanged() {
      return !Offer.equals(this.offer, this.clonedOffer);
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
      router.push("/offers");
    },
    deleteOffer() {
      this.$emit("deleteOffer", this.clonedOffer.id);
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
