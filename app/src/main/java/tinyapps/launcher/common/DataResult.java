package tinyapps.launcher.common;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by admin on 7/11/17.
 */
@Getter
@Setter
public class DataResult<T> {
    private T data;
    private boolean success;
    private String errorMsg;

    public DataResult(T data) {
        this.data = data;
    }
}
