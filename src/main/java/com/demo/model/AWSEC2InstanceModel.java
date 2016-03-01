package com.demo.model;

import com.amazonaws.services.ec2.model.Tag;

import java.util.List;

/**
 * Created by johnb on 2/25/16.
 */
public class AWSEC2InstanceModel {

    public AWSEC2InstanceModel(List<Tag> tags){
        this.setInstanceName(this.getNameFromTag(tags));
    }

    String instanceName = "No Name";
    String instanceId;;
    String amiId;
    String instanceType;
    String instanceState;
    String privateIpAddress;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getAmiId() {
        return amiId;
    }

    public void setAmiId(String amiId) {
        this.amiId = amiId;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceState() {
        return instanceState;
    }

    public void setInstanceState(String instanceState) {
        this.instanceState = instanceState;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    private String getNameFromTag(List<Tag> tags){
        for(Tag tag : tags){
            if(tag.getKey().equals("Name")){
                return tag.getValue();
            }
        }
        return "No Name";
    }
}
