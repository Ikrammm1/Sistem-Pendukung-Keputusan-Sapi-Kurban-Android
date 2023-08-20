<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];
$alternatif = $_POST['alternatif'];
$berat = $_POST['berat'];
$kecacatan = $_POST['kecacatan'];
$perilaku = $_POST['perilaku'];
$umur = $_POST['umur'];
$jenis_kelamin = $_POST['jenis_kelamin'];

//merubah berat menjadi bobot kriteria C1
if($berat< 200){
    $C1 = 0.2;
}elseif($berat>=200 && $berat<=300){
    $C1 = 0.7;
}elseif($berat>300){
    $C1 = 1;
}

//merubah kecacatan menjadi bobot kriteria C2
if($kecacatan =="Cacat"){
    $C2 = 0.01;
}elseif($kecacatan=="Tidak Cacat"){
    $C2 = 1;
}
//merubah perilaku menjadi bobot kriteria C3
if($perilaku=="Tidak Normal"){
    $C3 = 0.01;
}elseif($perilaku=="Normal"){
    $C3 = 1;
}

//merubah umur menjadi bobot kriteria C4
if($umur< 2){
    $C4 = 0.01;
}elseif($umur>=2 && $umur<=3){
    $C4 = 1;
}elseif($umur>3){
    $C4 = 0.3;
}

//merubah jenis_kelamin menjadi bobot kriteria C3
if($jenis_kelamin=="Betina"){
    $C5 = 0.5;
}elseif($jenis_kelamin=="Jantan"){
    $C5 = 1;
}

$queryinputsapi = "INSERT INTO tb_datasapi (id_user, alternatif,berat,C1, kecacatan,C2, perilaku,C3, umur,C4, jenis_kelamin,C5) 
VALUES ('$id_user','$alternatif','$berat','$C1', '$kecacatan','$C2', '$perilaku','$C3', '$umur','$C4', '$jenis_kelamin','$C5')";
$inputsapi = mysqli_query($koneksi, $queryinputsapi);

if($inputsapi){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
echo json_encode($data);



header('Content-Type: application/json')
?>