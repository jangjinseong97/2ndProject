package com.green.jobdone.room.chat;

import com.green.jobdone.common.model.ResultResponse;
import com.green.jobdone.room.chat.model.ChatGetReq;
import com.green.jobdone.room.chat.model.ChatGetRes;
import com.green.jobdone.room.chat.model.ChatPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("chat")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "7. 문의 채팅")
public class ChatController {
    private final ChatService chatService;

    @Operation(summary = "채팅 보내기")
    @PostMapping
    public ResultResponse<Integer> insChat(@RequestPart(required = false) List<MultipartFile> pics, @RequestPart ChatPostReq p){
        int res = chatService.insChat(pics, p);

        return ResultResponse.<Integer>builder()
                .resultMessage("송신 완료")
                .resultData(res)
                .build();
    }

    @Operation(summary = "채팅 조회")
    @GetMapping
    public ResultResponse<List<ChatGetRes>> getRoomChat(@ModelAttribute ChatGetReq p){
        List<ChatGetRes> res = chatService.selRoomChat(p);

        return ResultResponse.<List<ChatGetRes>>builder()
                .resultData(res)
                .resultMessage("조회 완료")
                .build();
    }

}
