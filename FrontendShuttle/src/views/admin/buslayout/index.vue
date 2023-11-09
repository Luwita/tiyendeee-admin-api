<template>
  <div>
    <section class="tables">
      <div class="row">
        <div class="col-lg-12">
          <Breadcrumb :breadcrumbs="breadcrumbs" />
        </div>
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
                  <b-dropdown-item :href="'#/buslayout/' + props.row.ids"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <!-- <b-dropdown-item
                    v-if="user.role == 'admin'"
                    @click.stop="deleteRow(props.row.ids, props.row.name)"
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
                class="btn btn-success"
                :data="excelDownload"
                :name="excelName"
              >
                Excel <i class="mdi mdi-file-excel-box"></i>
              </download-excel>
              </div>
            </template>
          </vue-bootstrap4-table>

          <!---START EDIT Modal--->
          <b-modal
            ref="myModalRefbustype"
            :title="title"
            size="xl"
            :ok-title="modaloktitle"
            @hidden="hideModal"
            @ok="handleOk"
          >
            <span v-if="modalEdit == true"><modalForm :form="form" /></span>
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
import { buslayoutService } from "../../../services";
import moment from "moment-timezone";
import VueBootstrap4Table from "vue-bootstrap4-table";
import downloadExcel from "vue-json-excel";

import modalView from "./modelView";

export default {
  name: "buslayouts",
  data() {
    return {
      title: "",
      breadcrumbs: {
        title: "Bus layout Lists",
        b1: "Manage Layouts",
        b2: "Bus layouts",
        b3: "Index",
        link: false,
        name: "Buslayouts",
      },
      showLoader: false,
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
          name: "name",
          sort: true,
        },
        {
          label: "Total seats",
          name: "max_seats",
          sort: true,
        },
        {
          label: "Layout",
          name: "layout",
          sort: false,
        },
        {
          label: "Status",
          name: "status",
          sort: true,
        },
        {
          label: "CreatedAt",
          name: "createdAt",
        },
        { label: "Action", name: "action", sort: false },
      ],
      rows: [],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        global_search: {
          placeholder: "Enter search bus type",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 1000,
        },
        per_page_options: [10, 20, 30, 50, 100],
        highlight_row_hover_color: "silver",
        // card_title: "Vue Bootsrap 4 advanced table",
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
        status: "",
        max_seats: "",
        combine_seats: [],
        createdAt: "",
      },
      modalEdit: false,
      modalView: false,
      modalData: {},
      modaloktitle: "",
    };
  },
  // add to component
  components: {
    modalView,
    downloadExcel,
    VueBootstrap4Table,
    Breadcrumb,
  },
  computed: {
    excelDownload() {
      return buslayoutService.tranform(this.rows);
    },
    excelName() {
      return this.breadcrumbs.title + "_" + moment().local().unix();
    },
  },
  methods: {
    viewRow(data) {
      console.log("data", data);
      this.title = "bustype Details";
      this.modalView = true;
      this.modalEdit = false;
      this.modaloktitle = "Ok";
      this.$refs.myModalRefbustype.show();
      this.modalData = data;
    },
    editRow(data) {
      this.title = "bustype Edit";
      this.modalEdit = true;
      this.modalView = false;
      this.modaloktitle = "Update";
      this.$refs.myModalRefbustype.show();
      console.log(data);
      this.form = data;
    },
    hideModal() {
      this.$refs.myModalRefbustype.hide();
      this.modalEdit = false;
      this.modalView = false;
    },
    handleOk(bvModalEvt) {
      // Prevent modal from closing
      bvModalEvt.preventDefault();
      this.modalEdit = false;
      this.modalView = false;
      // Trigger submit handler
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
              buslayoutService.deleteBustype(id, name).then((response) => {
                if (
                  typeof response.data === "object" &&
                  response.data.status === 403
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
    showAlert(id) {
      alert(id);
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    // load items is what brings back the rows from server
    loadItems() {
      buslayoutService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.buslayouts;
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

<style></style>
