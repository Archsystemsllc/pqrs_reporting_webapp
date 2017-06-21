package com.archsystemsinc.pqrs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.archsystemsinc.pqrs.model.StatewiseStatistic;

/**
 * interface for statewise_statistic repository class 
 * 
 * @author Grmahun Redda
 * @since 6/21/2017
 */
public interface StatewiseStatisticRepository extends JpaRepository<StatewiseStatistic, Long>{

}
