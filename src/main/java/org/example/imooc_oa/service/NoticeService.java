package org.example.imooc_oa.service;

import org.example.imooc_oa.Entity.Notice;
import org.example.imooc_oa.mapper.NoticeMapper;

import java.util.List;

public class NoticeService {
    public List<Notice> selectNoticeByReceiverId(long receiverId) {
        NoticeMapper noticeMapper = new NoticeMapper();
        return noticeMapper.selectNoticeByReceiverId(receiverId);
    }
}
