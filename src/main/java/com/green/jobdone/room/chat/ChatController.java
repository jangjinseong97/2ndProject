package com.green.jobdone.room.chat;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.room.chat.model.ChatPostReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    @Operation(summary = "채팅 보내기")
    @PostMapping
    public ResultResponse<Integer> insChat(@RequestPart List<MultipartFile> pic, @RequestPart ChatPostReq p){
        return null;
    }

}
