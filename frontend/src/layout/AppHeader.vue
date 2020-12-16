<template>
    <header class="header-global">
        <base-nav class="navbar-main" transparent type="" effect="light" expand style="top: 30px;">
            <router-link slot="brand" class="navbar-brand mr-lg-5" to="/">
                <img src="img/logos/default.png" alt="logo"
                     style="position: absolute; left: 450px; bottom: -47px; height: 187px;" class="logo-img">
            </router-link>

            <div class="row" slot="content-header" slot-scope="{closeMenu}">
                <div class="col-6 collapse-brand">
                    <a href="https://demos.creative-tim.com/vue-argon-design-system/documentation/">
                        <img src="img/brand/blue.png">
                    </a>
                </div>
                <div class="col-6 collapse-close">
                    <close-button @click="closeMenu"></close-button>
                </div>
            </div>

            <!--<ul class="navbar-nav navbar-nav-hover align-items-lg-center">
                <base-dropdown class="nav-item" menu-classes="dropdown-menu-xl">
                    <a slot="title" href="#" class="nav-link" data-toggle="dropdown" role="button">
                        <i class="ni ni-ui-04 d-lg-none"></i>
                        <span class="nav-link-inner&#45;&#45;text">Components</span>
                    </a>
                    <div class="dropdown-menu-inner">
                        <a href="https://demos.creative-tim.com/vue-argon-design-system/documentation/"
                           class="media d-flex align-items-center">
                            <div class="icon icon-shape bg-gradient-primary rounded-circle text-white">
                                <i class="ni ni-spaceship"></i>
                            </div>
                            <div class="media-body ml-3">
                                <h6 class="heading text-primary mb-md-1">Getting started</h6>
                                <p class="description d-none d-md-inline-block mb-0">Get started with Bootstrap, the
                                    world's most popular framework for building responsive sites.</p>
                            </div>
                        </a>
                        <a href="https://demos.creative-tim.com/vue-argon-design-system/documentation/"
                           class="media d-flex align-items-center">
                            <div class="icon icon-shape bg-gradient-warning rounded-circle text-white">
                                <i class="ni ni-ui-04"></i>
                            </div>
                            <div class="media-body ml-3">
                                <h5 class="heading text-warning mb-md-1">Components</h5>
                                <p class="description d-none d-md-inline-block mb-0">Learn how to use Argon
                                    compiling Scss, change brand colors and more.</p>
                            </div>
                        </a>
                    </div>
                </base-dropdown>
                <base-dropdown tag="li" class="nav-item">
                    <a slot="title" href="#" class="nav-link" data-toggle="dropdown" role="button">
                        <i class="ni ni-collection d-lg-none"></i>
                        <span class="nav-link-inner&#45;&#45;text">Examples</span>
                    </a>
                    <router-link to="/landing" class="dropdown-item">Landing</router-link>
                    <router-link to="/profile" class="dropdown-item">Profile</router-link>
                    <router-link to="/login" class="dropdown-item">Login</router-link>
                    <router-link to="/register" class="dropdown-item">Register</router-link>
                </base-dropdown>
            </ul>-->
            <ul class="navbar-nav align-items-lg-center ml-lg-auto">
                <!--<li class="nav-item">
                    <a class="nav-link nav-link-icon" href="https://www.facebook.com/creativetim" target="_blank" rel="noopener"
                       data-toggle="tooltip" title="Like us on Facebook">
                        <i class="fa fa-facebook-square"></i>
                        <span class="nav-link-inner&#45;&#45;text d-lg-none">Facebook</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-icon" href="https://www.instagram.com/creativetimofficial"
                       target="_blank" rel="noopener" data-toggle="tooltip" title="Follow us on Instagram">
                        <i class="fa fa-instagram"></i>
                        <span class="nav-link-inner&#45;&#45;text d-lg-none">Instagram</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-icon" href="https://twitter.com/creativetim" target="_blank" rel="noopener"
                       data-toggle="tooltip" title="Follow us on Twitter">
                        <i class="fa fa-twitter-square"></i>
                        <span class="nav-link-inner&#45;&#45;text d-lg-none">Twitter</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link nav-link-icon" href="https://github.com/creativetimofficial/vue-argon-design-system"
                       target="_blank" rel="noopener" data-toggle="tooltip" title="Star us on Github">
                        <i class="fa fa-github"></i>
                        <span class="nav-link-inner&#45;&#45;text d-lg-none">Github</span>
                    </a>
                </li>-->
                <li class="nav-item d-none d-lg-block ml-lg-4">
                    <base-button v-if="!isLogged && !landingPage && currentPage != '/login'" type="secondary" icon="ni ni-user-run" @click="goToLogin('login')">
                        Login
                    </base-button>
                    <base-button v-if="isLogged && !landingPage && currentPage != '/login'" type="secondary" icon="ni ni-user-run" @click="goToLogin('logout')">
                        Logout
                    </base-button>
                    <base-button v-if="isLogged && landingPage && currentPage != '/login'" type="secondary" icon="ni ni-user-run" @click="goToProfile()">
                        Profile
                    </base-button>
                    <base-button v-if="isLogged && !landingPage && currentPage != '/login'" type="secondary" icon="fa fa-home" @click="goToHome()">
                        Home
                    </base-button>
                </li>
            </ul>
        </base-nav>
    </header>
