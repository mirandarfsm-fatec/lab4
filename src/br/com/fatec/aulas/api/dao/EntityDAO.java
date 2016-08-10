package br.com.fatec.aulas.api.dao;

import java.util.List;

import br.com.fatec.aulas.api.entity.IdentifiedEntity;

public interface EntityDAO<EntityType extends IdentifiedEntity> {

	public EntityType save(EntityType entity);

	List<EntityType> findAll();

	void remove(EntityType entity);

	EntityType findById(Long id);

	EntityType update(EntityType entity);
}
