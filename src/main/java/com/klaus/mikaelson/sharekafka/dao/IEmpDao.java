package com.klaus.mikaelson.sharekafka.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klaus.mikaelson.sharekafka.model.Emp;

public interface IEmpDao extends JpaRepository<Emp, Long>{

}
