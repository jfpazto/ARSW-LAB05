/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

	private final Map<Tuple<String, String>, Blueprint> blueprints = new HashMap<>();

	public InMemoryBlueprintPersistence() {
		// load stub data
		Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115) };
		Blueprint bp = new Blueprint("Ricardo", "Perez ", pts);
		blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
		//
		Point[] pts1 = new Point[] { new Point(140, 140), new Point(115, 115) };
		Blueprint bp1 = new Blueprint("Juan", "Torres", pts1);
		blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
		//
		Point[] pts2 = new Point[] { new Point(190, 120), new Point(135, 115) };
		Blueprint bp2 = new Blueprint("Pablo", "Ruiz ", pts2);
		blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
		//
		Point[] pts3 = new Point[] { new Point(100, 115), new Point(115, 155) };
		Blueprint bp3 = new Blueprint("Alejandro", "_bpname_ ", pts3);
		blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);
	}

	@Override
	public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
		if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
			throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
		} else {
			blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
		}
	}

	@Override
	public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
		return blueprints.get(new Tuple<>(author, bprintname));
	}

	@Override
	public Set<Blueprint> getSetBlueSprints(String author) throws BlueprintNotFoundException {
		Set<Blueprint> blueprintFound = new HashSet<Blueprint>();
		for (Blueprint b : blueprints.values()) {
			if (b.getAuthor().equals(author)) {
				blueprintFound.add(b);
			}
		}
		return blueprintFound;
	}

	@Override
	public Set<Blueprint> getAll() throws BlueprintNotFoundException {
		Set<Blueprint> blueprintFound = new HashSet<Blueprint>();
		for (Blueprint b : blueprints.values()) {
			
			blueprintFound.add(b);
		}
		return blueprintFound;
	}

}