</template>
<script>
    import BaseNav from "@/components/BaseNav";
    import BaseDropdown from "@/components/BaseDropdown";
    import CloseButton from "@/components/CloseButton";

    export default {
        components: {
            BaseNav,
            CloseButton,
            BaseDropdown
        },
        name: 'ApplicationHeader',
        data() {
            return {
                isLogged: false,
                landingPage: false,
                currentPage: ''
            }
        },
        methods: {
            goToLogin(parameter) {
                if (parameter == 'logout') {
                    localStorage.removeItem('vismart_jwt_token');
                    localStorage.removeItem('user_details');
                    localStorage.removeItem('remember');
                    this.isLogged = false;
                }
                this.$router.push({name: 'login'});
            },
            goToProfile() {
                this.$router.push({name: 'profile'});
            },
            goToHome() {
                this.$router.push({name: 'components'});
            }
        },
        created() {
            this.isLogged = false;
            this.currentPage = this.$route.path;
            if (localStorage.getItem('vismart_jwt_token')) {
                this.isLogged = true;
            } else {
                this.isLogged = false;
            }
            if(this.$route.path == '/' && localStorage.getItem("vismart_jwt_token") != null) {
                this.landingPage = true;
            } else {
                this.landingPage = false;
            }
            if(this.$route.path == '/login') {
                document.querySelector('.logo-img').classList.add('bottom-reduced');
            } else {
                document.querySelector('.logo-img').classList.remove('bottom-reduced');
            }
        },
        mounted() {
            this.currentPage = this.$route.path;
            this.$root.$on('logged-in', (text) => { // here you need to use the arrow function
                this.isLogged = true;
            })
            if(this.$route.path == '/' && localStorage.getItem("vismart_jwt_token")) {
                this.landingPage = true;
            } else {
                this.landingPage = false;
            }
            if(this.$route.path == '/login') {
                document.querySelector('.logo-img').classList.add('bottom-reduced');
            } else {
                document.querySelector('.logo-img').classList.remove('bottom-reduced');
            }
        },
        watch: {
            'isLogged': function () {
                if (localStorage.getItem("vismart_jwt_token")) {
                    this.isLogged = true;
                } else {
                    this.isLogged = false;
                }
            },
            '$route.path': function() {
                this.currentPage = this.$route.path;
                if(this.$route.path == '/' && localStorage.getItem("vismart_jwt_token")) {
                    this.landingPage = true;
                } else {
                    this.landingPage = false;
                }
                if(this.$route.path == '/login') {
                    document.querySelector('.logo-img').classList.add('bottom-reduced');
                } else {
                    document.querySelector('.logo-img').classList.remove('bottom-reduced');
                }
            }
        }
    };
</script>
<style>
    .bottom-reduced {
        bottom: -70px !important;
    }
    .fa-home {
        font-size: 18px !important;
    }
</style>
