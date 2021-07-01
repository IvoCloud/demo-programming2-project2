import com.sun.javafx.image.impl.BaseIntToByteConverter;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

//IVAYLO IVANOV IVAI08039506
//INF1120-10

public class ModuleRecherche {

    //CONSTANTES

    public static final String MSG_INTRO = "Cette application permet d'executer diverses requetes pour rechercher des livres\n" +
            "dans une bibliotheque donnee. Elle permet plus particulierement de faire des\n" +
            "recherches par categorie(s), par expression dans le titre, par auteur, et par\n" +
            "periode de publication.";

    public static final String MSG_MENU = "-----------------\n"+
            "MENU DE RECHERCHE\n" +
            "-----------------\n" +
            "1. Recherche par categorie(s)\n" +
            "2. Recherche par titre\n" +
            "3. Recherche par auteur\n" +
            "4. Recherche par periode\n" +
            "5. Quitter\n\n" +
            "Entrez votre choix :";

    public static final String MSG_ERR_MENU = "Erreur, choix invalide! Recommencez...\n\n";
    public static final String MSG_FIN = "AUREVOIR !";


    public static final String OPT1_MSG = "*** RECHERCHE PAR CATEGORIE(S) ***\n\n";
    public static final String OPT1_SOL = "Recherche par (C)onjonction ou (D)isjonction : ";
    public static final String OPT1_ERR = "Erreur, entrez C ou D ! Recommencez...\n";
    public static final String OPT1_ERR_CATEGORIE = "Erreur, numero de categorie invalide! Recommencez...\n";
    public static final String OPT1_SOL_CATEGORIES = "Entrez un numero de categorie (0 pour terminer) : ";
    public static final String OPT1_CONDITION = "CD";
    public static final String OPT1_CONDITION_CATEGORIES = "0123456";

    public static final String OPT2_MSG = "*** RECHERCHE PAR TITRE ***\n\n";
    public static final String OPT2_SOL = "Entrez le titre ou une partie du titre (<ENTREE> pour terminer) : ";
    public static final String OPT2_ERR = "Erreur, le titre doit contenir au moins 5 caracteres ! Recommencez...\n";

    public static final String OPT3_MSG = "*** RECHERCHE PAR AUTEUR ***\n\n";
    public static final String OPT3_SOL = "Entrez le nom de l'auteur (<ENTREE> pour terminer) : ";
    public static final String OPT3_ERR = "Erreur, le nom de l'auteur doit contenir au moins 3 caracteres ! Recommencez...\n";

    public static final String OPT4_MSG = "*** RECHERCHE PAR PERIODE ***\n\n";
    public static final String OPT4_SOL = "Entrez l'annee du debut de la periode (0 pour terminer) : ";
    public static final String OPT4_ERR = "Erreur, l'annee doit etre un entier entre 1900 et 2019 inclusivement! Recommencez...\n";

    public static final String OPT_FIN = "RECHERCHE ANNULEE.\n";
    public static final String OPT_AUCUNE_LIVRE = "AUCUNE LIVRE TROUVEE!\n";

    public static final String OPT1_LISTE_CATEGORIES = "LISTE DES CATEGORIES :\n" +
            "   1. Science fiction\n" +
            "   2. Romance\n" +
            "   3. Thriller\n" +
            "   4. Policier\n" +
            "   5. Humour\n" +
            "   6. Drame\n";

    //METHODES

