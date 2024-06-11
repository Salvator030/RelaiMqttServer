<script setup>
  import Card from 'primevue/card';
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

      <Card v-for="(item) in shellysList" :key="item.model" style="margin: 4px; width: 24rem;" >
        <template #title>
      <h4>{{ item.model  }}</h4>
    </template>
    <template #content>
      <p>total power: {{ item.total_power }}</p>
    </template>
      </Card>
    </template>
  </Card>
</template>