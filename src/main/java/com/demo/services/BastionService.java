package com.demo.services;

import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by johnb on 2/29/16.
 */
@Service
public class BastionService {

    @Autowired
    DataSource dataSource;



    public void installKeys(String keyName, String host, String user, String identityFile, int port) throws Exception{
    //Start Here, setup defaults and config, setup repo for keys, setup database for user keys.
        //SCP secret key to bastion
        //execute ssh add command on secret key
        //Delete secret key from bastion

        //SCP public key to target machine
        //execute install authkeycommand

        //remove public key





    }

    private String getInstallAuthKeyCommand(String fileName){
        StringBuilder retVal = new StringBuilder();
        return retVal.append("cat ").append(fileName).append(" >> ./ssh/authorized_keys").toString();
    }

    private String getSshAddCommand(String keyName){
        StringBuilder retVal = new StringBuilder();
        return retVal.append("chmod 600 ").append(keyName).append("; ssh-add ").append(keyName).toString();
    }

    private String secureSCP(String fileName, String remoteDirectory, String host, String user, String identityFile, int port ) throws Exception{
        Channel channel = null;
        Session session = this.getShellSession(host, user, identityFile, port);
        StringBuilder builder = new StringBuilder();
        try{
            channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();

            ChannelSftp c = (ChannelSftp) channel;
            c.put(fileName, remoteDirectory);
            c.exit();


        }finally {
            channel.disconnect();
            session.disconnect();
        }

        return builder.toString();
    }


    private String executeSSHCommand(String command, String host, String user, String identityFile, int port) throws Exception{
        ChannelExec channel = null;
        Session session = this.getShellSession(host, user, identityFile, port);
        StringBuilder builder = new StringBuilder();
        try {
            session.connect();
            channel = (ChannelExec) session.openChannel("exec");
            BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            channel.setCommand(command);
            channel.connect();

            String msg = null;
            while ((msg = in.readLine()) != null) {
                builder.append(msg);
            }
            builder.append("|").append(channel.getExitStatus());
            return builder.toString();
        }finally{
            channel.disconnect();
            session.disconnect();
        }

    }

    private Session getShellSession(String host, String user, String identityFile, int port) throws Exception{
        JSch jSch = new JSch();
        jSch.addIdentity(identityFile);
        return jSch.getSession(user, host, port);
    }
}
