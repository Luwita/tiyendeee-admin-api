<template>
  <div>
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
            @on-change-query="onChangeQuery"
            :total-rows="total_rows"
            :classes="classes"
            :show-loader="showLoader"
          >
            <template slot="global-search-clear-icon">
              <i class="mdi mdi-account-search"></i>
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
              <div class="row">
                <div class="col-md-12">
                  <b-form inline>
                    <download-excel
                      class="btn btn-success mr-5"
                      :data="excelDownload"
                      :name="excelName"
                    >
                      Excel <i class="mdi mdi-file-excel-box"></i>
                    </download-excel>
                  </b-form>
                </div>
              </div>
            </template>
          </vue-bootstrap4-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { referralService } from "../../services";
import Breadcrumb from "../../components/breadcrumb";
import VueBootstrap4Table from "vue-bootstrap4-table";
import downloadExcel from "vue-json-excel";
import moment from "moment-timezone";
export default {
  name: "referralview",
  data() {
    return {
      breadcrumbs: {
        title: "Referral Details",
        b1: "Manage Referrals",
        b2: "Referrals",
        b3: "Index",
        link: false,
        name: "Referral Details",
      },
      columns: [
        {
          label: "id",
          name: "id",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "CustName",
          name: "user.firstname",
          sort: true,
        },
        {
          label: "RefName",
          name: "referral.firstname",
          sort: true,
        },
        {
          label: "Amt",
          name: "amount",
          sort: true,
        },
        {
          label: "status",
          name: "payment_status",
        },
      ],
      rows: [],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        global_search: {
          placeholder: "Enter search suggests",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 5000,
        },
        per_page_options: [10, 25, 50, 100],
        highlight_row_hover: true,
        highlight_row_hover_color: "silver",
        card_mode: true,
        preservePageOnDataChange: true,
        show_refresh_button: true,
      },
      classes: {
        table: "table-bordered table-striped",
      },
      queryParams: {
        sort: [],
        filters: [],
        global_search: "",
        per_page: 10,
        page: 1,
      },
      total_rows: 0,
      showLoader: false,
      dropdowns: [],
    };
  },
  components: {
    VueBootstrap4Table,
    downloadExcel,
    Breadcrumb,
  },
  computed: {
    excelDownload() {
      return referralService.transform(this.rows);
    },
    excelName() {
      return "referrals_" + moment().local().unix();
    },
  },
  methods: {
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    loadItems() {
        this.queryParams.userId = this.$route.params.userId;
       referralService.getSingle(this.queryParams).then((response) => {
        this.total_rows = response.totalRecords;
        this.rows = response.referrals;
        this.showLoader = false;
      });
    },
    enableDropdowns() {
      this.$el
        .querySelectorAll('[data-toggle="dropdown"]')
        .forEach(($dropdownToggle) => {
          const $dropdown = $dropdownToggle.nextElementSibling;
          let isShown = false;

          function setIsShown(state) {
            isShown = state;
            $dropdown.classList.toggle("show", isShown);
          }

          if (!this.dropdowns.includes($dropdown)) {
            this.dropdowns.push($dropdown);

            $dropdownToggle.addEventListener("click", (event) => {
              event.preventDefault();
              setIsShown(!isShown);
            });

            $dropdown.addEventListener("click", (event) => {
              event.preventDefault();
              setIsShown(false);
            });

            $dropdown.clickOutsideEvent = (event) => {
              const isDropdownOrChildren =
                $dropdown === event.target || $dropdown.contains(event.target);
              const isDropdownToggleOrChildren =
                $dropdownToggle === event.target ||
                $dropdownToggle.contains(event.target);

              if (!isDropdownOrChildren && !isDropdownToggleOrChildren) {
                setIsShown(false);
              }
            };
            document.addEventListener("click", $dropdown.clickOutsideEvent);
          }
        });
    },
  },
  mounted() {
    this.loadItems();
  },
  updated() {
    this.enableDropdowns();
  },
  destroyed() {
    this.dropdowns.forEach(($dropdown) => {
      document.removeEventListener("click", $dropdown.clickOutsideEvent);
    });
  },
};
</script>

<style>
</style>