<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];

$sqlsapi = mysqli_query($koneksi, 
"SELECT 
    *
FROM tb_datasapi
Where tb_datasapi.status_hitung = 'Belum'
AND tb_datasapi.id_user = '$id_user'");

$result = array(); 

while($sapi  = mysqli_fetch_array($sqlsapi)) :
array_push($result, array(
    'id_datasapi'       => $sapi['id_datasapi'],
    'alternatif'       => $sapi['alternatif'],
    'berat'         =>$sapi['berat'],
    'kecacatan'     =>$sapi['kecacatan'],
    'perilaku'      =>$sapi['perilaku'],
    'umur'          => $sapi['umur'],
    'jenis_kelamin' => $sapi['jenis_kelamin'],
    'C1'            => $sapi['C1'],
    'C2'            => $sapi['C2'],
    'C3'            => $sapi['C3'],
    'C4'            => $sapi['C4'],
    'C5'            => $sapi['C5'],
    
));

endwhile;
echo json_encode ( array('Datasapi' =>$result));
header('Content-Type: application/json')
?>