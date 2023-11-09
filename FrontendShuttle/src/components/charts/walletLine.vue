<template>
  <LineChartGenerator
    ref="walletChart"
    chartId="Line-Wallet-Chart"
    :chart-data="chartData"
    :chart-options="{
      responsive: true,
      maintainAspectRatio: false,
      plugins: {
        legend: { display: false },
        title: { display: true, text: 'Customer Wallet ReCharge' },
      },
      responsive: true,
      maintainAspectRatio: false,
    }"
  />
</template>

<script>
import { Line as LineChartGenerator } from "vue-chartjs/legacy";
import { paymentService } from "../../services";
import moment from "moment-timezone";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
  LineElement,
  PointElement,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  LineElement,
  CategoryScale,
  LinearScale,
  PointElement
);
const colors = {
  purple: {
    default: "rgba(149, 76, 233, 1)",
    half: "rgba(149, 76, 233, 0.5)",
    quarter: "rgba(149, 76, 233, 0.25)",
    zero: "rgba(149, 76, 233, 0)",
  },
  indigo: {
    default: "rgba(80, 102, 120, 1)",
    quarter: "rgba(80, 102, 120, 0.25)",
  },
};

export default {
  name: "walletbar",
  data() {
    return {
      gradient: null,
      start_date: moment().add(1, "days").format("YYYY-MM-DD"),
      end_date: moment().subtract(1, "years").format("YYYY-MM-DD"),
      is_wallet: "1",
      yAxes: {
        scaleLabel: {
          display: true,
          labelString: "Weight in KG",
          padding: 10,
        },
        gridLines: {
          display: true,
          color: colors.indigo.quarter,
        },
        ticks: {
          beginAtZero: false,
          max: 63,
          min: 57,
          padding: 10,
        },
      },
      chartData: {},
      loaded: false,
    };
  },
  components: { LineChartGenerator },
  mounted() {
    this.loaded = false;
    try {
      this.loadWalletCount();
      this.loaded = true;
    } catch (err) {
      return err;
    }
  },
  methods: {
    loadWalletCount() {
      paymentService
        .walletCount(
          "Completed",
          this.start_date,
          this.end_date,
          this.is_wallet
        )
        .then((response) => {
          this.chartData = {
            labels: response.years_data,
            datasets: [
              {
                fill: true,
                backgroundColor: colors.purple.half,
                pointBackgroundColor: colors.purple.default,
                borderColor: colors.purple.default,
                lineTension: 0.2,
                borderWidth: 2,
                pointRadius: 3,
                data: response.data,
                label: "wallet Recharge",
              },
            ],
          };
        });
    },
    updateChart(elem) {
      console.log(" this.$refs", this.$refs.chart1);
      if (elem) {
        this.$refs.chart1.chart.data.datasets[0].backgroundColor = "#D3D3D3";
        this.$refs.chart1.updateChart();

        for (
          let i = 0;
          i < this.$refs.chart1.chart.data.datasets[0].data.length;
          i++
        ) {
          if (i == elem.index) {
            this.$refs.chart1.chart.data.datasets[0].hoverBackground =
              "#DB4C77";
          }
        }
      }
    },
    getBackOriginalChart() {
      if (this.$refs.chart1.chart.data == null) {
        return;
      }
      this.$refs.chart1.chart.data.datasets[0].backgroundColor = "#DB4C77";
      this.$refs.chart1.updateChart();
    },
  },
};
</script>

<style scoped></style>
