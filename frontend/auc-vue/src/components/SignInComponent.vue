<!-- Login.vue -->
<template>
  <div class="login-container">
    <form @submit.prevent="signIn" class="form">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
        <p v-if="showError" id="errorMsg" class="text-danger">{{ errorMessage }}</p>
      </div>
      <button type="submit" class="btn-signin" @click="handleLogin">Sign In</button>
    </form>
  </div>
</template>

<script>
import {router} from '@/router'
export default {
  inject: ['sessionService'],
  data() {
    return {
      email: '',
      password: '',
      showError: false,
      errorMessage: '',
    };
  },
  methods: {
    /**
     * This function is responsible for logging the user in to their account
     */
    async handleLogin () {
      try {
        this.showError = false
        const response = await this.sessionService.asyncSignIn(this.email, this.password)
        if (response) {
          await router.push({ path: '/welcome' })
        } else {
          this.showError = true
          throw new Error('Something went wrong: ' + 'Invalid username or password')
        }
      } catch (error) {
        console.error(error)
        this.errorMessage = error.message
      }
    }
  },
  created() {
    // Check for the signOff query parameter
    const signOff = this.$route.query.signOff === 'true';

    // If signOff is true, call sessionService.signOut()
    if (signOff) {
      this.sessionService.signOut();
    }
  },
};
</script>

<style scoped>
/* Add your custom styling for the login form */
.login-container {
  display: flex;
  justify-content: center;
  margin-top: 50px;
  height: 350px;
  background-color: white;
}

.form {
  background-color: #D2B48C;
  padding: 50px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 5px;
}

input {
  width: 100%;
  padding: 8px;
  box-sizing: border-box;
}

.btn-signin {
  background-color: black;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.btn-signin:hover {
  background-color: grey;
}
</style>
