<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 stretch-card">
          <b-card no-body>
            <b-tabs
              pills
              card
              horizontal
              nav-wrapper-class="w-40"
              active-nav-item-class="font-weight-bold"
            >
              <b-tab active>
                <template #title>
                  <i class="mdi mdi-ticket"></i> BOOKING DETAILS
                </template>

                <b-card-text>
                  <BookingDetail :propBookingObj="bookingObj" /> </b-card-text
              ></b-tab>
              <b-tab>
                <template #title>
                  <i class="mdi mdi-account"></i> CUSTOMER DETAILS
                </template>

                <b-card-text
                  ><CustomerDetail
                    :propCustomerObj="customerObj" /></b-card-text
              ></b-tab>
              <b-tab>
                <template #title>
                  <i class="mdi mdi-currency-inr"></i> PAYMENT DETAILS
                </template>

                <b-card-text
                  ><PaymentDetail :propPaymentObj="paymentObj" /></b-card-text
              ></b-tab>
            </b-tabs>
          </b-card>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import BookingDetail from "../../../components/bookings/edit/booking-detail";
import CustomerDetail from "../../../components/bookings/edit/customer-detail";
import PaymentDetail from "../../../components/bookings/edit/payment-detail";
import { bookingService } from "../../../services";
//paymentService

export default {
  name: "bookingdetail",
  data() {
    return {
      breadcrumbs: {
        title: "Booking Details",
        b1: "Manage Bookings",
        b2: "Bookings",
        b3: "Index",
        link: true,
        name: "Bookings",
      },
      form: {
        pnr_no: "",
      },
      bookingObj: this.customerObject,
      customerObj: this.bookingObject,
      paymentObj: this.paymentObject,
    };
  },
  components: {
    Breadcrumb,
    BookingDetail,
    CustomerDetail,
    PaymentDetail,
  },
  props: {
    customerObject: Object,
    bookingObject: Object,
    paymentObject: Object,
  },
  mounted() {
    this.getBooking();
  },
  methods: {
    async getBooking() {
      try {
        const response = await bookingService.find(this.$route.params.id);
        console.log("response", response);
        if (response.status) {
          this.bookingObj = response.data;
          this.customerObj = response.data;
          this.paymentObj = response.data.payment_detail;
        }
      } catch (e) {
        console.log("params", e);
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
  },
};
</script>

<style lang="scss" scoped></style>
