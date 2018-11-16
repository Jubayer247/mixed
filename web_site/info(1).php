<?php
$request=$_POST['request'];
$db = mysqli_connect('sql304.epizy.com', 'epiz_22800649', 'LMxl6o8YDN', 'epiz_22800649_request');
$sql = "INSERT INTO `request`(`request`, `station_id`, `checked`) VALUES ('{$request}','1',0)";    
mysqli_query($db, $sql);
mysqli_close($db);
 exit();
?>
