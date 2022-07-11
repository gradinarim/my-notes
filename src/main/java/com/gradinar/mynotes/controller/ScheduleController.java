package com.gradinar.mynotes.controller;

import com.gradinar.mynotes.entity.Event;
import com.gradinar.mynotes.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/calendar")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public List<Long> getSchedules(@RequestParam String beginOfMonth, @RequestParam String endOfMonth) {
        return scheduleService.getSchedulesForMonth(beginOfMonth, endOfMonth);
    }

    @GetMapping("/{currentDate}")
    public List<Event> getTodayEvents(@PathVariable String currentDate) {
        return scheduleService.getTodayEvent(currentDate);
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestParam String date, @RequestParam String content) throws URISyntaxException {
        return scheduleService.addEvent(date, content);
    }

    @DeleteMapping("/{date}")
    public ResponseEntity<String> deleteSchedule(@PathVariable String date) {
        return scheduleService.deleteSchedule(date);
    }
}

