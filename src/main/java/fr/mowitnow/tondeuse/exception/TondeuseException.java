package fr.mowitnow.tondeuse.exception;

import lombok.extern.apachecommons.CommonsLog;

/**
 * Exception de l'application
 * 
 * @author Benjamin
 *
 */
@CommonsLog
public class TondeuseException extends RuntimeException {
    private static final long serialVersionUID = -2559842091333391697L;

    public TondeuseException(TondeuseExceptionEnum tondeuseExceptionEnum) {
        super(tondeuseExceptionEnum.getMessage());

        log.error(tondeuseExceptionEnum.getMessage());
    }

    public TondeuseException(TondeuseExceptionEnum tondeuseExceptionEnum, Exception e) {
        this(tondeuseExceptionEnum);

        log.error(e);
    }

}
