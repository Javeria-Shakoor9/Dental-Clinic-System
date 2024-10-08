import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class regServ extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
try (PrintWriter out = response.getWriter()) {
                try {
                    int ID = 3;
                    String fname = request.getParameter("fname");
            String mname = request.getParameter("mname");
            String lname = request.getParameter("lname");
            String Age = request.getParameter("age");
            int age = Integer.parseInt(Age);
            String phoneNo = request.getParameter("phno");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String dob = request.getParameter("DOB");
            String epassword = request.getParameter("Enter_Password");
            String cpassword = request.getParameter("Confirm_Password");
            String gender = request.getParameter("Gender");
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","root9@gmail.com");
                    Statement stmt = con.createStatement();
                     String query = "select count(*) from sys.reg;";
                     ResultSet rs = stmt.executeQuery(query);
                     rs.next();
                     int count = rs.getInt(1);
                     count = count+1;
                     if(epassword.equals(cpassword)){
                        PreparedStatement stm = con.prepareStatement("Insert into reg values('"+count+"','"+fname+"','"+mname+"','"+lname+"','"+age+"','"+phoneNo+"','"+email+"','"+address+"','"+dob+"','"+epassword+"','"+gender+"')");
                        int x = stm.executeUpdate();
                        if(x>0){
                        response.sendRedirect("Home.html");
                        out.println("Record inserted.");
                        }
                    }
                     else{
                        response.sendRedirect("Registeration.html");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(regServ.class.getName()).log(Level.SEVERE, null, ex);
                }   
            }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
