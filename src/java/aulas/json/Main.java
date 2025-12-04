package aulas.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.TipoUsuario;

public class Main {
    public static void main(String[] args) throws Exception {
        
        System.out.println("###");
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        
        tipoUsuario.setId(13);
        tipoUsuario.setNome("Tipo 13");        
        tipoUsuario.setModuloAdministrativo("S");
        tipoUsuario.setModuloAgendamento("S");
        tipoUsuario.setModuloAtendimento("N");
        
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
                        .create();
        
        String jsonTipoUsuario = gson.toJson(tipoUsuario);
        
        System.out.println(tipoUsuario);
        System.out.println(jsonTipoUsuario);
        
        System.out.println("###");
        
        jsonTipoUsuario = "{\"id\":1993,\"nome\":\"Tipo 1993\",\"moduloAdministrativo\":\"N\",\"moduloAgendamento\":\"N\",\"moduloAtendimento\":\"N\"}";
        System.out.println(jsonTipoUsuario);
        
        tipoUsuario = gson.fromJson(jsonTipoUsuario, TipoUsuario.class);
        System.out.println(tipoUsuario);
        
        System.out.println("###");
        
        
        ArrayList<TipoUsuario> tiposUsuario = new TipoUsuario().getAllTableEntities();
        System.out.println(tiposUsuario);
        
        gson = new GsonBuilder()
                        .excludeFieldsWithoutExposeAnnotation()
//                        .setPrettyPrinting()
                        .create();
        
        String jsonTiposUsuario = gson.toJson(tiposUsuario);
        System.out.println(jsonTiposUsuario);
        
        System.out.println("###");
        
//        jsonTiposUsuario = "[{\"id\":1,\"nome\":\"Recepcionista\",\"moduloAdministrativo\":\"S\",\"moduloAgendamento\":\"S\",\"moduloAtendimento\":\"N\"},{\"id\":2,\"nome\":\"Médico\",\"moduloAdministrativo\":\"N\",\"moduloAgendamento\":\"N\",\"moduloAtendimento\":\"S\"},{\"id\":3,\"nome\":\"Paciente\",\"moduloAdministrativo\":\"N\",\"moduloAgendamento\":\"N\",\"moduloAtendimento\":\"N\"}]";
//        System.out.println(jsonTiposUsuario);
//        
//        TipoUsuario[] array = gson.fromJson(jsonTiposUsuario, TipoUsuario[].class);
//        List<TipoUsuario> list = Arrays.asList(array);
//        System.out.println(list);
//        
//        System.out.println("###");        
//        
    }
}