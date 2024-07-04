package com.ksw.dao.forObject.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
