package com.aukhatov.vaccinations.web;

import com.aukhatov.vaccinations.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping(value = "/vaccinations")
public class MainController {
    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final BaseService baseService;

    @Autowired
    public MainController(BaseService baseService) {
        this.baseService = baseService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {

        logger.debug("index() is executed!");

        model.put("title", baseService.getTitle(""));
        model.put("msg", baseService.getDesc());

        return "index";
    }

    @RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {

        logger.debug("hello() is executed - $name {}", name);

        ModelAndView model = new ModelAndView();
        model.setViewName("index");

        model.addObject("title", baseService.getTitle(name));
        model.addObject("msg", baseService.getDesc());

        return model;

    }
}
