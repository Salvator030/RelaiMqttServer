<script setup>
import Pro4Pm from './Pro4Pm.vue';
import Em3 from './Em3.vue';
import { computed } from 'vue';
const probs = defineProps({
    device: {}
})
const model = computed(() => {
    return probs.device.model.split("-")[0]
});
</script>

<template> 
      <Pro4Pm v-if="model === 'shellypro4pm'" :channels="device.channels" />
      <Em3 v-else-if="model === 'shellyem3'" :emeters="device.emeterList"/>
</template>