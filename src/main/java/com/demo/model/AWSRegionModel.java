package com.demo.model;

import com.amazonaws.services.ec2.model.Instance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnb on 2/25/16.
 */
public class AWSRegionModel {

    String regionName;
    List<AWSEC2InstanceModel> instances = new ArrayList<>();

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public List<AWSEC2InstanceModel> getInstances() {
        return instances;
    }

    public void setInstances(List<AWSEC2InstanceModel> instances) {
        this.instances = instances;
    }

    public void addInstance(Instance instance){
        AWSEC2InstanceModel model = new AWSEC2InstanceModel(instance.getTags());
        model.setAmiId(instance.getImageId());
        model.setInstanceId(instance.getInstanceId());
        model.setInstanceType(instance.getInstanceType());
        model.setInstanceState(instance.getState().getName());
        model.setPrivateIpAddress(instance.getPrivateIpAddress());
        this.getInstances().add(model);
    }
}
