<template>
  <section class="buttons">
    <div class="row">
      <div class="col-md-8 grid-margin stretch-card">
        <div class="card">
          <b-card-body>
            <b-form @submit="onSubmit" v-if="show">
              <b-form-group
                id="input-group-first-name"
                label="Your First Name:"
                label-for="input-first-Name"
              >
                <b-form-input
                  id="input-first-name"
                  v-model="form.firstname"
                  placeholder="Enter first name"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                id="input-group-last-name"
                label="Your Last Name:"
                label-for="input-last-name"
              >
                <b-form-input
                  id="input-first-name"
                  v-model="form.lastname"
                  placeholder="Enter last name"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group
                id="input-group-1"
                label="Email address:"
                label-for="input-1"
                description="We'll never share your email with anyone else."
              >
                <b-form-input
                  id="input-1"
                  v-model="form.email"
                  type="email"
                  placeholder="Enter email"
                  required
                ></b-form-input>
              </b-form-group>
              <b-form-group
                id="input-phobe"
                label="Phone Number :"
                label-for="input-phone"
              >
                <VuePhoneNumberInput
                  :default-country-code="'IN'"
                  :required="true"
                  v-model="form.phone"
                  :no-example="true"
                />
              </b-form-group>

              <!-- <b-form-group
                id="input-group-3"
                label="Role:"
                label-for="input-3"
              >
                <b-form-select
                  id="input-3"
                  v-model="form.role"
                  :options="role"
                  required
                ></b-form-select>
              </b-form-group> -->

              <b-button type="submit" variant="primary">Submit</b-button>
            </b-form>
          </b-card-body>
        </div>
      </div>
      <div class="col-md-4 grid-margin stretch-card">
        <div class="card card-user">
          <div class="card-image">
            <img
              src="https://ununsplash.imgix.net/photo-1431578500526-4d9613015464?fit=crop&fm=jpg&h=300&q=75&w=400"
              alt="Profile image"
            />
          </div>
          <b-card-body>
            <div class="author">
              <a href="#">
                <img
                  class="avatar img-md rounded-circle"
                  :src="getUser.picture"
                  alt="Profile
                image"
                />
                <h4 class="title">
                  {{ getUser.firstname }} <br /><small
                    >{{ getUser.lastname }}
                  </small>
                </h4>
              </a>
            </div>
            <p class="description text-center">
              {{ getUser.email }} <br />
              +91-{{ getUser.phone }} <br />
            </p>
          </b-card-body>
          <b-card-footer> </b-card-footer>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import VuePhoneNumberInput from "vue-phone-number-input";
import "vue-phone-number-input/dist/vue-phone-number-input.css";
import { validationMixin } from "vuelidate";
import { required, email, minLength } from "vuelidate/lib/validators";
//import { authService } from "../../services";
import { mapState, mapActions } from "pinia";
//import { useAuth } from "../../store/useAuth.js";
import { fetchUsers } from "../../store/fetchUsers.js";

export default {
  mixins: [validationMixin],
  name: "MyProfile",
  computed: {
    ...mapState(fetchUsers, ["getUser"]),
  },
  data() {
    return {
      form: {},
      show: true,
    };
  },
  mounted() {
    this.form = this.getUser;
  },
  components: {
    VuePhoneNumberInput,
  },
  validations: {
    form: {
      email: { required, email },
      firstname: { required },
      lastname: { required },
      phone: { required, minLength: minLength(9) },
    },
  },
  methods: {
    ...mapActions(fetchUsers, ["updateUser"]),
    async onSubmit(event) {
      event.preventDefault();
      //   alert(JSON.stringify(this.User));
      const update = this.form;
      update.phone = this.form.phone.replace(/\s/g, "");
      await this.updateUser(this.form.id, update);
      // authService.updateProfile(this.form.id, update).then((response) => {
      //   console.log("response", response);
      //   if (response.status) {
      //     this.$toast.open({
      //       message: "Update profile successfully",
      //       type: "success",
      //       position: "top-right",
      //       duration: 3000,
      //     });
      //     this.$store.commit("auth/updateProfile", response.data);
      //   }
      // });
    },
  },
};
</script>

<style scoped>
.profile {
}
.card {
  border-radius: 4px;
  background-color: #fff;
  margin-bottom: 30px;
}
.card .card-body {
  padding: 15px 15px 10px;
}
.card-user .card-image {
  height: 110px;
  width: 100%;
  overflow: hidden;
  border-radius: 4px 4px 0 0;
  position: relative;
  -webkit-transform-style: preserve-3d;
  transform-style: preserve-3d;
}
.card .card-image img {
  width: 100%;
}
.card-user .card-body {
  min-height: 240px;
}
.card-body .author {
  text-align: center;
  text-transform: none;
  margin-top: -70px;
  font-size: 12px;
  font-weight: 600;
}
.card-body .description {
  font-size: 14px;
  color: #333;
}

.card-user .avatar.border-gray {
  border-color: #eee;
}
.card-user .avatar {
  width: 124px;
  height: 124px;
  border: 5px solid #fff;
  position: relative;
  margin-bottom: 15px;
}
</style>
