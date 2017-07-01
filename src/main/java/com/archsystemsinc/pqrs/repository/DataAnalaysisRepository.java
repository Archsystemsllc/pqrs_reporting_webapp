/**
 * 
 */
package com.archsystemsinc.pqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archsystemsinc.pqrs.model.DataAnalysis;

/**
 * @author MurugarajKandaswam
 *
 */
public interface DataAnalaysisRepository extends JpaRepository<DataAnalysis, Long> {
	DataAnalysis findById(final int id); 
}
