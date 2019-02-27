package com.multidatasoucres.store.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multidatasoucres.store.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
