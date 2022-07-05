<template>
    <div>
        <p>{{shellys}}</p>
        <button @click="getShellys">getShellys</button>
    </div>
</template>

<script>
    export default {
      name: 'ShellyView',

     data(){
        return {
           shellys: null
         }
     },

     methods: {
        async getShellys(){
             await fetch("/api/shellys/shellys").then((response) => response.text()).then((data) =>{
                           console.log("data: " + data)
                              this.shellys = data;
            });

        }
     },

     mounted(){
        this.getShellys();
     }
    }
</script>