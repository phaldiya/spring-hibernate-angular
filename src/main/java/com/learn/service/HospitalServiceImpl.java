package com.learn.service;

import com.learn.domain.Hospital;
import com.learn.repository.HospitalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HospitalServiceImpl extends BaseServiceImpl<Hospital,Integer> implements HospitalService {
    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    public Hospital save(Hospital hospital) throws Exception {
        return hospitalRepository.save(hospital);

    }
    @Override
    public void copyFormBean(Hospital source, Hospital destination) {
        BeanUtils.copyProperties(source, destination);
        //BeanUtils.copyProperties(source, destination, ArrayUtils.addAll(IGNORE_PROPERTIES, "schedule"));
    }
    @Override
    public Hospital find(Integer id) {
        return hospitalRepository.findOne(id);
    }

    @Override
    public List<Hospital> getAll() {
        return hospitalRepository.findAll();
    }

    public void delete(Integer id) throws Exception
    {
        hospitalRepository.delete(id);
    }
}
