/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.annotation.Priority;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import static jdk.nashorn.internal.runtime.Context.printStackTrace;

/**
 *
 * @author amine
 */
@Provider
@Singleton
public class SecuriteFiltre implements ContainerRequestFilter {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    public static final Key SIGNINIG_KEY = initKey();

    @Inject
    AutorisationService as;

    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (!requestContext.getUriInfo().getPath().contains("auth")) {
            List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);

            if (authHeader != null && !authHeader.isEmpty()) {
                String authToken = authHeader.get(0);
                authToken = authToken.replace(AUTHORIZATION_HEADER_PREFIX, "");
                try {
                    Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(SIGNINIG_KEY).parseClaimsJws(authToken);
                    long userIdFromToken = jwsClaims.getBody().get("userId", Long.class);
                    long userId = getPathParam(requestContext, "utilisateurId");
                    if (userIdFromToken != userId) {
                        sendNonAuthorized(requestContext, "Token ne correspond pas à l'utilisateur");
                        return;
                    }

                    //Agenda
                    long agendaId = getPathParam(requestContext, "agendaId");
                    if (agendaId != 0) {
                        if (!as.checkAgendaAccess(userId, agendaId)) {
                            sendNonAuthorized(requestContext, "Utilisateur non autorisé à accéder à cette donnée !");
                            return;
                        }
                        long evenementId = getPathParam(requestContext, "evenementId");
                        if (evenementId != 0) {
                            if (!as.checkEvenementAccess(agendaId, evenementId)) {
                                sendNonAuthorized(requestContext, "Utilisateur non autorisé à accéder à cette donnée !");
                                return;
                            }
                            long invitationId = getPathParam(requestContext, "invitationId");
                            if (invitationId != 0 && !as.checkInvitationAccess(evenementId, invitationId)) {
                                sendNonAuthorized(requestContext, "Utilisateur non autorisé à accéder à cette donnée !");
                                return;
                            }
                            long rappelId = getPathParam(requestContext, "rappelId");
                            if (rappelId != 0 && !as.checkRappelAccess(evenementId, rappelId)) {
                                sendNonAuthorized(requestContext, "Utilisateur non autorisé à accéder à cette donnée !");
                                return;
                            }
                        }
                    }
                    return;
                } catch (ExpiredJwtException e) {
                    sendNonAuthorized(requestContext, "Token expiré !");
                    return;
                } catch (SignatureException e) {
                    sendNonAuthorized(requestContext, "Token altérée !");
                    return;
                }

            }
            sendNonAuthorized(requestContext, "Utilisateur non autorisé !");
            return;
        }

    }

    private static Key initKey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            Key key = kpg.genKeyPair().getPrivate();
            return key;
        } catch (NoSuchAlgorithmException e) {
            printStackTrace(e);
        }
        return null;
    }

    private void sendNonAuthorized(ContainerRequestContext ctx, String message) {
        Response response = Response.status(Response.Status.UNAUTHORIZED)
                .entity(message)
                .build();
        ctx.abortWith(response);
    }

    public long getPathParam(ContainerRequestContext ctx, String pathparamName) {
        MultivaluedMap<String, String> pathParams = ctx.getUriInfo().getPathParameters();
        if (pathParams.get(pathparamName) != null
                && pathParams.get(pathparamName).size() > 0) {
            return Long.parseLong(pathParams.get(pathparamName).get(0));
        } else {
            return 0;
        }
    }
}
