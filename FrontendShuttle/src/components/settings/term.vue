<template>
  <div>
    <b-form @submit.prevent="updateTerms">
      <input type="hidden" v-model="form.id" />
      <b-form-group
        label="Terms & Conditions "
        label-for="term-input"
        class="mt-3"
        label-cols-sm="4"
        label-cols-lg="2"
        content-cols-sm
        content-cols-lg="10"
      >
        <vue-editor class="mt-3" v-model="form.terms"></vue-editor>
      </b-form-group>

      <b-form-group class="col-md-6 offset-md-4">
        <b-button type="submit" class="btn btn-success btn-lg text-center"
          >Submit</b-button
        >
      </b-form-group>
    </b-form>
  </div>
</template>

<script>
import { VueEditor } from "vue2-editor";
import { settingService } from "../../services";
export default {
  name: "term",
  data() {
    return {
      form: {
        type: "terms",
        id: "",
        terms: "",
      },
      submitted: false,
    };
  },
  components: {
    VueEditor,
  },
  methods: {
    async getSetting() {
      try {
        const response = await settingService.find("terms");
        if (response.status) {
          this.form = response.data;
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
    async updateTerms() {
      try {
        this.submitted = true;
        const response = await settingService.update(this.form.id, this.form);
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
