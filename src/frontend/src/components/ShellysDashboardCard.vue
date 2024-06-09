<script setup>
import Card from 'primevue/card';
import { useBroker } from '@/stores/broker';
import { storeToRefs } from 'pinia'
const broker = useBroker();
const { shellysList} = storeToRefs(broker);
console.log(shellysList)
</script>

<template>
    <Card>
        <template #title>
        <h1>Shellys</h1>
      </template>
      <template #content>
        <div v-for="(item) in shellysList" :key="item.id">
            <p>{{item.id}}</p>
        </div>
      </template>
    </Card>
 </template>