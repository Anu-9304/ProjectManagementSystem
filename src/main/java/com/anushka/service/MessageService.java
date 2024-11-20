package com.anushka.service;

import java.util.List;

import com.anushka.model.Message;

public interface MessageService {

    Message sendMessage (Long senderId, Long chatId, String content) throws Exception;
    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}