<template>
  <div>
    <b-form @submit.prevent="updatePayment">
      <input type="hidden" v-model="form.refunds.id" />
      <b-form-group
        label="Type"
        label-for="type-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          :options="typeOptions"
          v-model.trim="form.refunds.type"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Amount"
        label-for="amount-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-input v-model="form.refunds.amount" placeholder="amount"> </b-input>
      </b-form-group>

      <b-form-group class="col-md-6 offset-md-4">
        <b-button
          type="submit"
          class="btn btn-success btn-lg text-center"
          disabled
          >Submit</b-button
        >
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { settingService } from "../../services";

export default {
  name: "refund",
  data() {
    return {
      typeOptions: [
        { text: "Percentage", value: "percentage" },
        { text: "Number", value: "number" },
      ],
      form: {
        type: "refunds",
        refunds: {
          id: "",
          amount: "",
          type: "",
          contents: "",
        },
      },
    };
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("refunds");
        if (response.status) {
          this.form.refunds = response.data;
        }
      } catch (e) {
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 5000,
        });
      }
    },
    async updatePayment() {
      try {
        this.submitted = true;
        const response = await settingService.update(
          this.form.refunds.id,
          this.form
        );
        if (response.status) {
          this.$toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 2000,
            // all of other options may go here
          });
        }
      } catch (e) {
        this.$toast.open({
          message: e,
          type: "error",
          position: "top-right",
          duration: 4000,
        });
      }
    },
  },
  mounted() {
    this.getSetting();
  },
};
</script>

<style lang="scss" scoped></style>
