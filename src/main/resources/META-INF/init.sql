INSERT INTO Utilisateur (id,nom,prenom,email,motdepasse)VALUES (1,"aouffen","amine","amine@gmail.com","aminepwd");
INSERT INTO Utilisateur (id,nom,prenom,email,motdepasse)VALUES (2,"derradji","celia","celia@gmail.com","celiapwd");
INSERT INTO Utilisateur (id,nom,prenom,email,motdepasse)VALUES (3,"benhacine","hamza","hamza@gmail.com","hamzapwd");

INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (1,"Travail",1);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (2,"Anniversaires",1);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (3,"Réunions",2);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (4,"Anniversaires",2);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (5,"Travail",3);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (6,"Réunions",3);
INSERT INTO Agenda (id,nom,utilisateur_id) VALUES (7,"Rendez-Vous",3);


INSERT INTO Evenement(id,datedebut,datefin,descriptif,lieu,priorite,titre,agenda_id) VALUES (1,'20180201','20180205',"Reuinion de Travail","IBGBI",1,"Reunion",1);
INSERT INTO Evenement(id,datedebut,datefin,descriptif,lieu,priorite,titre,agenda_id) VALUES (2,'20180112','20180112',"Fete d'anniversaire de amine","Chez Amine",5,"Anniversaire",1);


INSERT INTO Invitation(id,message,reponse,evenement_id,hote_id,invite_id) VALUES (1,"Je t'invite a la reunion",0,1,1,3);
INSERT INTO Invitation(id,message,reponse,evenement_id,hote_id,invite_id) VALUES (2,"Je t'invite a mon anniversaire",0,1,1,2);

INSERT INTO Rappel  (id,date,evenement_id) VALUES (1,"20190201",1);

