package org.c2s3mc.Track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Track Not Found")
public class TrackNotFoundException extends Exception{
}
