<template>
  <div>
    <b-form @submit.prevent="createNotification">
      <b-form-group
        label="To"
        label-for="to-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          v-model.trim="$v.form.to.$model"
          :options="options"
          value-field="item"
          text-field="name"
          @change="toChange"
          :class="{
            'is-invalid': submitted && $v.form.to.$error,
          }"
          :state="validateState('to')"
        ></b-form-radio-group>
        <div v-if="submitted && !$v.form.to.required" class="invalid-feedback">
          to is required
        </div>
      </b-form-group>
      <b-form-group
        label="User Type"
        label-for="type-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          v-model.trim="$v.form.user_type.$model"
          :options="usertypeoptions"
          value-field="item"
          text-field="name"
          @change="toChange"
          :class="{
            'is-invalid': submitted && $v.form.user_type.$error,
          }"
          :state="validateState('user_type')"
        ></b-form-radio-group>
        <div
          v-if="submitted && !$v.form.user_type.required"
          class="invalid-feedback"
        >
          user type is required
        </div>
      </b-form-group>

      <b-form-group
        label="Message Type"
        label-for="message-type-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          v-model="form.message_type"
          :options="messagetypeoptions"
          value-field="item"
          text-field="name"
        ></b-form-radio-group>
      </b-form-group>

      <b-form-group
        label="Users firstname/phone/email"
        label-for="user-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
        v-if="isUsers"
      >
        <v-select
          @search="fetchUsers"
          label="type"
          :filterable="false"
          :options="usersoptions"
          v-model="form.user"
        >
          <template slot="no-options">
            type to users phone, name , email..
          </template>

          <template slot="option" slot-scope="usersoptions">
            <div class="d-center">
              {{ usersoptions.title }}
            </div>
          </template>
          <template slot="selected-option" slot-scope="usersoptions">
            <div class="selected d-center">
              {{ usersoptions.title }}
            </div>
          </template>
        </v-select>
      </b-form-group>

      <b-form-group
        label="Schedule"
        label-for="schedule-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-radio-group
          v-model="form.schedule"
          :options="scheduleoptions"
          value-field="item"
          text-field="name"
          @change="checkSchedule"
        ></b-form-radio-group>

        <b-form-group
          label="Time"
          label-for="time-input"
          label-cols-sm="4"
          label-cols-lg="2"
          content-cols-sm
          content-cols-lg="4"
          v-if="isDate"
        >
          <b-form-input
            v-model="form.time"
            :id="`type-time`"
            type="time"
          ></b-form-input>
        </b-form-group>
        <b-form-group
          label="Days"
          label-for="days-input"
          label-cols-sm="4"
          label-cols-lg="2"
          content-cols-sm
          content-cols-lg="10"
          v-if="isDate"
        >
          <b-form-checkbox-group
            id="checkbox-group-1"
            v-model.trim="form.days"
            :options="dayoptions"
            name="day-1"
          ></b-form-checkbox-group>
        </b-form-group>
      </b-form-group>
      <b-form-group
        label="Title"
        label-for="title-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-input
          id="title-input"
          v-model.trim="$v.form.title.$model"
          type="text"
          placeholder="Enter title"
          :class="{
            'is-invalid': submitted && $v.form.title.$error,
          }"
          :state="validateState('title')"
        ></b-form-input>
        <div
          v-if="submitted && !$v.form.title.required"
          class="invalid-feedback"
        >
          title is required
        </div>
      </b-form-group>
      <b-form-group
        label="Message"
        label-for="message-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <b-form-textarea
          placeholder="Enter something..."
          rows="5"
          max-rows="8"
          v-model.trim="$v.form.content.$model"
          v-on:keyup="liveCountDown"
          :class="{
            'is-invalid': submitted && $v.form.content.$error,
          }"
          :state="validateState('content')"
        ></b-form-textarea>
        <p>
          Total Remaining:
          <span v-bind:class="{ 'text-danger': generateErr }">{{
            totalRemainCount
          }}</span>
        </p>
        <div
          v-if="submitted && !$v.form.content.minLength"
          class="invalid-feedback"
        >
          maximum 160 character is allowed
        </div>
      </b-form-group>

      <b-form-group
        label="Image (720x480)"
        label-for="image-input"
        label-cols-sm="4"
        label-cols-lg="3"
        content-cols-sm
        content-cols-lg="7"
      >
        <div v-if="!form.picture">
          <b-form-file
            id="picture-input"
            accept="image/jpeg, image/png, image/jpg"
            placeholder="Choose a image or drop it here..."
            @change="onFileChange($event, 'picture')"
          ></b-form-file>
        </div>
        <div v-else>
          <img class="img-fluid" :src="form.picture" width="250" height="250" />
          <button
            class="btn social-btn btn-rounded btn-danger mr-4"
            @click="removeImage('picture')"
          >
            <i class="mdi mdi-close"></i>
          </button>
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
import {
  customerService,
  driverService,
  notificationService,
} from "../../services";
import { validationMixin } from "vuelidate";
import { required, maxLength } from "vuelidate/lib/validators";
import vSelect from "vue-select";
import "vue-select/src/scss/vue-select.scss";
import lodash from "lodash";
import moment from "moment-timezone";

