package com.ksw.dao.object;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksw.object.entity.jpa.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

}
