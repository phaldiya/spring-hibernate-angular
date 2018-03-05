package com.learn.repository;

import com.learn.domain.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by priyaan-pc on 5/18/2015.
 */
public interface HospitalRepository extends CustomJpaRepository<Hospital,Integer> {

}
