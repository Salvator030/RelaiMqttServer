<script setup>
import { ref } from 'vue';

import AppMenuItem from './AppMenuItem.vue';

const model = ref([
    {
        label: 'Home',
        items: [{ label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/' }]
    },  {
        label: 'Shellys',
        items: [{ label: 'Shellys', icon: 'pi pi-fw pi-list', to: '/Shellys' }]
    },  {
        label: 'Massages',
        items: [{ label: 'Massages', icon: 'pi pi-fw pi-mail', to: '/msgs' }]
    }
    
]);
</script>

<template>
    <ul class="layout-menu">
        <template v-for="(item, i) in model" :key="item">
            <app-menu-item v-if="!item.separator" :item="item" :index="i"></app-menu-item>
            <li v-if="item.separator" class="menu-separator"></li>
        </template>
        <li>
        
        </li>
    </ul>
</template>

<style lang="scss" scoped></style>
