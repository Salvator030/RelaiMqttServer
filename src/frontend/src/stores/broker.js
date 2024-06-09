import { defineStore } from 'pinia'
  import {Env} from '../Env'
  import { ref } from 'vue'
export const useBroker = defineStore( 'broker',() => {
  

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

return {serverMsg,isServerRunnig,isServerLoading, startServer, stopServer}
});