<?php

require_once 'Login.php';

$data = json_decode(file_get_contents('php://input'), true);
if(!empty($data['email']) && !empty($data['password'])) {
    $email = $data['email'];
    $password = $data['password'];
}
$login = new Login();
$isLogged = $login->makeAuth($email, $password);

if($isLogged['success'] == true)
    echo json_encode($isLogged);
else
    echo json_encode($isLogged);

