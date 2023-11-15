package com.seser.mancalagame.domain.response;

import lombok.Builder;
import lombok.Data;

/**
 * @author Eser
 * This class is a response DTO for keeping detailed error information.
 */
@Builder
@Data
public class ResponseError {
    private String createdAt;
    private String detailedMessage;
    private int errorCode;
    private String exceptionName;
    private String errorMessage;
}