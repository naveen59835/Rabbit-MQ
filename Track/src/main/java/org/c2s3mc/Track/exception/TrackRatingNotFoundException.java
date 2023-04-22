package org.c2s3mc.Track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Track Rating Greater Than 4 Not Found")
public class TrackRatingNotFoundException extends Exception{
}
