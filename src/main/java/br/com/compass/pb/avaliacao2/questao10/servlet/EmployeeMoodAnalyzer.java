package br.com.compass.pb.avaliacao2.questao10.servlet;

import br.com.compass.pb.avaliacao2.questao10.application.Menu;
import br.com.compass.pb.avaliacao2.questao10.model.EmployeeMood;
import br.com.compass.pb.avaliacao2.questao10.service.EmployeeMoodService;
import br.com.compass.pb.avaliacao2.questao10.util.JPAUtil;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;


@WebServlet("/employeeMoodAnalyzer")
public class EmployeeMoodAnalyzer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String name = req.getParameter("name");
        String msg = req.getParameter("message");

        try {
            EntityManager em = JPAUtil.getEntityManager();
            EmployeeMoodService service = new EmployeeMoodService(em);

            service.save(new EmployeeMood(name, Menu.getMoodFromMessage(msg)));
            resp.sendRedirect("mood-analyzer.html");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try {
            EntityManager em = JPAUtil.getEntityManager();
            EmployeeMoodService service = new EmployeeMoodService(em);

            List<EmployeeMood> employeeMoodList = service.listAll();

            req.setAttribute("registers", employeeMoodList);
            req.getRequestDispatcher("/WEB-INF/jsp/showRegisters.jsp").forward(req, resp);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
