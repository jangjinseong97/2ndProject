package com.green.jobdone.config.webSocket;

import com.green.jobdone.common.MyFileUtils;
import com.green.jobdone.room.chat.ChatMapper;
import com.green.jobdone.room.chat.ChatService;
import com.green.jobdone.room.chat.model.ChatPicDto;
import com.green.jobdone.room.chat.model.ChatPostReq;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Component


public class ChatWebSocketHandler extends TextWebSocketHandler {
    @Autowired
    private ChatService chatService;
    private MyFileUtils myFileUtils;

    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketHandler.class);
    private List<WebSocketSession> sessions = new ArrayList<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 새로운 세션이 연결되었을 때 세션 리스트에 추가
        sessions.add(session);
        logger.info("WebSocket connection established: " + session.getId());
    }
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 텍스트 메시지 수신 및 처리
        logger.info("Received message: " + message.getPayload());

        // 모든 클라이언트에게 메시지 브로드캐스트
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage("New message: " + message.getPayload()));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션이 종료되면 세션 리스트에서 제거
        sessions.remove(session);
        logger.info("WebSocket connection closed: " + session.getId());
    }

    private long getChatIdBinary(BinaryMessage message) {
        byte[] payload = message.getPayload().array();
        // 예시: 메시지의 첫 8바이트를 chatId로 사용
        return ByteBuffer.wrap(payload, 0, 8).getLong(); // 처음 8바이트를 chatId로 해석
    } // 바이너리 메세지에 chatId가 있는 경우

    private long getChatIdJson(BinaryMessage message) {
        byte[] payload = message.getPayload().array();
        String jsonString = new String(payload, StandardCharsets.UTF_8);

        // JSON에서 chatId 추출
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            return jsonObject.getLong("chatId");
        } catch (JSONException e) {
            throw new RuntimeException("메세지에 chatId가 없습니다.", e);
        }
    } // json으로 보내줄거면

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) {
        try {
            // 바이너리 메시지에서 파일 수신 (여기서는 이미지 파일을 예시로 들었습니다)
            List<MultipartFile> pics = convertByteArrayToMultipartFile(message.getPayload().array());  // 바이너리 데이터를 MultipartFile로 변환

            // 채팅 데이터와 파일을 서비스 계층에 전달
            ChatPostReq chatPostReq = new ChatPostReq();
            chatPostReq.setRoomId(123L);  // 예시 값
            chatPostReq.setChatId(456L);  // 예시 값

            int result = chatService.insChat(pics, chatPostReq);  // 서비스 계층에서 파일을 처리

            // 클라이언트에게 결과 전송
            session.sendMessage(new TextMessage("파일 업로드 완료: " + result));

        } catch (Exception e) {
            logger.error("파일 업로드 중 오류 발생", e);  // 예외 로그 남기기
            try {
                session.sendMessage(new TextMessage("파일 업로드 실패: " + e.getMessage()));  // 클라이언트에게 실패 메시지 전송
            } catch (IOException ex) {
                logger.error("웹소켓 응답 전송 중 오류 발생", ex);  // 이 예외는 또 다른 로그를 남기기
                // 필요시 추가 처리 (예: WebSocket 연결 종료 처리)
            }
        }
    }
    private List<MultipartFile> convertByteArrayToMultipartFile(byte[] payload) {
        List<MultipartFile> pics = new ArrayList<>();

        // 예시로 하나의 파일로 변환 (필요시 payload를 여러 개의 파일로 나누는 로직을 추가할 수 있음)
        String fileName = "uploaded_image.jpg"; // 파일 이름 설정
        String contentType = "image/jpeg"; // 콘텐츠 타입 설정

        // CustomMultipartFile로 변환
        MultipartFile multipartFile = new CustomMultipartFile(payload, fileName, contentType);
        pics.add(multipartFile);  // MultipartFile 리스트에 추가

        return pics;  // 변환된 MultipartFile 리스트 반환
    }


    }