export default {
  mixins: [validationMixin],
  name: "createnotifyform",
  data() {
    return {
      options: [
        { item: "to_all", name: "All" },
        { item: "to_specific", name: "Specific Users" },
      ],
      usertypeoptions: [
        { item: "customer", name: "Customer" },
        // { item: "driver", name: "Driver" },
      ],
      messagetypeoptions: [{ item: "push", name: "Push Notification" }],
      usersoptions: [],
      scheduleoptions: [
        { item: "immediately", name: "Send Immediately" },
        { item: "date", name: "Time & Days" },
      ],
      dayoptions: [
        { text: "Sunday", value: 0 },
        { text: "Monday", value: 1 },
        { text: "Tuesday", value: 2 },
        { text: "Wednesday", value: 3 },
        { text: "Thursday", value: 4 },
        { text: "Friday", value: 5 },
        { text: "Saturday", value: 6 },
      ],
      form: {
        to: "to_all",
        user_type: "customer",
        message_type: "push",
        title: "",
        content: "",
        user: "",
        schedule: "immediately",
        time: "",
        days: [],
        picture: "",
      },
      limitmaxCount: 160,
      totalRemainCount: 160,
      generateErr: false,
      submitted: false,
      isUsers: false,
      isDate: false,
      minDatetime: moment().tz("Asia/Kolkata").valueOf().toString(),
      maxDatetime: moment().tz("Asia/Kolkata").valueOf().toString(),
    };
  },
  validations: {
    form: {
      to: { required },
      user_type: { required },
      title: { required },
      content: { maxLength: maxLength(160) },
    },
  },
  components: {
    vSelect,
  },
  methods: {
    onFileChange(e, fileTitle) {
      var files = e.target.files || e.dataTransfer.files;
      console.log(files);
      if (!files.length) return;
      this.createImage(files[0], fileTitle);
    },
    createImage(file, fileTitle) {
      // var picture = new Image();
      var reader = new FileReader();
      var vm = this;

      reader.onload = (e) => {
        vm.form[fileTitle] = e.target.result;
      };
      reader.readAsDataURL(file);
    },
    removeImage: function (titlename) {
      this.form[titlename] = "";
    },
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    liveCountDown: function () {
      this.totalRemainCount = this.limitmaxCount - this.form.content.length;
      this.generateErr = this.totalRemainCount < 0;
    },
    toChange(value) {
      if (value === "to_specific") {
        this.isUsers = true;
      } else {
        this.user = "";
        this.isUsers = false;
      }
    },
    checkSchedule(value) {
      if (value === "date") {
        this.isDate = true;
      } else {
        this.form.days = [];
        this.form.time = "";
        this.isDate = false;
      }
    },
    fetchUsers(search, loading) {
      if (search.length) {
        loading(true);
        this.search(loading, search, this.form.user_type, this);
      }
    },
    search: lodash.debounce(async (loading, search, user_type, vm) => {
      try {
        if (user_type === "customer") {
          const response = await customerService.q({
            search: search,
          });
          vm.usersoptions = response.items;
          loading(false);
        } else if (user_type === "driver") {
          const response = await driverService.q({
            search: search,
          });
          vm.usersoptions = response.items;
          loading(false);
        }
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
    async createNotification() {
      try {
        this.submitted = true;
        // stop here if form is invalid
        this.$v.$touch();
        if (this.$v.$invalid) {
          return;
        }

        const reponse = await notificationService.create(this.form);
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
                path: `/notifications`,
              }),
            3000
          );
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
  },
};
</script>

<style scoped>
.theme-ferri .vdatetime-popup__header,
.theme-ferri .vdatetime-calendar__month__day--selected > span > span,
.theme-ferri .vdatetime-calendar__month__day--selected:hover > span > span {
  background: #46ac46;
}

.theme-ferri .vdatetime-year-picker__item--selected,
.theme-ferri .vdatetime-time-picker__item--selected,
.theme-ferri .vdatetime-popup__actions__button {
  color: #46ac46;
}
</style>
