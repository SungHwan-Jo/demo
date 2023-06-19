package com.tmax.hf.online.repository;

import com.tmax.hf.online.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "select o_emailaddress, o_regdt, o_totalpoint from fn_event_top_rating(:eventcode)", nativeQuery = true)
    public List<Event> getEventList(@Param("eventcode") String eventcode);
    //TODO : parameter 추가
}
