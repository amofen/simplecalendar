#Les fonctionnalités du calendrier: 
 - Authentification.
 - Créer/Modifier/Supprimer un agenda.
 - Créer/Modifier/Supprimer un évenement.
 - Inviter un utilisateur pour un évenement.
 - Editer/Recevoir un rappel pour un évenement.


 CHEMIN | METHOD | URL PARAMS | CONTENT | CODES RETOUR | DSC
 
http://servername:port/simplecalendar/utilisateur

. | @POST | aucun parametre | nomUtilisateur , motDePasse | 200 : 401 (non authentifié) | Retourner le profil utilisateur et un Token en cas de succès




http://servername:port/simplecalendar/utilisateur/{userId}/agenda/{agendaId} H
. | @GET | userId | pas de contenu | 200ok : 204 pas de contenu : 401 non authentifier : 403 interdit |Retourner la liste des agendas du user  
. | @POST | userId | agenda | 201created :208already reported : 401 non authentifier | Créer un agenda


/{agendaId} | @GET | pas de contenu |  200ok : 204 pas de contenu : 401 non authentifier: 403 interdit |Retourner un agenda avec son id

/{agendaId} | @PUT | userId agendaId| agenda | 200ok : 401 non authentifier: 403 interdit  | modifier agenda


/{agendaId} | @DELETE | userId agendaId | pas de contenu | 200ok : 401 non authentifier | supprimer agenda



http://servername:port/simplecalendar/utilisateur/{userId}/agenda/{agendaId}/evenement C

. | @GET | userId  agendaId | pas de contenu | 200ok : 204 pas de contenu : 401 non authentifier : 403 interdit |Retourner la liste des évenement d'un agenda

. | @POST | userId | agenda | 201created :208already reported : 401 non authentifier | Créer un agenda


/{agendaId} | @GET | pas de contenu |  200ok : 204 pas de contenu : 401 non authentifier: 403 interdit |Retourner un agenda avec son id

/{agendaId} | @PUT | userId agendaId| agenda | 200ok : 401 non authentifier: 403 interdit  | modifier agenda


/{agendaId} | @DELETE | userId agendaId | pas de contenu | 200ok : 401 non authentifier | supprimer agenda

http://servername:port/simplecalendar/utilisateur/{userId}/agenda/{agendaId}/evenement/{evenementId}/invitation H



http://servername:port/simplecalendar/utilisateur/{userId}/agenda/{agendaId}/evenement/{evenementId}/rappel C
