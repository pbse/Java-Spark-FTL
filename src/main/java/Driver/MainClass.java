package Driver;

import Model.Model;
import TemplateEngine.FreeMarkerEngine;
import User.User;
import java.io.IOException;
import static spark.Spark.*;
import spark.ModelAndView;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author prash_000
 */
public class MainClass {
    
    /**
     *  Entry Point
     * @param args
     */
    public static void main(String[] args) {
        staticFileLocation("/public");
        MainClass s = new MainClass();
        s.init();
    }
    
    /**
     *  Function for Routes
     */
    private void init() {
        Model mod = new Model();
        
        get("/", (request, response) -> {
           Map<String, Object> viewObjects = new HashMap<String, Object>();
           viewObjects.put("title", "Welcome to Spark Project");
           viewObjects.put("templateName", "home.ftl");
           return new ModelAndView(viewObjects, "main.ftl");
        }, new FreeMarkerEngine());
        
        get("/createUser", (request, response) -> {
           Map<String, Object> viewObjects = new HashMap<String, Object>();
           viewObjects.put("templateName", "createForm.ftl");
           return new ModelAndView(viewObjects, "main.ftl");
        }, new FreeMarkerEngine());
        
        post("/createUser", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                User u = mapper.readValue(request.body(), User.class);
                if (!u.isValid()) {
                    response.status(400);
                    return "Correct the fields";
                }
                if(mod.checkUser(u.getId())) {
                    int id = mod.createUser(u.getId(), u.getFirstName(), u.getMiddleName(), u.getLastName(),
                    u.getAge(), u.getGender(), u.getPhone(), u.getZip());
                    response.status(200);
                    response.type("application/json");
                    return id;
                }
                else {
                    response.status(400);
                    response.type("application/json");
                    return "User Already Exists";
                }
                } catch (JsonParseException jpe) {
                    response.status(404);
                    return "Exception";
                }
        });
        
        get("/getAllUsers", (request, response) -> {
            response.status(200);
            Map<String, Object> viewObjects = new HashMap<String, Object>();
            viewObjects.put("templateName", "showUser.ftl");
            return new ModelAndView(viewObjects, "main.ftl");
        }, new FreeMarkerEngine());

        get("/getusers", (request, response) -> {
            response.status(200);
            return toJSON(mod.sendElements());
        });

        get("/removeUser", (request, response) -> {
           Map<String, Object> viewObjects = new HashMap<String, Object>();
           viewObjects.put("templateName", "removeUser.ftl"); 
           viewObjects.put("users", toJSON(mod.sendUsersId()));
           return new ModelAndView(viewObjects, "main.ftl");
        }, new FreeMarkerEngine());

        put("/remove/:id", (request, response) -> {
           Map<String, Object> viewObjects = new HashMap<String, Object>();
           if(mod.removeUser(id)) return "User Removed";
           else return "No Such User Found";
        });
        
        get("/updateUser", (request, response) -> {
           Map<String, Object> viewObjects = new HashMap<String, Object>();
           viewObjects.put("templateName", "updateForm.ftl");
           return new ModelAndView(viewObjects, "main.ftl");
        }, new FreeMarkerEngine());
        
        post("/updateUser", (request, response) -> {
            ObjectMapper mapper = new ObjectMapper();
            try {
                User u = mapper.readValue(request.body(), User.class);
                if (!u.isValid()) {
                    response.status(400);
                    return "Correct the fields";
                }
                if(!mod.checkUser(u.getId())) {
                    int id = mod.updateUser(u.getId(), u.getFirstName(), u.getMiddleName(), u.getLastName(),
                    u.getAge(), u.getGender(), u.getPhone(), u.getZip());
                    response.status(200);
                    response.type("application/json");
                    return id;
                }
                else {
                    response.status(404);
                    return "User Does Not Exists";
                }
                } catch (JsonParseException jpe) {
                    response.status(404);
                    return "Exception";
                }
        });
        
    }
    
    /**
     *  This function converts an Object to JSON String
     * @param obj
     */
    private static String toJSON(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, obj);
            return sw.toString();
        }
        catch(IOException e) {
            System.err.println(e);
        }
        return null;
    }
    
}
