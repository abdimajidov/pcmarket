package uz.pdp.apppcmarket.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String message;
    private boolean succes;
    private Object object;

    public Result(String message, boolean succes) {
        this.message = message;
        this.succes = succes;
    }
}
