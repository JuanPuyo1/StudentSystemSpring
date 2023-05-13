package edu.ucentral.commonsservice.service;


import java.util.Optional;

import org.springframework.data.domain.Pageable;

public interface CommonService<E> {
	public Iterable<E> findAll();
	public Iterable<E> findAll(Pageable pageable);
	public Optional<E> findById(Long id);
	public E save(E entity);
	public void deleteById(Long id);
	
	
}
