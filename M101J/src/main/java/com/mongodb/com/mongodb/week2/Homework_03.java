package com.mongodb.com.mongodb.week2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import org.bson.*;

/**
 * Created by mariano.mariscal on 8/18/2015.
 */
public class Homework_03 {

    public static void main(String[] args) {

        MongoClient mongoClient = new MongoClient();

        MongoDatabase mongoDatabase = mongoClient.getDatabase("students");

        MongoCollection<Document> collection = mongoDatabase.getCollection("grades");

        Bson filter = new Document("type", "homework");
        Bson sortStudent = new Document("student_id", 1).append("score", 1);

        List<Document> filteredList = collection.find(filter).sort(sortStudent).into(new ArrayList<Document>());

        int lastId = -1000000;

        //for (Document doc : homeworkFilteredList){
        //    System.out.println(doc.toJson());
        //}

        for (Document doc : filteredList){
            if (doc.getInteger("student_id") != lastId){
                lastId = doc.getInteger("student_id");
                collection.deleteOne(doc);
            }
        }




    }

}
