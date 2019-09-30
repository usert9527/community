package cn.user9527.mycommunity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Question {

  private Integer id;
  private String title;
  private String description;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer creator;
  private Integer commentCount;
  private Integer viewCount;
  private Integer likeCount;
  private String tag;



}
