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
                <b-badge variant="success" v-if="props.row.status == 'Active'"
                  >Active</b-badge
                >
                <b-badge variant="danger" v-if="props.row.status == 'Inactive'"
                  >Inactive</b-badge
                >
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

            <template slot="action" slot-scope="props">
              <span v-if="props.column.name == 'action'">
                <b-dropdown
                  id="dropdown-left"
                  text="Actions"
                  variant="outline-info"
                  class="m-2"
                >
                  <b-dropdown-item @click="viewRow(props.row)">
                    <span class="text-dark">
                      <i class="mdi mdi-eye"></i> View
                    </span></b-dropdown-item
                  >

                  <b-dropdown-item
                    :href="
                      '#/customer-wallet-history/' +
                      props.row.ids +
                      '/' +
                      props.row.walletId
                    "
                  >
                    <span class="text-info">
                      <i class="mdi mdi-wallet"></i> Trans History
                    </span></b-dropdown-item
                  >

                  <b-dropdown-item :href="'#/customer/' + props.row.ids"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <!--    <b-dropdown-item
                    v-if="user.role == 'admin'"
                    @click.stop="deleteRow(props.row.ids, props.row.fullname)"
                    ><span class="text-danger">
                      <i class="mdi mdi-delete"></i> Delete
                    </span></b-dropdown-item
                  >
                <b-dropdown-item
                    v-if="user.role == 'super-admin'"
                    @click.stop="deleteRow(props.row.ids, props.row.fullname)"
                    ><span class="text-danger">
                      <i class="mdi mdi-delete"></i> Delete
                    </span></b-dropdown-item
                  > -->
                </b-dropdown>
              </span>
            </template>

            <template slot="paginataion-previous-button"> Previous </template>
            <template slot="paginataion-next-button"> Next </template>
            <template slot="vbt-action-buttons">
              <div class="row">
                <div class="col-md-6">
                  <div class="float-right">
                    <b-form inline>
                      <label class="mr-2">Date Range Filter :</label>
                      <date-range-picker
                        v-model="pickerDates"
                        @update="updateDateRange"
                        :locale-data="{ firstDay: 1, format: 'dd-mm-yyyy' }"
                      >
                        <template ref="picker" style="min-width: 350px"
                          >{{ pickerDates.startDate | date }} -
                          {{ pickerDates.endDate | date }}</template
                        >
                      </date-range-picker>
                    </b-form>
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="float-center">
                    <download-excel
                      class="btn btn-success mr-5"
                      :data="excelDownload"
                      :name="excelName"
                    >
                      Excel <i class="mdi mdi-file-excel-box"></i>
                    </download-excel>
                  </div>
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
import { customerService } from "../../services";
import Breadcrumb from "../../components/breadcrumb";
import VueBootstrap4Table from "vue-bootstrap4-table";
import downloadExcel from "vue-json-excel";
import moment from "moment-timezone";
import DateRangePicker from "vue2-daterange-picker";

import "vue2-daterange-picker/dist/vue2-daterange-picker.css";

export default {
  name: "customers",
  data() {
    let startDate = new Date();
    let endDate = new Date();
    endDate.setDate(endDate.getDate() + 10);
    return {
      breadcrumbs: {
        title: "Customer Lists",
        b1: "Manage Customers",
        b2: "Customers",
        b3: "Index",
        link: false,
        name: "Customer lists",
      },
      columns: [
        {
          label: "id",
          name: "id",
          // filter: {
          //   type: "simple",
          //   placeholder: "Enter id",
          // },
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "Name",
          name: "fullname",
          filter: {
            type: "simple",
            placeholder: "Enter full name",
          },
          sort: true,
        },
        {
          label: "Gender",
          name: "gender",
          filter: {
            type: "select",
            placeholder: "Select Gender",
            mode: "multi",
            options: [
              {
                name: "Male",
                value: "Male",
              },
              {
                name: "Female",
                value: "Female",
              },
            ],
          },
          sort: true,
        },
        {
          label: "Phone",
          name: "phone",
          filter: {
            type: "simple",
            placeholder: "Enter phone number",
          },
          sort: true,
        },
        {
          label: "Email",
          name: "email",
          filter: {
            type: "simple",
            placeholder: "Enter email",
          },
          sort: true,
        },
        {
          label: "Wallet",
          name: "wallet_balance",
          filter: {
            type: "simple",
            placeholder: "Enter Wallet",
          },
          sort: true,
        },
        {
          label: "Referode",
          name: "refercode",
          filter: {
            type: "simple",
            placeholder: "Enter refercode",
          },
          sort: true,
        },
        {
          label: "Status",
          name: "status",
          filter: {
            type: "select",
            placeholder: "Select Status",
            mode: "multi",
            options: [
              {
                name: "Active",
                value: true,
              },
              {
                name: "InActive",
                value: false,
              },
            ],
          },
          sort: true,
        },
        {
          label: "CreatedAt",
          name: "createdAt",
          sort: true,
        },
        { label: "Action", name: "action", sort: false },
      ],
      rows: [],
      pickerDates: { startDate, endDate },
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        global_search: {
          placeholder: "Enter search offers",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 1000,
        },
        per_page_options: [10, 20, 30, 50, 100],
        highlight_row_hover_color: "silver",
        highlight_row_hover: true,
        card_mode: true,
      },
      dropdowns: [],
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
      form: {
        id: "",
        name: "",
        slug: "",
        createdAt: "",
      },
      showLoader: false,
    };
  },
  components: {
    VueBootstrap4Table,
    Breadcrumb,
    downloadExcel,
    DateRangePicker,
  },
  filters: {
    date(date) {
      return new Intl.DateTimeFormat("en-US").format(date);
    },
  },
  computed: {
    excelDownload() {
      return customerService.tranform(this.rows);
    },
    excelName() {
      return this.breadcrumbs.title + "_" + moment().local().unix();
    },
    user() {
      return this.$store.state.auth.user;
    },
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
    updateDateRange(event) {
      this.queryParams.filters = {
        type: "date",
        value: { startDate: event.startDate, endDate: event.endDate },
      };
      customerService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.users;
        this.showLoader = false;
      });
    },
    // load items is what brings back the rows from server
    loadItems() {
      customerService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.users;
        this.showLoader = false;
      });
    },
    async deleteRow(id, name) {
      try {
        this.$swal
          .fire({
            title: `Are you sure?`,
            text:
              "You won't be able to revert all wallet and booking details of " +
              name +
              "!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!",
          })
          .then((result) => {
            if (result.isConfirmed) {
              customerService.deleteUser(id).then((response) => {
                console.log("response", response, "result", result);
                if (response.status) {
                  this.$swal.fire("Deleted!", response.message, "success");
                  this.$toast.open({
                    message: response.message,
                    type: "success",
                    position: "top-right",
                    duration: 3000,
                  });
                  this.showLoader = true;
                  this.loadItems();
                } else {
                  this.$swal.fire("Not Deleted ", response.message, "error");
                  this.showLoader = true;
                  this.loadItems();
                }
              });
            } else {
              this.$swal.fire("Cancelled ", "", "error");
              this.showLoader = true;
              this.loadItems();
            }
          });
      } catch (e) {
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
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

<style lang="scss" scoped></style>
