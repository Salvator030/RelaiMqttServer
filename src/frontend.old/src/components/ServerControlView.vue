<template>
    <div>
        <button :disabled='isDisabledStartBtn' @click="start">Start Server</button>
        <br>
        <button :disabled='isDisabledStopBtn' @click="stop">Stop Server</button>
        <p>{{msg}}</p>
        <p>{{err}}</p>
    </div>
    <div class="settingDiv">
        <h3>Settings</h3>
        <h4>publish msg</h4>
        <div class="qosDiv">
            <h5>Qos</h5>
             <input type="radio" v-model="qos" value="0">at most once
            <br>
            <input type="radio" v-model="qos" value="1">at least once
            <br>
             <input type="radio" v-model="qos" value="2" checked>exactly once
        </div>
        <div class="retainDiv">
           <h5>Retain</h5>
          <input type="checkbox" v-model="retain" name="retain_cB" value="true">Retain
        </div>
         <button @click="sendSetting">OK</button>
  ´´</div>
</template>

<script>
export default {
  name: 'ServerControlView',
  data() {

       return {
       msg:'Server is Stopped',
         startBtnTerms: false,
         stopBtnTerms: true,
         qos: 2,
         retain: false
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
    },

    sendSetting(){
          // Simple POST request with a JSON body using fetch
          const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ qos: this.qos, retain: this.retain })
          };
          fetch("/api/broker/msgPublish/setting/setConnfig", requestOptions)
            .then(response => response.json())
            .then(data => (this.postId = data.id));
        },

    async requestSetting(){
        await fetch("/api/broker/msgPublish/setting/getConfig")
        .then((response) => response.text()).then((data) =>{
              let setting = JSON.parse(data);
                 this.retain = setting.isRetain;
                 this.qos = setting.qos;

                            });
    }

  },


  mounted() {
    this.isRun();
    this.requestSetting();
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
<style>
    .settingDiv{
        border: solid 1px black;
          width: max-content;
          margin: auto;

    }
    .qosDiv, .retainDiv {

        margin: 0.75%;
          width: max-content;
          display: inline-block;
    }
</style>