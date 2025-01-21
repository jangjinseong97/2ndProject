package com.green.jobdone.room.chat;

import com.green.jobdone.room.chat.model.ChatPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatMapper chatMapper;

    @Transactional
    public int insChat(ChatPostReq p){
        int res = chatMapper.insChat(p);


        return 0;
    }
}
