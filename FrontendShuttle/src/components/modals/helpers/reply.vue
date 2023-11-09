<template>
  <div>
    <b-modal
      centered
      id="bv-modal-reply"
      @show="resetModal"
      @hidden="resetModal"
      @ok="handleOk"
      ref="modal"
    >
      <template #modal-title>
        {{ titlemodal }} : Ticket no({{ reply.ticket_no }})</template
      >
      <form ref="form" @submit.stop.prevent="handleSubmit">
        <input type="hidden" :reply="reply.ids" />
        <b-form-group
          label="TItle"
          label-for="title-input"
          invalid-feedback="Title is required"
          :state="titleState"
        >
          <b-form-input
            id="name-input"
            v-model="form.title"
            :state="titleState"
            required
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="Content"
          label-for="content-input"
          invalid-feedback="Content is required"
          :state="contentState"
        >
          <b-form-textarea
            id="content-input"
            v-model="form.content"
            rows="5"
            max-rows="10"
            size="lg"
            :state="contentState"
            required
          ></b-form-textarea>
        </b-form-group>
      </form>
    </b-modal>
  </div>
</template>

<script>
import { helperService } from "../../../services/helper.service";
import { mapState } from "pinia";
import { fetchUsers } from "../../../store/fetchUsers.js";

export default {
  name: "replymodal",
  props: ["titlemodal", "reply"],

  data() {
    return {
      titleState: null,
      contentState: null,
      helperId: "",
      form: {
        title: "",
        content: "",
        type: "notification",
        email: "",
      },
    };
  },
  computed: {
    ...mapState(fetchUsers, ["getUser"]),
  },
  methods: {
    checkFormValidity() {
      const valid = this.$refs.form.checkValidity();
      this.titleState = valid;
      this.contentState = valid;
      return valid;
    },
    resetModal() {
      this.form.title = "";
      this.titleState = null;
      this.form.content = "";
      this.contentState = null;
    },
    init() {
      this.helperId = this.reply.ids;
      this.form.email = this.reply.email; // step1. assign propId to id
    },
    handleOk(bvModalEvent) {
      // Prevent modal from closing
      bvModalEvent.preventDefault();
      // Trigger submit handler
      this.handleSubmit();
    },
    async handleSubmit() {
      // Exit when the form isn't valid
      if (!this.checkFormValidity()) {
        return;
      }
      this.form.adminId = this.getUser.id; // admin Id
      const response = await helperService.update(this.helperId, this.form);
      if (response.status) {
        this.$toast.open({
          message: response.message,
          type: "success",
          position: "top-right",
          duration: 2000,
          // all of other options may go here
        });
        // Hide the modal manually
        this.$nextTick(() => {
          this.$bvModal.hide("bv-modal-reply");
        });
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
        this.$nextTick(() => {
          this.$bvModal.hide("bv-modal-reply");
        });
      }
    },
  },
  watch: {
    reply: function () {
      this.init();
    },
  },
};
</script>

<style></style>
