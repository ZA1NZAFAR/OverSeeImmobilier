package models;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Log {

    private long idLog;
    private long idAgent;
    private String action;
    private String information;

}
