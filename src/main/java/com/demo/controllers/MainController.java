package com.demo.controllers;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.model.Instance;
import com.demo.model.AWSRegionModel;
import com.demo.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by johnb on 2/25/16.
 */
@Controller
public class MainController {

    @Autowired
    AWSService awsService;

    @RequestMapping("/main")
    public String main(Model model) throws Exception{
        ArrayList<AWSRegionModel> models = awsService.getEC2Instances();
        model.addAttribute("regionModel", models);
        return "main";
    }

}
