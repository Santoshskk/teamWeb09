
import Vue from 'vue';
import VueRouter from 'vue-router';

// Importing the provided .vue components
import OffersDetail32 from '@/components/offers/OffersDetail32';
import OffersOverview31 from '@/components/offers/OffersOverview31';
import OffersOverview32 from '@/components/offers/OffersOverview32';

Vue.use(VueRouter);

const routes = [
    { path: '/offers-detail', component: OffersDetail32 },
    { path: '/offers-overview-31', component: OffersOverview31 },
    { path: '/offers-overview-32', component: OffersOverview32 }
];

const router = new VueRouter({
    mode: 'hash',
    routes
});

export default router;
