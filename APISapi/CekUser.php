<?php 
require_once('koneksi.php');
$username = $_POST['username'];
$querySelect = mysqli_query($koneksi, "SELECT * FROM tb_user WHERE username = '$username'");

if($querySelect->num_rows !=0){
    $data["message"] = "data is already";
    $data["status"] = "ok";
}else{
    $data["message"] = "data is null";
    $data["status"] = "nodata";
}
echo json_encode($data);




header('Content-Type: application/json')
?>