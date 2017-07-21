package com.multitenancy.teste.repository;

import com.multitenancy.teste.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Tobias Andre [tobiasae@gmail.com] on 21/07/2017.
 */
public interface PaisRepository extends JpaRepository<Pais,Long> {
}
