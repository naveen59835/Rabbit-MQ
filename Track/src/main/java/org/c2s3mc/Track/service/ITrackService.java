package org.c2s3mc.Track.service;

import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.domain.User;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;

import java.util.List;

public interface ITrackService {
//    public List<Track>getAllTrack();
//    public Track addTrack(Track track) throws TrackAlreadyExistException;
//    public String deleteTrack(int id) throws TrackNotFoundException;
//    public List<Track> findByTrackRating(int rating) throws TrackRatingNotFoundException;
//    public List<Track> findAllByArtistName(String artistName) throws ArtistNameNotFoundException;

    public List<User> getAllUser();
    public User addUser(User user) throws TrackAlreadyExistException;
    public String deleteUser(String id) throws TrackNotFoundException;


    public User saveUserTrackToList(Track track, String email) throws TrackNotFoundException;
    public List<Track> getAllUserTrack(String email) throws TrackNotFoundException;

}
