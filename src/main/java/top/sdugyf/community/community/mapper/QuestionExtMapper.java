package top.sdugyf.community.community.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import top.sdugyf.community.community.model.Question;
import top.sdugyf.community.community.model.QuestionExample;

import java.util.List;

public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question  record);
}