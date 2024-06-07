

<template>
    <div>
        <div>
            <div v-for="(item) in shellys" :key="item.id" class="card">
                <table>
                    <tr class="cardHeader">
                        <th colspan="2">model: {{ item.model }}</th>
                    </tr>
                    <div class="conntainer" v-if="item.model.includes('shellypro4pm')">
                        <Pro4pm :shelly="item" />
                    </div>
                    <div v-if="item.model.includes('shellyem3')">
                      <Em3 :shelly="item" />
                    </div>
                </table>
            </div>
            <button @click="getShellys">getShellys</button>
        </div>
        <div>
            <h3>Setting</h3>
            <input v-model="maxPower">Max Power in Watt
            <button @click="sendMaxPower">Ok</button>
        </div>
    </div >
</template >
<script setup>
    import Em3 from'./Em3.vue'
    import Pro4pm from './Pro4pm.vue'
</script >
    <script>

        export default {
            name: 'ShellyView',

        data() {
        return {
            shellys: null,
        maxPower: "0",

        }
    },

        methods: {
            async getShellys() {
            await fetch("/api/shellys/shellys").then((response) => response.text()).then((data) => {
                this.shellys = JSON.parse(data);

                Object.keys(this.shellys).forEach((key) => {
                    this.shellys[key].model = key;
                    console.log(this.shellys[key])
                })

            });
        },


        getMaxPower() {
            //read maxPower from input

            let temp;

        temp = this.maxPower.replace(/,/, ".");
        temp = parseFloat(temp);
        console.log(temp)
        if (isNaN(temp)) {
            alert("x");
            }
        else {
            console.log("return temp")
                this.maxPower = temp;
            }


        },

        sendMaxPower() {
            this.getMaxPower();
        // Simple POST request with a JSON body using fetch
        const requestOptions = {
            method: "POST",
        headers: {"Content-Type": "application/json" },
        body: JSON.stringify({maxPower: this.maxPower })
            };
        fetch("/api/shellys/setting/setMaxPower", requestOptions)
                .then(response => response.json())
                .then(data => (this.postId = data.id));
        },

        async requestMaxPower() {
            await fetch("/api/shellys/setting/getMaxPower")
                .then((response) => response.text()).then((data) => {
                    let setting = JSON.parse(data);
                    console.log("maxPower " + setting.maxPower);
                    this.maxPower = setting.maxPower;
                    console.log(data);

                });
        }


    },

        mounted() {
            this.getShellys();
        this.requestMaxPower();
    }
}
</script>

<style>
.conntainer {
    flex: 25;
}

.card {
    width: max-content;
    border: 1px solid black;
    border-collapse: collapse;
    text-align: center;
    margin: 1%;
}

.greyTr {
    background-color: lightgray;
}

.cardHeader {
    background-color: darkgray;
}

.col {
    flex: 5
}

.textBold {
    font-weight: bold;
}
</style>