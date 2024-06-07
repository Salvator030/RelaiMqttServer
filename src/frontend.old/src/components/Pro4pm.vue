<script>
export default {
    name: 'Pro4pmEntity',
    props: {shelly: Object},
    setup(props) {
        console.log(props.shelly)
    }
}
</script>
<template>
    <tr class="whiteTr">
                            <td class="col">&nbsp;</td>
                            <td class="col">chanel1</td>
                            <td class="col">chanel2</td>
                            <td class="col">chanel3</td>
                            <td class="col">chanel4</td>
                        </tr>
                        <tr class="greyTr">
                            <td class="col">output:</td>
                            <td class="col">{{ shelly.channels[0].output }}</td>
                            <td class="col">{{ shelly.channels[1].output }}</td>
                            <td class="col">{{ shelly.channels[2].output }}</td>
                            <td class="col">{{ shelly.channels[3].output }}</td>
                        </tr>
                        <tr>
                            <td class="col">current:</td>
                            <td class="col">{{ shelly.channels[0].current }}</td>
                            <td class="col">{{ shelly.channels[1].current }}</td>
                            <td class="col">{{ shelly.channels[2].current }}</td>
                            <td class="col">{{ shelly.channels[3].current }}</td>
                        </tr>
                        <tr class="greyTr">
                            <td class="col">power:</td>
                            <td class="col">{{ shelly.channels[0].power }}</td>
                            <td class="col">{{ shelly.channels[1].power }}</td>
                            <td class="col">{{ shelly.channels[2].power }}</td>
                            <td class="col">{{ shelly.channels[3].power }}</td>
                        </tr>
                        <tr>
                            <td class="col">pf:</td>
                            <td class="col">{{ shelly.channels[0].pf }}</td>
                            <td class="col">{{ shelly.channels[1].pf }}</td>
                            <td class="col">{{ shelly.channels[2].pf }}</td>
                            <td class="col">{{ shelly.channels[3].pf }}</td>
                        </tr>
                        <tr class="greyTr">
                            <td class="col">source:</td>
                            <td class="col">{{ shelly.channels[0].source }}</td>
                            <td class="col">{{ shelly.channels[1].source }}</td>
                            <td class="col">{{ shelly.channels[2].source }}</td>
                            <td class="col">{{ shelly.channels[3].source }}</td>
                        </tr>
                        <tr>
                            <td class="col">voltage:</td>
                            <td class="col">{{ shelly.channels[0].voltage }}</td>
                            <td class="col">{{ shelly.channels[1].voltage }}</td>
                            <td class="col">{{ shelly.channels[2].voltage }}</td>
                            <td class="col">{{ shelly.channels[3].voltage }}</td>
                        </tr>
                        <tr class="greyTr">
                            <td class="col">energy.total:</td>
                            <td class="col">{{ shelly.channels[0].energy_total }}</td>
                            <td class="col">{{ shelly.channels[1].energy_total }}</td>
                            <td class="col">{{ shelly.channels[2].energy_total }}</td>
                            <td class="col">{{ shelly.channels[3].energy_total }}</td>
                        </tr>
                        <tr>
                            <td class="col">energy_minuteTs:</td>
                            <td class="col">{{ shelly.channels[0].energy_minuteTs }}</td>
                            <td class="col">{{ shelly.channels[1].energy_minuteTs }}</td>
                            <td class="col">{{ shelly.channels[2].energy_minuteTs }}</td>
                            <td class="col">{{ shelly.channels[3].energy_minuteTs }}</td>
                        </tr>
                        <tr class="greyTr">
                            <td class="col">energy_byMinute:</td>
                            <td class="col">{{ shelly.channels[0].energy_byMinute }}</td>
                            <td class="col">{{ shelly.channels[1].energy_byMinute }}</td>
                            <td class="col">{{ shelly.channels[2].energy_byMinute }}</td>
                            <td class="col">{{ shelly.channels[3].energy_byMinute }}</td>
                        </tr>
                        <tr>
                            <td class="col">temp.°C:</td>
                            <td class="col">{{ shelly.channels[0].temp_c }}</td>
                            <td class="col">{{ shelly.channels[1].temp_c }}</td>
                            <td class="col">{{ shelly.channels[2].temp_c }}</td>
                            <td class="col">{{ shelly.channels[3].temp_c }}</td>
                        </tr>
</template>
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