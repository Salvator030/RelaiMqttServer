<script setup>

import Message from 'primevue/message';
import Paginator from 'primevue/paginator';
import { computed, ref } from 'vue';

import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import { useBroker } from '@/stores/broker';
import { storeToRefs } from 'pinia'

const broker = useBroker();
const { mqttMsgs } = storeToRefs(broker);
console.log(mqttMsgs)
const mqttMsgsArray = ref([]);
const count = ref(0);

const labelStyle = ['font-medium','text-3xl'];

        setInterval(()=> { mqttMsgsArray.value = mqttMsgs._rawValue;
},3000)
    
// const page = computed(() => {return mqttMsgsArray.value.slice(count.value * 10 ,((count.value+1) * 10) )})
  
   



</script>

<template>
<span :class="labelStyle">Massages</span>

<DataTable :value="mqttMsgs"  paginator :rows="5" :rowsPerPageOptions="[5, 10, 20, 50]" stripedRows tableStyle="min-width: 50rem">
    <Column field="date" header="Date"></Column>
    <Column field="clientID" header="ClientID"></Column>
    <Column field="msg" header="msg"></Column>
</DataTable>
<Column field="code" header="Code"></Column>
    <!--
    <Message v-for="(item) in mqttMsgsArray" :key="item.id" :closable="false" icon="null" paginator :row="5">
        <div :class="labelStyle" style="color: var(--surface-900);">{{ item.date }}</div>
        <div :class="labelStyle" style="color: var(--surface-900);">{{ item.clientID }}</div>
        <div :class="labelStyle" style="color: var(--surface-900);">{{ item.topicName }}</div>
        <div style="color: var(--surface-900);"> {{ item.msg }} </div>
    </Message>
-->

</template>