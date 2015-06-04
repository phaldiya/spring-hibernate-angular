package com.learn.web;

import com.learn.domain.Hospital;
import com.learn.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/secure/api/hospital")
public class HospitalController {
    @Autowired
    private HospitalService hospitalService;

    @RequestMapping(method = RequestMethod.GET)
    public Object getAll() {
        return hospitalService.getAll();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Object delete(@PathVariable Integer id) throws Exception {
        hospitalService.delete(id);
        return true;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Hospital formBean) throws Exception {
        System.out.println("calling save");
        return hospitalService.toJson(hospitalService.save(formBean));
    }

    /*@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Object update(@PathVariable Integer id, @RequestBody Hospital formBean) throws Exception {
        System.out.println("calling update");
        Hospital hospital = hospitalService.find(id);
        return hospitalService.toJson(hospitalService.save(formBean));
    }*/

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Object update(@PathVariable Integer id, @RequestBody Hospital formBean) throws Exception {
        Hospital hospital = hospitalService.find(id);
        hospitalService.copyFormBean(formBean, hospital);

        return hospitalService.toJson(hospitalService.save(hospital));
    }
}