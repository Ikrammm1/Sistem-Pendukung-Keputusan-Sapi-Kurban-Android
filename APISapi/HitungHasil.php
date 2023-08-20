<?php

require_once('koneksi.php');

$id_user = $_POST['id_user'];


    $sqlsapi = mysqli_query($koneksi, 
    "SELECT 
        *
    FROM tb_datasapi where id_user = '$id_user'");

    $sqlnilaisapi = mysqli_query($koneksi, 
    "SELECT 
        *, 
        MAX(tb_datasapi.C1) as maxC1,
        MAX(tb_datasapi.C2) as maxC2,
        MAX(tb_datasapi.C3) as maxC3,
        MAX(tb_datasapi.C4) as maxC4,
        MIN(tb_datasapi.C5) as minC5
    FROM tb_datasapi where id_user = '$id_user'");


    //data sapi
    while($sapi  = mysqli_fetch_array($sqlsapi)) :

    while($nilaisapi  = mysqli_fetch_array($sqlnilaisapi)) :

    $MAX1 = $nilaisapi['maxC1'];
    $MAX2 = $nilaisapi['maxC2'];
    $MAX3 = $nilaisapi['maxC3'];
    $MAX4 = $nilaisapi['maxC4'];
    $MIN5 = $nilaisapi['minC5'];

    endwhile;
    //perhitungan normalisasi data
        $AC1 = ( $sapi['C1']/$MAX1);
        $AC2 = ( $sapi['C2']/$MAX2);
        $AC3 = ( $sapi['C3']/$MAX3);
        $AC4 = ( $sapi['C4']/$MAX4);
        $AC5 = ( $MIN5/$sapi['C5']);

        //perhitungan nilai ranking
        $N = round((($AC1*0.2)+($AC2*0.2)+($AC3*0.15)+($AC4*0.4)+($AC5*0.05)), 3);
        $NR = round($N, 2);

        if($NR > 0.9){
            $kesimpulan = "Sapi Sah dan Sangat Layak";
        }elseif($NR >0.7 && $NR <=0.9){
            $kesimpulan = "Sapi Sah dan Layak";
        }elseif($NR <=0.7){
            $kesimpulan = "Sapi Tidak Layak";
        }

    $sqlhasil = mysqli_query($koneksi, "SELECT * from tb_hasil WHERE id_datasapi = '$sapi[id_datasapi]'");
        
        if($sqlhasil->num_rows > 0){
            $querySimpan = "UPDATE tb_hasil SET nilai = '$NR', kesimpulan = '$kesimpulan' WHERE id_datasapi = '$sapi[id_datasapi]'";
        }else{
            $querySimpan = "INSERT INTO tb_hasil (id_datasapi, nilai, kesimpulan) VALUES('$sapi[id_datasapi]', '$NR', '$kesimpulan')";
        }
        $queryHasil = mysqli_query($koneksi, $querySimpan);
        $queryStat = "UPDATE tb_datasapi SET status_hitung = 'Selesai' WHERE id_datasapi = '$sapi[id_datasapi]'";
        $queryUpdate = mysqli_query($koneksi, $queryStat);


    endwhile;
    if($queryHasil){
        if($queryStat){
             $data["message"] = "data saved successfully";
            $data["status"] = "Ok";
        }else {
            $data["message"] = "data not saved successfully";
            $data["status"] = "error";   
        }
    }else {
        $data["message"] = "data not saved successfully";
        $data["status"] = "error";   
    }
echo json_encode($data);



header('Content-Type: application/json')
?>