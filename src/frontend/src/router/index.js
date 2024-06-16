import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Shellys from "../views/Shellys.vue"
import Msgs from "../views/Msgs.vue"
import AppLayout from '@/layout/AppLayout.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      component: AppLayout,
      children: [
        {
          path: '/',
          name: 'home',
          component: HomeView
        },
        {
          path: '/shellys',
          name: 'shellys',
          component: Shellys
        },
        {
          path: '/msgs',
          name: 'msgs',
          component: Msgs
        }
      ]
    }
  ]
})

export default router
