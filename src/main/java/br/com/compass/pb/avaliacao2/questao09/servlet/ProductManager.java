package br.com.compass.pb.avaliacao2.questao09.servlet;

import br.com.compass.pb.avaliacao2.questao09.model.Product;
import br.com.compass.pb.avaliacao2.questao09.service.ProductService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.util.List;


@WebServlet("/productManager")
public class ProductManager extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try (ProductService ps = new ProductService()) {
            String str = req.getParameter("search-str");

            List<Product> productList;
            if (str.isEmpty()) {
                productList = ps.listAll();
            }
            else {
                productList = ps.search(req.getParameter("search-str"));
            }

            req.setAttribute("productList", productList);
            req.getRequestDispatcher("/WEB-INF/jsp/showProducts.jsp").forward(req, resp);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        try (ProductService ps = new ProductService()) {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            float price = Float.parseFloat(req.getParameter("price"));
            float discount = Float.parseFloat(req.getParameter("discount")) / 100;
            Date date = Date.valueOf(req.getParameter("date"));

            Product product;
            if (req.getParameter("update") != null) {
                int id = Integer.parseInt(req.getParameter("id"));
                product = new Product(id, name, description, price, discount, date);
                ps.update(product);
            }
            else {
                product = new Product(name, description, price, discount, date);
                ps.save(product);
            }

            resp.sendRedirect("manage-products.html");
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

