package DAOs;

import classes.Element;
import classes.Liste;
import org.sql2o.*;

import java.util.*;

public class DAO_Element {
    DAO_Connect co = new DAO_Connect();

    public Element create(Element i){
        try{
            String query = "INSERT INTO item(id_liste, date_crea, date_modif, description, titre, statut) VALUES(:a, :b, :c, :d, :e, :f)";
            co.createConnection().createQuery(query)
                    .addParameter("a", i.getIdListe())
                    .addParameter("b", i.getDateCrea())
                    .addParameter("c", i.getDateModif())
                    .addParameter("d", i.getDescription())
                    .addParameter("e", i.getTitre())
                    .addParameter("f", i.getStatut())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return i;
    }

    public void delete(Element i){
        try{
            String query = "DELETE FROM item WHERE id = :i";
            co.createConnection().createQuery(query)
                    .addParameter("i", i.getId())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }
    }

    public void update(Element i){
        try{
            String query = "UPDATE item SET description = :d, titre = :t, date_modif = :da, statut = :s WHERE id = :i";
            co.createConnection().createQuery(query)
                    .addParameter("d", i.getDescription())
                    .addParameter("t", i.getTitre())
                    .addParameter("da", i.getDateModif())
                    .addParameter("s", i.getStatut())
                    .addParameter("i", i.getId())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }
    }

    public Element getItemFromId(int id){
        Element i = new Element();

        try{
            String query = "SELECT * FROM item WHERE id = :i LIMIT 1";
            i = co.createConnection().createQuery(query)
                    .addParameter("i", id)
                    .executeAndFetch(Element.class).get(0);

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return i;
    }

    public List<Element> getItemsFromListe(Liste l){
        List<Element> listeItems = new ArrayList<Element>();

        try {
            String query = "SELECT * FROM item WHERE id_list = :i ORDER BY ID ASC";
            listeItems = co.createConnection().createQuery(query)
                    .addParameter("i", l.getId())
                    .executeAndFetch(Element.class);

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return listeItems;
    }
}