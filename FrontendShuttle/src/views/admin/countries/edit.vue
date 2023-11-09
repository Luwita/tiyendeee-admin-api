<template>
  <div>
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-form-group
        label="Name"
        label-for="name-input"
        invalid-feedback="Name is required"
      >
        <b-form-input
          id="name-input"
          v-model="name"
          :state="validateState('name')"
          placeholder="enter a name"
          :class="{
            'is-invalid': submitted && v$.name.$error,
          }"
        ></b-form-input>
      </b-form-group>
      <b-form-group
        label="Code"
        label-for="code-input"
        invalid-feedback="Code is required"
      >
        <b-form-input
          id="code-input"
          v-model="code"
          :state="validateState('code')"
          placeholder="enter a code. example INR or USD"
          :class="{
            'is-invalid': submitted && v$.code.$error,
          }"
          :formatter="formatter"
        ></b-form-input>
        <small v-if="submitted && v$.code.maxLength" class="invalid-feedback">
          maximum 4 character is allowed
        </small>
      </b-form-group>

      <b-form-group
        label="Status"
        label-for="status-input"
        invalid-feedback="Status is required"
      >
        <b-form-checkbox
          v-model="status"
          name="check-status"
          :state="validateState('status')"
          :class="{
            'is-invalid': submitted && v$.status.$error,
          }"
          switch
        >
          {{ status ? "Active" : "Inactive" }}
        </b-form-checkbox>
      </b-form-group>
    </form>
  </div>
</template>

<script>
import { ref, reactive, toRefs, nextTick } from "vue";
import { useVuelidate } from "@vuelidate/core";
import { required, maxLength } from "@vuelidate/validators";
import { countryService } from "../../../services";
import { useBVModal, useToast } from "../../../composable/helper.js";

export default {
  name: "countryedit",
  props: {
    editRow: Object,
  },
  setup(props, context) {
    let state = reactive({
      name: "",
      code: "",
      status: true,
    });
    const submitted = ref(false);
    const bvModal = useBVModal();
    const toast = useToast();
    state = props.editRow;
    const rules = {
      name: { required },
      code: { required, maxLength: maxLength(4) },
      status: { required },
    };

    const v$ = useVuelidate(rules, state, { $lazy: true });
    const validateState = (name) => {
      const { $dirty, $error } = v$.value[name];
      return $dirty ? !$error : null;
    };
    const handleSubmit = async () => {
      submitted.value = true;
      // stop here if form is invalid
      v$.value.$touch();
      if (v$.value.$invalid) {
        return;
      } else {
        const response = await countryService.update(state.ids, state);
        if (response.status) {
          toast.open({
            message: response.message,
            type: "success",
            position: "top-right",
            duration: 3000,
            // all of other options may go here
          });

          nextTick(() => {
            bvModal.hide("modal-prevent-create");
          });
        } else {
          toast.open({
            message: "Failed to create new country",
            type: "error",
            position: "top-right",
            duration: 5000,
          });
        }
      }
    };
    context.expose({ handleSubmit });

    const formatter = (value) => {
      return value.toUpperCase();
    };

    return {
      ...toRefs(state),
      v$,
      submitted,
      validateState,
      handleSubmit,
      formatter,
    };
  },
};
</script>

<style lang="scss" scoped></style>
