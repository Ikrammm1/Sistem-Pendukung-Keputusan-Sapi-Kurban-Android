<?php

require_once('koneksi.php');

$username = $_POST['username'];
$querySelect = mysqli_query($koneksi, "SELECT * FROM tb_user WHERE username = '$username'");

if($querySelect !="null"){
$password = $_POST['password'];

$queryUpdate = "UPDATE tb_user SET
    password = '$password'
    WHERE username = '$username'";
    
$Update = mysqli_query($koneksi, $queryUpdate);

if($Update){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
}else{
    $data["message"] = "data is null";
    $data["status"] = "nodata";
}

echo json_encode($data);




header('Content-Type: application/json')
?>