package top.sdugyf.community.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.sdugyf.community.community.model.Comment;
import top.sdugyf.community.community.model.CommentExample;

import java.util.List;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}