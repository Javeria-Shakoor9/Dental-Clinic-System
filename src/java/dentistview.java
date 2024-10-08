
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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

public class dentistview extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/dental_clinic_system","root","root9@gmail.com");
                    PreparedStatement stm = con.prepareStatement("select * from dental_clinic_system.dentist");
                    out.print("<table border=10px width=1329px>");
                    ResultSet rs = stm.executeQuery();
                    ResultSetMetaData rsnd = rs.getMetaData();
                    int totalcol = rsnd.getColumnCount();
                    //RequestDispatcher reqdis = request.getRequestDispatcher("Schedule.html");
                   // reqdis.include(request, response);
                    for(int i =1;i<=totalcol;i++){
                        out.print("<th >"+rsnd.getColumnName(i)+"</th>");
                    }
                    out.print("<tr>");
                    while(rs.next()){
                        out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+rs.getString(6)+rs.getString(7)+rs.getString(8)+rs.getInt(9)+"</td></tr>");
                    }
                    out.print("</table>");
                    
                    
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(dentistview.class.getName()).log(Level.SEVERE, null, ex);
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
