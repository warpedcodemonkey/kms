package com.demo;


import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.demo.aws.connection.AWSConnection;
import com.demo.services.AWSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Start
{
    @Autowired
    AWSService service;

    public static void main( String[] args )
    {
        SpringApplication.run(Start.class, args);
    }


    public void test(){
        //Get list of EC2 instances
        AWSConnection connection = new AWSConnection("AWSKEY", "AWSSECRETKEY");
        AmazonEC2Client amazonEC2Client = new AmazonEC2Client(connection.getAwsCredentials());
        amazonEC2Client.setEndpoint("ec2.us-west-1.amazonaws.com");
        amazonEC2Client.setRegion(Region.getRegion(Regions.US_WEST_2));

        DescribeInstancesResult result = amazonEC2Client.describeInstances();
        System.out.println(result.toString());
        for(Reservation reservation : result.getReservations()){
            for(Instance instance : reservation.getInstances()){
                System.out.println(instance.getInstanceId());
            }
        }
    }
}
