import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor; // Import this class

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class asgn_12 {
    public static void main(String[] args) {
        // Suppress MongoDB driver INFO logs
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);

        // Replace these with your MongoDB connection details
        String uri = "mongodb://te31226:te31226@10.10.13.76:27017/te31226db";

        // Set up the MongoDB connection with authentication
        MongoClientURI mongoClientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(mongoClientURI);
        MongoDatabase database = mongoClient.getDatabase("te31226db");

        // Specify the collection you want to work with
        MongoCollection<Document> collection = database.getCollection("Posts");

        Scanner scanner = new Scanner(System.in);

        while(true) {
        	System.out.println("Choose an option:");
            System.out.println("1. Insert Data");
            System.out.println("2. Show Data");
            System.out.println("3. Delete Data");
            int choice = scanner.nextInt();

            if (choice == 1) {
                // Insert data
                System.out.print("Enter name: ");
                String name = scanner.next();
                System.out.print("Enter likes: ");
                int likes = scanner.nextInt();

                Document document = new Document("name", name)
                        .append("likes", likes);

                collection.insertOne(document);
                System.out.println("Data inserted successfully.");
            } else if (choice == 2) {
                // Show data
                MongoCursor<Document> cursor = collection.find().iterator();

                try {
                    System.out.println("Data in the collection:");
                    while (cursor.hasNext()) {
                        Document document = cursor.next();
                        String name = document.getString("name");
                        int likes = document.getInteger("likes");
                        System.out.println("Name: " + name + ", Likes: " + likes);
                    }
                } finally {
                    cursor.close();
                }
            } else if (choice == 3) {
                // Delete data
                System.out.print("Enter name to delete: ");
                String nameToDelete = scanner.next();

                Bson filter = Filters.eq("name", nameToDelete);
                collection.deleteOne(filter);
                System.out.println("Data deleted successfully.");
            }
            else {
            	break;
            }
        }

        // Close the MongoDB connection
        mongoClient.close();
    }
}
