import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("sid");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/school", "root", "password");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Attendance(StudentID, Date, Status) VALUES(?, ?, ?)");
            ps.setString(1, sid);
            ps.setString(2, date);
            ps.setString(3, status);
            ps.executeUpdate();

            out.println("<h3>Attendance submitted successfully for Student ID: " + sid + "</h3>");
            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
