package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.TipoUsuario;

import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "ControllerAPI", urlPatterns = {"/api/tipousuario/*"})

//    API for CRUD (Create Read Update Delete)
public class TipoUsuarioAPI extends HttpServlet {

    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();

        gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    // Read
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("request.getPathInfo() " + request.getPathInfo());

        String id = request.getPathInfo();
        if (id != null) {
            id = id.replace("/", "");
        }

        try {

            String json = "";

            if (id == null) {

                ArrayList<TipoUsuario> tiposUsuario = new TipoUsuario().getAllTableEntities();

                json = gson.toJson(tiposUsuario);

            } else {

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(Integer.valueOf(id));

                if (tipoUsuario.load()) {
                    json = gson.toJson(tipoUsuario);
                }

            }

            response.setContentType("application/json;charset=UTF-8");

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");

            PrintWriter pw = response.getWriter();
            response.setStatus(200); // 200 OK	The request is OK (this is the standard response for successful HTTP requests)
            pw.append(json);

        } catch (Exception ex) {
            response.sendError(500, ex.getMessage()); // 500 Internal Server Error	A generic error message, given when no more specific message is suitable
        }
    }

    // Create
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = getReader(request);
        System.out.println("json " + json);

        try {

            TipoUsuario tipoUsuarioJSON = gson.fromJson(json, TipoUsuario.class);

            TipoUsuario tipoUsuario = new TipoUsuario();

            tipoUsuario.setId(tipoUsuarioJSON.getId());
            tipoUsuario.setNome(tipoUsuarioJSON.getNome());
            tipoUsuario.setModuloAdministrativo(tipoUsuarioJSON.getModuloAdministrativo());
            tipoUsuario.setModuloAgendamento(tipoUsuarioJSON.getModuloAgendamento());
            tipoUsuario.setModuloAtendimento(tipoUsuarioJSON.getModuloAtendimento());

            tipoUsuario.save();

            response.setStatus(201); // 201 Created	The request has been fulfilled, and a new resource is created 

        } catch (Exception ex) {
            response.sendError(500, ex.getMessage()); // 500 Internal Server Error	A generic error message, given when no more specific message is suitable
        }
    }

    // Update
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String json = getReader(request);
        System.out.println("json " + json);

        try {

            TipoUsuario tipoUsuarioJSON = gson.fromJson(json, TipoUsuario.class);

            TipoUsuario tipoUsuario = new TipoUsuario();

            tipoUsuario.setId(tipoUsuarioJSON.getId());

            tipoUsuario.load();

            tipoUsuario.setNome(tipoUsuarioJSON.getNome());
            tipoUsuario.setModuloAdministrativo(tipoUsuarioJSON.getModuloAdministrativo());
            tipoUsuario.setModuloAgendamento(tipoUsuarioJSON.getModuloAgendamento());
            tipoUsuario.setModuloAtendimento(tipoUsuarioJSON.getModuloAtendimento());

            tipoUsuario.save();

            response.setStatus(202); // 202 Accepted    The request has been fulfilled, and a new resource is created 

        } catch (Exception ex) {
            response.sendError(500, ex.getMessage()); // 500 Internal Server Error	A generic error message, given when no more specific message is suitable
        }
    }

    // Delete
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("request.getPathInfo() " + request.getPathInfo());

        String id = request.getPathInfo();
        if (id != null) {
            id = id.replace("/", "");

            try {

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(Integer.valueOf(id));
                tipoUsuario.delete();

                response.setStatus(202);

            } catch (Exception ex) {
                response.sendError(500, ex.getMessage()); // 500 Internal Server Error	A generic error message, given when no more specific message is suitable
            }
        } else {
            response.sendError(400);
        }

    }

    private String getReader(HttpServletRequest request) throws IOException {
        String reader = "", line;

        BufferedReader bufferedReader = request.getReader();
        line = bufferedReader.readLine();

        while (line != null) {
            reader += line;
            line = bufferedReader.readLine();
        }

        return reader;
    }

    @Override
    public void destroy() {
        super.destroy();
    }

}
