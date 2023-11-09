<template lang="html">
  <section class="dashboard">

      <count-to />

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
import  CountTo from './../../components/dashboard/CountTo';

export default {
  name: 'staffdashboard',
  components: {
    CountTo
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
  },
  methods: {
    // toggleProBanner: function () {
    //   $('body').toggleClass('pro-banner-collapse')
    // }
  }
}
</script>

<style scoped lang="css">
.card-statistics .card-col {
  border-right: 1px solid #cdd6dc;
}
</style>
