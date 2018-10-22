<?php
// initializing variables
$username = "";
$email    = "";
$errors = array();

//$db = mysqli_connect('	sql304.epizy.com', 'epiz_22800649', 'LMxl6o8YDN', 'epiz_22800649_request');
$con = mysqli_connect('localhost', 'root', 'password', 'request');

$username = mysqli_real_escape_string($con, $_POST["login_name"]);
$password = mysqli_real_escape_string($con, $_POST["login_pass"]);
$password = md5($password);
$sql_query = "SELECT * FROM users WHERE username='$username' AND password='$password'";
$result = mysqli_query($con,$sql_query);
if(mysqli_num_rows($result) >0 )
{
    $row = mysqli_fetch_assoc($result);
    $name =$row["name"];
    echo "Login Success..Welcome ".$name;
}
else {
    echo "Login Failed.......Try Again..";

}
?>