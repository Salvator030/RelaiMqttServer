<script setup>
  import Card from 'primevue/card';
  import { useBroker } from '@/stores/broker';
  import { storeToRefs } from 'pinia'
  const broker = useBroker();
  const { shellysList } = storeToRefs(broker);


</script>

<template>
    <Card  v-for="(item) in shellysList" :key="item.model" :device="item" >

        <template #title><h3>{{item.model}}</h3></template>
        <template #content></template>
    </Card>	
</template>