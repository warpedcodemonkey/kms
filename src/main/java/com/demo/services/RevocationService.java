package com.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

/**
 * Created by johnb on 3/1/16.
 */
public class RevocationService {

    @Autowired
    DataSource dataSource;


}
