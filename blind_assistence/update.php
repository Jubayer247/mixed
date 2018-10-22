<!-- You can create scalable multi-column layouts with up to 12 columns. Scaling is focused on screens < 768px, >= 768px, >=992px and >1200px -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width = device-width, initial-scale = 1">
    <title>Bootstrap Tutorial</title>
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <div class="jumbotron">
        <h4 class="page-header"> Car Notification service for the blind </h4>

        <h6>Give the information below to update a vehicle</h6>


        <form>
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
            <button type="submit" class="btn btn-primary" name="addVehicle">Update</button>
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