<template>
  <header class="header">
    <img class="imgHammer" src="@/assets/images/auctioneerHammer.jpeg" alt="auctioneerHammer" />

    <div class="header-content">
      <h1>{{ title }}</h1>
      <p class="subtitle">{{ text }}</p>
      <p class="date"> Today is: {{ currentDate }}</p>
    </div>

    <img class="imgClock" src="@/assets/images/woodenClock.jpeg" alt="woodenClock" />
  </header>
</template>

<script>
export default {
  name: "HeaderComponent",
  props: ['title', 'text', 'imgSrc', 'clockImgSrc'],
  computed: {
    currentDate() {
      const today = new Date();
      return today.toLocaleDateString();
    }
  }
}
</script>

<style scoped>
.header {
  background-color: #D2B48C;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}

.imgClock {
  max-width: 180px;
  width: auto;
  height: auto;
  margin-right: 10px;
  margin-left: 20px;
}

.imgHammer{
  max-width: 150px;
  width: auto;
  height: auto;
  margin-right: 20px;
  margin-left: 10px;
}

.header-content {
  flex-grow: 1;
  text-align: center;
  display: flex;
  flex-direction: column;
  justify-content: center;
}



.date {
  font-size: 14px;
  color: #333;
  margin-left: 20px;
}

@media screen and (max-width: 768px) {
  .imgClock, .imgHammer {
    max-width: 100px;
    margin-right: 10px;
  }

  .header-content {
    flex-direction: row;
    justify-content: space-between;
  }

  .subtitle {
    text-align: center;
    font-size: 14px;
  }

  h1 {
    font-size: 24px;

  }
}
</style>
