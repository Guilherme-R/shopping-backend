package gsobrinho.shoppingbackend.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

    private Integer status;
//    private String type;
    private String title;
    private String detail;
    private LocalDateTime dateTime;
}
