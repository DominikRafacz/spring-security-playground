package org.dr.box.control.repository;

import org.dr.box.entity.BoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoxRepostiory extends JpaRepository<BoxEntity, Long> {

    Optional<BoxEntity> findById(Long id);
}
