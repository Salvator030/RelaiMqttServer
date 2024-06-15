<script setup>
    import DataTable from 'primevue/datatable';
    import Column from 'primevue/column';

    const probs = defineProps({
        channels: {}
    })
  
  </script>

<template><DataTable :value="channels" :size="'small'" tableStyle="min-width: 25rem max-width: 25rem" showGridlines stripedRows  >
    <Column field="relayIndex" header="index" /> 
    <Column field="output" header="output" /> 
    <Column field="energy_total" header="energy_total" /> 
    <Column field="pf" header="pf" /> 
    <Column field="power" header="power" /> 
    <Column field="energy_minuteTs" header="energy_minuteTs" /> 
    <Column field="voltage" header="voltage" /> 
    <Column field="temp_c" header="temp_c" /> 
</DataTable>
</template>