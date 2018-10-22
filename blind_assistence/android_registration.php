<?php
$con = mysqli_connect('localhost', 'root', 'password', 'request');

$username = $_POST["user"];
$email = $_POST["user_name"];
$password = $_POST["user_pass"];
$password=md5($password);
$sql_query="INSERT INTO users (username, email, password) VALUES('$username', '$email', '$password')";
mysqli_query($con,$sql_query);
?>