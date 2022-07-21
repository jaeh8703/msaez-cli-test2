package petstore.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Embeddable
@Data
public class Photo {

    private String imgName;

    @Lob
    private String imgFile;
}
