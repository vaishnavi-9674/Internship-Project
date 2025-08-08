package com.example.recommender;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RecommenderApp {
	public static void main(String[] args) {
        try
        {
        	 String csvPath = "src/main/java/com/example/recommender/data.csv"; // or use a proper resources folder
             FileWriter writer = new FileWriter(csvPath);
             writer.write("1,101,5.0\n");
             writer.write("1,102,3.0\n");
             writer.write("2,101,4.0\n");
             writer.write("2,103,2.0\n");
             writer.write("3,104,4.5\n");
             writer.write("4,101,3.0\n");
             writer.write("4,105,4.0\n");
             writer.write("3,102,2.0\n");
             writer.close();
             File file = new File(csvPath);
             DataModel model = new FileDataModel(file);

             
             UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            
             UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

          
             GenericUserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

             
             List<RecommendedItem> recommendations = recommender.recommend(1, 3);

             
             for (RecommendedItem recommendation : recommendations)
             {
                 System.out.println("Recommended item: " + recommendation.getItemID() +
                                    " (Score: " + recommendation.getValue() + ")");
             }

        }
        catch (IOException | TasteException e)
        {
                e.printStackTrace();
        }
	}


}
