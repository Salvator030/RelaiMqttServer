<template>
 <p>last 100 msg</p>
   <div class="table-responsive">
             <table class="table">
               <thead>
                   <tr>
                       <th colspan="6" >MSG</th>
                       <th>clientID</th>
                       <th>topic</th>
                       <th>date</th>
                   </tr>
               </thead>
               <tbody  v-if="data">
                   <tr v-for="(item) in data" :key="item.id">
                       <td colspan="6">{{item.msg}}</td>
                       <td>{{item.clientID}}</td>
                       <td>{{item.topicName}}</td>
                       <td>{{item.date}}</td>
                  </tr>
               </tbody>
           </table>
       </div>
       <button @click="getData">getMsg</button>
</template>

<script>
export default {
  name: 'MsgView',
 data() {
     return {
         data: null,
         req: null
     }
 },

 mounted() {
     this.getData();

 },

 methods: {
    async getData() {
         let t = true

        do{
          await fetch("/api/broker/msgs").then((response) => response.text()).then(( data) =>{
               console.log("data: " + data)
                  this.data = JSON.parse(data);

               });

        await this.Sleep(10000)
        }while(t);
     },

      Sleep(milliseconds) {
        return new Promise(resolve => setTimeout(resolve, milliseconds));
     }
    }
 }

</script>
<style>
    .table{
         wight: 100%;
        border: 1px solid black;
    }

     th, td{
         border: 1px solid black;
     }
</style>

