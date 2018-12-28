package html;

import classes.*;
import DAOs.*;

import java.util.*;
import java.io.*;
import freemarker.template.*;
import spark.Request;
import static spark.Spark.*;

public class Main {

    private static final String USER_SESSION_ID = "user"; //Définition de la session

    public static void main (String[] args){

        /*User u = new User("toto", "mdp");

        DAO_User dao_u = new DAO_User();

        User test = dao_u.getUserFromNom("toto");

        System.out.print(test.toString());*/


        //FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassForTemplateLoading(Main.class, "");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.FRANCE);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //Spark
        port(8083);

        //Schema d'URL


        get("/viewAllLists", (req, res) ->{

            if(getAuthenticatedUser(req) == null){
                res.redirect("/", 300);
                return "";
            }

            Template template = cfg.getTemplate("viewAllLists.ftl");
            Map<String, Object> input = new HashMap<>();

            List<Liste> listes;
            DAO_Liste dao_liste = new DAO_Liste();
            listes = dao_liste.getListesFromUser(getAuthenticatedUser(req));

            input.put("listes", listes.toArray());
            input.put("user", getAuthenticatedUser(req).getNom());


            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });

        get("/logout", (req, res) ->{
            if(getAuthenticatedUser(req) != null){
                removeAuthenticatedUser(req);
            }
            res.redirect("/", 301);
            return "";
        });


        post("/login", (req, res) ->{
            if(req.queryParams("username") != null){
                String username = req.queryParams("username");
                String password = req.queryParams("password");

                //Test utilisateur
                DAO_User dao_user = new DAO_User();
                User user_test = dao_user.getUserFromNom(username);

                //l'utilisateur n'existe pas
                if(user_test.getID() == 0){
                    user_test = new User(username, password);
                }

                //Il existe
                if(user_test.getMdp().equals(password)){
                    addAuthenticatedUser(req, user_test);
                }

                res.redirect("/", 301);
            }
            return "";
        });


        get("/", (req, res) -> {
            Template template = cfg.getTemplate("login.ftl");
            Map<String, Object> input = new HashMap<>();

            String userName = "";
            if (getAuthenticatedUser(req) != null) {
                userName = getAuthenticatedUser(req).getNom();
            }

            input.put("user", userName);

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });
    }

    //Méthodes sur les sessions
    private static void addAuthenticatedUser(Request request, User u) {
        request.session().attribute(USER_SESSION_ID, u);
    }

    private static void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);

    }

    private static User getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }
}