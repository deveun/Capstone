<?php
    //Encoding line
	header('Content-Type: text/html; charset=utf-8');
	//localhost, DB user, Password, DB name
	$con = mysqli_connect("localhost", "deveun","deveun123","deveun");

    $voteNum = $_POST["voteNum"];

    $result = mysqli_query($con,"SELECT * FROM CANDIDATE");
    $response = array();

    while($row=mysqli_fetch_array($result)){

	if($voteNum == $row[0])
{
  		array_push($response, array("voteNum"=>$row[0], "candidateNum"=>$row[1],
		"candidateName"=>$row[2], "candidateInfo"=>$row[3], "img"=>base64_encode($row[4])));}
}

	echo json_encode(array("response"=>$response), JSON_UNESCAPED_SLASHES);
?>