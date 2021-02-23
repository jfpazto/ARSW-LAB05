package edu.eci.arsw.blueprints.filtros;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Service
public class FiltroSubmuestreo implements IFiltros {

	@Override
	public Blueprint filtrar(Blueprint bp) {
		List<Point> actual = bp.getPoints();
		int longitud = actual.size();
		Point[] nlista = new Point[longitud / 2];
		int con = 0;
		for (int i = 1; i < longitud; i += 2) {

			nlista[con] = actual.get(i);
			con++;
		}

		Blueprint finala = new Blueprint(bp.getAuthor(), bp.getName(), nlista);
		return finala;
	}

	@Override
	public Set<Blueprint> filterBlueprintSet(Set<Blueprint> bp) {
		for (Blueprint blueP : bp) {
			blueP = filtrar(blueP);
		}
		return bp;
	}

}
