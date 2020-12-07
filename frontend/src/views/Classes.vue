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
              <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Distributed Systems Master</div>
              <div><i class="ni education_hat mr-2"></i>Faculty of Computer Science Iasi Romania</div>

              <table class="table mt-2">
                <thead>
                <tr>
                  <th>Class name</th>
                  <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                  <template v-for="myclass in classes">
                    <tr>
                      <td>{{myclass.name}}</td>
                      <td>
                        <button type="button" rel="tooltip" class="btn btn-primary btn-icon btn-sm btn-simple" data-original-title="" title="">
                        <i class="ni ni-circle-08 pt-1"></i>
                      </button></td>
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
  </div>
</template>

<script>
import axios from 'axios';
export default {
name: "classes",
  data() {
    return {
      classes: []
    }
  },
  created() {
    var self = this;
    this.$route.params.loggedIn = true;
    axios.post('/api/get-classes').then(function(response) {
      console.log(response.data);
      self.classes = response.data;
    })
  }

}
</script>


