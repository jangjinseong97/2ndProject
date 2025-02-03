package com.green.jobdone.room.chat;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.common.PicUrlMaker;
import com.green.jobdone.room.chat.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatMapper chatMapper;
    private final MyFileUtils myFileUtils;

    @Transactional
    public int insChat(List<MultipartFile> pics, ChatPostReq p){
        int res = chatMapper.insChat(p);
        if(pics == null){
            return res;
        }

        long chatId = p.getChatId();
        String filePath = String.format("room/%d/chat/%d",p.getRoomId(),chatId);
        myFileUtils.makeFolders(filePath);
        List<String> picName = new ArrayList<>(pics.size());
        for(MultipartFile pic : pics){
            String fileName = myFileUtils.makeRandomFileName(pic.getOriginalFilename());
            picName.add(fileName);
            try {
                myFileUtils.transferTo(pic, filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        ChatPicDto chatPicDto = new ChatPicDto();
        chatPicDto.setChatId(chatId);
        chatPicDto.setPics(picName);
        int res2 = chatMapper.insChatPic(chatPicDto);

        return res2;
    }

    public List<ChatGetRes> selRoomChat(ChatGetReq p){
        List<ChatGetRes> res = chatMapper.selRoomChat(p);
        long roomId = p.getRoomId();
        for (ChatGetRes chat : res) {
            long chatId = chat.getChatId();
            List<GetPicDto> a = chat.getPics();

            if (a == null) {continue;}

            for (GetPicDto picDto : a) {
                String picName = picDto.getPic();
                picDto.setPic(PicUrlMaker.makePicUrlChat(roomId, chatId, picName));
            }
        }

        return res;
    }
}
