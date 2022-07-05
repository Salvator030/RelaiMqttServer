<template>
    <ServerControlView/>
    <MsgView/>
    <ShellyView/>

</template>

<script>
import ServerControlView from './components/ServerControlView.vue'
import MsgView from './components/MsgView.vue'
import ShellyView from './components/ShellyView'

export default {
  name: 'App',

  components: {
   ServerControlView,
   MsgView,
   ShellyView
  }
}
</script>

