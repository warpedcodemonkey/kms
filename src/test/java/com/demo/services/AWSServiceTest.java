package com.demo.services;

import com.demo.Start;
import com.demo.aws.connection.AWSConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by johnb on 2/25/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Start.class)
public class AWSServiceTest {
   @Autowired
   AWSService service;

    @Test
    public void testAWSService(){
//        AWSConnection connection = service.
    }
}
