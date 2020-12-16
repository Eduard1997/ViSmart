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
                </div>
              </div>
              <div class="col-lg-4 order-lg-1">
                <div class="card-profile-stats d-flex justify-content-center">
                  <div>
                    <span class="heading">10</span>
                    <span class="description">Materials</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="text-center mt-5">
              <h3>Available Classes
              </h3>
              <h3>{{userDetails.first_name}} {{userDetails.last_name}}</h3>
              <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Distributed Systems Master</div>
              <div><i class="ni education_hat mr-2"></i>Faculty of Computer Science Iasi Romania</div>

              <table class="table mt-2">
                <thead>
                <tr>
                  <th>Class name</th>
                  <th>Stream Started</th>
                  <th>Stream Available For</th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                  <template v-for="myclass in classes">
                    <tr :key="myclass.id">
                      <td>{{myclass.name}}</td>
                      <td>{{myclass.is_live == 1 ? 'Online' : 'Offline'}}</td>
                      <td>{{myclass.available_for_groups ? myclass.available_for_groups : '-'}}</td>
                      <td>
                        <button type="button" rel="tooltip" class="btn btn-primary btn-icon btn-sm btn-simple" @click="goToStream(myclass.name)" data-original-title="Enter LiveStream" title="Enter LiveStream">
                          <i class="fa fa-briefcase pt-1"></i>
                        </button>
                        <button v-if="userDetails.role == 3" type="button" rel="tooltip" class="btn btn-primary btn-icon btn-sm btn-simple" @click="showStreamSettings(myclass.name)"  data-original-title="Stream Settings" title="Stream Settings">
                          <i class="fa fa-gears pt-1"></i>
                        </button>
                        <button v-if="userDetails.role == 3 && myclass.is_live == 1" type="button" rel="tooltip" class="btn btn-primary btn-icon btn-sm btn-simple" @click="stopStream(myclass.name)"  data-original-title="Stop Stream" title="Stop Stream">
                          <i class="fa fa-window-close pt-1"></i>
                        </button>
                      </td>
                    </tr>
                  </template>
                </tbody>
              </table>

            </div>
            <div class="mt-5 py-5 border-top text-center">
              <div class="row justify-content-center">
              </div>
            </div>
          </div>
        </card>
      </div>
    </section>
    <modal :show.sync="showModal">
      <template slot="header">
        <h5 class="modal-title" id="exampleModalLabel">Stream settings for <b>{{courseName}}</b></h5>
      </template>
      <div>
        <label for="groups">Enter stream groups:</label>
        <input type="text" class="form-control" id="groups" placeholder="B1, B2, ..." v-model="groupsList">
      </div>
      <template slot="footer">
        <base-button type="secondary" @click="showModal = false">Close</base-button>
        <base-button type="primary" @click="startStream()">Start Stream</base-button>
      </template>
    </modal>
    <modal :show.sync="showErrorModal"
           gradient="danger"
           modal-classes="modal-danger modal-dialog-centered">
      <h6 slot="header" class="modal-title" id="modal-title-notification">Invalid group</h6>

      <div class="py-3 text-center">
        <i class="ni ni-bell-55 ni-3x"></i>
        <h4 class="heading mt-4">Something went wrong!</h4>
        <p>You are not part of the groups that this stream is started for.</p>
      </div>

      <template slot="footer">
        <base-button type="white" @click="showErrorModal = false">Ok, Got it</base-button>
      </template>
    </modal>
  </div>
</template>

<script>
import axios from 'axios';
import "../assets/vendor/font-awesome/css/font-awesome.css";
import Modal from "../components/Modal";

export default {
  name: "classes",
  components: {modal: Modal},
  data() {
    return {
      classes: [],
      userDetails: {},
      showModal: false,
      courseName: '',
      groupsList: '',
      showErrorModal: false,
    }
  },
  methods: {
    goToStream(courseName) {
      let self = this;
      var groupFound = false;
      if(this.userDetails.role != 3) {
        this.classes.forEach(function(item, index) {
          if(item.name == courseName) {
            if(item.available_for_groups.indexOf(self.userDetails.study_group) !== -1) {
              groupFound = true
            }
          }
        });
        if(groupFound) {
          this.$router.push({ name: 'stream', query: {stream: courseName}});
        } else {
          this.showErrorModal = true;
        }
      } else {
        this.$router.push({ name: 'stream', query: {stream: courseName}});
      }
    },
    showStreamSettings(courseName) {
      this.courseName = courseName;
      this.showModal = true;
    },
    startStream() {
      var self = this;
      axios.post('/api/start-stream', {courseName: this.courseName, groupsList: this.groupsList}, {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
        if(response.data == 'success') {
          self.showModal = false;
          self.getTeacherData();
        }

      });
    },
    stopStream(courseName) {
      var self = this;
      axios.post('/api/stop-stream', {courseName: courseName}, {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
        if(response.data == 'success') {
          self.getTeacherData();
        }
      });
    },
    getTeacherData() {
      var self = this;
      axios.post('/api/get-teacher-courses', {userId: self.userDetails.id}, {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
        self.classes = response.data;
      })
    }
  },
  created() {
    var self = this;
    this.userDetails = JSON.parse(localStorage.getItem('user_details'))
    var userRole = this.userDetails.role;

    if(userRole === 2) {
      axios.get('/api/get-courses', {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
        self.classes = response.data;
      })
    } else if(userRole === 3){
      axios.post('/api/get-teacher-courses', {userId: self.userDetails.id}, {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
        self.classes = response.data;
      })
    }

  }

}
</script>
