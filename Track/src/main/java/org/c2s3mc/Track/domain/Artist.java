package org.c2s3mc.Track.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Artist {
    @Id
    private int artistId;
    private String artistName;


}
