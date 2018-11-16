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
$db = mysqli_connect('sql304.epizy.com', 'epiz_22800649', 'LMxl6o8YDN', 'epiz_22800649_request');


$qry = "SELECT * from vahicle order by name asc ;";
$result=mysqli_query($db, $qry);

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
        <h4 class="page-header">BUS Notification Service For Blind</h4>
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



            <div class="row">
<table class="table table-hover">

                    <thead>
                    <tr>
                        <th scope="col">Tag</th>
                        <th scope="col">Name</th>
                        <th scope="col">Route</th>
                        <th scope="col">From</th>
                        <th scope="col">To</th>
                    </tr>
                    </thead>
                    <tbody>

                    <?php
                        while ($row=mysqli_fetch_assoc($result)){
                            echo "<tr>";
                            echo "<th scope=\"row\">{$row['tagNO']}</th>";
                            echo "<td>{$row['name']}</td>";
                            echo "<td>{$row['routeNo']}</td>";
                            echo "<td>{$row['vfrom']} </td>";
                            echo "<td>{$row['vto']}</td>";
                            echo "</tr>";

                    }

                    ?>
                    </tbody>

                </table>

            <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
            </div>
        </div>

    </div>

</div><br>











<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>









