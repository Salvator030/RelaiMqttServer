<script setup>
import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
const props = defineProps({ emeters: {} })
</script>
<template>
    <DataTable :value="emeters" :size="'small'" tableStyle="min-width: 25rem max-width: 25rem" showGridlines
        stripedRows>
        <Column field="emeterIndex" header="index" />
        <Column field="current" header="current" />
        <Column field="returned_energy" header="returned energy" />
        <Column field="total_returned" header="total returned" />
        <Column field="total" header="total" />
        <Column field="pf" header="pf" />
        <Column field="power" header="power" />
        <Column field="energy" header="energy" />
        <Column field="voltage" header="voltage" />
    </DataTable>
</template>