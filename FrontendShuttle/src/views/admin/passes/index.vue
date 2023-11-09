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

            <template slot="refresh-button-text">
              <i class="mdi mdi-sync-alert"></i> Refresh
            </template>
            <template slot="reset-button-text">
              <i class="mdi mdi-broom"></i> Reset filters
            </template>

            <template slot="is_active" slot-scope="props">
              <!-- <span v-if="props.column.name == 'is_active'">
                <b-badge
                  variant="success"
                  v-if="props.row.is_active == 'Active'"
                  >Active</b-badge
                >
                <b-badge
                  variant="danger"
                  v-if="props.row.is_active == 'Inactive'"
                  >Inactive</b-badge
                >
              </span> -->
              <b-form-select
                v-model="props.row.is_active"
                v-if="props.column.name == 'is_active'"
                :class="
                  props.row.is_active == 'Active'
                    ? 'text-success'
                    : 'text-danger'
                "
                :options="options"
                @change="
                  updateStatus(
                    props.row.is_active,
                    props.row.role,
                    props.row.ids
                  )
                "
              ></b-form-select>
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
                  <b-dropdown-item :href="'#/pass/' + props.row.ids"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <!-- <b-dropdown-item @click.stop="deleteRow(props.row.ids)"
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
              <div
                class="btn-group float-right"
                role="group"
                aria-label="Basic example"
              >
                <download-excel
                  class="btn btn-success mr-2"
                  :data="excelDownload"
                  :name="excelName"
                >
                  Excel <i class="mdi mdi-file-excel-box"></i>
                </download-excel>
              </div>
            </template>
          </vue-bootstrap4-table>

          <template slot="custom-action-export">
            Excel <i class="mdi mdi-file-excel-box"></i>
          </template>

          <!---START EDIT Modal--->
          <b-modal
            ref="myModalRefPass"
            :title="title"
            size="xl"
            :ok-title="modaloktitle"
            @hidden="hideModal"
            @ok="handleOk"
          >
            <!-- <span v-if="modalEdit == true"><modalForm :form="form" /></span> -->
            <span v-if="modalView == true">
              <modalView :modalData="modalData"
            /></span>
          </b-modal>
          <!--END EDIT modal--->
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import Breadcrumb from "../../../components/breadcrumb";
import { passService } from "../../../services";
import moment from "moment-timezone";
//import lodash from "lodash";
import downloadExcel from "vue-json-excel";
// import modalForm from "./modalForm";
import modalView from "./modelView";
import VueBootstrap4Table from "vue-bootstrap4-table";

export default {
  name: "passes",
  data() {
    return {
      title: "",
      breadcrumbs: {
        title: "Passes Lists",
        b1: "Manage Passes",
        b2: "Passes",
        b3: "Index",
        link: false,
        name: "Pass lists",
      },
      options: [
        { text: "Active", value: "Active" },
        { text: "Inactive", value: "Inactive" },
      ],
      showLoader: true,
      status: "",
      modaloktitle: "",
      rows: [],
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
        { label: "No of ride", name: "no_of_rides", sort: true },
        { label: "Valid Days", name: "no_of_valid_days", sort: true },
        { label: "Price per km", name: "price_per_km", sort: true },
        { label: "Discount", name: "discount", sort: true },
        { label: "Status", name: "status", sort: false },
        { label: "CreatedAt", name: "createdAt", sort: true },
        { label: "Action", name: "action" },
      ],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        per_page_options: [10, 20, 30, 50, 100],
        global_search: {
          placeholder: "Enter search pass",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 1000,
        },
        highlight_row_hover_color: "silver",
        // card_title: "Vue Bootsrap 4 advanced table",
        card_mode: true,
      },
      dropdowns: [],
      classes: {
        table: "  table-bordered  table-striped",
      },
      queryParams: {
        sort: [],
        filters: [],
        global_search: "",
        per_page: 10,
        page: 1,
      },
      total_rows: 0,
      modalView: false,
      modalData: {},
    };
  },
  components: {
    Breadcrumb,
    VueBootstrap4Table,
    downloadExcel,
    modalView,
  },
  computed: {
    excelDownload() {
      return passService.tranform(this.rows);
    },
    excelName() {
      return this.breadcrumbs.title + "_" + moment().local().unix();
    },
  },
  methods: {
    onGenerateExcel() {
      return passService.tranform(this.rows);
    },
    updateStatus(status, role, id) {
      passService.changeStatus(status, role, id).then((response) => {
        if (response.status) {
          this.$toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 3000,
          });
        } else {
          this.$toast.open({
            message: response.message,
            type: "danger",
            position: "top-right",
            duration: 3000,
          });
        }
      });
    },
    viewRow(data) {
      this.title = "Pass Details";
      this.modalView = true;
      //  this.modalEdit = false;
      this.modaloktitle = "Ok";
      this.$refs.myModalRefPass.show();
      this.modalData = data;
    },
    editRow(data) {
      console.log(data);
    },
    async deleteRow(id) {
      try {
        this.$swal
          .fire({
            title: `Are you sure?`,
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!",
          })
          .then((result) => {
            if (result.isConfirmed) {
              passService.deletePass(id).then((response) => {
                if (
                  typeof response.data === "object" &&
                  response.data.status === 401
                ) {
                  this.$swal.fire(response.data.message, "", "error");
                  this.$toast.open({
                    message: response.data.message,
                    type: "error",
                    position: "top-right",
                    duration: 3000,
                    // all of other options may go here
                  });
                  this.showLoader = false;
                } else if (response.status) {
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
    hideModal() {
      this.$refs.myModalRefPass.hide();
      //  this.modalEdit = false;
      this.modalView = false;
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault();
      /// this.modalEdit = false;
      this.modalView = false;
      // Trigger submit handler
    },
    showAlert(id) {
      alert(id);
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.fetchData();
    },
    // load items is what brings back the rows from server
    fetchData() {
      passService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.passes;
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
    this.fetchData();
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

<style></style>
