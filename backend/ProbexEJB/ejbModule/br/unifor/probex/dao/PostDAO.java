package br.unifor.probex.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PostDAO {

	@PersistenceContext
	private EntityManager manager;

}
