
/**
 * Exercices sur les methodes de la classe String - solutions
 *
 * @author Melanie Lord
 * @version novembre 2015
 */
public class ExercicesStringSolutions {
   
   /**
    * Retourne le caractere a la position pos dans s. Si la positions pos
    * n'existe pas dans s ou si s est null, retourne le caractere '*'.
    *
    * @param s la chaine dans laquelle on veut chercher le caractere a la
    *          position pos.
    * @param pos la position du caractere a retourner dans s.
    * @return char le caractere a la position pos dans s.
    */
   public static char caracPos(String s, int pos) {
      char c = '*'; 
      if (s != null && pos >= 0 && pos < s.length()) {
         c = s.charAt(pos);
      } 
      return c;
   }

   /**
    * Retourne une nouvelle chaine identique a s mais dont la premiere lettre
    * a ete transformee en majuscule. Si la premiere lettre de s est une
    * majuscule (ou n'est pas une lettre), la nouvelle chaine retournee est 
    * identique a s. Si s est vide, la chaine retournee est la chaine vide.
    * Si s est null, la methode retourne null.
    *
    * @param s la chaine dont on veut changer la premiere lettre en majuscule.
    * @return une nouvelle chaine identique a s mais dont la premiere
    *         lettre a ete transformee en majuscule ou null si s est null.
    */
   public static String premLettreMajuscule(String s) {
      char car; 
      String chaine = null;
      if (s != null) {
         chaine = "";
         if (!s.isEmpty()) {
            car = s.charAt(0);
            car = Character.toUpperCase(car);
            chaine = car + s.substring(1);
         }
      }
      return chaine;
   }
   
   //SOLUTION 2
   public static String premLettreMajuscule2(String s) {
      String chaine = null;
      if (s != null) {
         chaine = "";
         if (!s.isEmpty()) {
            chaine = s.substring(0, 1).toUpperCase() 
                    + s.substring(1);
         }
      }
      return chaine; 
   }
   
   /**
    * Saisit et valide une chaine de caracteres de longueur comprise entre
    * lngMin et lngMax inclusivement. Si le parametre lngMin ou lngMax
    * n'est pas valide, la methode retourne null.
    * 
    * @param msgSoll le message de sollicitation pour la chaine a valider
    * @param msgErr le message d'erreur pour une chaine invalide.
    * @param lngMin la longueur minimale valide pour la chaine a saisir
    *               lngMin est valide si lngMin >= 0 et lngMin <= lngMax. 
    * @param lngMax la longueur maximale valdie pour la chaine a saisir
    *               lngMax est valide si lngMax >= 0 et lngMax >= lngMin.      
    * @return 
    */
   public static String validerString (String msgSoll, String msgErr, 
           int lngMin, int lngMax) {
      String chaine = null; 
      
      //verifier les parametres.
      if (lngMin >= 0 && lngMax >= 0 && lngMax >= lngMin) {
         do {
            System.out.print(msgSoll);
            chaine = Clavier.lireString();
            System.out.println(chaine.length());
            if (chaine.length() < lngMin || chaine.length() > lngMax) {
               System.out.println(msgErr);
            }
         } while (chaine.length() < lngMin || chaine.length() > lngMax);
      }
      return chaine;
   }
   
   /**
    * Retourne vrai si la chaine donnee represente un nombre entier positif, 
    * faux sinon.
    * @param chaine la chaine a verifier.
    * @return true si chaine represente un entier positif, faux sinon.
    */
   public static boolean estUnNombreEntierPositif (String chaine) {
      int i = 0;
      boolean estNbr = false;
      char car;
      
      if (chaine != null && !chaine.isEmpty()) {
         estNbr = true;
         while (i < chaine.length() && estNbr) {
            car = chaine.charAt(i);
            estNbr = car >= '0' && car <= '9';
            i++;
         }
      }
      return estNbr;
   }

   /**
    * Formate un numero de telephone sous le format (###) ###-####
    *
    * @param indReg un indicatif regional compose de 3 chiffres.
    * @param telephone un numero de telephone compose de 7 chiffres.
    * @return le numero de telephone formate ou null si un des parametre  
    *         iest nvalide.
    */
   public static String formaterTelephone(String indReg, String telephone) {
      String tel = null;

      if ((indReg != null && telephone != null)
              && (indReg.length() == 3 && estUnNombreEntierPositif(indReg) 
              && telephone.length() == 7) 
              && estUnNombreEntierPositif(telephone)) {
         
         tel = "(" + indReg + ") " + telephone.substring(0, 3) + "-"
              + telephone.substring(3, 7);
      }
      return tel;
   }
   
