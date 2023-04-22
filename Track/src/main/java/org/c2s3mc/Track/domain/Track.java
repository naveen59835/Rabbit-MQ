package org.c2s3mc.Track.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Track {
@Id
    private int trackId;
    private String trackName;
    private int trackRating;
    private Artist trackArtist;
    private boolean isPlayed;


}
