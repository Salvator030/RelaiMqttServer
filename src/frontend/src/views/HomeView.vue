<script setup>
import { ref } from 'vue'
import Card from 'primevue/card';
import Button from 'primevue/button';
import {Env} from '../Env'

const serverMsgs = ref(['Server is Stoped', 'Server is Bootig', 'Server is Running']);
const serverMsg = ref(serverMsgs.value[0]);

const isServerRunnig = ref(false);
const isServerLoading = ref(false);

const startServer = async () => {

  isServerLoading.value = true;
  await fetch(`${Env.API_BASE_URL}/broker/start`)
  .then((response) => response.text())
    .then((data) => {
      console.log(data)
      if (data == "0") {
        isServerLoading.value = false;
        isServerRunnig.value = true;
        serverMsg.value = serverMsgs.value[2];
      }

    });

};

const isServerRun = async () => {
  await fetch(`${Env.API_BASE_URL}/broker/isRun`)
    .then((response) => response.text())
    .then((data) => {
      console.log("data: " + data)
      if (data == "0") {
        serverMsg.value = serverMsgs.value[2];
        return true;
      }
      return false
    })
};

const stopServer = async () => {
  isServerLoading.value = true;

 await fetch(`${Env.API_BASE_URL}/broker/stop`)
 .then((response) => response.text())
    .then((data) => {
      console.log(data)
      if (data == "0") {
        isServerLoading.value = false;
        isServerRunnig.value = false;
        serverMsg.value = serverMsgs.value[2];
      }

    });
  isServerRunnig.value = false;
  serverMsg.value = serverMsgs.value[0];
};

</script>


<template>
  <Card style="width: 20rem;"><template #title>
      <h1>Server</h1>
    </template>
    <template #content>
      <h4>{{ serverMsg }}</h4>
      <div class="flex justify-content-center gap-2">
        <Suspense> <Button label="Stop Server" :disabled="!isServerRunnig" @click="stopServer" /></Suspense>
       <Button label="Start Server" :disabled='isServerRunnig' @click="startServer" />


      </div>
    </template>
  </Card>
</template>
