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
                                    <base-button type="info" size="sm" class="mr-4" @click="viewClasses">View classes</base-button>
                                    <base-button type="default" size="sm" class="float-right">View Messages</base-button>
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
                            <h3>{{userDetails.first_name}} {{userDetails.last_name}}
                                <span class="font-weight-light">, 27</span>
                            </h3>
                            <div class="h6 font-weight-300"><i class="ni location_pin mr-2"></i>Iasi, Romania</div>
                            <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Distributed Systems Master</div>
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
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: "profile",
    data() {
        return {
            userDetails: {}
        }
    },
  methods: {
    viewClasses() {
      this.$router.push({ name: 'classes', params: {loggedIn: true, role: this.userDetails.role, userId: this.userDetails.id}});
    }
  },
  created() {
        let self = this;
        this.$route.params.loggedIn = true;
        axios.post('/api/get-user-details', {userId: this.$route.params.userId}).then(function(response) {
            console.log(response.data);
            self.userDetails = response.data;
        });
    }
};
</script>

<style>
</style>
