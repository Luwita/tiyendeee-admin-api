<template>
  <div>
    <section class="tables">
      <div class="row">
        <Breadcrumb :breadcrumbs="breadcrumbs" />

        <div class="col-lg-12">
          <vue-bootstrap4-table
            :rows="rows"
            :columns="columns"
            :config="config"
            @on-change-query="onChangeQuery"
            :total-rows="total_rows"
            :classes="classes"
            :show-loader="showLoader"
            @on-download="onDownload"
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
            <template slot="action" slot-scope="props">
              <span v-if="props.column.name == 'action'">
                <b-button variant="info" @click="viewRow(props.row)" size="xs"
                  ><span class="text-light">
                    <i class="mdi mdi-eye"></i> View
                  </span></b-button
                >
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
                  <div class="float-right">
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
          <!---START EDIT Modal--->
          <b-modal
            ref="myModalRefUser"
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
import { suggestService } from "../../../services";
import Breadcrumb from "../../../components/breadcrumb";
import VueBootstrap4Table from "vue-bootstrap4-table";
import downloadExcel from "vue-json-excel";
import modalView from "./models/view";
import moment from "moment-timezone";
import DateRangePicker from "vue2-daterange-picker";

import "vue2-daterange-picker/dist/vue2-daterange-picker.css";

export default {
  name: "suggests",
  data() {
    let startDate = new Date();
    let endDate = new Date();
    endDate.setDate(endDate.getDate() + 10);

    return {
      breadcrumbs: {
        title: "Suggest Lists",
        b1: "Manage Suggests",
        b2: "Suggests",
        b3: "Index",
        link: false,
        name: "Suggest lists",
      },
      columns: [
        {
          label: "id",
          name: "id",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "Pickup",
          name: "pickup_address",
          sort: true,
        },
        {
          label: "Drop",
          name: "drop_address",
          sort: true,
        },
        {
          label: "Name",
          name: "userId_firstname",
          sort: true,
        },
        {
          label: "Created At",
          name: "createdAt",
          // filter: {
          //   type: " ",
          // },
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
      actions: [
        {
          btn_text: "Download",
          event_name: "on-download",
          event_payload: {
            msg: "my custom message",
          },
        },
      ],
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
      modaloktitle: "",
      showLoader: false,
      modalView: false,
      modalData: {},
      title: "",
    };
  },
  filters: {
    date(date) {
      return new Intl.DateTimeFormat("en-US").format(date);
    },
  },
  components: {
    VueBootstrap4Table,
    downloadExcel,
    modalView,
    Breadcrumb,
    DateRangePicker,
  },
  computed: {
    excelDownload() {
      return suggestService.transform(this.rows);
    },
    excelName() {
      return "suggests_" + moment().local().unix();
    },
  },
  methods: {
    onDownload(payload) {
      alert(payload.msg);
    },
    // dateFormat(classes, date) {
    //   if (!classes.disabled) {
    //     classes.disabled = date.getTime() < new Date();
    //   }
    //   return classes;
    // },
    updateDateRange(event) {
      this.queryParams.filters = {
        type: "date",
        value: { startDate: event.startDate, endDate: event.endDate },
      };
      suggestService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.suggests;
        this.showLoader = false;
      });
    },
    viewRow(data) {
      this.title = "Suggest Details";
      this.modalView = true;
      this.modaloktitle = "Ok";
      this.$refs.myModalRefUser.show();
      this.modalData = data;
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    loadItems() {
      suggestService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.suggests;
        this.showLoader = false;
      });
    },
    hideModal() {
      this.$refs.myModalRefUser.hide();
      this.modalView = false;
    },
    handleOk(bvModalEvt) {
      bvModalEvt.preventDefault();
      this.modalView = false;
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
