package DAOs;

import classes.*;
import org.sql2o.*;

import java.util.*;

public class DAO_Liste {
    DAO_Connect co = new DAO_Connect();

    public Liste create(Liste l){
        try{
            String query = "INSERT INTO liste(id_user, titre, description) values(:x, :y, :z)";
            co.createConnection().createQuery(query)
                    .addParameter("x", l.getIdUser())
                    .addParameter("y", l.getTitre())
                    .addParameter("z", l.getDescription())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return l;
    }

    public void delete(Liste l){
        try{
            //On delete la liste
            String query = "DELETE FROM liste WHERE id = :x";
            co.createConnection().createQuery(query)
                    .addParameter("x", l.getId())
                    .executeUpdate();

            //On delete les elements de cette liste
            String query2 = "DELETE FROM element WHERE id_liste = :y";
            co.createConnection().createQuery(query2)
                    .addParameter("y", l.getId())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }
    }

    public void update(Liste l){
        try{
            String query = "UPDATE liste SET titre = :t, description = :d WHERE id = :i";
            co.createConnection().createQuery(query)
                    .addParameter("t", l.getTitre())
                    .addParameter("d", l.getDescription())
                    .addParameter("i", l.getId())
                    .executeUpdate();

        } catch (Sql2oException e){
            e.printStackTrace();
        }
    }

    public Liste getListeFromId(int id) {
        Liste l = new Liste();
        try {
            String query = "SELECT * FROM liste WHERE id = :p LIMIT 1";
            l = co.createConnection().createQuery(query)
                    .addParameter("p", id)
                    .executeAndFetch(Liste.class)
                    .get(0);

        } catch (Sql2oException e) {
            e.printStackTrace();
        }

        return l;
    }

    public int getIdFromTitre(String t){
        int id_to_return = 0;

        try {
            String query = "SELECT * FROM liste WHERE titre = :ti LIMIT 1";
            id_to_return = co.createConnection().createQuery(query)
                    .addParameter("ti", t)
                    .executeAndFetch(Liste.class).get(0).getId();

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return id_to_return;
    }

    public List<Liste> getListesFromUser(User u){
        List<Liste> listes = new ArrayList<Liste>();

        try{
            String query = "SELECT * FROM liste WHERE id_user = :z ORDER BY ID ASC";
            listes = co.createConnection().createQuery(query)
                    .addParameter("z", u.getID())
                    .executeAndFetch(Liste.class);

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return listes;
    }
}
