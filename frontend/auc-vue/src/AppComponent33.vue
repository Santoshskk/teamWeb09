
<template>
  <div>
    <headerComponent :title="title" :text="text" :imgSrc="imgSrc"/>
    <nav-bar/>
    <router-view/>
  </div>
</template>

<script>
import NavBar from "@/components/Navbar";
import headerComponent from "@/components/Header";

export default {
  name: "AppComponent33",
  components: {NavBar,headerComponent},
  data(){
    return{
      title:"The Auctioneer",
      text:"Offered to you by Santosh and Tygo",
      welcomeText:'welcome',
      imgSrc:"./assets/auctioneerHammer.jpeg",
      clockImgSrc:"./assets/woodenClock.jpeg"

    }
  }
}
</script>

<style scoped>
</style>
