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
import {OffersAdaptor} from "@/services/offers-adaptor";
import CONFIG from '@/app-config.js'
export default {
  name: "AppComponent33",
  components: {NavBar,headerComponent},
  provide(){
    return {
      offersServices: new OffersAdaptor(CONFIG.BACKEND_URL)
    }
  },
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