    /**
     *  Cette boucle verifie si une chaine de caractere de longueur 1
     *  se trouve dans une autre chaine fournie en tant  que parametre.
     * -Si la chaine saisie ne fais pas partie de la chaine de base, on
     * affiche un message d'erreur et un message pour demander l'utilisateur
     * d'entrer une selection a nouveau.
     *
     * @param chaineChoix la chaine saisie par l'utilisateur.
     * @param chaineCondition la chaine principale ou on verifie le choix.
     * @param msgSol message de solicitation.
     * @param msgErreur message d'erreur
     * @return le choix saisie et valide
     */
    public static String boucleVerificationEntree(String chaineChoix, String chaineCondition, String msgSol, String msgErreur){
        while (!chaineCondition.contains(chaineChoix)||chaineChoix.equals("")||chaineChoix.length()>1){
            System.out.print(msgErreur+"\n"+msgSol);
            chaineChoix = Clavier.lireString().toUpperCase();
            System.out.println();
        }
        return chaineChoix;
    }
    /**
     *  Cette boucle verifie si la longueur d'une chaine de caractere est plus longue
     *  qu'une certaine limite determinee par limiteLongueur
     * -Si la chaine saisie est plus courte que la limite desiree, on
     * affiche un message d'erreur et un message pour demander l'utilisateur
     * d'entrer une selection a nouveau.
     *
     * @param chaineChoix la chaine saisie par l'utilisateur.
     * @param limiteLongueur la chaine principale ou on verifie le choix.
     * @param msgSol message de solicitation.
     * @param msgErreur message d'erreur
     * @return le choix saisie et valide
     */
    public static String boucleVerificationEntree(String chaineChoix, int limiteLongueur, String msgSol, String msgErreur){
        while (chaineChoix.length()<limiteLongueur&&!chaineChoix.equals(""))
        {
            System.out.println();
            System.out.print(msgErreur+msgSol);
            chaineChoix = Clavier.lireString().toUpperCase();
        }
        return chaineChoix;
    }

    /**
     *  Cette methode retourne une chaine de caractere extraite a partir d'un mot ou phrase cle
     *  selon le parametre indexDebut. On peut choisir si le mot cle est le nom du livre, l'auteur,
     *  le genre ou l'annee.
     *  Apres avoir trouve des correspondances du mot cle, la methode extrait la ligne entiere et l'ajoute
     *  dans une chaine de caractere texteRetour.
     * @param srcText chaine de caractere dont on recherche et extrait une autre chaine
     * @param critere mot cle qu'on recherche dans srcText
     * @param indexDebut l'indice qui determine si on cherche:
     *                   0 : Nom du livre
     *                   1 : Auteur
     *                   2 : Annee
     *                   3 : Genre
     * @return texteRetour une chane de caractere extraite de srcTexte
     */
    public static String rechercheLivre(String srcText, String critere,String indexDebut){
        String texteRetour="";
        int indexCritere;
        int indexLnLigne;
        int indexLnFinLigne;
        int indexT1;
        int indexT2;
        int indexT3;
        int indexTabDebut=0;
        int indexTabFin=0;
        if(srcText.contains(critere)) {
            indexCritere = srcText.indexOf(critere);
            while (indexCritere <= srcText.lastIndexOf(critere)) {
                indexLnLigne = srcText.lastIndexOf("\n",indexCritere);
                indexT1 = srcText.indexOf("\t", indexLnLigne);
                indexT2 = srcText.indexOf("\t", srcText.indexOf("\t", indexT1+1));
                indexT3 = srcText.indexOf("\t", srcText.indexOf("\t", indexT2+1));
                indexLnFinLigne = srcText.indexOf("\n",indexLnLigne+1);
                switch (indexDebut) {
                    case "0":
                        indexTabDebut = indexLnLigne;
                        indexTabFin = indexT1;
                        break;
                    case "1":
                        indexTabDebut = indexT1;
                        indexTabFin = indexT2;
                        break;
                    case "2":
                        indexTabDebut = indexT2;
                        indexTabFin = indexT3;
                        break;
                    case "3":
                        indexTabDebut = indexT3;
                        indexTabFin = indexLnFinLigne;
                        break;
                }

                if (indexTabFin > indexCritere&&indexCritere > indexTabDebut) {
                    texteRetour = texteRetour + srcText.substring(srcText.lastIndexOf("\n", indexCritere), srcText.indexOf("\n", indexCritere));
                }
                if(indexCritere < srcText.lastIndexOf(critere)) {
                    indexCritere = srcText.indexOf(critere, indexCritere + 1);
                }else {indexCritere+=1;}
            }
        }else{texteRetour = "";}
        return texteRetour;
    }

