<template lang="html">
  <section class="dashboard">
    <count-to />

    <div class="row">
      <div class="col-md-12 grid-margin">
        <div class="card card-statistics">
          <div class="row">
            <div class="card-col col-xl-6 col-lg-6 col-md-6 col-6">
              <div class="card-body">
                <walletLine />
              </div>
            </div>
            <div class="card-col col-xl-6 col-lg-6 col-md-6 col-6">
              <div class="card-body">
                <bookingBar />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script lang="js">
// import statsLineGraph1 from '../components/charts/dashboard_1/stats-line-graph-1'
// import statsLineGraph2 from '../components/charts/dashboard_1/stats-line-graph-2'
// import statsLineGraph3 from '../components/charts/dashboard_1/stats-line-graph-3'
// import statsLineGraph4 from '../components/charts/dashboard_1/stats-line-graph-4'
// import salesStatisticsOverview from '../components/charts/dashboard_1/sales-statistics-overview'
// import netProfit from '../components/charts/dashboard_1/net-Profit'
// import totalRevenue from '../components/charts/dashboard_1/total-revenue'
// import marketOverviewChart from '../components/charts/dashboard_1/market-overview-chart'
// import totalTransaction from '../components/charts/dashboard_1/total-transaction'
// import realtimeStatistics from '../components/charts/dashboard_1/realtime-statistics'
// import usersDoughnutChart from '../components/charts/dashboard_1/usersDoughnutChart'
// import JQuery from 'jquery'
// let $ = JQuery

import walletLine from "../../components/charts/walletLine"
import bookingBar from "../../components/charts/bookingBar"
import  CountTo from '../../components/dashboard/CountTo';

export default {
  name: 'admindashboard',
   data() {
    return {
      countDriver:{
        startVal: 10,
        endVal: 0
      },
      countCustomer:{
        startVal: 200,
        endVal: 0
      },
      countAgent:{
        startVal: 10,
        endVal: 0
      },
      countHelp:{
        startVal: 5,
        endVal: 0
      },
      countTotalBooking:{
        startVal: 5,
        endVal: 0
      },
    }
   },
  components: {
    CountTo,
    walletLine,
    bookingBar
   // statsLineGraph1,
    //statsLineGraph2,
   // statsLineGraph3,
    //statsLineGraph4,
    //salesStatisticsOverview,
   // netProfit,
   // totalRevenue,
   // marketOverviewChart,
    //totalTransaction,
   //realtimeStatistics,
    // usersDoughnutChart

  }
}
</script>

<style scoped lang="css">
.card-statistics .card-col {
  border-right: 1px solid #cdd6dc;
}
</style>
