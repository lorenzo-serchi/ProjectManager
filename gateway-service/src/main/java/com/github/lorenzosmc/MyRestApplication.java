package com.github.lorenzosmc;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//FIXME putting version in path is not RESTful
@ApplicationPath("/rest1.0")
public class MyRestApplication extends Application{
}
