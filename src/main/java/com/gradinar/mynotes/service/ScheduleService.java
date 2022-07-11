package com.gradinar.mynotes.service;

import com.gradinar.mynotes.entity.Event;
import com.gradinar.mynotes.entity.Schedule;
import com.gradinar.mynotes.entity.User;
import com.gradinar.mynotes.exception.EventIsEmptyException;
import com.gradinar.mynotes.exception.ScheduleNotFoundException;
import com.gradinar.mynotes.repository.EventRepository;
import com.gradinar.mynotes.repository.ScheduleRepository;
import com.gradinar.mynotes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public List<Long> getSchedulesForMonth(String beginOfMonth, String endOfMonth) {
        List<Schedule> schedules = scheduleRepository.findAllByMonth(
                new Date(Long.parseLong(beginOfMonth)),
                new Date(Long.parseLong(endOfMonth))
        );

        List<Long> timestamps = new ArrayList<>();
        for (Schedule schedule:
             schedules) {
            timestamps.add(schedule.getEvent_date().getTime());
        }

        return timestamps;
    }

    public List<Event> getTodayEvent(String currentDate) {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(currentDate)));
        if(schedule != null) {
            return eventRepository.findByScheduleId(schedule.getId());
        } else {
            throw new ScheduleNotFoundException();
        }
    }

    public ResponseEntity<Event> addEvent(String date, String content) throws URISyntaxException {
        if(content.isEmpty()) {
            throw new EventIsEmptyException();
        }
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(date)));
        if(schedule != null) {
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
            return ResponseEntity.created(new URI("/calendar/"+date)).body(event);
        } else {
            User user = userRepository.findCurrentByEmail();
            schedule = new Schedule();
            schedule.setEvent_date(new Date(Long.parseLong(date)));
            schedule.setUser(user);
            scheduleRepository.save(schedule);
            Event event = new Event();
            event.setSchedule(schedule);
            event.setContent(content);
            eventRepository.save(event);
            return ResponseEntity.created(new URI("/calendar/"+date)).body(event);
        }
    }

    public ResponseEntity<String> deleteSchedule(String date) {
        Schedule schedule =
                scheduleRepository.findByDate(new Date(Long.parseLong(date)));
        if(schedule != null) {
            scheduleRepository.delete(schedule);
        } else {
            throw new ScheduleNotFoundException();
        }

        return ResponseEntity.ok("Deleted");
    }

}
