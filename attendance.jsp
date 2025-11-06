<!DOCTYPE html>
<html>
<head>
  <title>Student Attendance</title>
</head>
<body>
  <h2>Attendance Form</h2>
  <form action="AttendanceServlet" method="post">
    Student ID: <input type="text" name="sid"><br><br>
    Date: <input type="date" name="date"><br><br>
    Status: 
    <select name="status">
      <option value="Present">Present</option>
      <option value="Absent">Absent</option>
    </select><br><br>
    <input type="submit" value="Submit">
  </form>
</body>
</html>
