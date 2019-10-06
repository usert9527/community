package cn.user9527.mycommunity.model;

import com.sun.org.glassfish.gmbal.AMXMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommentTest {

  private Integer id;
  private Integer parentId;
  private Integer type;
  private Integer commentator;
  private Long gmtCreate;
  private Long gmtModified;
  private Integer likeCount;
  private String comment;

}
