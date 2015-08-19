<?php
   $con = mysqli_connect("mysql3.000webhost.com",
                                 "a1097241_steby", 
                                 "3mj02MDgyMwjrm5o",
                                 "a1097241_teachy");

   $email = $_POST["email"];
   $password = $_POST["password"];


   $statement = mysqli_prepare($con, "SELECT * FROM users WHERE email = ? AND password = ?");

   mysqli_stmt_bind_param($statement, "ss", $email, $password);
   mysqli_stmt_execute($statement);

   mysqli_stmt_store_result($statement);
   mysqli_stmt_bind_result($statement, $name, $email, $password, $school, $phone);

   $user = array();

   while(mysqli_stmt_fetch($statement)) {
      $user[name] = $name;
      $user[email] = $email;
      $user[password] = $password;
      $user[school] = $school;
      $user[phone] = $phone;
   }

   echo json_encode($user);

   mysqli_stmt_close($statement);

   mysqli_close($con);
?>