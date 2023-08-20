<?php
require_once('koneksi.php');

$sqlkriteria = mysqli_query($koneksi, "SELECT * FROM tb_kriteria");
//kriteria
$result = array(); 
while($kriteria  = mysqli_fetch_array($sqlkriteria)) {
    $W = ($kriteria['bobot']/100);
    

array_push($result, array(
    'id_kriteria'   =>$kriteria['id_kriteria'],
    'kriteria'      =>$kriteria['kriteria'],
    'sifat'         =>$kriteria['sifat'],
    'bobot'         =>$kriteria['bobot']

 ));

}echo json_encode ( array('Kriteria' =>$result));
header('Content-Type: application/json')
?>