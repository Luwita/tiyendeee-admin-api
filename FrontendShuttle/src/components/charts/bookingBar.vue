<template>
  <div>
    <b-skeleton-wrapper :loading="loading">
      <template #loading>
        <b-card>
          <b-skeleton animation="wave" width="85%"></b-skeleton>
          <b-skeleton animation="wave" width="55%"></b-skeleton>
          <b-skeleton animation="wave" width="70%"></b-skeleton>
          <b-skeleton animation="wave" width="50%"></b-skeleton>
          <b-skeleton animation="wave" width="60%"></b-skeleton>
          <b-skeleton animation="wave" width="80%"></b-skeleton>
        </b-card>
      </template>

      <BarChartGenerator
        ref="bookingChart"
        chartId="Line-Booking-Chart"
        :chart-data="chartData"
        :chart-options="{
          responsive: true,
          maintainAspectRatio: false,
          plugins: {
            legend: { display: false },
            title: { display: true, text: 'Total Booking Completed' },
          },
          responsive: true,
          maintainAspectRatio: false,
        }"
      />
    </b-skeleton-wrapper>
  </div>
</template>

<script>
import { Bar as BarChartGenerator } from "vue-chartjs/legacy";
import { bookingService } from "../../services";
import moment from "moment-timezone";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  CategoryScale,
  LinearScale
);

export default {
  name: "bookingbar",
  data() {
    return {
      gradient: null,
      start_date: moment().add(1, "days").format("YYYY-MM-DD"),
      end_date: moment().subtract(1, "years").format("YYYY-MM-DD"),
      chartData: {},
      loading: false,
    };
  },
  components: { BarChartGenerator },
  mounted() {
    this.loading = true;
    try {
      this.loadWalletCount();
      this.loading = false;
    } catch (err) {
      return err;
    }
  },
  methods: {
    loadWalletCount() {
      bookingService
        .bookingCount("Completed", this.start_date, this.end_date)
        .then((response) => {
          this.chartData = {
            labels: response.years_data,
            datasets: [
              {
                backgroundColor: "#DB4C77",
                data: response.data,
                label: "Booking Completed",
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
