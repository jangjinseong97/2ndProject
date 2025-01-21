package com.green.jobdone.room;

import com.green.jobdone.room.model.RoomGetReq;
import com.green.jobdone.room.model.RoomGetRes;
import com.green.jobdone.room.model.RoomPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {
    private final RoomMapper roomMapper;
    public List<RoomGetRes> selRoom(RoomGetReq req) {
        List<RoomGetRes> res = roomMapper.selRoom(req);
        return res;
    }

    public int insRoom(RoomPostReq req) {
        int res = roomMapper.insRoom(req);

        return res;
    }
}
