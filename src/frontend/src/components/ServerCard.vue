<script setup>
import { ref } from 'vue'
import Card from 'primevue/card';
import Button from 'primevue/button';
import ProgressSpinner from 'primevue/progressspinner';

import {Env} from '../Env'

const serverMsgs = ref(['Server is Stoped', 'Server is Bootig', 'Server is Running', 'Server is ShutDown']);
const serverMsg = ref(serverMsgs.value[0]);
const isServerRunnig =  ref(false);
const isServerLoading = ref(false);

const startServer = async () => {

  isServerLoading.value = true;
  serverMsg.value = serverMsgs.value[1];

  await fetch(`${Env.API_BASE_URL}/broker/start`)
  .then((response) => response.text())
    .then((data) => {
      if (data == "0") {
        isServerLoading.value = false;
        isServerRunnig.value = true;
        serverMsg.value = serverMsgs.value[2];
      }

    });

};

const stopServer = async () => {
  isServerLoading.value = true;
  serverMsg.value = serverMsgs.value[3];

 await fetch(`${Env.API_BASE_URL}/broker/stop`)
 .then((response) => response.text())
    .then((data) => {
      if (data == "0") {
        isServerLoading.value = false;
        isServerRunnig.value = false;
        serverMsg.value = serverMsgs.value[2];
      }

    });
  isServerRunnig.value = false;
  serverMsg.value = serverMsgs.value[0];
};

const isServerRun = async () => {
  await fetch(`${Env.API_BASE_URL}/broker/isRun`)
    .then((response) => response.text())
    .then((data) => {
      if (data === "0") {
        serverMsg.value = serverMsgs.value[2];
        isServerRunnig.value = true;
      }else{
      serverMsg.value = serverMsgs.value[0];
      isServerRunnig.value = false;}
      
    })
};

isServerRun();
</script>

<template>
    <Card style="width: 20rem;"><template #title>
        <h1>Server</h1>
      </template>
      <template #content>
        <div class="flex justify-content-center gap-2"> <h4>{{ serverMsg }}</h4>
        <ProgressSpinner v-if="isServerLoading"  style="width: 2rem; height:2rem" strokeWidth="8"/></div>
       
        <div class="flex justify-content-center gap-2">
          <Button label="Stop Server" :disabled="!isServerRunnig" @click="stopServer" />
         <Button label="Start Server" :disabled='isServerRunnig' @click="startServer" />
  
  
        </div>
      </template>
    </Card>
  </template>