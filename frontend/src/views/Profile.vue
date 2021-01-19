<template>
  <div class="profile-page">
    <section class="section-profile-cover section-shaped my-0">
      <div class="shape shape-style-1 shape-primary shape-skew alpha-4">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        <span></span>
      </div>
    </section>
    <section class="section section-skew">
      <div class="container">
        <card shadow class="card-profile mt--300" no-body>
          <div class="px-4">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <a href="#">
                    <img v-lazy="'img/theme/team-1-800x800.jpg'" class="rounded-circle">
                  </a>
                </div>
              </div>
              <div class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center">
                <div class="card-profile-actions py-4 mt-lg-0">
                  <base-button type="default" size="sm" class="float-right">View Messages
                  </base-button>
                  <base-button type="default" size="sm" class="float-left"
                               v-if="userDetails.role == 1" @click="openCreateAccountModal()">Create
                    account
                  </base-button>
                  <base-button type="info" size="sm" class="float-right mr-3 view-classes-btn" @click="viewClasses()">
                    View classes
                  </base-button>
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center" style="width: 100%;">
                  <div>
                    <span class="heading">10</span>
                    <span class="description">Homeworks done</span>
                  </div>
                  <div>
                    <span class="heading">10</span>
                    <span class="description">Unread messages</span>
                  </div>
                  <div>
                    <span class="heading">10</span>
                    <span class="description">New materials</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="text-center mt-5">
              <h3>{{ userDetails.first_name }} {{ userDetails.last_name }}
                <span class="font-weight-light">, 27</span>
              </h3>
              <div class="h6 font-weight-300"><i class="ni location_pin mr-2"></i>Iasi, Romania</div>
              <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Distributed Systems Master
              </div>
              <div><i class="ni education_hat mr-2"></i>Faculty of Computer Science Iasi Romania</div>
            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
                <div class="col-lg-9">
                  "Nothing is true, everything is permitted"
                </div>
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
    <modal :show.sync="showModal">
      <div class="alert alert-danger" v-if="showAlertDangerModal">{{ account_message }}</div>
      <div class="alert alert-success" v-if="showAlertSuccessModal">{{ account_message }}</div>
      <template slot="header">
        <h5 class="modal-title" id="exampleModalLabel">Create new account</h5>
      </template>
      <form id="new-account-form">
        <div>
          <label for="email">First name:</label>
          <input type="email" class="form-control" id="first_name" placeholder="First name" v-model="first_name">
        </div>
        <div>
          <label for="email">Last name:</label>
          <input type="email" class="form-control" id="last_name" placeholder="Last name" v-model="last_name">
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="email" class="form-control" id="email" placeholder="Email" v-model="email">
        </div>
        <div>
          <label for="password">Password:</label>
          <input type="text" class="form-control" id="password" placeholder="Password" v-model="password">
        </div>
        <div>
          <label for="role">Role:</label>
          <select id="role" class="form-control" v-model="role">
            <option value="1" selected>Admin</option>
            <option value="2">Student</option>
            <option value="3">Teacher</option>
          </select>
        </div>
        <div v-if="role == 2">
          <label for="group">Group:</label>
          <input type="text" class="form-control" id="group" placeholder="Group" v-model="group">
        </div>
        <div class="form-group mt-3 teacher-courses" v-if="role == 3">
          <label>Select courses for teacher:</label>
          <template v-for="courses in classes">
            <div class="row" :key="classes.id">
              <div class="col-md-3">
                <label>{{ courses.name }}</label>
              </div>
              <div class="col-md-1">
                <input type="checkbox" style="height: 20px !important;" :value="courses.id" v-model="classes_arr">
              </div>
            </div>
          </template>
        </div>
      </form>
      <template slot="footer">
        <base-button type="secondary" @click="showModal = false">Cancel</base-button>
        <base-button type="primary" @click="createAccount()">Create account</base-button>
      </template>
    </modal>
  </div>
</template>

<script>
import axios from 'axios';
import Modal from "../components/Modal";
import $ from "jquery";

