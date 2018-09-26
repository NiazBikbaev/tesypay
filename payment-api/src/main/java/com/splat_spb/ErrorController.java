package com.splat_spb;

import com.splat_spb.dto.error.ErrorCode;
import com.splat_spb.dto.error.ErrorDto;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.splat_spb.dto.error.ErrorCode.AUTHENTICATION_FAILURE;
import static com.splat_spb.dto.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static com.splat_spb.dto.error.ErrorCode.INVALID_REQUEST;

/**
 * Default comment.
 **/
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class ErrorController extends AbstractErrorController {

    private final ErrorProperties errorProperties;

    public ErrorController(ErrorAttributes errorAttributes,
                           ServerProperties serverProperties, List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        ErrorProperties errorProperties = serverProperties.getError();
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @Override
    public String getErrorPath() {
        return this.errorProperties.getPath();
    }

    public ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }

    @ResponseBody
    @RequestMapping
    public ResponseEntity<ErrorDto> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request));
        HttpStatus status = getStatus(request);
        ErrorCode errorCode;
        String message = null;
        switch (status) {
            case FORBIDDEN:
                errorCode = AUTHENTICATION_FAILURE;
                break;
            case NOT_FOUND:
                errorCode = INVALID_REQUEST;
                message = "Not found";
                break;
            case UNAUTHORIZED:
                errorCode = AUTHENTICATION_FAILURE;
                break;
            case BAD_REQUEST:
                errorCode = INVALID_REQUEST;
                break;
            default:
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                errorCode = INTERNAL_SERVER_ERROR;
        }
        ErrorDto errorDto = new ErrorDto(errorCode, message == null ? (String) body.get("message") : message);
        return new ResponseEntity<>(errorDto, status);
    }


    private boolean isIncludeStackTrace(HttpServletRequest request) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

}
