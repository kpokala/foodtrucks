package com.truck.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.truck.model.FoodTruck;
import com.truck.service.FoodTruckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@RestController
@Api("Set of endpoints for retrieving food trucks.")
public class FoodTruckController {

    private static final Logger logger = LoggerFactory.getLogger(FoodTruckController.class);
    private final FoodTruckService foodTruckService;
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public FoodTruckController(FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }

    @Produces({APPLICATION_JSON})
    @GetMapping("/trucks")
    @ApiOperation("Gets all trucks from the system")
    public List<FoodTruck> findAllTrucks() {

        return Arrays.stream(foodTruckService.getAllTrucks().toArray())
                .map(object -> mapper.convertValue(object, FoodTruck.class))
                .collect(Collectors.toList());
    }

    @Produces({APPLICATION_JSON})
    @GetMapping("/trucks/type/{foodType}")
    @ApiOperation("Filter By Food Type")
    public List<FoodTruck> filterbyFoodType(@PathVariable String foodType) {
        return Arrays.stream(foodTruckService.getTrucks(foodType).toArray())
                .map(object -> mapper.convertValue(object, FoodTruck.class))
                .collect(Collectors.toList());
    }

    @Produces({APPLICATION_JSON})
    @GetMapping("/trucks/type/{foodType}/latitude/{latitude}/distance/{distance}")
    @ApiOperation("Filter By Food Type")
    public List<FoodTruck> filterbyFoodTypeAndDistance(@PathVariable String foodType,
                                                       @PathVariable double latitude, @PathVariable double distance) {
        return Arrays.stream(foodTruckService.getTrucks(foodType,latitude,distance).toArray())
                .map(object -> mapper.convertValue(object, FoodTruck.class))
                .collect(Collectors.toList());
    }


}
