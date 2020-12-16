<template>
    <section class="section section-shaped section-lg my-0">
        <div class="shape shape-style-1 bg-gradient-default">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            <span></span>
        </div>
        <div class="container pt-lg-md">
            <div class="row justify-content-center">
                <div class="col-lg-5">
                    <card type="secondary" shadow
                          header-classes="bg-white pb-5"
                          body-classes="px-lg-5 py-lg-5"
                          class="border-0">

                        <template>
                            <form role="form">
                                <span class="error-text">Invalid username or password</span>
                                <base-input alternative
                                            class="mb-3 is-invalid"
                                            placeholder="Email"
                                            addon-left-icon="ni ni-email-83"

                                v-model="email">
                                </base-input>
                                <base-input alternative
                                            type="password"
                                            placeholder="Password"
                                            addon-left-icon="ni ni-lock-circle-open"
                                v-model="password">
                                </base-input>
                                <base-checkbox v-model="remember">
                                    Remember me
                                </base-checkbox>
                                <div class="text-center">
                                    <base-button type="primary" class="my-4" @click="login">Sign In</base-button>
                                </div>
                            </form>
                        </template>
                    </card>
                    <div class="row mt-3">
                        <div class="col-6">
                            <a href="#" class="text-light">
                                <small>Forgot password?</small>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>
<script>
import axios from 'axios';
import $ from 'jquery';

export default {
  name:"login",
  data() {
    return {
        email: '',
        password: '',
        remember: false
    }
  },
  methods: {
    login() {
      var self = this;
      axios.get("/api/login", {params: {'email':this.email,'password':this.password},headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).then(function(response) {
          if(response.data) {
            localStorage.setItem('remember', self.remember === true);
            localStorage.setItem('vismart_jwt_token', response.data);
          axios.get("/api/get-user-details",{headers:{'Authorization':response.data}}).then(function(response){
              localStorage.setItem('user_details', JSON.stringify(response.data));
          self.$router.push({ name: 'profile'});
          });
        }
      }).catch(function(error) {
        $('.error-text').show();
        $('input').addClass('error-input');
      })
    }
  }
};
</script>
<style>
    .error-input {
        border: 2px solid red !important;
        padding-left: 6px !important;
    }
    .error-text {
        color: red;
        display: none;
    }
</style>
