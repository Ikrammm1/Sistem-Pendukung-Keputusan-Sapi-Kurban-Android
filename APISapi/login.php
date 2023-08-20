<?php
//koneksi
require_once 'koneksi.php';

//ambil data yang dikirim dari android
$username = $_POST["username"];
$password = $_POST['password'];


//proses periksa username dan password di DB
$query = "SELECT * FROM tb_user WHERE username='$username' AND password = '$password'";
$obj_query = mysqli_query($koneksi, $query);
$data = mysqli_fetch_array($obj_query);
//periksa apakah login sudah benar
if($data){
    echo json_encode(
        array(
            'response' => true,
            'payload' => array(
                "id_user" => $data["id_user"],
                "nama_user" => $data["nama_user"],
                "username" => $data["username"],
                "password" => $data["password"],
                "level" => $data["level"]
                
                )
        )
            );
} else {
    echo json_encode(
        array(
            'response' => false,
            'payload' => null
        )
        );
}



// mengatur tampilan json

header('Content-Type: application/json')

?>