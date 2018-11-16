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
        <h4 class="page-header"> BUS Notification Service For Blind </h4>

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


        <h6>Give the information below to add a bus</h6>


        <form method="post" action="validation.php">
            <div class="form-group">
                <label for="formGroupExampleInput">Tag No</label>
                <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Tag No" name="tagNo">
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput2">Name</label>
                <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Name" name="name">
            </div>

            <div class="form-group">
                <label for="formGroupExampleInput2">Route no</label>
                <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="Route no" name="routeNo">
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput2">From</label>
                <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="From" name="from">
            </div>
            <div class="form-group">
                <label for="formGroupExampleInput2">To</label>
                <input type="text" class="form-control" id="formGroupExampleInput3" placeholder="To" name="to">
            </div>
            <button type="submit" class="btn btn-primary" name="addVehicle">ADD</button>
        </form>



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