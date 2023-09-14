<template>
  <headerComponent :title="title" :text="text" :imgSrc="imgSrc"/>
  <nav-bar/>
  <welcomeComponent :welcomeText="welcomeText" :clockImgSrc="clockImgSrc"/>
</template>

<script>
import welcomeComponent from "@/components/Welcome";
import headerComponent from "@/components/Header";
import NavBar from "@/components/Navbar";



export default {
  name: "AppComponent31",
  components: {NavBar, welcomeComponent, headerComponent},
  data(){
    return{
      title:"The Auctioneer",
      text:"Offed to you by Santosh and Tygo",
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