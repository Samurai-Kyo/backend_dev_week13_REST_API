package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultJeepSalesController implements JeepSalesController {
    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        return null;
    }
}