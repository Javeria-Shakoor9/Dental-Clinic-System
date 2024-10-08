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

public class LoginSev extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys","root","root9@gmail.com");
                    PreparedStatement stm = con.prepareStatement("Insert into login values('"+name+"','"+password+"')");
                    int x = stm.executeUpdate();
                    if(x>0){
                        if(name.equals("admin") && password.equals("admin")){
                            response.sendRedirect("Admin_Home.html");
                        }
                        else if(name.equals("Supplier") && password.equals("supplier1")){
                            response.sendRedirect("supplier.html");
                    }else {
                            String dbname = null;
                            String pass;
                            try{
                            String sql = "SELECT F_name, password FROM reg";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ResultSet rs ;
                            Statement s = con.createStatement();
                            s.executeQuery (sql);
                            rs = s.getResultSet();
                            while (rs.next ()){
                                dbname=rs.getString("F_Name");
                                pass=rs.getString("password");
                                if(dbname.equals(name)&& pass.equals(password)){
                                    response.sendRedirect("Home.html");
                                }else{
                                    rs.next();
                                    dbname=rs.getString("F_Name");
                                    pass=rs.getString("password");
                                    if(dbname.equals(name)&& pass.equals(password)){
                                         response.sendRedirect("Home.html");
                                    }
                                }
                            }
                            rs.close ();
                            s.close ();
                            }catch(Exception e){
                                System.out.println("Exception is ;"+e);
                            }
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(LoginSev.class.getName()).log(Level.SEVERE, null, ex);
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
