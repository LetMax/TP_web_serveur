package DAOs;

import java.util.*;
import classes.User;
import org.sql2o.*;

public class DAO_User {
    DAO_Connect co = new DAO_Connect();

    public User create(User u){
        try {
            String query = "INSERT INTO user(nom, mdp) values(:nomp, :mdpp)";
            co.createConnection().createQuery(query)
                    .addParameter("nomp", u.getNom())
                    .addParameter("mdpp", u.getMdp())
                    .executeUpdate();
        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return u;
    }

    public void delete(User u){
        try{
            String query = "DELETE FROM user WHERE id = :idp";
            co.createConnection().createQuery(query)
                    .addParameter("idp", u.getID())
                    .executeUpdate();
        } catch (Sql2oException e){
            e.printStackTrace();
        }
    }

    public User getUserFromNom(String nomp){
        User u_to_return = new User();
        List<User> liste;

        try{
            String query = "SELECT * FROM user WHERE nom = :n LIMIT 1";
            liste = co.createConnection().createQuery(query)
                    .addParameter("n", nomp)
                    .executeAndFetch(User.class);

            if(!liste.isEmpty()){
                u_to_return = liste.get(0);
            }

        } catch (Sql2oException e){
            e.printStackTrace();
        }

        return u_to_return;
    }
}
