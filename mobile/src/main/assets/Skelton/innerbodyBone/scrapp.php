<?php

$mysqli = new mysqli("localhost", "root", "", "innerbodybone");

if ($mysqli->connect_errno) {
    die("Failed to connect to MySQL: " . mysqli_connect_error());
}

if ($result = $mysqli->query("Select DISTINCT url from pages where downloaded=0 order by id desc")) {
    $fp = fopen("logs/" . time() . ".log", "w");
    while ($row = $result->fetch_assoc()) {
        $output = !file_exists("output{$row["url"]}.html") ? shell_exec("phantomjs page.js {$row["url"]}") : "{$row["url"]}: present \n";
        fwrite($fp, $output);
        $mysqli->query("Update pages Set downloaded=" . (strpos($output, "timeout") > -1 ? 2 : 1) . " where url = '{$row['url']}'");
    } $result->free();
    fclose($fp);
} $mysqli->close();
