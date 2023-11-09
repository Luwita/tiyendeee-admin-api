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

            <template slot="permissions" slot-scope="props">
              <b-container v-if="props.row.permissions.length > 0">
                <b-row cols="10">
                  <b-col
                    class="pb-1 pr-0.5"
                    v-for="(permission, index) in props.row.permissions"
                    :key="index"
                    ><b-badge variant="dark">{{ permission.name }}</b-badge>
                  </b-col>
                </b-row>
              </b-container>
            </template>
            <template slot="action" slot-scope="props">
              <span v-if="props.column.name == 'action'">
                <b-dropdown
                  id="dropdown-left"
                  text="Actions"
                  variant="outline-info"
                  class="m-2"
                >
                  <!-- <b-dropdown-item :href="`#/role-permission/` + props.row.ids">
                    <span class="text-dark">
                      <i class="mdi mdi-badge-account"></i> Assign Permission
                    </span></b-dropdown-item
                  > -->
                  <b-dropdown-item :href="`#/role/` + props.row.ids"
                    ><span class="text-primary">
                      <i class="mdi mdi-pencil"></i> Edit
                    </span></b-dropdown-item
                  >
                  <!-- <b-dropdown-item
                    @click.stop="deleteRow(props.row.ids, props.row.name)"
                    ><span class="text-danger">
                      <i class="mdi mdi-delete"></i> Delete
                    </span></b-dropdown-item
                  > -->
                </b-dropdown>
              </span>
            </template>
            <!-- <template slot="action" slot-scope="props">
              <span v-if="props.column.name == 'action'">
                <b-button
                  variant="success"
                  @click="
                    $router.push({
                      name: 'roleedit',
                      params: { id: props.row.ids },
                    })
                  "
                  size="xs"
                  ><span class="text-light">
                    <i class="mdi mdi-pencil"></i> Edit
                  </span></b-button
                >
                 <b-button
                  variant="danger"
                  @click.stop="deleteRow(props.row.ids, props.row.name)"
                  size="xs"
                  ><span class="text-light">
                    <i class="mdi mdi-delete"></i> Delete
                  </span></b-button
                > 
              </span>
            </template> -->
            <template slot="paginataion-previous-button"> Previous </template>
            <template slot="paginataion-next-button"> Next </template>
          </vue-bootstrap4-table>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import { roleService } from "../../../services";
import Breadcrumb from "../../../components/breadcrumb";
import VueBootstrap4Table from "vue-bootstrap4-table";
export default {
  name: "roles",
  data() {
    return {
      title: "",
      breadcrumbs: {
        title: "Role Lists",
        b1: "Manage Roles",
        b2: "Roles",
        b3: "Index",
        link: false,
        name: "Role lists",
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
          label: "Permissions",
          name: "permissions",
          sort: false,
        },
        { label: "Action", name: "action", sort: false },
      ],
      rows: [],
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
        slug: "",
        createdAt: "",
      },
    };
  },
  components: {
    VueBootstrap4Table,
    Breadcrumb,
  },
  methods: {
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
              roleService.deleteRole(id, name).then((response) => {
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
    onChangeQuery(queryParams) {
      this.queryParams = queryParams;
      this.showLoader = true;
      this.loadItems();
    },
    // load items is what brings back the rows from server
    loadItems() {
      roleService.getAll(this.queryParams).then((response) => {
        this.total_rows = response.data.totalRecords;
        this.rows = response.data.roles;
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

<style lang="scss" scoped></style>
