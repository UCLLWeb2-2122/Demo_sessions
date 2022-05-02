package ui.controller;

import domain.db.UserDB;
import domain.model.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private UserDB users = new UserDB();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination;

        switch (command) {
            case "hello":
                destination = hello(request, response);
                break;
            case "helloagain":
                destination = helloagain(request, response);
                break;
            default:
                destination = "index.jsp";
        }
        request.getRequestDispatcher(destination).forward(request, response);
    }

    private String helloagain(HttpServletRequest request, HttpServletResponse response) {
        Person person = (Person) request.getSession().getAttribute("user");
        person.setPassword("ttttttt");
        return "helloagain.jsp";
    }

    private String hello(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Person person = users.getUserWithName(name);
        if (person != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", person);
            return "hello.jsp";
        } else {
            request.setAttribute("message", "Not a registered user. Use 'Elke' or 'Mieke'.");
            return "index.jsp";
        }
    }

}