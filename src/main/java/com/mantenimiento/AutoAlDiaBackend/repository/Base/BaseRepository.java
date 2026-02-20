package com.mantenimiento.AutoAlDiaBackend.repository.Base;

import com.mantenimiento.AutoAlDiaBackend.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID>{
}
