<script setup>
import Card from 'primevue/card';
import { useBroker } from '@/stores/broker';
import { storeToRefs } from 'pinia'
import { ref } from 'vue'
const broker = useBroker();
const { shellysList } = storeToRefs(broker);
const shellys = ref([]);
setInterval(() => { shellys.value = shellysList; console.log(shellys.value.) },10000)

</script>

<template>
  <Card>
    <template #title>
      <h1>Shellys</h1>
    </template>
    <template #content>
      <div v-for="(item) in shellys.value" :key="item.id">
        <p>{{ item.id }}</p>
      </div>
    </template>
  </Card>
</template>