/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univevry.aos.simplecalendar.agenda;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author Enima
 */
@Path("agenda")
public class AgendaService {
    @GET
    public String test(){
        return "test";
    } 
}
