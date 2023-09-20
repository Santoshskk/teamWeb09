<template>
  <div class="welcome">
    <div class="center-column">
      <h3>Auctions that expire today</h3>
      <img src="@/assets/images/kroonluchter.jpeg" alt="kroonluchterImg" class="center-image" />
      <img src="@/assets/images/car.jpeg" alt="" class="center-image" />
      <img src="@/assets/images/oldBike.webp" alt="oldBikeImg" class="center-image" />
      <img src="@/assets/images/nachtwacht.webp" alt="nachtwachtImg" class="center-image" />
    </div>


  </div>
</template>

<script>
export default {
  name: "ExpiredAuction",
};
</script>

<style scoped>
.center-column {
  flex: 1;
  justify-content: space-between;
  align-items: flex-start;
  padding: 0 10px;
  margin-left: 10px;
  margin-right: 10px;
}



.center-image {
  max-width: 100px;
  width: auto;
  height: auto;
  margin: 5px;
}



@media screen and (max-width: 768px) {

  .center-column {
    padding: 0;
    flex-direction: column;
    align-items: center;
    text-align: center;
    margin: 10px 0;
  }
}
</style>
