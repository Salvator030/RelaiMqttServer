<script setup>
  import Card from 'primevue/card';
  import { ref } from 'vue'
const probs = defineProps({
   device: {},
})
const color = ref("bg-pink-300");
const getColor = (deviceName) => {
    const colorNameArray = ['primary','blue','green','yellow','cyan','pink','indigo','teal','orange','bluegray','purple','red','gray'];
    if (deviceName.includes("pro4pm")){
return colorNameArray[1] +"-500"
    }else if (deviceName.includes("em3")){
        return colorNameArray[2] +"-500"
    }
} 
</script>

<template>  
       <Card  style="margin: 4px; width: 25rem;"  >
        <template #title>
      <span  class="text-900 font-medium color border-round">{{ device.model}}</span>
    </template>
    <template #content>
      <span class="mt-1 text-600">total power: {{ device.total_power }}</span>
    </template>
      </Card>
</template>