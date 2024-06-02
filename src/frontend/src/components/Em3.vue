<script >
export default {
    name: 'Em3Entity',
    props: {shelly: Object},
    setup(props) {
        console.log(props.shelly)
    }
}
</script>

<template>
    <tr class="whiteTr">
        <td class="col">total_power</td>
        <td class="col">{{ shelly.total_power }}</td>
    </tr>
    <tr class="greyTr">
        <td class="textBold">wifi</td>
        <td class="col">&nbsp;</td>

    </tr>
    <tr class="whiteTr">
        <td class="col">conectet</td>
        <td class="col">{{ shelly.wifi_sta.conectet }}</td>
    </tr>
    <tr class="greyTr">
        <td class="col">ssid</td>
        <td class="col">{{ shelly.wifi_sta.ssid }}</td>
    </tr>
    <tr class="whiteTr">
        <td class="col">ip</td>
        <td class="col">{{ shelly.wifi_sta.ip }}</td>
    </tr>
    <tr class="greyTr">
        <td class="col">rssi</td>
        <td class="col">{{ shelly.wifi_sta.rssi }}</td>
    </tr>
    <tr class="whiteTr">
        <td class="col">&nbsp;</td>
        <td class="textBold">emeter1</td>
        <td class="textBold">emeter2</td>
        <td class="textBold">emeter3</td>
        <td class="textBold">emeter4</td>
    </tr>
    <tr class="greyTr">
        <td class="col">current:</td>
        <td class="col">{{ shelly.emeterList[0].current }}</td>
        <td class="col">{{ shelly.emeterList[1].current }}</td>
        <td class="col">{{ shelly.emeterList[2].current }}</td>
        <td class="col">{{ shelly.emeterList[3].current }}</td>
    </tr>
    <tr>
        <td class="col">energy:</td>
        <td class="col">{{ shelly.emeterList[0].energy }}</td>
        <td class="col">{{ shelly.emeterList[1].energy }}</td>
        <td class="col">{{ shelly.emeterList[2].energy }}</td>
        <td class="col">{{ shelly.emeterList[3].energy }}</td>
    </tr>
    <tr class="greyTr">
        <td class="col">power:</td>
        <td class="col">{{ shelly.emeterList[0].power }}</td>
        <td class="col">{{ shelly.emeterList[1].power }}</td>
        <td class="col">{{ shelly.emeterList[2].power }}</td>
        <td class="col">{{ shelly.emeterList[3].power }}</td>
    </tr>
    <tr>
        <td class="col">pf:</td>
        <td class="col">{{ shelly.emeterList[0].pf }}</td>
        <td class="col">{{ shelly.emeterList[1].pf }}</td>
        <td class="col">{{ shelly.emeterList[2].pf }}</td>
        <td class="col">{{ shelly.emeterList[3].pf }}</td>
    </tr>
    <tr class="greyTr">
        <td class="col">voltage:</td>
        <td class="col">{{ shelly.emeterList[0].voltage }}</td>
        <td class="col">{{ shelly.emeterList[1].voltage }}</td>
        <td class="col">{{ shelly.emeterList[2].voltage }}</td>
        <td class="col">{{ shelly.emeterList[3].voltage }}</td>
    </tr>
    <tr>
        <td class="col">total:</td>
        <td class="col">{{ shelly.emeterList[0].total }}</td>
        <td class="col">{{ shelly.emeterList[1].total }}</td>
        <td class="col">{{ shelly.emeterList[2].total }}</td>
        <td class="col">{{ shelly.emeterList[3].total }}</td>
    </tr>
    <tr class="greyTr">
        <td class="col">total_returned:</td>
        <td class="col">{{ shelly.emeterList[0].total_returned }}</td>
        <td class="col">{{ shelly.emeterList[1].total_returned }}</td>
        <td class="col">{{ shelly.emeterList[2].total_returned }}</td>
        <td class="col">{{ shelly.emeterList[3].total_returned }}</td>
    </tr>
    <tr>
        <td class="col">emeterIndex:</td>
        <td class="col">{{ shelly.emeterList[0].emeterIndex }}</td>
        <td class="col">{{ shelly.emeterList[1].emeterIndex }}</td>
        <td class="col">{{ shelly.emeterList[2].emeterIndex }}</td>
        <td class="col">{{ shelly.emeterList[3].emeterIndex }}</td>
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