<?php
$server="localhost";
$username="root";
$password="";
$database="student";
$conn=mysqli_connect($server,$username,$password,$database);
if($conn->connect_error){

    die("Connection field : ".$conn->connect_error);
    
}

?>

