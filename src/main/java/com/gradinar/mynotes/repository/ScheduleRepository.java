package com.gradinar.mynotes.repository;

import com.gradinar.mynotes.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("SELECT s FROM schedule s WHERE s.event_date = ?1 AND s.user.email = ?#{principal?.username}")
    Schedule findByDate(Date scheduleDate);

    @Query("SELECT s FROM schedule s WHERE s.event_date BETWEEN ?1 AND ?2 AND s.user.email = ?#{principal?.username}")
    List<Schedule> findAllByMonth(Date beginOfMonth, Date endOfMonth);

}
