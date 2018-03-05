package com.learn.service;

import com.learn.domain.Hospital;

import java.util.List;

/**
 * Created by priyaan-pc on 5/18/2015.
 */
public interface HospitalService extends BaseService<Hospital,Integer> {

    List<Hospital> getAll();

    void delete(Integer id) throws Exception;
    void copyFormBean(Hospital source, Hospital destination);
}
