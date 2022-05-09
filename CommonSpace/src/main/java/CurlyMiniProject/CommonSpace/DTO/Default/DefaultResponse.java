package CurlyMiniProject.CommonSpace.DTO.Default;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public DefaultResponse(final int statusCode, final String message) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int statusCode, final String message, final T data) {
        return DefaultResponse.<T>builder()
                .data(data)
                .statusCode(statusCode)
                .message(message)
                .build();
    }
}
