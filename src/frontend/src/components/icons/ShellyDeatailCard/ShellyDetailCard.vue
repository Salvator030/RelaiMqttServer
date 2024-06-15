<script setup>
import SpecificShellyDetails from './SpecificShellyDetails.vue';
import Card from 'primevue/card';
const probs = defineProps({ device: {} })

const labelStyle = ['font-medium','text-surface-500','dark:text-surface-400','text-sm'];
const contentStyle =['text-lg','font-medium mt-2'];
</script>

<template>
    <Card style="margin: 4px; max-width: 50rem;" >

        <template #title>
            <h3>{{ device.model }}</h3>
        </template>
        <template #content>

            <div :class="margin">
                <span :class="labelStyle">IP:</span>
                <div :class="contentStyle">{{ device.ip }}</div>
            </div>
            <div>
                <span :class="labelStyle">MAC:</span>
                <div :class="contentStyle">{{ device.mac }}</div>
            </div>
            <SpecificShellyDetails :device="probs.device" />

            <div>
                <span :class="labelStyle">Total Power:</span>
                <div :class="contentStyle">{{ device.total_power }}</div>
            </div>
        </template>
    </Card>

</template>

