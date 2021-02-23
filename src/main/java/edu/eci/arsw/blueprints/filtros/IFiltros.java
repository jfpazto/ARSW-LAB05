package edu.eci.arsw.blueprints.filtros;

import java.util.Set;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface IFiltros {
	public Blueprint filtrar(Blueprint bp);
	public Set<Blueprint> filterBlueprintSet(Set<Blueprint> bp);
}
