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
?>


<!-- You can create scalable multi-column layouts with up to 12 columns. Scaling is focused on screens < 768px, >= 768px, >=992px and >1200px -->


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <title>BUS Notification Service For Blind</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <h4 class="page-header">BUS Notification Service For Blind </h4>

        <?php if (isset($_SESSION['success'])) : ?>
            <div class="error success" >
                <h3>
                    <?php
                    echo $_SESSION['success'];
                    unset($_SESSION['success']);
                    ?>
                </h3>
            </div>
        <?php endif ?>

        <!-- logged in user information -->
        <?php  if (isset($_SESSION['username'])) : ?>
            <p>Welcome <strong><?php echo $_SESSION['username']; ?></strong></p>
            <p> <a href="index.php?logout='1'" style="color: red;">logout</a> </p>
        <?php endif ?>


        <a class="btn btn-primary btn-lg btn-block" href="add.php" role="button">Add a bus</a>
        <a class="btn btn-primary btn-lg btn-block" href="update.php" role="button">Update a bus</a>

        <a class="btn btn-primary btn-lg btn-block" href="delete.php" role="button">Delete a bus</a>

         <a class="btn btn-primary btn-lg btn-block" href="status.php" role="button">Station status</a>

        <a class="btn btn-primary btn-lg btn-block" href="allBuses.php" role="button">Browse Bus list</a>
        <a class="btn btn-primary btn-lg btn-block" href="addStation.php" role="button">Add Bus Station </a>
        <a class="btn btn-primary btn-lg btn-block" href="deleteStation.php" role="button">Delete Bus Station </a>
        <a class="btn btn-primary btn-lg btn-block" href="updateStation.php" role="button">Update Bus station information </a>
        <a class="btn btn-primary btn-lg btn-block" href="stationList.php" role="button">List all bus station</a>

        <a class="btn btn-primary btn-lg btn-block" href="deleteLog.php" role="button">Delete bus entry logs</a>



        <div class="row">
            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">


            </div>
        </div>

    </div>

</div><br>











<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>