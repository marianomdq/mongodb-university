package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by mariano.mariscal on 8/10/2015.
 */
public class HelloWorldSparkStyle {

    public static void main(String[] args){
        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                return "Hello world from Spark!";
            }
        });
    }
}
