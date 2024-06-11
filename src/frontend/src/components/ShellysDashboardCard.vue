<script setup>
  import Card from 'primevue/card';
  import ShellyDeviceOverViewCard from './ShellyDeviceOverViewCard.vue';
  import { useBroker } from '@/stores/broker';
  import { storeToRefs } from 'pinia'
  const broker = useBroker();
  const { shellysList } = storeToRefs(broker);
</script>

<template>
  <Card style="margin: 4px; ">
    <template #title >
      <h1 >Shellys</h1>
    </template>
    <template #content>

   <ShellyDeviceOverViewCard  v-for="(item) in shellysList" :key="item.model" :device="item"/>
    </template>
  </Card>
</template>