package com.zero.downtime.userservice;

import com.zero.downtime.userservice.dto.MigrationEvent;
import com.zero.downtime.userservice.entity.User;
import com.zero.downtime.userservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MigrationListener {

    @Autowired
    private UserRepository userRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void handleMigration(MigrationEvent event) {
        if ("add-email".equals(event.getAction())) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                if (user.getEmail() == null) {
                    user.setEmail(user.getName().toLowerCase() + "@example.com");
                    userRepository.save(user);
                }
            }
            System.out.println("Email field initialized for all users ✅");
        }
        if ("add-status".equals(event.getAction())) {
            List<User> users = userRepository.findAll();
            for (User user : users) {
                if (user.getStatus() == null) {
                    user.setStatus("active"); // Default status
                    userRepository.save(user);
                }
            }
            System.out.println("Status field initialized for all users ✅");
        }
    }
}
