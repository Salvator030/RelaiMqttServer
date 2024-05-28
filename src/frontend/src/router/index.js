import { createRouter, createWebHistory } from 'vue-router'
import Broker from '../components/ServerControlView.vue'

const routes = [
  {
    path: '/',
    name: 'broker',
    component: Broker,
  },

  {
    path: '/msg',
    name: 'msg',
    component: () => import(/* webpackChunkName: "about" */ '../components/MsgView.vue')
  },

  {
      path: '/shellys',
      name: 'shellys',
      component: () => import(/* webpackChunkName: "about" */ '../components/ShellyView.vue')
    }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
