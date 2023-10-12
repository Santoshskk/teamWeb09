import {createRouter, createWebHashHistory} from "vue-router";
import offersOverview32 from "@/components/offers/OffersOverview32";
import category from "@/components/Category";
import offersOverview31 from "@/components/offers/OffersOverview31";
import unknownRoute from "@/components/UnknownRoute";
import offersOverview33 from "@/components/offers/OffersOverview33";
import OffersDetail32 from "@/components/offers/OffersDetail32";
import offersDetail34 from "@/components/offers/OffersDetail34";
import offersOverview34 from "@/components/offers/OffersOverview34";
export const router = createRouter({
    history: createWebHashHistory() ,
    routes: [
        {path: '/offers-detail', component: offersOverview32},
        {path: '/offers', component: offersOverview31},
        {path: '/welcome', component: category},
        {path: '/', redirect: "/welcome"},
        {path: '/signup', component: unknownRoute},
        {path: '/login',redirect: "/signup"},
        {path: '/:catchAll(.*)', component: unknownRoute},
        {
            path: '/offers/overview33',
            component: offersOverview33,
            children: [
                {
                    path: ':id',
                    component: OffersDetail32,
                    props: true
                }
            ]
        },
        {
            path: '/offers/overview34',
            component: offersOverview34,
            children: [
                {
                    path: ':id',
                    component: offersDetail34,
                    props: true
                }
            ]
        }


    ]
})
