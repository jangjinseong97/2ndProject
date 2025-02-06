package com.green.jobdone.config.webSocket;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.room.chat.ChatMapper;
import com.green.jobdone.room.chat.ChatService;
import com.green.jobdone.room.chat.model.ChatPicDto;
import com.green.jobdone.room.chat.model.ChatPostReq;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Component


public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private ChatService chatService;

    @Autowired
    private MyFileUtils myFileUtils;

    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketHandler.class);
    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
        logger.info("WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        logger.info("Received message: " + message.getPayload());

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                try {
                    webSocketSession.sendMessage(new TextMessage("새 메세지: " + message.getPayload()));
                } catch (IOException e) {
                    logger.error("웹소켓 메시지 전송 중 오류 발생", e);
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        logger.info("WebSocket connection closed: " + session.getId());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            byte[] payload = message.getPayload().array();
            String jsonString = new String(payload, StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);

            // ✅ 필수 필드 확인
            if (!jsonObject.has("roomId") || !jsonObject.has("flag")) {
                throw new RuntimeException("JSON 데이터에 roomId 또는 flag가 없습니다.");
            }

            long roomId = jsonObject.getLong("roomId");
            int flag = jsonObject.getInt("flag");

            // ✅ 메시지 (없을 수도 있음)
            String textMessage = jsonObject.optString("message", "").trim();

            // ✅ 파일 처리 (여러 개)
            JSONArray filesArray = jsonObject.optJSONArray("files");
            List<MultipartFile> pics = new ArrayList<>();

            if (filesArray != null) {
                for (int i = 0; i < filesArray.length(); i++) {
                    String base64File = filesArray.getString(i);
                    byte[] fileData = Base64.getDecoder().decode(base64File);
                    pics.add(convertByteArrayToMultipartFile(fileData, "uploaded_file_" + i));
                }
            }

            // ✅ 메시지와 파일이 모두 없으면 예외 처리
            if (textMessage.isEmpty() && pics.isEmpty()) {
                throw new RuntimeException("메시지와 파일이 모두 비어 있습니다.");
            }

            // ✅ 채팅 저장 요청 객체 생성
            ChatPostReq chatPostReq = new ChatPostReq();
            chatPostReq.setRoomId(roomId);
            chatPostReq.setContents(textMessage.isEmpty() ? null : textMessage);
            chatPostReq.setFlag(flag);

            // ✅ 채팅 저장
            chatService.insChat(pics, chatPostReq);

            // ✅ 클라이언트 응답
            session.sendMessage(new TextMessage("파일 업로드 및 메시지 저장 완료"));

        } catch (Exception e) {
            logger.error("파일 업로드 및 메시지 처리 중 오류 발생", e);
            try {
                session.sendMessage(new TextMessage("파일 업로드 및 메시지 처리 실패: " + e.getMessage()));
            } catch (IOException ex) {
                logger.error("웹소켓 응답 전송 중 오류 발생", ex);
            }
        }
    }

    private MultipartFile convertByteArrayToMultipartFile(byte[] payload, String fileName) {
        return new CustomMultipartFile(payload, fileName, "application/octet-stream");
    }
}

