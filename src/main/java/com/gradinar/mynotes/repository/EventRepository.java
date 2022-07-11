package com.gradinar.mynotes.repository;

import com.gradinar.mynotes.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT sn FROM Event sn WHERE sn.schedule.id = ?1")
    List<Event> findByScheduleId(Long eventId);
}
