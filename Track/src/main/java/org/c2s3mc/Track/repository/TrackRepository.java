package org.c2s3mc.Track.repository;

import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.domain.User;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<User,String> {
//@Query("{trackRating:{$gt:?0}}")
//    public List<Track> findByTrackRating(int rating) throws TrackRatingNotFoundException;
//@Query("{'trackArtist.artistName':{$in:[?0]}}")
//public List<Track> findAllByArtistName(String artistName) throws ArtistNameNotFoundException;

}
