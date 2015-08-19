<?php
   $con = mysqli_connect("mysql3.000webhost.com",
                                 "a1097241_steby", 
                                 "3mj02MDgyMwjrm5o",
                                 "a1097241_teachy");

   $name = $_POST["name"];
   $email = $_POST["email"];
   $password = $_POST["password"];
   $school = $_POST["school"];
   $phone = $_POST["phone"];

   $statement = mysqli_prepare($con, "INSERT INTO users (name, email, password, school, phone)
      VALUES (?, ?, ?, ?, ?) ");

   mysqli_stmt_bind_param($statement, "sssss", $name, $email, $password, $school, $phone);
   mysqli_stmt_execute($statement);

   mysqli_stmt_close($statement);

   mysqli_close($con);
?>