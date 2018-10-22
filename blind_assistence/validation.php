<?php
session_start();

if (!isset($_SESSION['username'])) {
    $_SESSION['msg'] = "You must log in first";
    header('location: login.php');
}
if (isset($_GET['logout'])) {
    session_destroy();
    unset($_SESSION['username']);
    header("location: login.php");
}

// initializing variables
$username = "";
$email    = "";
$errors = array();

// connect to the database
//$db = mysqli_connect('	sql304.epizy.com', 'epiz_22800649', 'LMxl6o8YDN', 'epiz_22800649_request');

$db = mysqli_connect('localhost', 'root', 'password', 'request');

// REGISTER USER
if (isset($_POST['addVehicle'])) {
    // receive all input values from the form
    $tagNo = mysqli_real_escape_string($db, $_POST['tagNo']);
    $name = mysqli_real_escape_string($db, $_POST['name']);
    $routeNo = mysqli_real_escape_string($db, $_POST['routeNo']);
    $from = mysqli_real_escape_string($db, $_POST['from']);
    $to = mysqli_real_escape_string($db, $_POST['to']);
    $query = "INSERT INTO `vahicle`(`tagNO`, `name`, `routeNo`, `vfrom`, `vto`) VALUES ('{$tagNo}','{$name}','{$routeNo}','{$from}','{$to}')";
    $status = "";
    if (mysqli_query($db, $query)) {
        $status = "success";
    } else {
        $status = "Error description: " . mysqli_error($db);

    }
}

if (isset($_POST['deleteVehicle'])) {
// receive all input values from the form
    $tagNo = mysqli_real_escape_string($db, $_POST['tagNo']);

    $qry="DELETE FROM `vahicle` WHERE `tagNO`='{$tagNo}';";
    $status = "";
    if (mysqli_query($db, $query)) {
        $status = "success";
    } else {
        $status = "Error description: " . mysqli_error($db);

    }

}


if (isset($_POST['updateVehicle'])) {
// receive all input values from the form
    $tagNo = mysqli_real_escape_string($db, $_POST['tagNo']);
    $name = mysqli_real_escape_string($db, $_POST['name']);
    $routeNo = mysqli_real_escape_string($db, $_POST['routeNo']);
    $from = mysqli_real_escape_string($db, $_POST['from']);
    $to = mysqli_real_escape_string($db, $_POST['to']);
    $query = "UPDATE `vahicle` SET `name`='{$name}',`routeNo`='{$routeNo}',`vfrom`='{$from}',`vto`='{$to}' WHERE `tagNO`= '{$tagNo}';";
    $status = "";
    if (mysqli_query($db, $query)) {
        $status = "success";
    } else {
        $status = "Error description: " . mysqli_error($db);
    }
}





    mysqli_close($db);


?>

<!-- You can create scalable multi-column layouts with up to 12 columns. Scaling is focused on screens < 768px, >= 768px, >=992px and >1200px -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <title>Car notification service for blind</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <h4 class="page-header"> Car Notification service for the blind </h4>

        <?php
        echo $status;
        ?>



        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">

                <a class="btn btn-dark" href="home.php" role="button">Link</a>
            </div>
        </div>

    </div>

</div><br>











<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
