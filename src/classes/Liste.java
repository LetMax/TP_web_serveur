package classes;

import DAOs.DAO_Liste;

public class Liste {

    private int id;
    private int id_user;
    private String titre;
    private String description;
    private int id_liste_mere;

    public Liste(){}

    public Liste(String titrep, String descriptionp, int id_Userp){
        this.titre = titrep;
        this.description = descriptionp;
        this.id_user = id_Userp;
        this.id_liste_mere = 0;

        DAO_Liste dao = new DAO_Liste();
        dao.create(this);
        this.id = dao.getIdFromTitre(titrep);
    }

    public Liste(String titrep, String descriptionp, int id_Userp, int id_liste_merep){
        this.titre = titrep;
        this.description = descriptionp;
        this.id_user = id_Userp;
        this.id_liste_mere = id_liste_merep;

        DAO_Liste dao = new DAO_Liste();
        dao.create(this);
        this.id = dao.getIdFromTitre(titrep);
    }

    public Liste(int idp, String titrep, String descriptionp, int id_Userp){
        this.id = idp;
        this.titre = titrep;
        this.description = descriptionp;
        this.id_user = id_Userp;
    }

    @Override
    public String toString(){
        return "Liste #" + this.getId() + " (Id_user : " + this.getIdUser() + ", id_liste_mere : " + this.getId_liste_mere() + ") " + " : " + this.getTitre() + " (" + this.getDescription() + ")";
    }

    public int getId(){ return this.id; }
    public String getTitre(){ return this.titre; }
    public String getDescription(){ return this.description; }
    public int getIdUser(){ return this.id_user; }
    public int getId_liste_mere(){ return  this.id_liste_mere; }


    public void setId(int id_to_set){ this.id = id_to_set; }
    public void setTitre(String titre_to_set){ this.titre = titre_to_set; }
    public void setDescription(String description_to_set){ this.description = description_to_set; }
    public void setIdUser(int id_User_to_set){ this.id_user = id_User_to_set; }
    public void setId_liste_mere(int id_liste_mere_to_set){ this.id_liste_mere = id_liste_mere_to_set; }

}
