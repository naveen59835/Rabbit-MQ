package org.c2s3mc.Track.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Artist Not Found")
public class ArtistNameNotFoundException extends Exception{
}
