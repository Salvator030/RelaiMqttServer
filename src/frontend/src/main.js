import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import '@/assets/styles.scss';

import App from './App.vue'
import router from './router'

import PrimeVue from 'primevue/config';
const app = createApp(App)

app.use(PrimeVue, {ripple: true});
app.use(createPinia());
app.use(router);

app.mount('#app');
