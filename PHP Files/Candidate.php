<?php
    //Encoding line
	header('Content-Type: text/html; charset=utf-8');
	//localhost, DB user, Password, DB name
	$con = mysqli_connect("localhost", "deveun","deveun123","deveun");

    $voteNum = $_POST["voteNum"];

    $result = mysqli_query($con,"SELECT voteNum, candidateNum, candidateName FROM CANDIDATE");
    $response = array();
    $trash = array();

    while($row=mysqli_fetch_array($result)){

	if($voteNum == $row[0])
{
  		array_push($response, array("voteNum"=>$row[0], "candidateNum"=>$row[1], "candidateName"=>$row[2]));}
	else
		array_push($trash, array("voteNum"=>$row[0], "candidateNum" =>$row[1], "candidateName"=>$row[2]));
}

	echo json_encode(array("response"=>$response));
?>