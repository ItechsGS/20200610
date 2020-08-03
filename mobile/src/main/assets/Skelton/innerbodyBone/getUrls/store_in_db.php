<?php

$conn = mysqli_connect("localhost", "root", "", "innerbodybone");

if (mysqli_connect_errno()) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
}
$sql = "INSERT INTO pages (title, url) VALUES ('{$_POST['title']}','{$_POST['url']}')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