   /**
    * Extrait et retourne la sous-chaine de la chaine donnee qui se trouve
    * entre la premiere occurrence du caractere debut dans chaine et la premiere 
    * occurrence du caractere fin qui se trouve apres le caractere debut dans
    * chaine. Si debut ou fin (apres debut) n'existe pas dans la chaine, retourne 
    * null.
    *
    * @param chaine la chaine de laquelle on veut extraire la sous-chaine a 
    *               retourner.
    * @param debut le caractere debut de la sous-chaine (non compris dans la 
    *              sous-chaine retournee).
    * @param fin le caratere fin de la sous-chaine (non compris dans la 
    *              sous-chaine retournee).
    * @return la sous-chaine de chaine entre debut et fin ou null si debut et fin 
    *         n'existe pas.
    */
   public static String extraireSousChaine(String chaine, char debut, char fin) {
      int indexDebut;
      int indexFin;
      String sousChaine = null;

      if (chaine != null) {
         indexDebut = chaine.indexOf(debut);
         if (indexDebut != -1) {
            indexFin = chaine.indexOf(fin, indexDebut + 1);
            if (indexFin != -1) {
               sousChaine = chaine.substring(indexDebut + 1, indexFin);
            }
         }
      }
      return sousChaine;
   }
   
      /**
    * Cette methode retourne une chaine de caracteres indiquant la "sorte" de 
    * la chaine donnee en parametre.
    * - Si tous les caracteres de chaine sont des chiffres, elle retourne la 
    *   chaine "numerique".
    * - Si tous les caracteres de chaine sont des lettres (maj. ou min.), elle 
    *   retourne la chaine "alphabetique".
    * - Si chaine ne contient que des lettres et des chiffres (et au moins une
    *   lettre et au moins un chiffre), elle retourne la chaine "alphanumerique".
    * - Si chaine est vide, elle retourne la chaine "vide".
    * - Si chaine est null, elle retourne la chaine "nulle".
    * 
    * @param chaine la chaine dont on veut retourner la sorte.
    * @return la sorte de la chaine donnee en parametre.
    */
   public static String sorte(String chaine) {
      String sorteChaine;
      int nbrChiffres = 0;
      int nbrLettres = 0;
      int nbrAutres = 0;
      char c;

      if (chaine == null) {
         sorteChaine = "nulle";
      } else if (chaine.length() == 0) {
         sorteChaine = "vide";
      } else {
         for (int i = 0; i < chaine.length(); i++) {
            c = chaine.charAt(i);
            if (Character.toLowerCase(c) >= 'a' && Character.toLowerCase(c) <= 'z') {
               nbrLettres++;
            } else if (c >= '0' && c <= '9') {
               nbrChiffres++;
            } else {
               nbrAutres++;
            }
         }

         if (nbrAutres != 0) {
            sorteChaine = String.valueOf(chaine.length());
         } else if (nbrChiffres > 0 && nbrLettres > 0) {
            sorteChaine = "alphanumerique";
         } else if (nbrLettres > 0) {
            sorteChaine = "alphabetique";
         } else {
            sorteChaine = "numerique";
         }
      }
      return sorteChaine;
   }
   
   /**
    * Retourne la chaine cryptee de la chaine a crypter donnee en parametre, 
    * selon les regles suivantes :
    * 1. Chacune de lettres min. ('a' a 'z') de chaineACrypter doit etre 
    *    remplacee par le caractere a codeCryptage positions APRES cette lettre
    *    minuscule (en suivant l'ordre Unicode). Par exemple, si codeCryptage
    *    est 2, la lettre est 'a' sera remplacee par 'c'.
    * 
    * 2. Chacune de lettres maj. ('A' a 'Z') de chaineACrypter doit etre 
    *    remplacee par le caractere a codeCryptage positions AVANT cette lettre
    *    minuscule (en suivant l'ordre Unicode). Par exemple, si codeCryptage
    *    est 5, la lettre est 'm' sera remplacee par 'h'.
    * 
    * 3. Chacun des chiffres ('0' a '9') de chaineACrypter doit etre remplace
    *    par (7 modulo codeCryptage) positions APRES ce chiffre (en suivant
    *    l'ordre des Unicode). Par exemple, le codeCryptage est 4, le caractere
    *    '1' sera remplace par '4' (7 modulo 4 = 3).
    * 
    * 4. Tous les autres caracteres de chaineACrypter demeurent inchanges.
    * 
    * NOTE : Pour cet exercice, on suppose que le codeCryptage donne est un 
    *        entier > 0 ET que chaineACrypter n'est pas null, n'est pas vide  
    *        et peut effectivement etre cryptee selon les regles donnees.
    * 
    * @param chaineACrypter la chaine qu'on veut crypter.
    * @param codeCryptage le code pour crypter la chaine.
    * @return la chaine cryptee.
    */
   public static String crypter (String chaineACrypter, int codeCryptage) {
      char c;
      String chaineCryptee = "";
      
      for (int i = 0; i < chaineACrypter.length() ; i++) {
         c = chaineACrypter.charAt(i);
         
         if (c >= 'a' && c <= 'z') {
            chaineCryptee = chaineCryptee + (char)(c + codeCryptage);
         } else if (c >= 'A' && c <= 'Z') {
            chaineCryptee = chaineCryptee + (char)(c - codeCryptage);
         } else if (c >= '0' && c <= '9') {
            chaineCryptee = chaineCryptee + (char)(c + 7 % codeCryptage);
         } else {
            chaineCryptee = chaineCryptee + c;
         }
      }
      return chaineCryptee;
   }

}
