<?php

require_once('koneksi.php');

$id_datasapi = $_POST['id_datasapi'];


$query = "DELETE FROM tb_datasapi where id_datasapi = '$id_datasapi'";
$sql = mysqli_query($koneksi, $query);

if($sql){
    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";
} else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
echo json_encode($data);

?>