package com.green.jobdone.room.chat.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter

public class ChatDto {
    private List<MultipartFile> pics;
    private ChatPostReq p;
}
