import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: () => import('./components/Home.vue'),
        },
        {
            path: '/food',
            name: 'food',
            component: () => import('./components/Food.vue'),
        },
        {
            path: '/about',
            name: 'about',
            component: () => import('./components/About.vue'),
        },
    ]
})

export default router
