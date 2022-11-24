package com.tinkoff.skipper.repository;

import com.tinkoff.skipper.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, RoleEntity.Role> {



}
