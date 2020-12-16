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
                            <!--<div class="col-lg-3 order-lg-2">
                                <div class="card-profile-image">
                                    <video style="width: auto; height: 20vh;" id="localVideo"
                                           poster="https://img.icons8.com/fluent/48/000000/person-male.png" autoplay muted></video>
                                </div>
                            </div>-->
                            <div class="col-lg-4 order-lg-3 text-lg-right align-self-lg-center">
                                <div class="card-profile-actions py-4 mt-lg-0">
                                </div>
                            </div>
                            <!--<div class="col-lg-4 order-lg-1">
                                <div class="card-profile-stats d-flex justify-content-center">
                                    <div>
                                        <span class="heading">10</span>
                                        <span class="description">Materials</span>
                                    </div>
                                </div>
                            </div>-->
                        </div>
                        <div class="text-center" style="position: relative">
                            <div class="">
                                <div class="card-profile-stats" style="position:absolute; bottom: 35px;">
                                    <div>
                                        <span class="heading">10</span>
                                        <span class="description">Materials</span>
                                    </div>
                                </div>
                                <h3>Live Stream for {{this.$route.query.stream}}</h3>
                                <div class="h6 mt-4"><i class="ni business_briefcase-24 mr-2"></i>Distributed Systems Master</div>
                                <div><i class="ni education_hat mr-2"></i>Faculty of Computer Science Iasi Romania</div>
                            </div>

                        </div>
                        <div class="row justify-content-center mt-4">
                            <div class="col-lg-3 order-lg-2">
                                <div class="card-profile-image">
                                    <video style="width: auto; height: 20vh;" id="localVideo"
                                           poster="https://img.icons8.com/fluent/48/000000/person-male.png" autoplay muted></video>
                                </div>
                            </div>
                        </div>
                        <div class="mt-5 py-5 border-top text-center">
                            <div class="row justify-content-center">
                                <video style="width: 50vh; height: 50vh;" id="remoteVideo"
                                       poster="https://img.icons8.com/fluent/48/000000/person-male.png" autoplay v-if="streamOnline"></video>
                                <img v-lazy="'img/project_images/offline_img.jpg'" class="rounded-circle" v-if="!streamOnline">
                            </div>
                        </div>
                    </div>
                </card>
            </div>
        </section>
    </div>
</template>

