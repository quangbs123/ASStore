/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import SB.CartDetailFacadeLocal;
import SB.MediaFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zerox
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart/*"})
public class Cart extends HttpServlet {

  @EJB
  private MediaFacadeLocal mediaFacade;
  @EJB
  private CartDetailFacadeLocal cartDetailFacade;
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    String clientRequest = request.getPathInfo();
    switch(clientRequest) {
      case "/list":
        java.util.List<Models.CartDetail> details = cartDetailFacade.findByCartId(1);
        HashMap images = new HashMap();
        BigDecimal total = new BigDecimal(0);
        for (Models.CartDetail detail : details) {
          total = total.add(detail.getProductId().getPrice().multiply(new BigDecimal(detail.getQuantity())));
          images.put(detail.getProductId().getId(), mediaFacade.getFirstImageFromProduct(detail.getProductId()));
        }
        request.setAttribute("cartTotal", total);
        request.setAttribute("images", images);
        request.setAttribute("details", details);
        request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
        break;
      case "/remove":
        int detailId = Integer.parseInt(request.getParameter("detailId"));
        cartDetailFacade.remove(cartDetailFacade.find(detailId));
        response.sendRedirect("/cart/list");
    }
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
