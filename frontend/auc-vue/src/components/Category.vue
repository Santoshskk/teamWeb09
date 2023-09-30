<template>
  <div class="container">
  <div class="left-column">

    <h2>Pick a date</h2>
    <input type="date">

    <h2>Pick a category</h2>
    <p><a href="https://www.example.com">Category 1</a></p>
    <p><a href="https://www.example.com">Category 2</a></p>
    <p><a href="https://www.example.com">Category 3</a></p>
    <p><a href="https://www.example.com">Category 4</a></p>
    <p><a href="https://www.example.com">Category 5</a></p>
  </div>

  <div class="mid-column">
    <expired-auction/>
  </div>

  <div class="right2-column">
    <Info/>
  </div>
  </div>




</template>

<script>
import Info from "@/components/Info";
import ExpiredAuction from "@/components/ExpiredAuction";

export default {
  name: "CategoryAuc",
  components: {ExpiredAuction, Info}
}
</script>

<style scoped>

.container {
  display: flex;
  flex-direction: row;
}

.left-column{
  max-width: 200px;
  flex: 1;
}

.mid-column{
  flex: 2;
}

.right2-column{
  flex: 0.5;

}


</style>
