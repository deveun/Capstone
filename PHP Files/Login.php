<?php
    //Encoding line
	header('Content-Type: text/html; charset=utf-8');
	//localhost, DB user, Password, DB name
	$con = mysqli_connect("localhost", "deveun","deveun123","deveun");

    $userID = $_POST["userID"];
    $userPassword = $_POST["userPassword"];

    $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE userID = ? AND userPassword = ?");
    mysqli_stmt_bind_param($statement,"ss", $userID, $userPassword);
    mysqli_stmt_execute($statement);

    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userNum, $userID, $userPassword);

    $response = array();
    $response["success"] = false;

    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;
        $response["userNum"] = $userNum;
	$response["userID"] = $userID;
        }
    
    echo json_encode($response);
?>
