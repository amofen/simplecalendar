/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.securite;

import fr.univevry.aos.simplecalendar.utilisateur.UtilisateurManager;
import java.io.IOException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

/**
 *
 * @author amine
 */
@Stateless
public class FiltreAutorisation implements ContainerRequestFilter{
    
    private static final String AUTHORIZATION_HEADER_KEY="Athorization";
    private static final String AUTHORIZATION_HEADER_PREFIX="Basic";
    
    private UtilisateurManager um;
    
    public void filter(ContainerRequestContext requestContext) throws IOException {
        
    }
    
}
