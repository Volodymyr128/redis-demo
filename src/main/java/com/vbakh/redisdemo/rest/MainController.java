package com.vbakh.redisdemo.rest;

import com.vbakh.redisdemo.domain.Location;
import com.vbakh.redisdemo.response.UserResponse;
import com.vbakh.redisdemo.service.LocationService;
import com.vbakh.redisdemo.service.MainService;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by volodymyr.bakhmatiuk on 5/29/18.
 */
@RestController
@RequestMapping(path = "api/")
public class MainController {

    @Resource
    private MainService mainService;
    @Resource
    private LocationService locationService;

    @GetMapping(path = "test")
    public String test() {
        return "I'm here! [1.1]";
    }

    @PostMapping(path = "init")
    public void initialize() {
        mainService.initialize();
    }

    @GetMapping(path = "user/{id}")
    public UserResponse getUserResponse(@PathVariable("id") String id) {
        return mainService.getUserResponse(id);
    }

    @PostMapping(path = "location/init")
    public void initLocations() {
        locationService.init();
    }

    @GetMapping(path = "location/{fromX}/{fromY}/{toX}/{toY}")
    public Set<Location> getLocations(@PathVariable("fromX") Integer fromX, @PathVariable("fromY") Integer fromY, @PathVariable("toX") Integer toX, @PathVariable("toY") Integer toY) {
        return locationService.get(fromX, fromY, toX, toY);
    }

    @GetMapping(path = "location/{from}/{to}/download")
    public String getLocationsCsv(@PathVariable("from") String from, @PathVariable("to") String to, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain; charset=utf-8");
        StringBuilder builder = new StringBuilder();
        locationService.get(from, to)
                .forEach(location -> {
                    builder.append(location.getX() + "," + location.getY() + "\n");
                });
        return builder.toString();
    }
}