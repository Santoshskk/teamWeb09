<template>
  <div>
    <table>
      <tr>
        <td>Titel:</td>
        <td><input type="text" v-model="title" /></td>
      </tr>
      <tr>
        <td>Status:</td>
        <td><input type="text" v-model="status" /></td>
      </tr>
      <tr>
        <td>Description:</td>
        <td><input type="text" v-model="description" /></td>
      </tr>
      <tr>
        <td>Sell Date:</td>
        <td><input type="date" v-model="sellDate" /></td>
      </tr>
      <tr>
        <td>Highest Bid:</td>
        <td><input type="text" v-model="highestBidValue" /></td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  name: "OffersDetail32",
  data() {
    return {
      title: "",
      status: "",
      description: "",
      sellDate: "",
      highestBidValue: "",
    };
  },
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
