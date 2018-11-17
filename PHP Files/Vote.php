<?php
    //Encoding line
	header('Content-Type: text/html; charset=utf-8');
	//localhost, DB user, Password, DB name
	$con = mysqli_connect("localhost", "deveun","deveun123","deveun");

    $result = mysqli_query($con,"SELECT * FROM VOTE;");
    $response = array();

   while($row=mysqli_fetch_array($result)){
	
	array_push($response, array("voteNum"=>$row[0], "voteName"=>$row[1]
	, "voteContent"=>$row[2], "voteSdate"=>$row[3], "voteEdate"=>$row[4]));
}
	echo json_encode(array("response"=>$response));
?>
