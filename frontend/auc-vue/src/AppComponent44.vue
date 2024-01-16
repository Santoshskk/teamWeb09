<template>
  <div>
    <headerSbComponent :title="title" :text="text" :imgSrc="imgSrc"/>
    <NavBarSb/>
    <router-view/>
  </div>
</template>

<script>
import NavBarSb from "@/components/NavBarSb";
import headerSbComponent from "@/components/HeaderSb";
import {OffersAdaptor} from "@/services/offers-adaptor";
import CONFIG from '@/app-config.js';
import {shallowReactive} from "vue";
import {SessionSbService} from "@/services/SessionSbService";
import {FetchInterceptor} from "@/services/FetchInterceptor";
export default {
  name: "AppComponent44",
  components: {NavBarSb, headerSbComponent},
  provide() {
    this.theSessionService = shallowReactive(
        new SessionSbService(CONFIG.BACKEND_URL + '/authentication/login'))
    this.theFetchInterceptor =
        new FetchInterceptor(this.theSessionService, this.$router);
    return {
      offersServices: new OffersAdaptor(CONFIG.BACKEND_URL),
      sessionService: this.theSessionService

    }
  },
  beforeUnmount() {
    this.theFetchInterceptor.unregister();
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
