<?php
$baseUrl = "https://www.innerbody.com";
if (isset($_GET["page"])) {
    echo file_get_contents($baseUrl . $_GET["page"]);
}