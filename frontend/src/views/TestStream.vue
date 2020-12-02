<template>
    <div>
        <!-- Other person's camera video will show up here -->
        <div>
            <h3 style="margin: 5px">Other Person</h3>
            <video style="width: 50vh; height: 50vh;" id="remoteVideo"
                   poster="https://img.icons8.com/fluent/48/000000/person-male.png" autoplay></video>
        </div>

        <!-- Your camera video will show up here. -->
        <div>
            <h3 style="margin: 5px">You</h3>
            <video style="width: auto; height: 20vh;" id="localVideo"
                   poster="https://img.icons8.com/fluent/48/000000/person-male.png" autoplay muted></video>
        </div>

        <!-- Button to leave video conference. -->
        <div class="box">
            <button id="leaveButton" style="background-color: #008CBA; color: white; " @click="leave()">Leave Video Conference</button>
        </div>
    </div>
</template>

<script>
    export default {
        name: "TestStream",
        data() {
            return {
                peerConnection: '',
                signalingWebsocket: ''
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
                    alert(`getUserMedia() error: ${e.name}`);
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
                    alert("Error creating an offer");
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
                    alert("Error creating an answer");
                });
            },
            handleAnswer(answer) {
                this.peerConnection.setRemoteDescription(new RTCSessionDescription(
                    answer));
                console.log("connection established successfully!!");
            },
            handleCandidate(candidate) {
                alert("handleCandidate");
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
            }
        },
        mounted() {
            let self = this;
                console.log(window.location.host +
                    "/video-conf-tutorial/signal")
                self.signalingWebsocket = new WebSocket("ws://" + window.location.host +
                    "/video-conf-tutorial/signal");
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