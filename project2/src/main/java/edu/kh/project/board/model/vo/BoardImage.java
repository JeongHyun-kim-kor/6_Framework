package edu.kh.project.board.model.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardImage {

    private int imageNo;
    private String imagePath;
    private String imageReName;
    private String imageOriginal;
    private int imageOrder;
    private int boardNo;
    
    // 이미지 목록
    private List<BoardImage> imageList;
    
    // 댓글 목록
    private List<Comment> commentList;
}
