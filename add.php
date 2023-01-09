<!DOCTYPE html>
<html>
<head>
	<title>insert bage</title>
</head>
<body>
<form method="post" action="">
name<input type="text" name="a"><br>
phone<input type="int" name="cc"><br>
<br>
<input type="submit" name="save" value="insert">
</form>
</body>
</html>
<?php


include("conn.php");


if(isset($_POST["save"])){
	$ar=$_POST['a'];
	$am=$_POST['cc'];


	$we="INSERT INTO rad VALUES('','$ar','$am')";

	$w= mysqli_query($conn , $we);
}
if (isset($w)) {
	echo "data add";
}
else
{
	echo"not add";
}


