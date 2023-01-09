<?php

include "con2.php";

$user_email=$_POST['email'];
$user_password=$_POST['password'];

$stmt = $mysqli -> query("SELECT * FROM reqs WHERE email LIKE '$user_email' AND pass_number LIKE '$user_password' AND status_req='1' ") ;

if ($stmt) {
	$communicates=array();

  while ($row=mysqli_fetch_array($stmt)) {
 	$communicates[]=$row;
 	
}

print "{login_result:".json_encode($communicates,JSON_UNESCAPED_UNICODE). "}";
}

?>