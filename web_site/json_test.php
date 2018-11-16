<?php
//these are the server details
//the username is root by default in case of xampp
//password is nothing by default
//and lastly we have the database named android. if your database name is different you have to change it
/*
$servername = "localhost";
$username = "root";
$password = "password";
$database = "request";
*/

//creating a new connection object using mysqli
//$conn = new mysqli($servername, $username, $password, $database);
$conn = mysqli_connect('sql304.epizy.com', 'epiz_22800649', 'LMxl6o8YDN', 'epiz_22800649_request');
//if there is some error connecting to the database
//with die we will stop the further execution by displaying a message causing the error
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

//if everything is fine

//creating an array for storing the data
$heroes = array();

//this is our sql query
$sql = "SELECT * , CAST((CURRENT_TIMESTAMP-time_stamp)/100 as UNSIGNED ) as ago FROM `request` JOIN vahicle ON request.request=vahicle.tagNO  WHERE (CURRENT_TIMESTAMP-time_stamp)/100<5 order by id asc;";
/*
//creating an statment with the query
$stmt = $conn->prepare($sql);

//executing that statment
$stmt->execute();

//binding results for that statment
$stmt->bind_result($id,$tagNO,$station_id,$checked,$time_stamp,$tagNO2,$name,$route,$vfrom,$vto,$ago);

//looping through all the records
while($stmt->fetch()){

    //pushing fetched data in an array
    $temp = [
        'vto'=>$id,
        'vfrom'=>$name
    ];

    //pushing the array inside the hero array
    array_push($heroes, $temp);
}
*/

if ($result = mysqli_query($conn, $sql)) {

    /* fetch associative array */
    while ($row = mysqli_fetch_assoc($result)) {
        array_push($heroes,$row);
    }

    /* free result set */
    mysqli_free_result($result);
}

//displaying the data in json format
echo json_encode($heroes);
/* close connection */
mysqli_close($conn);
exit();
?>