<script setup>
  import Card from 'primevue/card';
  import { computed, ref } from 'vue'
const probs = defineProps({
   device: {},
})

 const colorNameArray = ['primary','blue','green','yellow','cyan','pink','indigo','teal','orange','bluegray','purple','red','gray'];
  
const getColor = (deviceName) => {
     if (deviceName.includes("pro4pm")){
return "bg-" + colorNameArray[1] +"-300"
    }else if (deviceName.includes("em3")){
        return "bg-" +colorNameArray[2] +"-300"
    }
} 

const base = ref('text-900 font-medium border-round')
const color = computed( ()=>{return getColor(probs.device.model)
    } );
</script>

<template>  
       <Card  style="margin: 4px; width: 25rem;"  >
        <template #title>
      <span  :class="[base,color]">{{ device.model}}</span>
    </template>
    <template #content>
      <span class="mt-1 text-600">total power: {{ device.total_power }}</span>
    </template>
      </Card>
</template>