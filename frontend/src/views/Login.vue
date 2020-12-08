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
                                <base-input alternative
                                            class="mb-3"
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
                                <base-checkbox>
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
export default {
  name:"login",
  data() {
    return {
      email: '',
      password: ''
    }
  },
  methods: {
    login() {
      var self = this;
      axios.post("/api/user", {email: this.email, password: this.password}).then(function(response) {
        if(response.data.username) {
          self.$router.push({ name: 'profile', params: {firstName: response.data.first_name, lastName: response.data.last_name, userId: response.data.id, loggedIn: true}});
        }
      })
    }
  }
};
</script>
<style>
</style>
