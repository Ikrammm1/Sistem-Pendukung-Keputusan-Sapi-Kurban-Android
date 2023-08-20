<?php

require_once('koneksi.php');

$id_datasapi = $_POST['id_datasapi'];
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
if($perilaku=="Tidak Baik"){
    $C3 = 0.01;
}elseif($perilaku=="Baik"){
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

$queryUpdatesapi = "UPDATE tb_datasapi SET alternatif = '$alternatif',
    berat = '$berat',
    C1 ='$C1', 
    kecacatan = '$kecacatan',
    C2 = '$C2', 
    perilaku = '$perilaku',
    C3 = '$C3', 
    umur='$umur',
    C4='$C4', 
    jenis_kelamin='$jenis_kelamin',
    C5='$C5', 
    status_hitung='Belum' 
    WHERE id_datasapi = '$id_datasapi'";
    
$Updatesapi = mysqli_query($koneksi, $queryUpdatesapi);

if($Updatesapi){

    $data["message"] = "data saved successfully";
    $data["status"] = "Ok";

}else {
    $data["message"] = "data not saved successfully";
    $data["status"] = "error";   
}
echo json_encode($data);



header('Content-Type: application/json')
?>