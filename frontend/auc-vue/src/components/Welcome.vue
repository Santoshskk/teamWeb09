<template>
  <div class="welcome">
    <div class="left-column">

      <h2>Pick a date</h2>
      <input type="date">

      <h2>Pick a category</h2>
      <p><a href="https://www.example.com">Category 1</a> </p>
      <p><a href="https://www.example.com">Category 2</a> </p>
      <p><a href="https://www.example.com">Category 3</a> </p>
      <p><a href="https://www.example.com">Category 4</a> </p>
      <p><a href="https://www.example.com">Category 5</a> </p>
    </div>

    <div class="center-column">
      <h3>Auctions that expire today</h3>
      <img src="@/assets/kroonluchter.jpeg" alt="kroonluchterImg" class="center-image" />
      <img src="@/assets/car.jpeg" alt="" class="center-image" />
      <img src="@/assets/oldBike.webp" alt="oldBikeImg" class="center-image" />
      <img src="@/assets/nachtwacht.webp" alt="nachtwachtImg" class="center-image" />
    </div>

    <div class="right-column">
        <h3>Category 2</h3>

      <h4>Orer Race-Truck Fire Decker New State</h4>
      <h5>Lorem ipsum dolor sit amet. Et numquam unde ut voluptatum iusto eos necessitatibus ratione. Et quos voluptas
        sed debitis dolor et dolorum neque?
        In natus dolore qui reprehenderit modi eos quia consequuntur aut magnam quia ut quis quod. Quo obcaecati autem
        nam accusamus delectus sed consequuntur nisi sed repudiandae autem eos quis debitis eum assumenda beatae.</h5>

    </div>
  </div>
</template>

<script>
export default {
  name: "WelcomeComponent",
};
</script>

<style scoped>
.welcome {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.left-column, .right-column {
  flex: 0 0 200px;
  text-align: center;
  margin: 10px;
  border: 2px solid #000;
  padding: 10px;
  float: left;
}

.center-column {
  flex: 1;
  justify-content: space-between;
  align-items: flex-start;
  padding: 0 10px;
  margin-left: 10px;
  margin-right: 10px;
}

h3 {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.center-image {
  max-width: 100px;
  width: auto;
  height: auto;
  margin: 5px;
}

h3 {
  margin: 10px 0;
}

@media screen and (max-width: 768px) {
  .welcome {
    flex-direction: column;
  }

  .left-column, .right-column {
    flex: 0 0 auto;
    width: 100%;
    max-width: none;
    margin: 10px 0;
    border: 2px solid #000;
    padding: 10px;
  }

  .center-column {
    padding: 0;
    flex-direction: column;
    align-items: center;
    text-align: center;
    margin: 10px 0;
  }
}
</style>
