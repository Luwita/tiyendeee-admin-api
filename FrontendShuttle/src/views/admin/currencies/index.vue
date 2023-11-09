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
            @refresh-data="onRefreshData"
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
                <b-badge variant="success" v-if="props.row.status"
                  >Active</b-badge
                >
                <b-badge variant="danger" v-if="!props.row.status"
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
                  <b-dropdown-item @click="EditRow(props.row)"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
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

            <template slot="vbt-action-buttons">
              <div class="float-right">
                <b-button variant="primary" v-b-modal.modal-prevent-create>
                  <i class="mdi mdi-plus"></i> Create Currency
                </b-button>
              </div>
            </template>
          </vue-bootstrap4-table>

          <b-modal
            id="modal-prevent-create"
            ref="modal"
            centered
            :title="modalTitle"
            @show="resetModal"
            @hidden="resetModal"
            @ok="handleOk"
            :ok-title="modalOkTitle"
          >
            <CreateCurrency v-if="!modalEdit" ref="handleCurrency" />
            <EditCurrency
              v-else-if="modalEdit"
              :editRow="editRow"
              ref="handleCurrency"
            />
          </b-modal>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { onMounted, onUpdated, ref, onUnmounted } from "vue";
import Breadcrumb from "../../../components/breadcrumb";
import { currencyService } from "../../../services";
import CreateCurrency from "./create.vue";
import EditCurrency from "./edit.vue";
import { useToast, useSwal } from "../../../composable/helper.js";
import VueBootstrap4Table from "vue-bootstrap4-table";

export default {
  name: "currency",
  components: { Breadcrumb, VueBootstrap4Table, CreateCurrency, EditCurrency },
  setup() {
    const breadcrumbs = ref({
      title: "Currency Lists",
      b1: "Manage Currencies",
      b2: "Currencies",
      b3: "Index",
      link: false,
      name: "Currency lists",
    });
    const options = ref([
      { text: "Active", value: "Active" },
      { text: "Inactive", value: "Inactive" },
    ]);
    const showLoader = ref(true);
    let rows = ref([]);
    let columns = ref([
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
      { label: "Name", name: "name", sort: true },
      { label: "Code", name: "code", sort: true },
      { label: "Symbol", name: "symbol", sort: true, html: true },
      { label: "Status", name: "status", sort: false },
      { label: "CreatedAt", name: "createdAt", sort: true },
      { label: "Action", name: "action" },
    ]);
    const modal = ref(null);
    const config = ref({
      server_mode: true, // by default false
      loaderText: "Updating...", // by default 'Loading...'
      pagination: true,
      per_page_options: [20, 30, 50, 90, 150],
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
    });
    const modalTitle = ref("Create Currency");
    const modalOkTitle = ref("submit");
    const classes = ref({
      table: "  table-bordered  table-striped",
    });
    const dropdowns = ref([]);
    const queryParams = ref({
      sort: [],
      filters: [],
      global_search: "",
      per_page: 10,
      page: 1,
    });
    const total_rows = ref(0);
    const modalEdit = ref(false);
    const editRow = ref({});
    const handleCurrency = ref(null);

    onMounted(() => {
      fetchData();
    });
    const fetchData = () => {
      currencyService.getAll(queryParams.value).then((response) => {
        total_rows.value = response.data.totalRecords;
        rows.value = response.data.currencies;
        showLoader.value = false;
      });
    };

    const swal = useSwal(); // sweeralert pop for deletion
    const toast = useToast(); // notify

    const deleteRow = async (id) => {
      try {
        swal
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
              currencyService.remove(id).then((response) => {
                if (
                  typeof response.data === "object" &&
                  response.data.status === 401
                ) {
                  swal.fire(response.data.message, "", "error");
                  toast.open({
                    message: response.data.message,
                    type: "error",
                    position: "top-right",
                    duration: 3000,
                    // all of other options may go here
                  });
                  showLoader.value = false;
                } else if (response.status) {
                  swal.fire("Deleted!", response.message, "success");
                  toast.open({
                    message: response.message,
                    type: "success",
                    position: "top-right",
                    duration: 3000,
                    // all of other options may go here
                  });
                  showLoader.value = true;
                  fetchData();
                }
              });
            }
          });
      } catch (e) {
        toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    };

    const onChangeQuery = (queryParams) => {
      queryParams.value = queryParams;
      showLoader.value = true;
      fetchData();
    };

    const onRefreshData = () => {
      showLoader.value = true;
      fetchData();
    };

    const enableDropdowns = () => {
      document
        .querySelectorAll('[data-toggle="dropdown"]')
        .forEach(($dropdownToggle) => {
          const $dropdown = $dropdownToggle.nextElementSibling;
          let isShown = false;

          function setIsShown(state) {
            isShown = state;
            $dropdown.classList.toggle("show", isShown);
          }

          if (!dropdowns.value.includes($dropdown)) {
            dropdowns.value.push($dropdown);

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
    };

    onUpdated(() => enableDropdowns());

    onUnmounted(() => {
      dropdowns.value.forEach(($dropdown) => {
        document.removeEventListener("click", $dropdown.clickOutsideEvent);
      });
    });

    const handleOk = (bvModalEvent) => {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      handleCurrency.value.handleSubmit();
      onRefreshData();
      modalEdit.value = false;
    };

    const resetModal = () => {};

    const EditRow = (row) => {
      modalEdit.value = true;
      modalTitle.value = "Edit Currency";
      modalOkTitle.value = "Update";
      modal.value.show();
      editRow.value = row;
    };
    return {
      breadcrumbs,
      options,
      showLoader,
      dropdowns,
      rows,
      editRow,
      columns,
      config,
      classes,
      queryParams,
      total_rows,
      modal,
      modalTitle,
      modalOkTitle,
      modalEdit,
      EditRow,
      onChangeQuery,
      deleteRow,
      onRefreshData,
      enableDropdowns,
      handleCurrency,
      handleOk,
      resetModal,
    };
  },
};
</script>

<style lang="scss" scoped></style>
