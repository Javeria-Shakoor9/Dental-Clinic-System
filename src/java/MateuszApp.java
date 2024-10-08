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

public class MateuszApp extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                try {
            String day = request.getParameter("day");
            String timem = request.getParameter("timem");
            String timet = request.getParameter("timet");
            String name = "Dr. Mateusz Chojnicki";
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","root9@gmail.com");
                    Statement stmt = con.createStatement();
                     String query = "select count(*) from sys.appointments";
                     ResultSet rs = stmt.executeQuery(query);
                    rs.next();
                     int count = rs.getInt(1);
                     count = count+1;
                    PreparedStatement stm = con.prepareStatement("Insert into appointments values('"+count+"','"+name+"','"+day+"','"+timem+"','"+timet+"')");
                    int x = stm.executeUpdate();
                    if(x>0){
                        response.sendRedirect("Home.html");
                        out.println("Record inserted.");
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(MateuszApp.class.getName()).log(Level.SEVERE, null, ex);
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
