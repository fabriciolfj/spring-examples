package com.github.fabriciolfj.javaexamples.service;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ReplicationNotificationListener implements NotificationListener {

    @Override
    public void handleNotification(Notification not, Object handback) {
        if(not.getType().startsWith("replication")) {
            System.out.printf("%s %s #%d%n", not.getSource(), not.getType(), not.getSequenceNumber());
        }
    }
}
