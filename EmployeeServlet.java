import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empID = request.getParameter("empid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/company", "root", "password");

            String query = (empID != null && !empID.isEmpty()) ?
                "SELECT * FROM Employee WHERE EmpID=?" : "SELECT * FROM Employee";

            PreparedStatement ps = con.prepareStatement(query);
            if (empID != null && !empID.isEmpty())
                ps.setInt(1, Integer.parseInt(empID));

            ResultSet rs = ps.executeQuery();

            out.println("<h2>Employee Records</h2>");
            out.println("<form method='get'>Search by ID: <input type='text' name='empid'><input type='submit' value='Search'></form>");
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" +
                            rs.getDouble("Salary") + "</td></tr>");
            }

            out.println("</table>");
            con.close();

        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
