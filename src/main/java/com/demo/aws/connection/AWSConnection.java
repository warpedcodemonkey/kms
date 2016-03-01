package com.demo.aws.connection;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;

/**
 * Created by johnb on 2/25/16.
 */
public class AWSConnection {

    private AWSCredentials awsCredentials = null;

    public AWSConnection(){
        this(System.getenv("AWS_ID").toString(), System.getenv("AWS_KEY").toString());
    }

    public AWSConnection(String id, String key){
        this.setupCreditials(id, key);
    }
    public AWSCredentials getAwsCredentials() {
        return awsCredentials;
    }

    public void setAwsCredentials(AWSCredentials awsCredentials) {
        this.awsCredentials = awsCredentials;
    }

    public AWSCredentials setupCreditials(String id, String key){
        this.setAwsCredentials(new BasicAWSCredentials(id, key));
        return this.getAwsCredentials();
    }

}
