<?php

require_once 'DBHandler.php';

class Login {
    public $conn;

    function __construct()
    {
        $db = new DBHandler();
        $db->makeConnection();
        $this->conn = $db->conn;
    }

    public function makeAuth($email, $password) {
        $sql = "SELECT * from users";
        $result = $this->conn->query($sql);

        if($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                if($row['email'] == $email && $row['password'] == $password) {
                    return ['success' => true, 'first_name' => $row['first_name'], 'last_name' => $row['last_name']];
                }
            }
        }
    }
}
