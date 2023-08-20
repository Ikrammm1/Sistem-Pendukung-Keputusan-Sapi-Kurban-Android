<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];


$query = "DELETE FROM tb_datasapi where id_user = '$id_user'";
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