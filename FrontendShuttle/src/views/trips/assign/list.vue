<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 grid-margin stretch-card">
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

            <template slot="refresh-button-text">
              <i class="mdi mdi-sync-alert"></i> Refresh
            </template>
            <template slot="reset-button-text">
              <i class="mdi mdi-broom"></i> Reset filters
            </template>

            <template slot="driver_name" slot-scope="props">
              <span v-if="props.column.name == 'driver_name'">
                <div v-html="props.row.driver_name"></div>
              </span>
            </template>
            <template slot="assistantId" slot-scope="props">
              <span v-if="props.column.name == 'assistantId'">
                <div v-html="props.row.assistantId"></div>
              </span>
            </template>

            <template slot="trip_status" slot-scope="props">
              <span v-if="props.column.name == 'trip_status'">
                <b-badge
                  variant="success"
                  v-if="props.row.trip_status == 'COMPLETED'"
                  >Completed</b-badge
                >
                <b-badge
                  variant="warning"
                  v-else-if="props.row.trip_status == 'ASSIGNED'"
                  >Assigned</b-badge
                >
                <b-badge
                  variant="primary"
                  v-else-if="props.row.trip_status == 'STARTED'"
                  >Started</b-badge
                >

                <b-badge
                  variant="info"
                  v-else-if="props.row.trip_status == 'RIDING'"
                  >Riding</b-badge
                >
                <b-badge
                  variant="danger"
                  v-else-if="props.row.trip_status == 'EXPIRED'"
                  >Expired</b-badge
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
                  <b-dropdown-item :href="`#/trips/assign/` + props.row.ids"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <b-dropdown-item
                    @click.stop="deleteRow(props.row.ids, props.row.route_name)"
                    ><span class="text-danger">
                      <i class="mdi mdi-delete"></i> Delete
                    </span></b-dropdown-item
                  >
                </b-dropdown>
              </span>
            </template>

            <template slot="paginataion-previous-button"> Previous </template>
            <template slot="paginataion-next-button"> Next </template>
            <template slot="vbt-action-buttons">
              <div class="float-right">
                <download-excel
                  class="btn btn-success mr-5"
                  :data="excelDownload"
                  :name="excelName"
                >
                  Excel <i class="mdi mdi-file-excel-box"></i>
                </download-excel>
              </div>
            </template>
          </vue-bootstrap4-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import { assignService } from "../../../services";
import VueBootstrap4Table from "vue-bootstrap4-table";
import downloadExcel from "vue-json-excel";
import moment from "moment-timezone";

export default {
  name: "tripassignlist",
  data() {
    return {
      breadcrumbs: {
        title: "List Assign",
        b1: "Manage Assign",
        b2: "Assign",
        b3: "Index",
        link: true,
        name: "Assign",
      },
      columns: [
        {
          label: "id",
          name: "id",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "Vendor",
          name: "admin_name",
          sort: true,
        },
        {
          label: "Route",
          name: "route_name",
          sort: true,
        },
        {
          label: "Driver",
          name: "driver_name",
          sort: true,
        },

        {
          label: "Assistant",
          name: "assistantId",
          sort: true,
        },

        {
          label: "Date/Time",
          name: "date_time",
          sort: true,
        },
        {
          label: "Status",
          name: "trip_status",
          sort: true,
        },
        { label: "Action", name: "action", sort: false },
      ],
      rows: [],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        global_search: {
          placeholder: "Enter search assign",
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
      return assignService.transform(this.rows);
    },
    excelName() {
      return "referrals_" + moment().local().unix();
    },
    user() {
      return this.$store.state.auth.user;
    },
  },
  methods: {
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    loadItems() {
      assignService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.bookingassigns;
        this.showLoader = false;
      });
    },
    deleteRow(id, name) {
      try {
        this.$swal
          .fire({
            title: `Are you sure you want to delete ${name}?`,
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!",
          })
          .then((result) => {
            if (result.isConfirmed) {
              assignService.deleteAssign(id, name).then((response) => {
                if (response.status) {
                  this.$swal.fire("Deleted!", response.message, "success");
                  this.$toast.open({
                    message: response.message,
                    type: "success",
                    position: "top-right",
                    duration: 3000,
                    // all of other options may go here
                  });
                  this.showLoader = true;
                  this.loadItems();
                } else {
                  this.$swal.fire("Failed!", response.message, "danger");
                  this.$toast.open({
                    message: response.message,
                    type: "danger",
                    position: "top-right",
                    duration: 3000,
                    // all of other options may go here
                  });
                  this.showLoader = true;
                  this.loadItems();
                }
              });
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

<style scoped></style>
