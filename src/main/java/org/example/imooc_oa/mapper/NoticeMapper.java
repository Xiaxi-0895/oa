package org.example.imooc_oa.mapper;

import org.example.imooc_oa.Entity.Notice;
import org.example.imooc_oa.Utils.MybatisUtils;

import java.util.List;

public class NoticeMapper {
    public void insertNotice(Notice notice) {
        MybatisUtils.executeSql(sqlSession -> {
            sqlSession.insert("notice.insert", notice);
            sqlSession.commit();
            return null;
        });
    }
    public List<Notice> selectNoticeByReceiverId(long id) {
        return (List<Notice>) MybatisUtils.executeSql(sqlSession -> sqlSession.selectList("notice.selectNoticeByReceiverId", id));
    }
}
