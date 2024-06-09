<script setup>
import Card from 'primevue/card';

const props = defineProps({shellysList: []});
</script>

<template>
    <Card>
        <template #title>
        <h1>Shellys</h1>
      </template>
      <template #content>
        <div v-for="(item) in props.shellysList" :key="item.id">
            <p>abc</p>
        </div>
      </template>
    </Card>
 </template>