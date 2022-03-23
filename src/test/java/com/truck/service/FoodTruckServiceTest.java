package com.truck.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodTruckServiceTest {

    FoodTruckService sut = new FoodTruckService();

    @Test
    void getTrucks() {
        assertTrue(sut.getAllTrucks().size()>0);
    }

    @Test
    void testGetTrucksByType() {
        assertTrue(sut.getTrucks("Sandwich: Donuts: Coffee").size()==1);
    }

    @Test
    void testGetTrucksByTypeAndDistance() {
        //get 22coffee shops in a latitude
        assertTrue(sut.getTrucks("Coffee", 27.5, 1000.0).size()==22);
    }

    @Test
    void testGetNoTrucksWthinDistance() {
        //get no coffee shops in the specified range
        assertTrue(sut.getTrucks("Coffee", 27.5, 1).size()==0);
    }


    @Test
    void testGetNoTrucks() {
        assertTrue(sut.getTrucks("BMW").size()==0);
    }
}