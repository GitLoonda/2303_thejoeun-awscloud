package com.tj.edu.practice5.jpa.model;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class TimeAuditEntityListener {

    @PrePersist
    public void preInsert(Object obj) {
        if(obj instanceof TimeAuditable) {
            ((TimeAuditable) obj).setCreateAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object obj) {
        if(obj instanceof TimeAuditable) {
            ((TimeAuditable) obj).setUpdateAt(LocalDateTime.now());
        }
    }
}
