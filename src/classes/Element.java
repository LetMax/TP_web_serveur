package classes;

import DAOs.DAO_Element;

import java.util.Date;

public class Element {

    private int id;
    private int id_liste;
    private Date date_crea;
    private Date date_modif;
    private String description;
    private String titre;
    private int statut; //1 : a faire | 2 : fait | 3 : supprimé

    public Element(){}

    public Element(String titrep, String descriptionp, int idListep){
        this.titre = titrep;
        this.description = descriptionp;
        this.id_liste = idListep;
        this.date_crea = new Date();
        this.date_modif = this.date_crea;
        this.statut = 1;

        DAO_Element dao = new DAO_Element();
        dao.create(this);
        this.id = dao.getIdfromElement(this);
    }

    public int getId(){ return this.id; }
    public int getIdListe(){ return this.id_liste; }
    public String getTitre(){ return this.titre; }
    public String getDescription(){ return this.description; }
    public Date getDateCrea(){ return this.date_crea; }
    public Date getDateModif(){ return this.date_modif; }
    public int getStatut(){ return this.statut; }

    public void setId(int idp){ this.id = idp; }
    public void setIdListe(int idListep){ this.id_liste = idListep; }
    public void setTitre(String titre_to_set){ this.titre = titre_to_set; this.date_modif = new Date(); }
    public void setDescription(String description_to_set){ this.description = description_to_set; this.date_modif = new Date(); }
    public void setDateCrea(Date date_to_set){ this.date_crea = date_to_set; }
    public void setDateModif(Date date_to_set){ this.date_modif = date_to_set; }
    public void setStatut(int statutp){ this.statut = statutp; this.date_modif = new Date();}
}
