<script setup>
import ShellyDetailCard from '@/components/icons/ShellyDeatailCard/ShellyDetailCard.vue';
import { useBroker } from '@/stores/broker';
import { storeToRefs } from 'pinia'
const broker = useBroker();
const { shellysList } = storeToRefs(broker);

console.log(shellysList)
</script>

<template>
  <div style="display: flex;  flex-wrap: wrap ; justify-content: flex-start;" >
    <ShellyDetailCard v-for="(item) in shellysList" :key="item.model" :device="item" />
  </div>
</template>