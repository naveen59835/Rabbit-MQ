package org.c2s3mc.Track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "Track is Already Exist")
public class TrackAlreadyExistException extends Exception{
}
