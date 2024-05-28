<template>
<div>
    <div>
       <div v-for="(item) in shellys" :key="item.id" class="card" >
         <table >
         <tr class="cardHeader">
          <th colspan="2">model: {{item.model}}</th></tr>
          <tr class="whiteTr"><td>&nbsp;</td><td>chanel1</td> <td>chanel2</td><td>chanel3</td><td>chanel4</td></tr>
          <tr class="greyTr"><td>output:</td><td>{{item.channels[0].output}}</td><td>{{item.channels[1].output}}</td><td>{{item.channels[2].output}}</td><td>{{item.channels[3].output}}</td></tr>
           <tr><td>current:</td><td>{{item.channels[0].current}}</td><td>{{item.channels[1].current}}</td><td>{{item.channels[2].current}}</td><td>{{item.channels[3].current}}</td></tr>
            <tr class="greyTr"><td>power:</td><td>{{item.channels[0].power}}</td><td>{{item.channels[1].power}}</td><td>{{item.channels[2].power}}</td><td>{{item.channels[3].power}}</td></tr>
             <tr><td>pf:</td><td>{{item.channels[0].pf}}</td><td>{{item.channels[1].pf}}</td><td>{{item.channels[2].pf}}</td><td>{{item.channels[3].pf}}</td></tr>
             <tr class="greyTr"><td>source:</td><td>{{item.channels[0].source}}</td><td>{{item.channels[1].source}}</td><td>{{item.channels[2].source}}</td><td>{{item.channels[3].source}}</td></tr>
             <tr><td>voltage:</td><td>{{item.channels[0].voltage}}</td><td>{{item.channels[1].voltage}}</td><td>{{item.channels[2].voltage}}</td><td>{{item.channels[3].voltage}}</td></tr>
             <tr class="greyTr"><td>energy.total:</td><td>{{item.channels[0].energy_total	}}</td><td>{{item.channels[1].energy_total	}}</td><td>{{item.channels[2].energy_total	}}</td><td>{{item.channels[3].energy_total}}</td></tr>
             <tr><td>energy_minuteTs:</td><td>{{item.channels[0].energy_minuteTs}}</td><td>{{item.channels[1].energy_minuteTs}}</td><td>{{item.channels[2].energy_minuteTs}}</td><td>{{item.channels[3].energy_minuteTs}}</td></tr>
             <tr class="greyTr"><td>energy_byMinute:</td><td>{{item.channels[0].energy_byMinute}}</td><td>{{item.channels[1].energy_byMinute}}</td><td>{{item.channels[2].energy_byMinute}}</td><td>{{item.channels[3].energy_byMinute}}</td></tr>
             <tr><td>temp.°C:</td><td>{{item.channels[0].temp_c}}</td><td>{{item.channels[1].temp_c}}</td><td>{{item.channels[2].temp_c}}</td><td>{{item.channels[3].temp_c}}</td></tr>

          </table>
        </div>
        <button @click="getShellys">getShellys</button>
    </div>
    <div>
    <h3>Setting</h3>
        <input v-model="maxPower">Max Power in Watt
        <button @click="sendMaxPower">Ok</button>
    </div>
</div>
</template>

<script>
    export default {
      name: 'ShellyView',

     data(){
        return {
           shellys: null,
           maxPower: "0",

         }
     },

     methods: {
        async getShellys(){
             await fetch("/api/shellys/shellys").then((response) => response.text()).then((data) =>{
                          this.shellys = JSON.parse(data);


            });
        },


        getMaxPower(){
        //read maxPower from input

             let temp;

               temp =this.maxPower.replace(/,/,".");
                temp =  parseFloat(temp);
                console.log(temp)
                if (isNaN(temp)){
                    alert("x");
                 }
                 else{
                    console.log("return temp")
                     this.maxPower = temp;
                 }


        },

        sendMaxPower(){
            this.getMaxPower();
                  // Simple POST request with a JSON body using fetch
                    const requestOptions = {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ maxPower: this.maxPower })
                  };
                  fetch("/api/shellys/setting/setMaxPower", requestOptions)
                    .then(response => response.json())
                    .then(data => (this.postId = data.id));
                },

        async requestMaxPower(){
                     await fetch("/api/shellys/setting/getMaxPower")
                        .then((response) => response.text()).then((data) =>{
                                 let setting = JSON.parse(data);
                                     console.log("maxPower " + setting.maxPower);
                                     this.maxPower = setting.maxPower;
                                      console.log(data);

                    });
                }


     },

     mounted(){
        this.getShellys();
        this.requestMaxPower();
     }
    }
</script>

<style>
    .card{
        width: max-content;
         border: 1px solid black;
         border-collapse: collapse;
         text-align: center;
         margin: 1%;
    }

    .greyTr{
        background-color: lightgray;
    }

    .cardHeader{
         background-color: darkgray;
    }
</style>