<script>
    import axios from "axios";
    export default {
        name: "Stream",
        data() {
            return {
                peerConnection: '',
                signalingWebsocket: '',
                streamOnline: false,
            }
        },
        methods: {
            leave() {
                console.log('Ending call');
                this.peerConnection.close();
                this.signalingWebsocket.close();
                //window.location.href = './index.html';
            },
            sendSignal(signal) {
                if (this.signalingWebsocket.readyState === 1) {
                    this.signalingWebsocket.send(JSON.stringify(signal));
                }
            },
            init() {
                console.log("Connected to signaling endpoint. Now initializing.");
                this.preparePeerConnection();
                this.displayLocalStreamAndSignal(true);

            },
            preparePeerConnection() {
                let self = this;
                // Using free public google STUN server.
                const configuration = {
                    iceServers: [{
                        urls: 'stun:stun.l.google.com:19302'
                    }]
                };

                // Prepare peer connection object
                this.peerConnection = new RTCPeerConnection(configuration);
                this.peerConnection.onnegotiationneeded = async () => {
                    console.log('onnegotiationneeded');
                    self.sendOfferSignal();
                };
                this.peerConnection.onicecandidate = function(event) {
                    if (event.candidate) {
                        self.sendSignal(event);
                    }
                };

                /*
                 * Track other participant's remote stream & display in UI when available.
                 *
                 * This is how other participant's video & audio will start showing up on my
                 * browser as soon as his local stream added to track of peer connection in
                 * his UI.
                 */
                this.peerConnection.addEventListener('track', self.displayRemoteStream);

            },
            async displayLocalStreamAndSignal(firstTime) {
                let self = this;
                console.log('Requesting local stream');
                const localVideo = document.getElementById('localVideo');
                let localStream;
                try {
                    // Capture local video & audio stream & set to local <video> DOM
                    // element
                    const stream = await navigator.mediaDevices.getUserMedia({
                        audio: true,
                        video: true
                    });
                    console.log('Received local stream');
                    localVideo.srcObject = stream;
                    localStream = stream;
                    this.logVideoAudioTrackInfo(localStream);

                    // For first time, add local stream to peer connection.
                    if (firstTime) {
                        setTimeout(
                            function() {
                                self.addLocalStreamToPeerConnection(localStream);
                            }, 2000);
                    }

                    // Send offer signal to signaling server endpoint.
                    self.sendOfferSignal();

                } catch (e) {
                    console.log(`getUserMedia() error: ${e.name}`);
                    throw e;
                }
                console.log('Start complete');
            },
            async addLocalStreamToPeerConnection(localStream) {
                let self = this;
                console.log('Starting addLocalStreamToPeerConnection');
                localStream.getTracks().forEach(track => self.peerConnection.addTrack(track, localStream));
                console.log('localStream tracks added');
            },

            displayRemoteStream(e) {
                console.log('displayRemoteStream');
                const remoteVideo = document.getElementById('remoteVideo');
                if (remoteVideo.srcObject !== e.streams[0]) {
                    remoteVideo.srcObject = e.streams[0];
                    console.log('pc2 received remote stream');
                }
            },
            sendOfferSignal() {
                let self = this;
                this.peerConnection.createOffer(function(offer) {
                    self.sendSignal(offer);
                    self.peerConnection.setLocalDescription(offer);
                }, function(error) {
                    console.log("Error creating an offer");
                });
            },
            handleOffer(offer) {
                let self = this;
                this.peerConnection
                    .setRemoteDescription(new RTCSessionDescription(offer));

                // create and send an answer to an offer
                this.peerConnection.createAnswer(function(answer) {
                    self.peerConnection.setLocalDescription(answer);
                    self.sendSignal(answer);
                }, function(error) {
                    console.log("Error creating an answer");
                });
            },
            handleAnswer(answer) {
                this.peerConnection.setRemoteDescription(new RTCSessionDescription(
                    answer));
                console.log("connection established successfully!!");
            },
            handleCandidate(candidate) {
                console.log("handleCandidate");
                this.peerConnection.addIceCandidate(new RTCIceCandidate(candidate));
            },
            logVideoAudioTrackInfo(localStream) {
                const videoTracks = localStream.getVideoTracks();
                const audioTracks = localStream.getAudioTracks();
                if (videoTracks.length > 0) {
                    console.log(`Using video device: ${videoTracks[0].label}`);
                }
                if (audioTracks.length > 0) {
                    console.log(`Using audio device: ${audioTracks[0].label}`);
                }
            },
        },
        created() {
            var self = this;
            axios.post('/api/check-stream-availability', {streamName: this.$route.query.stream}, {headers:{'Authorization':localStorage.getItem('vismart_jwt_token')}}).then(function(response) {
                self.streamOnline = response.data == 0 ? false : true;
            })
        },
        mounted() {
            let self = this;
            console.log(window.location.host +
                "/api/signal")
            self.signalingWebsocket = new WebSocket("ws://" + window.location.host +
                "/api/signal");
            self.signalingWebsocket.onmessage = function(msg) {
                console.log("Got message", msg.data);
                var signal = JSON.parse(msg.data);
                switch (signal.type) {
                    case "offer":
                        self.handleOffer(signal);
                        break;
                    case "answer":
                        self.handleAnswer(signal);
                        break;
                    // In local network, ICE candidates might not be generated.
                    case "candidate":
                        self.handleCandidate(signal);
                        break;
                    default:
                        break;
                }
            };
            self.signalingWebsocket.onopen = self.init();
        }
    }
</script>

<style scoped>

</style>