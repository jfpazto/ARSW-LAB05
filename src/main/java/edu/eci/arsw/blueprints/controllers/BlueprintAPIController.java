/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
	@Autowired
	BlueprintsServices bps;

	/*@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> manejadorBlueprints() {

		try {
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(bps.getAllBlueprints(), HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
		}
	}*/
	
	/*@RequestMapping(method = RequestMethod.GET, path="{author}")
	public ResponseEntity<?> manejadorBlueprintsAutor(@PathVariable("author") String nombre) {

		try {
			Set<Blueprint> datos=bps.getBlueprintsByAuthor(nombre);
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
		}
	}*/
	
	@RequestMapping(method = RequestMethod.GET, path="{author}/{bpname}")
	public ResponseEntity<?> manejadorBlueprintsAutor(@PathVariable("author") String nombre,@PathVariable("bpname") String bpname) {

		try {
			Blueprint datos=bps.getBlueprint(nombre, bpname);
			// obtener datos que se enviarán a través del API
			return new ResponseEntity<>(datos, HttpStatus.ACCEPTED);
		} catch (Exception ex) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
			return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)	
	public ResponseEntity<?> manejadorPostBlueprintsAutor(@RequestBody Blueprint p){
	    try {
	        bps.addNewBlueprint(p);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Cannot found field",HttpStatus.BAD_REQUEST);            
	    }        

	}
	
	@RequestMapping(method = RequestMethod.PUT)	
	public ResponseEntity<?> manejadorPutBlueprintsAutor(@RequestBody Blueprint p){
	    try {
	        bps.UpdateBlueprint(p);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    } catch (Exception ex) {
	        Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
	        return new ResponseEntity<>("Cannot found field",HttpStatus.BAD_REQUEST);            
	    }        

	}

}

