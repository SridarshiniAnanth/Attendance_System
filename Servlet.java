package servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import model.Student;
import dao.StudentDAO;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {

            if (action.equals("addStudent")) {

                String name = request.getParameter("name");
                String dept = request.getParameter("department");

                Student student = new Student(name, dept);
                StudentDAO.addStudent(student);

                response.getWriter().println("Student Added Successfully!");

            } else if (action.equals("attendance")) {

                int id = Integer.parseInt(request.getParameter("id"));
                String status = request.getParameter("status");

                StudentDAO.markAttendance(id, status);
                response.getWriter().println("Attendance Updated!");

            } else if (action.equals("marks")) {

                int id = Integer.parseInt(request.getParameter("id"));
                int s1 = Integer.parseInt(request.getParameter("s1"));
                int s2 = Integer.parseInt(request.getParameter("s2"));
                int s3 = Integer.parseInt(request.getParameter("s3"));

                StudentDAO.addMarks(id, s1, s2, s3);

                int total = s1 + s2 + s3;
                double avg = total / 3.0;

                response.getWriter().println("Marks Added! Total: " + total + " Average: " + avg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
