package com.anushka.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anushka.config.request.CreateMessageRequest;
import com.anushka.model.Chat;
import com.anushka.model.Message;
import com.anushka.model.User;
import com.anushka.service.MessageService;
import com.anushka.service.ProjectService;
import com.anushka.service.UserService;

@RestController
@RequestMapping("/api/Messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) 
    throws Exception {

        User user= userService.findUserById(request.getSenderId()); 

        Chat chats= projectService.getProjectById(request.getProjectId()).getChat(); 
        Message sentMessage= messageService.sendMessage(request.getSenderId(), request.getProjectId(), request.getContent());
        
        return ResponseEntity.ok(sentMessage);
    }    

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId)
    throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        
        return ResponseEntity.ok(messages);
    }    

}
