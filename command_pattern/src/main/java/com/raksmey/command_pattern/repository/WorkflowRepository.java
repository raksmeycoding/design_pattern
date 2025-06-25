package com.raksmey.command_pattern.repository;

import com.raksmey.command_pattern.entity.WorkflowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface WorkflowRepository extends JpaRepository<WorkflowEntity, Long> {

    Optional<WorkflowEntity> findByIdAndIsDeletedFalse(Long id);

    List<WorkflowEntity> findAllByIsDeletedFalse(); // Custom query

    Page<WorkflowEntity> findAllByIsDeletedFalse(Pageable pageable);
}
