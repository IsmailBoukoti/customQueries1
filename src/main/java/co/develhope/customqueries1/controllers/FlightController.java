package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatus;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/flights")
public class FlightController {

    List<Flight> flightList = new ArrayList<>();
    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PostMapping("/post")
    public ResponseEntity<String> createFlights(){

        Random random = new Random();
        for (int i = 0; i<50; i++){
            Flight flight = new Flight();
            flight.setDescription(Integer.toString(random.nextInt()));
            flight.setFromAirport(Integer.toString(random.nextInt()));
            flight.setToAirport(Integer.toString(random.nextInt()));
            flight.setStatus(FlightStatus.ON_TIME);
            flightList.add(flight);
        }
        flightRepository.saveAll(flightList);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flights have been created");
    }
    @GetMapping("/get")
    public List<Flight> getList(){
        flightList = flightRepository.findAll();
        return flightList;
    }

    /*@GetMapping
    public ResponseEntity<List<Flight>> getFlights() {
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flights);
    }*/
}