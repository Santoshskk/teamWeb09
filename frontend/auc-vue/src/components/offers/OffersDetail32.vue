<template>
  <div>
    <table>
      <tr>
        <td>Title:</td>
        <td><input type="text" v-model="offer.title" /></td>
      </tr>
      <tr>
        <td>Status:</td>
        <td><input type="text" v-model="offer.status" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><input type="text" v-model="offer.description" /></td>
      </tr>
      <tr>
        <td>Sell Date:</td>
        <td><input type="date" v-model="sellDateValue" /></td>

      </tr>
      <tr>
        <td>Highest Bid:</td>
          <td><input type="text" v-model="offer.valueHighestBid" /></td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "OffersDetail32",
  props: {
    offer: {
      type: Object,
      required: true
    }
  },
  computed: {
    sellDateValue: {
      get() {
        // Convert date to YYYY-MM-DD format
        return this.formatDate(this.offer.sellDate);
      },
      set(value) {
        this.offer.sellDate = value;
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
        const month = (date.getMonth() + 1).toString().padStart(2, '0');  // Months are 0-based
        const day = date.getDate().toString().padStart(2, '0');
        return `${year}-${month}-${day}`;
      }

     //return null
      return null;
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
