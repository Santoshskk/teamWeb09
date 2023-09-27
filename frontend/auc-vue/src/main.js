import { createApp } from 'vue'
import App from './AppComponent31.vue'
import './assets/global.css'
import router from "@/router";
// import {createRouter, createWebHistory} from "vue-router";
// const router = createRouter({
//     history: createWebHistory(),
//     routes:[{
//
//     }]
// })
createApp(App).use(router).mount('#app');
