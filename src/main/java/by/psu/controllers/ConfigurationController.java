package by.psu.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.TimeZone;

@RestController
@CrossOrigin
public class ConfigurationController {
    @GetMapping("/timezones/available")
    public List<String> getAvailableTimezones() {
        return List.of(TimeZone.getAvailableIDs());
    }
}
