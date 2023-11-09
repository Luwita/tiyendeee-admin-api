<template>
  <div>
    <b-form @submit.prevent="createRecharge">
      <b-form-group
        label="Customer Name/Phone/Email"
        label-for="name-input"
        label-cols-sm="4"
        label-cols-lg="5"
        content-cols-sm
        content-cols-lg="5"
      >
        <v-select
          @search="fetchCustomers"
          label="type"
          :filterable="false"
          :options="options"
          v-model="form.user"
        >
          <template slot="no-options">
            type to customers phone, name , email..
          </template>

          <template slot="option" slot-scope="option">
            <div class="d-center">
              {{ option.title }}
            </div>
          </template>
          <template slot="selected-option" slot-scope="option">
            <div class="selected d-center">
              {{ option.title }}
            </div>
          </template>
        </v-select>
      </b-form-group>

      <b-form-group
        label="Amount"
        label-for="name-input"
        label-cols-sm="4"
        label-cols-lg="5"
        content-cols-sm
        content-cols-lg="5"
      >
        <b-form-input
          id="name-input"
          v-model.trim="$v.form.amount.$model"
          type="number"
          placeholder="Enter name"
          :class="{
            'is-invalid': submitted && $v.form.amount.$error,
          }"
          :state="validateState('amount')"
        ></b-form-input>
        <div
          v-if="submitted && !$v.form.amount.required"
          class="invalid-feedback"
        >
          amount is required
        </div>
        <div
          v-if="submitted && !$v.form.amount.minValue"
          class="invalid-feedback"
        >
          minimum amount should be 10 or greater than 10
        </div>
      </b-form-group>

      <b-form-group class="col-md-6 offset-md-5">
        <b-button type="submit" class="btn btn-lg btn-success text-center"
          >Submit</b-button
        >
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { customerService, walletService } from "../../../services";
import vSelect from "vue-select";
import lodash from "lodash";
import { validationMixin } from "vuelidate";
import { required, numeric, minValue } from "vuelidate/lib/validators";
import "vue-select/src/scss/vue-select.scss";
import { mapState } from "pinia";
import { fetchUsers } from "../../../store/fetchUsers.js";

export default {
  mixins: [validationMixin],
  name: "createform",
  data() {
    return {
      submitted: false,
      loading: false,
      options: [],
      form: {
        user: {},
        amount: 0,
      },
    };
  },
  validations: {
    form: {
      user: { required },
      amount: { required, numeric, minValue: minValue(10) },
    },
  },
  computed: {
    ...mapState(fetchUsers, ["getUser"]),
  },
  components: {
    vSelect,
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    fetchCustomers(search, loading) {
      if (search.length) {
        loading(true);
        this.search(loading, search, this);
      }
    },
    search: lodash.debounce(async (loading, search, vm) => {
      try {
        const response = await customerService.q({
          search: search,
        });
        vm.options = response.items;
        loading(false);
      } catch (err) {
        console.log("err", err);
        // this.$toast.open({
        //   message: err,
        //   type: "error",
        //   position: "top-right",
        //   duration: 5000,
        // });
      }
    }, 350),
    async createRecharge() {
      this.submitted = true;
      // stop here if form is invalid
      this.$v.$touch();
      if (this.$v.$invalid) {
        return;
      }
      this.form.adminId = this.getUser.id; // admin Id
      const reponse = await walletService.create(this.form);
      if (reponse.status) {
        this.$toast.open({
          message: reponse.message,
          type: "success",
          position: "top-right",
          duration: 3000,
          // all of other options may go here
        });
        setTimeout(
          () =>
            this.$router.push({
              path: `/customers`,
            }),
          3000
        );
      }
    },
  },
};
</script>

<style></style>
