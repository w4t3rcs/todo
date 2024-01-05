package com.w4t3rcs.newtodo.model.data.dao;

import com.w4t3rcs.newtodo.model.entity.holder.NotificationHolder;
import com.w4t3rcs.newtodo.model.entity.message.Notification;
import org.springframework.data.repository.CrudRepository;

public interface NotificationHolderRepository extends CrudRepository<NotificationHolder, Long> {
    Iterable<NotificationHolder> findAllByNotification(Notification notification);
}
