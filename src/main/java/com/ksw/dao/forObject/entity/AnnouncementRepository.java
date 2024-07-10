package com.ksw.dao.forObject.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.Announcement;


//															 	<객체,id타입>
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

	List<Announcement> findAll();
}
