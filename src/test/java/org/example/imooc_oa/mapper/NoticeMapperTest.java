package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Notice;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class NoticeMapperTest {

    @Test
    void insertNotice() {
        NoticeMapper noticeMapper = new NoticeMapper();
        Notice notice = new Notice();
        notice.setReceiverId(5);
        notice.setContent("aaa");
        notice.setCreateTime(new Date());
        noticeMapper.insertNotice(notice);
    }
}