package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.halt;

/**
 * Created by mariano.mariscal on 8/10/2015.
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get("/", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                try {
                    Map<String, Object> fruitsMap = new HashMap<String, Object>();
                    fruitsMap.put("fruits",
                            Arrays.asList("apple", "orange", "banana", "peach"));

                    Template fruitsPickerTemplate = configuration.getTemplate("fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    fruitsPickerTemplate.process(fruitsMap, writer);
                    return writer;

                } catch (Exception e) {
                    halt(500);
                    return null;
                }
            }
        });

        Spark.post("/favorite_fruit", new Route() {
            public Object handle(Request request, Response response) throws Exception {
                final String fruit = request.queryParams("fruit");
                if (fruit == null) {
                    return "Why don't you pick one?";
                }
                else {
                    return "Your favorite fruit is " + fruit;
                }
            }
        });
    }
}
