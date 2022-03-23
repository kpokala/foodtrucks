package com.truck.service;

import com.truck.model.FoodTruck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("foodTruckService")
public class FoodTruckService {

    Logger log = LoggerFactory.getLogger(FoodTruckService.class);

    @Autowired
    public FoodTruckService() {
    }

    public static double distance(double lat1, double lat2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = 0.0;
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000;
        System.out.println("distance: " + Math.sqrt(distance));

        return Math.sqrt(distance / 1000);
    }

    private static List<FoodTruck> readTrucks(String fileName) {
        List<FoodTruck> trucks = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                FoodTruck truck = createTruck(attributes);
                trucks.add(truck);
                line = br.readLine();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return trucks;
    }

    private static FoodTruck createTruck(String[] metadata) {

        return new FoodTruck(metadata[0], metadata[1], metadata[2], metadata[3],
                metadata[4], metadata[5], metadata[6], metadata[7], metadata[8], metadata[9], metadata[10], metadata[11],
                metadata[12], metadata[13], metadata[14], metadata[15], metadata[16], metadata[17], metadata[18], metadata[19],
                metadata[20], metadata[21], metadata[22], metadata[23]);
    }

    public List<FoodTruck> getAllTrucks() {
        return readTrucks("src/main/resources/truckList.csv");
    }

    public List<FoodTruck> getTrucks(String foodType) {
        return getAllTrucks()
                .stream().filter(x -> x.getFoodItems() != null && x.getFoodItems().contains(foodType))
                .collect(Collectors.toList());
    }

    public List<FoodTruck> getTrucks(String foodType, double fromLatidute, double maxDistance) {
        return getAllTrucks()
                .stream().filter(x -> x.getFoodItems() != null && x.getFoodItems().contains(foodType))
                .filter(x -> distance(new Double(x.getLatitude()).doubleValue(), fromLatidute) < maxDistance)
                .collect(Collectors.toList());
    }


}