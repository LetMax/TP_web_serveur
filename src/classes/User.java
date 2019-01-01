package classes;

import DAOs.DAO_User;

public class User {

    private int id;
    private String nom;
    private String mdp;

    public User(){}

    public User(String nomp, String mdpp){
        this.nom = nomp;
        this.mdp = mdpp;
        DAO_User dao = new DAO_User();
        dao.create(this);
        this.id = dao.getUserFromNom(nomp).getID();
    }

    public User(String nomp, String mdpp, int idp){
        this.id = idp;
        this.mdp = mdpp;
        this.nom = nomp;
        DAO_User dao = new DAO_User();
        dao.create(this);
    }

    @Override
    public String toString(){
        return "Utilisateur #" + this.getID() + " : " + this.getNom() + " (" + this.getMdp() + ")";
    }

    public int getID(){ return this.id; }
    public String getNom(){ return this.nom; }
    public String getMdp(){ return this.mdp; }

    public void setID(int id_to_set){ this.id = id_to_set; }
    public void setID(String nom_to_set){ this.nom = nom_to_set; }
    public void setMdp(String mdp){ this.mdp = mdp; }
}
