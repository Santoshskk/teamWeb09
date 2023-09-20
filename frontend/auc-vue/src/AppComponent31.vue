<template>
  <headerComponent :title="title" :text="text" :imgSrc="imgSrc"/>
  <nav-bar/>
  <ExpiredAuction :welcomeText="welcomeText" :clockImgSrc="clockImgSrc"/>
  <Category/>
  <Info/>


  <offers-overview31></offers-overview31>
</template>

<script>
import ExpiredAuction from "@/components/ExpiredAuction";
import headerComponent from "@/components/Header";
import NavBar from "@/components/Navbar";
import Category from "@/components/Category";
import Info from "@/components/Info";
import offersOverview31 from "@/components/offers/OffersOverview31";


export default {

  //ExpiredAuction,
  //Info
  //Category
  name: "AppComponent31",
  components: {NavBar, headerComponent, offersOverview31},
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

<style >
#app{
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}



</style>