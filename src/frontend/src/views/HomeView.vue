<script setup>
import Card from 'primevue/card';
import Button from 'primevue/button';

import { ref } from 'vue';

const serverStat = ['stop','load',''];
const options = ref(['Stop', 'Start']);

</script>

<template>
  <Card style="width: 15rem;"><template #title>
      <h1>Server</h1>
    </template>
    <template #content>
      <div class="flex justify-content-center gap-2">
        <Button label="Stop Server" />
        <Button label="Start Server" />

      </div>
    </template>
  </Card>
</template>
