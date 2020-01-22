import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;
import java.util.Random;
import java.util.Scanner;
import MG2D.audio.*;
    
//import java.lang.RuntimeException;
// javac -cp /mnt/c/users/level/MG2D.jar:. Golf.java

public class Golf{
    public static void main(String[] args){
        
        
    //*********************** MENU *****************************************************************//
        
        Fenetre menu = new Fenetre("GOLFBATTLE",600,600);
        
        Rectangle fond3 = new Rectangle(Couleur.NOIR, new Point(0, 0),600, 600, true);
        Texte golf = new Texte(Couleur.JAUNE, new String ("GOLFBATTLE "), new Font("Calibri", Font.TYPE1_FONT, 40), new Point(300, 520));
        Texte s = new Texte(Couleur.JAUNE, new String ("start "), new Font("Calibri", Font.TYPE1_FONT, 30), new Point(300, 400));
        Texte q = new Texte(Couleur.JAUNE, new String ("quitter "), new Font("Calibri", Font.TYPE1_FONT, 30), new Point(300, 300));
        Texte credit = new Texte(Couleur.JAUNE, new String ("Crédit "), new Font("Calibri", Font.TYPE1_FONT, 30), new Point(300, 200));
        //je suis vraiment trop déçus de ne pas avoir pu mettre mes musiques et bruitages dans mon jeu //
        //Musique m = new Musique(new String ("img/flower-garden-yoshis-island.mp3"));
        //m.lecture();
        int com = 0;
        Clavier cla = menu.getClavier();
        
        menu.ajouter(fond3);
        menu.ajouter(golf);
        menu.ajouter(s);
        menu.ajouter(q);
        menu.ajouter(credit);
        menu.rafraichir();
        
        do{
        if(cla.getSTape()==true){
            com = com + 1;
        }
        else if(cla.getQTape()==true){
            com = com + 2;
        }
        System.out.println(" ");
        }while(com<1);
        
        menu.fermer();

//****** initialisation score ********************************************//
        
        int scoret = 0;
        int scoreM = 0;
        int rec = 0;
        
//******** initialisation pour les Tableaux scores ******************************//
        int partie = 0;
        int tab[] = new int[256];
        
        for(int k = 0; k<tab.length; k++){
            tab[k] = 0;
        }
        
//*********************** START **********************************************//
        if(com == 1){
            Fenetre f = new Fenetre("GOLFBATTLE",1600,600);
            do{

            Clavier clavier = f.getClavier();
                
            // création des différents points //
            Point p1 = new Point(0,0);
            Point p2 = new Point(180,95);
            Point p3 = new Point(1600,0);
            Point p4 = new Point(300,103);
            Point p5 = new Point(-200, 400);



            // creation des différentes textures
                
            // cercle
            Cercle balle = new Cercle(Couleur.ORANGE, new Point(253,103), 5, true); 
            Carre c = new Carre(Couleur.VERT ,new Point(302,50), 28, true);
                
            //ligne
            Ligne l = new Ligne(Couleur.ROUGE, new Point (253,103), p4);
                
            //rectangle
            Rectangle barre = new Rectangle(new Point(300, 50),900, 30, false);
            Rectangle trampoline = new Rectangle(Couleur.ROUGE, new Point(-50, 93),30, 10, true);
            Rectangle trampoline1 = new Rectangle(Couleur.ROUGE, new Point(-50, 93),30, 10, true);
                
            //texture
            Texture fond = new Texture("img/background.png", p1, 1600, 600);
            Texture fond2 = new Texture("img/background.png", p3, 1600, 600);
            Texture oiseau = new Texture("img/oiseau.png", p5, 150, 90 );
            Texture tortank = new Texture("img/tortank.png", new Point(-200, 93),150, 100);
            Texture joueur = new Texture("img/Saber.png", new Point(160, 90), 221, 121);
                
            //texte
            Texte haut = new Texte(Couleur.ROUGE, new String ("hauteur : 0"), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(750,550));
            Texte score = new Texte(Couleur.BLEU, new String ("Score : 0"), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(1400,550));
            Texte retry = new Texte(Couleur.BLANC, new String ("Pour recommencer appuyez sur espace "), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(750,300));
            Texte scoref = new Texte(Couleur.ROUGE, new String ("Score :"), new Font("Calibri", Font.TYPE1_FONT, 50), new Point(750,400));
            Texte scoremax = new Texte(Couleur.JAUNE, new String ("Score Max :"+scoreM), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(200,550));
            Texte start = new Texte(Couleur.NOIR, new String ("Pour commencer appuyez sur espace "), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(750,400));
            Texte fin = new Texte(Couleur.NOIR, new String ("Pour quitter appuyez sur Q "), new Font("Calibri", Font.TYPE1_FONT, 25), new Point(750,300));


            // attribut qui permet de modifier la vitesse du carre //
            int v = 30;

            //
            int puissance = 0;
            double tp = 0.0;
            int angle = 0;
            // attribut pour la boucle de la balle //
            int v2 = -40;
            int nombreAleatoire;
            rec = 0;
            // ************************************ //


            // ajout des objets dans la fenetre //
            f.ajouter(fond);
            f.ajouter(fond2);
            f.ajouter(joueur);
            f.ajouter(oiseau);
            f.ajouter(balle);
            f.ajouter(barre);
            f.ajouter(c);
            f.ajouter(l);
            f.ajouter(start);
            f.ajouter(tortank);
            f.ajouter(haut);
            f.ajouter(score);
            f.ajouter(scoremax);
            f.rafraichir();





    /*********************************  Puissance **********************************************/




            do{
                try{
                     Thread.sleep(6);
                        if(c.getA().getX()<=301||c.getA().getX()>=1160){v = -v;}
                            c.translater(v,0);
                        
                        if (c.getA().getX()<500){
                            c.setCouleur(Couleur.VERT);
                        } 
                        if (c.getA().getX()>500&&c.getA().getX()<800){
                            c.setCouleur(Couleur.JAUNE);
                        } 
                        if (c.getA().getX()>800){
                              c.setCouleur(Couleur.ROUGE);
                        }
                    
                    f.rafraichir();
                    
                }catch(Exception e){e.printStackTrace();}
            }while(clavier.getEspaceTape()==false);
                
         start.setTexte("appuyez sur espace pour tirer ");
         int j = 0;
         int rot = 3;
        
            do{
                try{
                     Thread.sleep(6);
                         
                    double xc = 353*Math.cos(j*3.14/180);
                    double yc = 353*Math.sin(j*3.14/180)+103;
                    
                    if (l.getB().getY()<170){
                            l.setCouleur(Couleur.VERT);
                        } 
                    if (l.getB().getY()>170 && l.getB().getY()<300){
                            l.setCouleur(Couleur.JAUNE);
                        } 
                    if (l.getB().getY()>300){
                            l.setCouleur(Couleur.ROUGE);
                        }
                    
                    if (j*2 > 90){
                        rot = -rot;
                    }else if (j*2 < 0){
                        rot = -rot;
                    }
                    
                    l.setB(new Point((int)xc, (int)yc));
                    f.rafraichir();
                    
                    j = j + rot ;
                }catch(Exception e){e.printStackTrace();}
            }while(clavier.getEspaceTape()==false);
        
            start.setTexte(" ");
            l.translater(-500, 0);
            f.rafraichir();
            tp =c.getA().getX()*0.8;
            puissance = (int)tp;
            angle = j;
        
        
/***************************  Mouvement *****************************************************/
    
         try{
                 Thread.sleep(60);
                 joueur.setImg("img/Saber1.2.png");
                 joueur.setA(new Point(-45, 65));
                 joueur.setLargeur(400);
                 joueur.setHauteur(180);
             
                f.rafraichir();
                Thread.sleep(300);
                joueur.setImg("img/Saber2.2.png");
                 joueur.setA(new Point(-130, 85));
                 joueur.setLargeur(400);
                 joueur.setHauteur(281);
                f.rafraichir();
             
                Thread.sleep(200);
               joueur.setImg("img/Saber3.2.png");
                 joueur.setA(new Point(-25, 20));
                 joueur.setLargeur(400);
                 joueur.setHauteur(281);
                f.rafraichir();
             
                Thread.sleep(60);
                 joueur.setImg("img/Saber4.2.png");
                 joueur.setA(new Point(30, 60));
                 joueur.setLargeur(400);
                 joueur.setHauteur(281);
                f.rafraichir();
             
                Thread.sleep(100);
             
                f.supprimer(joueur);
                f.rafraichir();
        }catch(Exception e){e.printStackTrace();}
        
   /*********************** Fond ***********************************************************/
    
        scoret = 0;
        do{
         Tir t = new Tir(angle, puissance);
            
            // attribut de la boucle
             double i = 0;
             double hauteur = 0;
             double tps = 0;
            
            do{
                try{
                            
                          Thread.sleep(15);
                          if(fond.getB().getX()<=0){
                              fond.setA(p3);
                           
                          }
                          if(fond2.getB().getX()<=0){
                              fond2.setA(p3);
                             
                          }
                            fond.translater(v2,0);
                            fond2.translater(v2,0);
                            
        
        /**************** Création de la courbe par rapport à la puissance ********************/
           
                   // System.out.println("lancer "+tps+" "+t);
                            
                            tps += 0.13;
                            t.upDate(tps);
                            balle.setO( new Point ( 253,(int)t.getHauteur()+103 ) );
                            
                            // création des tableaux de score //
                            hauteur  = t.getHauteur();
                            scoret = scoret + (int)t.getPosition();
                            haut.setTexte("hauteur : "+(int)hauteur);
                            score.setTexte("Score : "+ scoret);
                            f.ajouter(haut);
                            
    //*******************************************  TRAMPOLINE ****************************************************//    
                            
                            
                            
                            if (trampoline.getA().getX()<=-50){
                            Random rand = new Random();
                            nombreAleatoire = rand.nextInt(60);
                            if (nombreAleatoire == 1){
                                trampoline = new Rectangle(Couleur.ROUGE, new Point(1900, 93),30, 10, true);
                              
                            }
                            }
                            
                            if (trampoline1.getA().getX()<=-50){
                            Random rand = new Random();
                            nombreAleatoire = rand.nextInt(100);
                            if (nombreAleatoire == 1){
                                trampoline1 = new Rectangle(Couleur.ROUGE, new Point(2500, 93),30, 10, true);
                               
                            }
                            }
                            
                            trampoline.translater(v2,0);
                            f.ajouter(trampoline);
                            trampoline1.translater(v2,0);
                            f.ajouter(trampoline1);
                        
                            
    //***************************************** OBSTACLE ****************************************************************//
                            
                            if (tortank.getA().getX()<=-50){
                            Random rand = new Random();
                            nombreAleatoire = rand.nextInt(300);
                            if (nombreAleatoire == 1){
                                tortank.setA(new Point(2600, 93));
                               
                            }
                              }
                            
                            tortank.translater(v2,0);
                            
                            
                            
        //************************************* OISEAU **********************************************************//
                            
                            if (oiseau.getA().getX()<=-50){
                            Random rand = new Random();
                            nombreAleatoire = rand.nextInt(400);
                            if (nombreAleatoire == 1){
                                oiseau.setA(new Point(1500, 400));
                               
                            }
                              }
                            
                            oiseau.translater(v2,0);
                            
                                  
     //******************************** Trampoline INTERSECTION ************************************************//
                            
                            
                            if (trampoline.getA().getX()>=240 && trampoline.getA().getX()<=280){
                                  
                                if (t.getHauteur()>=0 && t.getHauteur()<=20){
                                    puissance = puissance + 1000;
                                    v2 = v2 - 30;
                                }
                            }
                            
                            if (trampoline1.getA().getX()>=240 && trampoline1.getA().getX()<=280){
                                   
                                if (t.getHauteur()>0 && t.getHauteur()<=20){
                                    puissance = puissance + 1000 ;
                                    v2 = v2 - 30;
                                }
                            }
                                  
    //********************************************  OBSTACLE INTERSECTION  ******************************************************//
                                  
                            if (tortank.getA().getX()>=200 && tortank.getA().getX()<=300){
                                   
                                if (t.getHauteur()>0 && t.getHauteur()<100){
                                    puissance = puissance * 0;
                                    hauteur = hauteur * 0.1;

                                    v2 = v2 - v2;
                                }
                            }      
            
    //********************************************  OISEAU INTERSECTION ************************************************************//
                            
                            if (oiseau.getA().getX()>=240 && oiseau.getA().getX()<=290){
                                   
                                if (t.getHauteur()>=380 && t.getHauteur()<=420){
                                    puissance = (int)(puissance * 0.2);
                                    Thread.sleep(100);
                                    v2 = v2 - 5;
                                }
                            }      
                            
                           
        /**********************************************************************************************************************/
                        
                         f.rafraichir();   
        
                        }catch(Exception e){e.printStackTrace();}
                }while(hauteur>=0);
            
                // addition des scores a chaques tour de boucle
                scoret = scoret + (int)t.getPosition();
                double temp = v2*0.9;
                v2 = (int)temp;
                puissance = (int)(puissance*0.6);
            
            }while(puissance>0);
    
        
            haut.setTexte(" ");
            score.setTexte("");
            scoref.setTexte("Score : "+scoret);
            f.ajouter(scoref);
            f.ajouter(fin);
            start.setA(new Point(750, 350));
            start.setTexte("pour recommencer appuyez sur espace ");
            f.rafraichir();

    
    
            if(scoreM < scoret){
                scoreM = scoret;
            }
    
    


            do{
                //******** recommencer ****************//
                if(clavier.getEspaceTape()==true){

                    rec = rec + 1;

                }
                //********** quitter ****************//
                else if(clavier.getQTape()==true){

                    rec = rec + 2;
                }

                System.out.println(" ");

            }while(rec<1);

    
    
            //*****  reinitialiser la fenetre **********//
            f.effacer();
            tab[partie] = scoret;
            partie = partie + 1;
            }while(rec<2);
    
    //****** fermeture fenetre ***********//
    
            f.fermer();
            
    //***** TABLEAU DES SCORES ****************//
    System.out.println(" TABLEAU DES SCORES ");
            
    for(int j = 0; j<partie; j++){
        System.out.println("score n°"+(j+1)+" = "+tab[j]);
    }
    
    //****** quitter *****************//
    
        }else if (com == 2){
    
    
        menu.fermer();
    
        }
    }
}