    /**
     * Cette methode retourne le dernier caractere d'une ligne
     * de caracteres fournie comme parametre.
     * @param texte chaine de caractere dont on retorune
     *              le dernier caractere.
     * @return dernier chr. de texte.
     */
    public static String retourneDernierChar(String texte){
        return  Character.toString(texte.charAt(texte.length()-1));
    }

    /**
     *  Cette methode transforme une chaine de caractere dont
     *  chaque ligne est formee de:
     * \n Livre \t Auteur \t2 Annee \t3 Genre \n
     * et reorganise les lignes en format:
     * Livre (Annee), Auteur [ Genre ]
     * @param texte chaine de caractere a formatter
     * @return une chain de caractere formatee
     */
    public static String formatterRecherche(String texte){
        String livre;
        String annee;
        String auteur;
        String genre;
        String ligneFormat="";
        texte=texte+"\n";
        int indexLn = texte.indexOf("\n");
        int indexT1;
        int indexT2;
        int indexT3;

        if (!texte.equals("")) {
            do {
                indexT1 = texte.indexOf("\t", indexLn);
                indexT2 = texte.indexOf("\t", indexT1 + 1);
                indexT3 = texte.indexOf("\t", indexT2 + 1);

                livre = texte.substring(indexLn, texte.indexOf("\t", indexLn));
                annee = texte.substring(indexT2, indexT3).trim();
                auteur = texte.substring(indexT1, indexT2).trim();
                genre = texte.substring(indexT3, texte.indexOf("\n", indexLn + 1)).trim();

                ligneFormat += livre + " (" + annee + ") ," + auteur + " [ " + genre + " ] ";
                ligneFormat = ligneFormat.replace("  ", " ");
                indexLn = texte.indexOf("\n", texte.indexOf("\n", indexLn + 1));
            } while (indexLn < texte.lastIndexOf("\n"));
        }
        return ligneFormat;
    }

    /**
     * Cette methode enleve le dernier caractere d'une ligne
     * de caracteres fournie comme parametre.
     * @param texte chaine de caractere dont on enleve le dernier caractere.
     * @return texte sans le dernier caractere.
     */
    public static String enleverDernierChar(String texte) {
        texte = texte.substring(0,texte.length()-1);
        return texte;
    }

    /**
     *  Cette methode prends deux chaines delimitees par \n a chaque ligne, et
     *  recherche ligne par ligne s'il y a des correspondances de disjonction dans srcText.
     *  -Si il y  a des correspondances, la ligne correspondante est enlevee du
     *  srcText et remplacee par "".
     *  A la fin, la methode retourne srcText moins disjonction.
     * @param srcText chaine de caractere a formatter
     * @param discjonction chaine de caractere a formatter
     * @return chaine de caractere srcText
     */
    public static String disjonctionTexte(String srcText, String discjonction){
        int indexLn = discjonction.indexOf("\n");
        String ligne="";
        srcText = srcText.toUpperCase();
        discjonction = discjonction.toUpperCase();
        while(indexLn<discjonction.lastIndexOf("\n")){
            if(indexLn==discjonction.indexOf("\n")&&indexLn!=0)
            {
                discjonction="\n"+discjonction;
                indexLn = discjonction.indexOf("\n");
            }
            ligne = discjonction.substring(indexLn, discjonction.indexOf("\n", indexLn + 1)).toUpperCase() + "\n";
            srcText = srcText.replace(ligne,"\n");
            indexLn = discjonction.indexOf("\n",indexLn+1);
        }
        return srcText;
    }

    /**
     *  Cette methode verifie si la chaine de caractere en parametre est composee
     *  uniquement des chiffres et retourne une boolean vrai ou faux.
     *  -Si source est compposee des chiffres - vrai
     *  -Si source n'est pas compposee des chiffres - faux
     * @param source chaine de caractere qu'on verifie si est composee uniquement de chiffres
     * @return estNumero boolean vrai ou faux
     */
    public static boolean verifierChiffre(String source){
        String numeros = "1234567890";
        boolean estNumero=true;
        for (int i=0;i<source.length();i++){
            estNumero &= numeros.contains(Character.toString(source.charAt(i)));
        }
        return estNumero;
    }

