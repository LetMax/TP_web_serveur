package html;

import classes.*;
import DAOs.*;

import java.util.*;
import java.io.*;

import freemarker.template.*;
import spark.Request;
import static spark.Spark.*;

public class Main {

    private static final String GESTION_SESSION = "utilisateur";

    public static void main (String[] args){

        //FreeMarker
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setClassForTemplateLoading(Main.class, "");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.FRANCE);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        //Spark
        port(8083);

        //Schema d'URL
        get("/viewAllLists/:idListe/statut/:idElement", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":idListe"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            DAO_Element dao_element = new DAO_Element();

            if(dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getIdListe() != Integer.parseInt(req.params(":idListe"))){
                res.redirect("/viewAllLists"+req.params(":idListe")+"/liste");
                return "";
            }

            Element elem = dao_element.getItemFromId(Integer.parseInt(req.params(":idElement")));
            int statutp = 1;

            if(elem.getStatut() == 1){
                statutp = 2;
            }

            dao_element.update(new Element(Integer.parseInt(req.params(":idElement")), Integer.parseInt(req.params(":idListe")), new Date(), elem.getDescription(), elem.getTitre(), statutp));

            res.redirect("/viewAllLists/"+req.params(":idListe")+"/liste");
            return "";
        });

        post("/viewAllLists/:idListe/editElement/:idElement", (req, res) -> {

            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            if(req.queryParams("titre") == null){
                res.redirect("/viewAllLists");
                return "";
            }

            String titre = req.queryParams("titre");
            String description = req.queryParams("description");

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":idListe"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            DAO_Element dao_element = new DAO_Element();
            if(dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getIdListe() != Integer.parseInt(req.params(":idListe"))){
                res.redirect("/viewAllLists/"+req.params(":idListe")+"/liste");
                return "";
            }

            dao_element.update(new Element(Integer.parseInt(req.params(":idElement")), Integer.parseInt(req.params(":idListe")), new Date(), description, titre, dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getStatut()));

            res.redirect("/viewAllLists/"+req.params(":idListe")+"/liste");
            return "";
        });

        get("/viewAllLists/:idListe/editElement/:idElement", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":idListe"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            DAO_Element dao_element = new DAO_Element();

            if(dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getIdListe() != Integer.parseInt(req.params(":idListe"))){
                res.redirect("/viewAllLists"+req.params(":idListe")+"/liste");
                return "";
            }

            Template template = cfg.getTemplate("modifElement.ftl");
            Map<String, Object> input = new HashMap<>();

            input.put("idListe", dao_liste.getListeFromId(Integer.parseInt(req.params(":idListe"))).getId());
            input.put("idElement", dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getId());
            input.put("titre", dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getTitre());
            input.put("description", dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getDescription());

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });

        get("/viewAllLists/:idListe/removeElement/:idElement", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":idListe"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            DAO_Element dao_element = new DAO_Element();

            if(dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))).getIdListe() != Integer.parseInt(req.params(":idListe"))){
                res.redirect("/viewAllLists"+req.params(":idListe")+"/liste");
                return "";
            }

            dao_element.delete(dao_element.getItemFromId(Integer.parseInt(req.params(":idElement"))));

            res.redirect("/viewAllLists/"+req.params(":idListe")+"/liste");
            return "";
        });

        post("/viewAllLists/:id/ajoutElement", (req, res) -> {

            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            if(req.queryParams("titre") == null){
                res.redirect("/viewAllLists/"+req.params(":id")+"/liste");
                return "";
            }

            String titre = req.queryParams("titre");
            String description = req.queryParams("description");

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            new Element(titre, description, Integer.parseInt(req.params((":id"))));

            res.redirect("/viewAllLists/"+req.params(":id")+"/liste");
            return "";
        });

        get("/viewAllLists/:id/liste", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste= new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            Template template = cfg.getTemplate("liste.ftl");
            Map<String, Object> input = new HashMap<>();

            List<Element> elements;
            DAO_Element dao_elem = new DAO_Element();

            elements = dao_elem.getItemsFromListe(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))));

            input.put("elements", elements.toArray());
            input.put("user", getConnectedUser(req).getNom());
            input.put("id", req.params(":id"));
            input.put("titre", dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getTitre());

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });

        post("/viewAllLists/:id/edit", (req, res) -> {

            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            if(req.queryParams("titre") == null){
                res.redirect("/viewAllLists");
                System.out.println("Titre null");
                return "";
            }

            String titre = req.queryParams("titre");
            String description = req.queryParams("description");

            DAO_Liste dao_liste= new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                System.out.println("Pas le bon user");
                return "";
            }

            dao_liste.update(new Liste(Integer.parseInt(req.params(":id")), titre, description, getConnectedUser(req).getID()));
            res.redirect("/viewAllLists");
            return "";
        });

        get("/viewAllLists/:id/edit", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            Template template = cfg.getTemplate("modifListe.ftl");
            Map<String, Object> input = new HashMap<>();

            input.put("id", dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getId());
            input.put("titre", dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getTitre());
            input.put("description", dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getDescription());

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();

        });

        get("/viewAllLists/:id/remove", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            DAO_Liste dao_liste = new DAO_Liste();

            if(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))).getIdUser() != getConnectedUser(req).getID()){
                res.redirect("/viewAllLists");
                return "";
            }

            dao_liste.delete(dao_liste.getListeFromId(Integer.parseInt(req.params(":id"))));
            res.redirect("/viewAllLists");
            return "";
        });

        post("/viewAllLists/creaListe", (req, res) -> {
            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            if(req.queryParams("titre") == null){
                res.redirect("/viewAllLists");
                return "";
            }

            String titre = req.queryParams("titre");
            String description = req.queryParams("description");

            DAO_Liste dao_liste = new DAO_Liste();

            new Liste(titre, description, getConnectedUser(req).getID());

            res.redirect("/viewAllLists");
            return "";
        });

        get("/viewAllLists", (req, res) -> {

            if(getConnectedUser(req) == null){
                res.redirect("/");
                return "";
            }

            Template template = cfg.getTemplate("viewAllLists.ftl");
            Map<String, Object> input = new HashMap<>();

            List<Liste> listes;
            DAO_Liste dao_liste = new DAO_Liste();
            listes = dao_liste.getListesFromUser(getConnectedUser(req));

            input.put("listes", listes.toArray());
            input.put("user", getConnectedUser(req).getNom());
            input.put("id", req.params(":id"));

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });

        get("/logout", (req, res) -> {
            if(getConnectedUser(req) != null){
                removeConnectedUser(req);
            }
            res.redirect("/");
            return "";
        });

        post("/login", (req, res) -> {
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
                    addConnectedUser(req, user_test);
                }

                res.redirect("/");
            }
            return "";
        });

        get("/", (req, res) -> {
            Template template = cfg.getTemplate("login.ftl");
            Map<String, Object> input = new HashMap<>();

            String userName = "";
            if (getConnectedUser(req) != null) {
                userName = getConnectedUser(req).getNom();
            }

            input.put("user", userName);

            Writer output = new StringWriter();
            template.process(input, output);
            return output.toString();
        });
    }

    private static void addConnectedUser(Request request, User u) {
        request.session().attribute(GESTION_SESSION, u);
    }

    private static void removeConnectedUser(Request request) {
        request.session().removeAttribute(GESTION_SESSION);
    }

    private static User getConnectedUser(Request request) {
        return request.session().attribute(GESTION_SESSION);
    }
}
