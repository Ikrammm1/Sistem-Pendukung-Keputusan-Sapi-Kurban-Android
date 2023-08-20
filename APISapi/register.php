<?php

require_once('koneksi.php');

$nama_user = $_POST['nama_user'];
$username = $_POST['username'];
$password = $_POST['password'];



$cekuser = mysqli_query($koneksi, "SELECT * From tb_user Where username = '$username'");
if($cekuser == "NULL"){
    $query = "INSERT INTO tb_user (nama_user, username, password, level) VALUES ('$nama_user', '$username', '$password', 'user')";
    $sql = mysqli_query($koneksi, $query);
    if($sql){
        $data["message"] = "data saved successfully";
        $data["status"] = "Ok";
    } else {
        $data["message"] = "data not saved successfully";
        $data["status"] = "error";   
    }
}else{
     $data["message"] = "data Already";
        $data["status"] = "Ada";
}

echo json_encode($data);

?>