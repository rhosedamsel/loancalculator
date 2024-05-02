import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoanServlet")
public class LoanServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double interestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numberOfYears = Integer.parseInt(request.getParameter("numberOfYears"));
        
        // Convert annual interest rate to monthly interest rate
        double monthlyInterestRate = interestRate / 12 / 100;
        
        // Calculate monthly payment
        int numberOfPayments = numberOfYears * 12;
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        
        // Calculate total payment
        double totalPayment = monthlyPayment * numberOfPayments;
        
        // Set response content type
        response.setContentType("text/html");
        
        // Write response
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Loan Payment Result</title></head><body>");
        out.println("<h2>Loan Payment Result</h2>");
        out.println("<p>Monthly Payment: $" + String.format("%.2f", monthlyPayment) + "</p>");
        out.println("<p>Total Payment: $" + String.format("%.2f", totalPayment) + "</p>");
        out.println("</body></html>");
    }
}
