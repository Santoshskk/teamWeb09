<!-- NavBar.vue -->
<template>
  <nav class="navbar">
    <ul class="nav-menu">
      <li class="nav-item">
        <router-link to="/welcome">Home</router-link>
      </li>

      <li class="nav-item dropdown">
        <a> My Offers</a>
        <div class="dropdown-content">
          <router-link to="/offers">Show overview</router-link>
          <router-link to="/offers-detail">Offersdetail</router-link>
          <router-link to="/offers/overview33">OffersEdit</router-link>
          <router-link to="/offers/overview34">OffersEdit [2.0] 34 </router-link>
          <router-link to="/offers/overview37">OffersEdit 37</router-link>
        </div>
      </li>

      <li class="nav-item dropdown">
        <a href="#">My Bids</a>
        <div class="dropdown-content">
          <a href="#">Active</a>
          <a href="#">History</a>
        </div>
      </li>

      <div class="signUpLogIn">
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/signup">Sign Up</router-link>
        </li>
        <li class="nav-item" v-if="!isAuthenticated">
          <router-link to="/login">Log In</router-link>
        </li>
        <li class="nav-item" v-if="isAuthenticated">
          <button @click="logout" >Log Out</button>
        </li>
      </div>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "NavBarSb",
  inject: ['sessionService'],
  computed: {
    isAuthenticated() {
      return this.sessionService.isAuthenticated();
    }
  },
  methods: {
    logout() {
      this.sessionService.signOut();
    }
  }
};
</script>
<style scoped>
/* Navbar styles */
.navbar {
  background-color: black;
}

.nav-menu {
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.nav-item {
  display: inline-block;
  padding: 10px 20px;
}

.nav-item a {
  color: white;
  text-decoration: none;
}

/* Dropdown styles */
.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {
  background-color: grey;
}

.dropdown:hover .dropdown-content {
  display: block;
}

.right {
  float: right;
}

.nav-itemRight {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin-right: 20px;
}

@media screen and (max-width: 768px) {
  .nav-menu {
    flex-direction: column;
  }

  .nav-item {
    text-align: center;
    padding: 10px 0;
  }

  .dropdown-content {
    position: static;
    display: none;
    background-color: transparent;
    box-shadow: none;
  }

  .nav-item:hover .dropdown-content {
    display: block;
  }
}
</style>