    //    BOUCLE MAIN

    public static void main (String [] args){
        String biblo = UtilitaireTP2.lireBibliotheque();
        biblo = "\n"+ biblo.toUpperCase() +"\n";
        String copyBiblio;                  //Une copie du texte bibli qio contiens les livres.
        String texteDisjonction;            //Chaine finale pour Option 1 Disjonction
        String choixMenuPrincipal;          //Choix du menu saisi par l'utilisateur (1-5)
        String saisirChoix;                 //Tout autre ligne saisi par l'utilisateur pour faire des recherches selon les options selectionnees
        String categorieRecherche;          //Chaine qui saisit les categories de recherche desirees par l'utilisateur pour Option1
        String categories;                  //Chaine qui retourne les categories de recherche selectionnees par l'utilisateur pour Option1
        String categorieMot;                //Chaine qui corresponds a une categorie qui est ensuite ajoutee a categories


        System.out.println(MSG_INTRO);

        //BOUCLE MENU PRINCIPAL
        do {
            copyBiblio=biblo;
            System.out.println();
            System.out.print(MSG_MENU);
            choixMenuPrincipal = Clavier.lireString();
            System.out.println();
            boucleVerificationEntree(choixMenuPrincipal,OPT1_CONDITION_CATEGORIES, MSG_MENU, MSG_ERR_MENU);
            categories="";
            categorieRecherche="";
            texteDisjonction="";

            //SWITCH MENU PRINCIPAL 1 A 5
            switch (choixMenuPrincipal){
                //CHOIX 1 RECHERCHE PAR GENRE
                case ("1"):
                    System.out.print(OPT1_MSG + OPT1_SOL);
                    saisirChoix = Clavier.lireString().toUpperCase();
                    saisirChoix = boucleVerificationEntree(saisirChoix, OPT1_CONDITION, OPT1_SOL, OPT1_ERR);
                    System.out.println();
                    switch(saisirChoix) {
                        //CHOIX 1 RECHERCHE PAR GENRE - CONJONCTION
                        case ("C"):
                            System.out.println(OPT1_LISTE_CATEGORIES);
                            do {
                                System.out.print(OPT1_SOL_CATEGORIES);
                                saisirChoix = Clavier.lireString();
                                saisirChoix = boucleVerificationEntree(saisirChoix, OPT1_CONDITION_CATEGORIES, OPT1_SOL_CATEGORIES, OPT1_ERR_CATEGORIE);
                                if(!categorieRecherche.contains(saisirChoix)) {
                                    categorieRecherche = categorieRecherche + saisirChoix;
                                }
                            }while (!saisirChoix.equals("0"));
                            //VERIFICATION DE LA SELECTION DES CATEGORIES.
                            //LA CONJOCTION PRENDS DES EXTRAITS DU TEXTE PRINCIPAL - copyBiblio
                            //ET REMPLACE LE TEXTE ORIGINAL - copyBiblio PAR L'EXTRAIT TROUVEE
                            //LA BOUCLE REPETE L'ACTION CETTE FOIS-CI AVEC LA NOUVELLE - copyBiblio
                            // JUSQU'A saisirChoix EST VIDE
                            if(!categorieRecherche.equals("0")){
                                categorieRecherche=categorieRecherche.replace("0","");
                                while (categorieRecherche.length()>0) {
                                    categorieMot = OPT1_LISTE_CATEGORIES.substring(OPT1_LISTE_CATEGORIES.indexOf(" ", OPT1_LISTE_CATEGORIES.indexOf(retourneDernierChar(categorieRecherche))),
                                            OPT1_LISTE_CATEGORIES.indexOf("\n",OPT1_LISTE_CATEGORIES.indexOf(retourneDernierChar(categorieRecherche)))).toUpperCase().trim();
                                    switch (retourneDernierChar(categorieRecherche)) {
                                        case ("1"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                        case ("2"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                        case ("3"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                        case ("4"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                        case ("5"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                        case ("6"):
                                            categories += " et " + categorieMot + " et ";
                                            copyBiblio = rechercheLivre(copyBiblio,categorieMot,"3");
                                            break;
                                    }
                                    categorieRecherche = enleverDernierChar(categorieRecherche);
                                }
                                System.out.println();
                                categories = categories.replace("  "," ");
                                categories = categories.replace("et et","et");
                                categories = categories.substring(3,categories.length()-4);
                                System.out.println("Les categories de recherche sont :" + categories);
                                if (!copyBiblio.equals("")) {
                                    System.out.println(formatterRecherche(copyBiblio));
                                    System.out.println();
                                }else{
                                    System.out.println();
                                    System.out.println(OPT_AUCUNE_LIVRE);
                                    Clavier.lireFinLigne();
                                }
                            }else {
                                System.out.println(OPT_FIN);
                            }
                            break;
                        //CHOIX 1 RECHERCHE PAR GENRE - DISJONCTION
                        case ("D"):
                            System.out.println(OPT1_LISTE_CATEGORIES);
                            do {
                                System.out.print(OPT1_SOL_CATEGORIES);
                                saisirChoix = Clavier.lireString();
                                saisirChoix = boucleVerificationEntree(saisirChoix, OPT1_CONDITION_CATEGORIES, OPT1_ERR_CATEGORIE, OPT1_SOL_CATEGORIES);
                                if(!categorieRecherche.contains(saisirChoix)) {
                                    categorieRecherche = categorieRecherche + saisirChoix;
                                }
                            }while (!saisirChoix.equals("0"));
                            //VERIFICATION DE LA SELECTION DES CATEGORIES.
                            //LA DISJONCTION PRENDS DES EXTRAITS DU TEXTE PRINCIPAL - copyBiblio
                            //ET LES AJOUTE A texteDisjonction.
                            //LA METHODE disjonctionTexte ENLEVE texteDisjonction DE copyBiblio
                            //LA BOUCLE REPETE L'ACTION CETTE FOIS-CI AVEC LA NOUVELLE - copyBiblio
                            // JUSQU'A saisirChoix EST VIDE
                            if(!categorieRecherche.equals("0")){
                                categorieRecherche=categorieRecherche.replace("0","");
                                while (categorieRecherche.length()>0) {
                                    categorieMot = OPT1_LISTE_CATEGORIES.substring(OPT1_LISTE_CATEGORIES.indexOf(" ", OPT1_LISTE_CATEGORIES.indexOf(retourneDernierChar(categorieRecherche))),
                                            OPT1_LISTE_CATEGORIES.indexOf("\n",OPT1_LISTE_CATEGORIES.indexOf(retourneDernierChar(categorieRecherche)))).toUpperCase().trim();
                                    switch (retourneDernierChar(categorieRecherche)) {
                                        case ("1"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                        case ("2"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                        case ("3"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                        case ("4"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                        case ("5"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                        case ("6"):
                                            categories += " ou " + categorieMot + " ou ";
                                            texteDisjonction += rechercheLivre(copyBiblio,categorieMot,"3");
                                            copyBiblio = disjonctionTexte(copyBiblio,rechercheLivre(copyBiblio,categorieMot,"3"));
                                            break;
                                    }
                                    categorieRecherche = enleverDernierChar(categorieRecherche);
                                }
                                System.out.println();
                                categories = categories.replace("  "," ");
                                categories = categories.replace("ou ou","ou");
                                categories = categories.substring(3,categories.length()-4);
                                System.out.println("Les categories de recherche sont :" + categories);
                                if(!texteDisjonction.equals("")) {
                                    System.out.println(formatterRecherche(texteDisjonction));
                                    System.out.println();
                                }else{
                                    System.out.println();
                                    System.out.println(OPT_AUCUNE_LIVRE);
                                    Clavier.lireFinLigne();
                                }
                            }else {
                                System.out.println(OPT_FIN);
                            }
                            break;
                    }
                    break;
                //CHOIX 2 RECHERCHE PAR TITRE
                case ("2"):
                    System.out.print(OPT2_MSG + OPT2_SOL);
                    saisirChoix = Clavier.lireString().toUpperCase();
                    saisirChoix = boucleVerificationEntree(saisirChoix, 5, OPT2_SOL, OPT2_ERR);
                    if(!saisirChoix.equals("")) {
                        if(copyBiblio.contains(saisirChoix)) {
                            copyBiblio = rechercheLivre(copyBiblio, saisirChoix, "0");
                            System.out.println();
                            System.out.println(formatterRecherche(copyBiblio));
                            System.out.println();
                        }else{
                            System.out.println();
                            System.out.println(OPT_AUCUNE_LIVRE);
                            Clavier.lireFinLigne();
                        }
                    }
                    break;
                //CHOIX 3 RECHERCHE PAR AUTEUR DANS copyBiblio
                case ("3"):
                    System.out.print(OPT3_MSG + OPT3_SOL);
                    saisirChoix = Clavier.lireString().toUpperCase();
                    boucleVerificationEntree(saisirChoix, 3, OPT3_SOL, OPT3_ERR);
                    if(!saisirChoix.equals("")) {
                        saisirChoix = saisirChoix+"\t";
                        if(copyBiblio.contains(saisirChoix)) {
                            copyBiblio = rechercheLivre(copyBiblio, saisirChoix, "1");
                            System.out.println();
                            System.out.println(formatterRecherche(copyBiblio));
                            System.out.println();
                        }else{
                        System.out.println();
                        System.out.println(OPT_AUCUNE_LIVRE);
                        Clavier.lireFinLigne();
                    }
                    }else{
                        System.out.println(OPT_FIN);
                    }
                    break;
                //CHOIX 4 RECHERCHE PAR ANNEE - VERIFIE SI L'ENTREE DE L'UTILISATEUR (String) EST UN CHIFFRE DE LONGUEUR 4
                // ENTRE 1990 ET 2019 ET RECHERCHE DANS copyBiblio
                case ("4"):
                    System.out.print(OPT4_MSG + OPT4_SOL);
                    saisirChoix = Clavier.lireString();

                    while(saisirChoix.length()!=4&&!saisirChoix.equals("0")) {
                        System.out.print("Erreur! Entrez un numero valide (Entre 1990 et 2019, ou 0 pour terminer): ");
                        saisirChoix = Clavier.lireString();
                    }

                    while (!(verifierChiffre(saisirChoix)) || ((Integer.parseInt(saisirChoix) < 1900 || Integer.parseInt(saisirChoix) > 2019) && !saisirChoix.equals("0"))) {
                        System.out.print("Erreur! Entrez un numero valide (Entre 1990 et 2019, 0 pour terminer): ");
                        saisirChoix = Clavier.lireString();
                        if(saisirChoix.equals("")){
                            saisirChoix = "0000";
                        }
                    }
                    if(!saisirChoix.equals("0")) {
                        saisirChoix = saisirChoix+"\t";
                        if(copyBiblio.contains(saisirChoix)) {
                            copyBiblio = rechercheLivre(copyBiblio, saisirChoix, "2");
                            System.out.println();
                            if(!copyBiblio.equals("")) {
                                System.out.println(formatterRecherche(copyBiblio));
                                System.out.println();
                            }else{
                                System.out.println();
                                System.out.println(OPT_AUCUNE_LIVRE);
                                Clavier.lireFinLigne();
                            }
                        }
                    }else{
                        System.out.println(OPT_FIN);
                    }
                    break;
            }
        //CHOIX 5 FIN PROGRAMME
        }while (!choixMenuPrincipal.equals("5"));
        System.out.println(MSG_FIN);
    }
}
