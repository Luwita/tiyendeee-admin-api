<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />
        <div class="col-lg-12 stretch-card">
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

            <template slot="is_active" slot-scope="props">
              <span v-if="props.column.name == 'is_active'">
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
                  <b-dropdown-item @click.stop="editRow(props.row)"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <b-dropdown-item
                    @click.stop="deleteRow(props.row.ids, props.row.firstname)"
                    ><span class="text-danger">
                      <i class="mdi mdi-delete"></i> Delete
                    </span></b-dropdown-item
                  >
                </b-dropdown>
                <!-- <b-dropdown-item
                  v-if="user.role == 'super-admin'"
                  @click.stop="deleteRow(props.row.ids, props.row.firstname)"
                  ><span class="text-danger">
                    <i class="mdi mdi-delete"></i> Delete
                  </span></b-dropdown-item
                > -->
              </span>
            </template>

            <!-- <template slot="pagination-info" slot-scope="props">
              Total Agent is {{ props.currentPageRowsLength }} | Filterd agent
              total is {{ props.filteredRowsLength }} | Original agent total is
              {{ props.originalRowsLength }}
            </template> -->
            <template slot="paginataion-previous-button"> Previous </template>
            <template slot="paginataion-next-button"> Next </template>

            <template slot="vbt-action-buttons">
              <div class="float-right">
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

          <!---START EDIT Modal--->
          <b-modal
            ref="myModalRefAgent"
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
import { agentService } from "../../../services";
import moment from "moment-timezone";
//import lodash from "lodash";
import downloadExcel from "vue-json-excel";
// import modalForm from "./modalForm";
import modalView from "./modelView";
import VueBootstrap4Table from "vue-bootstrap4-table";

export default {
  name: "agents",
  data() {
    return {
      title: "",
      breadcrumbs: {
        title: "Agents Lists",
        b1: "Manage Routes",
        b2: "Agents",
        b3: "Index",
        link: false,
        name: "Agent lists",
      },
      options: [
        { value: true, text: "Active" },
        { value: false, text: "Inactive" },
      ],
      showLoader: true,
      status: "",
      modaloktitle: "",
      rows: [],
      columns: [
        {
          label: "id",
          name: "id",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "First Name",
          name: "firstname",
          filter: {
            type: "simple",
            placeholder: "Enter firstname",
          },
          sort: true,
        },
        {
          label: "Last Name",
          name: "lastname",
          filter: {
            type: "simple",
            placeholder: "Enter lastname",
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
          label: "Phone",
          name: "phone",
          filter: {
            type: "simple",
            placeholder: "Enter phone",
          },
          sort: true,
        },
        {
          label: "Company",
          name: "company",
          filter: {
            type: "simple",
            placeholder: "Enter company",
          },
          sort: true,
        },
        {
          label: "Status",
          name: "is_active",
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
        { label: "CreatedAt", name: "createdAt", sort: true },
        { label: "Action", name: "action" },
      ],
      config: {
        server_mode: true, // by default false
        loaderText: "Updating...", // by default 'Loading...'
        pagination: true,
        per_page_options: [10, 20, 30, 50, 100],
        global_search: {
          placeholder: "Enter search agent",
          visibility: true,
          case_sensitive: false,
          showClearButton: false,
          searchOnPressEnter: false,
          searchDebounceRate: 1000,
        },
        //highlight_row_hover_color: "grey",
        // card_title: "Vue Bootsrap 4 advanced table",
        // card_mode: false,
      },
      dropdowns: [],
      classes: {
        table: " table-striped",
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
      return agentService.tranform(this.rows);
    },
    excelName() {
      return this.breadcrumbs.title + "_" + moment().local().unix();
    },
  },
  mounted() {
    this.fetchData();
  },
  methods: {
    viewRow(data) {
      this.title = "Agent Details";
      this.modalView = true;
      //  this.modalEdit = false;
      this.modaloktitle = "Ok";
      this.$refs.myModalRefAgent.show();
      this.modalData = data;
    },
    editRow(data) {
      console.log(data);
    },
    onRefreshData() {
      this.showLoader = true;
      this.fetchData();
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.fetchData();
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
              agentService.deleteAgent(id).then((response) => {
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
                  });
                  this.showLoader = true;
                  this.fetchData();
                } else {
                  this.$swal.fire("Not Deleted ", response.message, "error");
                  this.showLoader = false;
                  this.fetchData();
                }
              });
            } else {
              this.$swal.fire("Cancelled ", "", "error");
              this.showLoader = false;
              this.fetchData();
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
      this.$refs.myModalRefAgent.hide();
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
    // load items is what brings back the rows from server
    fetchData() {
      agentService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.agents;
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
