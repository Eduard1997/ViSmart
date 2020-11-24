<?php

class DBHandler {
    public $conn;

    public function makeConnection() {
        $servername = "localhost";
        $username = "root";
        $password = "";
        $dbname = "vismart";

        $conn = new mysqli($servername, $username, $password, $dbname);
        if ($conn->connect_error) {
            die("Connection failed: " . $conn->connect_error);
        }
        $this->conn = $conn;
    }
}
