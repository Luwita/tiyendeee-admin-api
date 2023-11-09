<template>
  <section class="tables">
    <div class="row">
      <Breadcrumb :breadcrumbs="breadcrumbs" />
      <div class="col-lg-12 grid-margin stretch-card">
        <b-card no-body>
          <b-tabs
            pills
            card
            vertical
            nav-wrapper-class="w-40"
            active-nav-item-class="font-weight-bold"
          >
            <b-tab title="General" active
              ><b-card-text> <General /> </b-card-text
            ></b-tab>
            <b-tab title="AWS S3"
              ><b-card-text><AWS /></b-card-text
            ></b-tab>
            <b-tab title="Email"
              ><b-card-text><SMTP /></b-card-text
            ></b-tab>
            <b-tab title="SMS"
              ><b-card-text><SMS /></b-card-text
            ></b-tab>
            <b-tab title="Payments"
              ><b-card-text><Payment /></b-card-text
            ></b-tab>
            <b-tab title="Refunds"
              ><b-card-text> <Refund /> </b-card-text
            ></b-tab>
            <b-tab title="Notifications"
              ><b-card-text><Notification /></b-card-text
            ></b-tab>
            <b-tab title="Terms"
              ><b-card-text> <Term /> </b-card-text
            ></b-tab>
          </b-tabs>
        </b-card>
      </div>
    </div>
  </section>
</template>

<script>
import Breadcrumb from "../../components/breadcrumb";
import General from "../../components/settings/general";
import Term from "../../components/settings/term";
import AWS from "../../components/settings/aws";
import SMTP from "../../components/settings/smtp";
import SMS from "../../components/settings/sms";
import Payment from "../../components/settings/payment";
import Notification from "../../components/settings/notification";
import Refund from "../../components/settings/refund";

export default {
  name: "applicationSettings",
  data() {
    return {
      breadcrumbs: {
        title: "Application Settings",
        b1: "Manage Application Settings",
        b2: "Application Settings",
        b3: "Index",
        link: false,
        name: "Application Settings",
      },
    };
  },
  components: {
    Breadcrumb,
    General,
    Term,
    AWS,
    SMTP,
    SMS,
    Payment,
    Notification,
    Refund,
  },
};
</script>

<style lang="scss" scoped></style>
