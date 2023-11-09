<template>
  <section class="tables">
    <div class="row">
      <div class="col-lg-12">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
      </div>
      <div class="col-lg-12">
        <vue-bootstrap4-table
          :rows="rows"
          :columns="columns"
          :config="config"
          @refresh-data="onRefreshData"
          @on-change-query="onChangeQuery"
          :total-rows="total_rows"
          :classes="classes"
          :show-loader="showLoader"
        >
          <template slot="global-search-clear-icon">
            <i class="mdi mdi-account-search"></i>
          </template>

          <template slot="refresh-button-text">
            <i class="mdi mdi-sync-alert"></i> Refresh
          </template>
          <template slot="reset-button-text">
            <i class="mdi mdi-broom"></i> Reset filters
          </template>

          <template slot="status" slot-scope="props">
            <span v-if="props.column.name == 'status'">
              <b-badge variant="default" v-if="props.row.type == '0'">{{
                props.row.status
              }}</b-badge>
              <b-badge variant="danger" v-else-if="props.row.type == '1'">{{
                props.row.status
              }}</b-badge>
            </span>
          </template>
          <template slot="payment_status" slot-scope="props">
            <span v-if="props.column.name == 'payment_status'">
              <b-badge
                variant="success"
                v-if="props.row.payment_status == 'Completed'"
                >Completed</b-badge
              >
              <b-badge
                variant="warning"
                v-else-if="props.row.payment_status == 'Processing'"
                >Processing</b-badge
              >
              <b-badge
                variant="info"
                v-else-if="props.row.payment_status == 'Pending'"
                >Pending</b-badge
              >
              <b-badge
                variant="danger"
                v-else-if="props.row.payment_status == 'Failed'"
                >Failed</b-badge
              >
            </span>
          </template>

          <template slot="paginataion-previous-button"> Previous </template>
          <template slot="paginataion-next-button"> Next </template>
          <template slot="card-header">
            <div class="h-50 text-center mb-1">
              Trans History
              <b class="p-3"
                >({{ customer.fullname }} - {{ customer.phone }})</b
              >
            </div>
          </template>
        </vue-bootstrap4-table>
      </div>
    </div>
  </section>
</template>

<script>
import { customerService } from "../../../services";
import VueBootstrap4Table from "vue-bootstrap4-table";

export default {
  name: "walletHistory",
  data() {
    return {
      columns: [
        {
          label: "id",
          name: "id",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "Name",
          name: "title",
          sort: true,
        },
        {
          label: "Method",
          name: "method",
          sort: true,
        },
        {
          label: "Amount",
          name: "amount",
          sort: true,
        },
        {
          label: "Status",
          name: "status",
          sort: true,
        },
        {
          label: "Payment Status",
          name: "payment_status",
          sort: true,
        },

        {
          label: "created At",
          name: "createdAt",
          sort: true,
        },
      ],
      rows: [],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        global_search: {
          placeholder: "Enter search wallet amount",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 1000,
        },
        per_page_options: [10, 20, 30, 50, 100],
        highlight_row_hover_color: "silver",
        //card_title: "Vue Bootsrap 4 advanced table",
        card_mode: true,
      },
      dropdowns: [],
      classes: {
        table: "table-bordered table-striped",
      },
      queryParams: {
        sort: [],
        filters: "", //"SEARCHING",
        global_search: "",
        per_page: 10,
        page: 1,
      },
      total_rows: 0,
      showLoader: false,
      customer: {},
    };
  },
  components: {
    VueBootstrap4Table,
  },
  mounted() {
    this.loadItems();
  },
  methods: {
    onRefreshData() {
      this.showLoader = true;
      this.loadItems();
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    loadItems() {
      this.queryParams.userId = this.$route.params.userId;
      this.queryParams.walletId = this.$route.params.walletId; //
      customerService.walletHistory(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.walletHistories;
        this.customer = response.data.customers;
        this.showLoader = false;
      });
    },
  },
};
</script>

<style></style>
