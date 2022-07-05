<template>
    <button :disabled='isDisabledStartBtn' @click="start">Start Server</button>
    <br>
    <button :disabled='isDisabledStopBtn' @click="stop">Stop Server</button>
    <p>{{msg}}</p>
    <p>{{err}}</p>
</template>

<script>
export default {
  name: 'ServerControlView',
  data() {

       return {
       msg:'Server is Stopped',
         err: '',
         startBtnTerms: false,
         stopBtnTerms: true
     }

  },
  methods:{


 async isRun(){
    await fetch("/api/broker/isRun")
        .then((response) => response.text())
        .then(( data) =>{
        console.log("data: " + data)
            if( data == "0"){
                 this.msg = 'Server is Running'
                 this.startBtnTerms = true;
                 this.stopBtnTerms = false
             }
        })
  },

    async start(){
      await fetch("/api/broker/start")
              .then((response) => response.text())
              .then(( data) => {
                  if ( data == "0"){
                     this.msg = 'Server is Running'
                     this.startBtnTerms = true;
                     this.stopBtnTerms = false
                 }

              });

    },

    stop(){
     fetch("/api/broker/stop")
        this.msg ='Server is Stopped'
        this.startBtnTerms = false;
        this.stopBtnTerms = true;
    }
  },


  mounted() {
    this.isRun();
  },

  computed: {
    isDisabledStartBtn: function(){
    return this.startBtnTerms;
        },

        isDisabledStopBtn: function(){
            return this.stopBtnTerms
        }
    }
   }
</script>