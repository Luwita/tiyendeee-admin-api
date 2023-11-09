<template>
  <section class="tables">
    <div class="row">
      <div class="col-lg-12"><Breadcrumb :breadcrumbs="breadcrumbs" /></div>
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
          <template slot="refresh-button-text">
            <i class="mdi mdi-sync-alert"></i> Refresh
          </template>
          <template slot="reset-button-text">
            <i class="mdi mdi-broom"></i> Reset filters
          </template>

          <template slot="description" slot-scope="props">
            <p
              class="text-sm-left text-justify"
              v-html="props.row.description"
            ></p>
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
                  v-b-modal.bv-modal-reply
                  @click="currentReply(props.row)"
                  ><span class="text-primary">
                    <i class="mdi mdi-pencil"></i> reply
                  </span></b-dropdown-item
                >

                <b-dropdown-item @click.stop="deleteRow(props.row.ids)"
                  ><span class="text-danger">
                    <i class="mdi mdi-delete"></i> Delete
                  </span></b-dropdown-item
                >
              </b-dropdown>
            </span>
          </template>
          <template slot="paginataion-previous-button"> Previous </template>
          <template slot="paginataion-next-button"> Next </template>
        </vue-bootstrap4-table>

        <ReplyModel titlemodal="Reply" :reply="reply" />
      </div>
    </div>
  </section>
</template>

<script>
import { helperService } from "../../services";
import Breadcrumb from "../../components/breadcrumb";
import ReplyModel from "../../components/modals/helpers/reply";
import VueBootstrap4Table from "vue-bootstrap4-table";
export default {
  name: "HelpAndSupport",
  data() {
    return {
      reply: {},
      breadcrumbs: {
        title: "Help & Support Lists",
        b1: "Manage Help & Supports",
        b2: "Help & Supports",
        b3: "Index",
        link: false,
        name: "Help & Support lists",
      },
      showLoader: false,
      columns: [
        {
          label: "Ticket",
          name: "ticket_no",
          sort: true,
          uniqueId: true, // like this
        },
        {
          label: "First Name",
          name: "firstname",
          sort: true,
        },
        {
          label: "Last Name",
          name: "lastname",
          sort: true,
        },
        {
          label: "Email",
          name: "email",
          sort: true,
        },

        {
          label: "Phone",
          name: "phone",
          sort: true,
        },
        {
          label: "Description",
          name: "description",
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
          placeholder: "Enter search help and support",
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
    };
  },
  components: {
    VueBootstrap4Table,
    Breadcrumb,
    ReplyModel,
    // downloadExcel,
    // modalView,
  },
  methods: {
    currentReply(row) {
      this.reply = row;
    },
    deleteRow(id) {
      try {
        this.$swal
          .fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#3085d6",
            cancelButtonColor: "#d33",
            confirmButtonText: "Yes, delete it!",
          })
          .then((result) => {
            if (result.isConfirmed) {
              helperService.deleteHelper(id).then((response) => {
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
          duration: 3000,
        });
      }
    },
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    // load items is what brings back the rows from server
    loadItems() {
      helperService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.helpers;
        this.showLoader = false;
      });
    },
  },
  mounted() {
    this.loadItems();
  },
};
</script>

<style lang="scss" scoped></style>
