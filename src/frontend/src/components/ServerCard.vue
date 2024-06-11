<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';
import ProgressSpinner from 'primevue/progressspinner';
import { useBroker } from '@/stores/broker';
import { storeToRefs } from 'pinia'

const broker = useBroker();
const { serverMsg, isServerRunnig, isServerLoading } = storeToRefs(broker);
const { startServer, stopServer } = broker;

</script>

<template>
  <Card style="width: 20rem;"><template #title>
      <h1>Server</h1>
    </template>
    <template #content>
      <div class="flex justify-content-center gap-2">
        <h4>{{ serverMsg }}</h4>
        <ProgressSpinner v-if="isServerLoading" style="width: 2rem; height:2rem" strokeWidth="8" />
      </div>

      <div class="flex justify-content-center gap-2">
        <Button label="Stop Server" :disabled="!isServerRunnig" @click="stopServer" />
        <Button label="Start Server" :disabled='isServerRunnig' @click="startServer" />


      </div>
    </template>
  </Card>
</template>