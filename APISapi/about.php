<?php
require_once('koneksi.php');

$sqlabout = mysqli_query($koneksi, "SELECT * FROM tb_about");
//about
$result = array(); 
while($about  = mysqli_fetch_array($sqlabout)) {
    echo json_encode ( array(
    'id_about'   =>$about['id_about'],
    'about'      =>$about['about']
));

}
header('Content-Type: application/json')
?>