package top.sdugyf.community.community.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.sdugyf.community.community.enums.CommentTypeEnum;
import top.sdugyf.community.community.exception.CustomizeErrorCode;
import top.sdugyf.community.community.exception.CustomizeException;
import top.sdugyf.community.community.mapper.CommentMapper;
import top.sdugyf.community.community.mapper.QuestionExtMapper;
import top.sdugyf.community.community.mapper.QuestionMapper;
import top.sdugyf.community.community.model.Comment;
import top.sdugyf.community.community.model.Question;

@Service
public class CommentService {

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || CommentTypeEnum.notExist(comment.getType())){

            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(dbComment==null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
        } else {

            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if(question==null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
