package com.demo.services;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.demo.aws.connection.AWSConnection;
import com.demo.model.AWSRegionModel;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johnb on 2/25/16.
 */
@Service
public class AWSService {

    private AWSConnection getConnection(String id, String key){
        AWSConnection connection = null;
        if(!StringUtils.isEmpty(id) && !StringUtils.isEmpty(key)){
            connection = new AWSConnection(id, key);
        }else{
            connection = new AWSConnection();
        }
        return connection;
    }



    public ArrayList<AWSRegionModel> getEC2Instances() throws Exception{
        AWSConnection connection =this.getConnection("AWSKEY", "AWSSECRETKEY");
        if(connection == null){
            throw new Exception("Unable to make AWS Connection");
        }
        ArrayList<AWSRegionModel> models = new ArrayList<>();
        AWSRegionModel model = null;
        AmazonEC2Client amazonEC2Client = new AmazonEC2Client(connection.getAwsCredentials());
        amazonEC2Client.setEndpoint("ec2.us-west-2.amazonaws.com");
        DescribeInstancesResult result = null;

        List<Regions> regionsList = new ArrayList<>();
        regionsList.add(Regions.US_EAST_1);
        regionsList.add(Regions.US_WEST_1);
        regionsList.add(Regions.US_WEST_2);

        for(Regions region : regionsList){
            model = new AWSRegionModel();
            model.setRegionName(region.toString());
            amazonEC2Client.setRegion(com.amazonaws.regions.Region.getRegion(region));
            result = amazonEC2Client.describeInstances();
            for(Reservation reservation : result.getReservations()){
                reservation.getInstances().stream().forEach(model::addInstance);
            }
            if(model.getInstances().size() > 0){
                models.add(model);
            }

        }




        return models;
    }

//    public String getNameTag(List<Tag> tags){
//        for(Tag tag : tags){
//            if(tag.getKey().equals("Name"))
//                return tag.getKey();
//        }
//        return "No Name";
//    }

}