export default {
  name: "profile",
  components: {modal: Modal},
  data() {
    return {
      userDetails: {},
      showModal: false,
      email: '',
      password: '',
      role: '',
      group: '',
      classes: [],
      classes_arr: [],
      last_name: '',
      first_name: '',
      account_message: '',
      showAlertDangerModal: false,
      showAlertSuccessModal: false,
      formValid: true,
    }
  },
  methods: {
    viewClasses() {
      this.$router.push({name: 'classes'});
    },
    openCreateAccountModal() {
      this.showModal = true;
    },
    createAccount() {
      var self = this;
      self.account_message = '';
      self.showAlertDangerModal = false;
      self.showAlertSuccessModal = false;
      this.checkForRequiredInputs();
      setTimeout(function () {
        if(self.formValid === true) {
          console.log('intra')
          axios.post('/api/create-account', {
            email: self.email,
            password: self.password,
            role: self.role,
            group: self.group,
            classes: self.classes_arr.toString(),
            first_name: self.first_name,
            last_name: self.last_name
          }, {headers: {'Authorization': localStorage.getItem('vismart_jwt_token')}}).then(function (response) {
            if (response.data == "User already exists") {
              self.account_message = "An user with this email already exists!";
              self.showAlertDangerModal = true;
            } else {
              self.account_message = "User added succesfully!";
              self.showAlertSuccessModal = true;
              setTimeout(function () {
                self.showModal = false;
                self.showAlertSuccessModal = false;
                self.account_message = '';
                self.resetForm();
              }, 1500);
            }
          });
        }
      }, 1000);
    },
    getAllClasses() {
      var self = this;
      axios.get('/api/get-courses', {headers: {'Authorization': localStorage.getItem('vismart_jwt_token')}}).then(function (response) {
        self.classes = response.data;
      })
    },
    resetForm() {
      this.email = '';
      this.password = '';
      this.role = '';
      this.group = '';
      this.classes = [];
      this.classes_arr = [];
      this.last_name = '';
      this.first_name = '';
      this.showAlertDangerModal = false;
      this.showAlertSuccessModal = false;
      this.account_message = "";
      $("input").removeClass("error-input");
      $(".teacher-courses").removeClass("error-input");
    },
    checkForRequiredInputs() {
      this.showAlertDangerModal = false;
      this.account_message = "";
      this.formValid = true;
      $("input").removeClass('error-input');
      $(".teacher-courses").removeClass("error-input");
      if (this.first_name == "") {
        $('#first_name').addClass('error-input');
        this.formValid = false;
        this.account_message = "First Name field is required!";
        this.showAlertDangerModal = true;
      } else if (this.last_name == "") {
        $('#last_name').addClass('error-input');
        this.formValid = false;
        this.account_message = "Last Name field is required!";
        this.showAlertDangerModal = true;
      } else if (this.email == "") {
        $('#email').addClass('error-input');
        this.formValid = false;
        this.account_message = "Email Field is required!";
        this.showAlertDangerModal = true;
      } else if (this.password == "") {
        $('#password').addClass('error-input');
        this.formValid = false;
        this.account_message = "Password field is required!";
        this.showAlertDangerModal = true;
      } else if (this.role == "") {
        $('#role').addClass('error-input');
        this.formValid = false;
        this.account_message = "Role field is required!";
        this.showAlertDangerModal = true;
      }
      if(this.role == 2) {
        if(this.group == "") {
          $('#group').addClass('error-input');
          this.formValid = false;
          this.account_message = "Group field is required!";
          this.showAlertDangerModal = true;
        }
      } else if(this.role == 3) {
        if(this.classes_arr.length == 0) {
          $(".teacher-courses").addClass("error-input");
          this.formValid = false;
          this.account_message = "Please select some courses!";
          this.showAlertDangerModal = true;
        }
      }
    },
  },
    created() {
      this.$root.$emit('logged-in', 'logged-in');
      this.userDetails = JSON.parse(localStorage.getItem('user_details'))
      if (this.userDetails.role == 1) {
        this.getAllClasses();
        setTimeout(function () {
          $('.view-classes-btn').addClass('mt-2');
        }, 500);
      }
    }
}
</script>

<style>
.error-input {
  border: 2px solid red !important;
  padding-left: 6px !important;
}
</style>
