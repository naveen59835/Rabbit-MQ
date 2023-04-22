package org.c2s3mc.Track.repositoryTest;

import org.c2s3mc.Track.domain.Artist;
import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.c2s3mc.Track.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class RepositoryTest {
@Autowired
  private   TrackRepository trackRepository;
private Track track;
private Artist artist;
private List<Track> trackList;
@BeforeEach
    public void setUp(){
    trackList=new ArrayList<>();
this.artist=new Artist(1,"Arijit Singh");
this.track=new Track(1,"Apna Bana Le",5,this.artist);
trackList.add(track);

}

@Test
@DisplayName("Test Case For Saving Track ")
public void saveTrackForTest(){
trackRepository.save(track);
Track track1=trackRepository.findById(track.getTrackId()).get();
assertEquals(track,track1);
}

@Test
@DisplayName("Test Case Delete Track")
public void testForDelete(){
    this.artist=new Artist(2,"Aayushman");
    this.track=new Track(2,"Mitti Di ",4,this.artist);
    trackRepository.insert(track);
Track track1=trackRepository.findById(track.getTrackId()).get();
trackRepository.delete(track1);
assertEquals(Optional.empty(),trackRepository.findById(track1.getTrackId()));
}

@Test
@DisplayName("Test Case For Get All Track")
public void testForGetAllTrack(){
trackRepository.deleteAll();
    this.artist=new Artist(3,"HoneySingh");
    this.track=new Track(3,"Brown Rang",6,this.artist);
    trackRepository.insert(track);
    this.artist=new Artist(4,"Atif Aslam");
    this.track=new Track(4,"Kabhi To Pass",6,this.artist);
    trackRepository.insert(track);
    List<Track> trackList=trackRepository.findAll();
    assertEquals(2,trackList.size());
}
@Test
@DisplayName("Test Case For Get Track By Artist Name")
public void testForGetTrackByArtistName() throws ArtistNameNotFoundException {
    trackRepository.deleteAll();
    this.artist=new Artist(3,"HoneySingh");
    this.track=new Track(3,"Brown Rang",6,this.artist);
    trackRepository.insert(track);
    this.artist=new Artist(4,"Atif Aslam");
    this.track=new Track(4,"Kabhi To Pass",6,this.artist);
    trackRepository.insert(track);
List<Track> trackList=trackRepository.findAllByArtistName("HoneySingh");
assertEquals(1,trackList.size());
}
@Test
@DisplayName("Test Case For Get Track By Rating Greater Than 4")
public void testForGetTrackByRating() throws TrackRatingNotFoundException {
    trackRepository.deleteAll();
    this.artist=new Artist(3,"HoneySingh");
    this.track=new Track(3,"Brown Rang",6,this.artist);
    trackRepository.insert(track);
    this.artist=new Artist(4,"Atif Aslam");
    this.track=new Track(4,"Kabhi To Pass",6,this.artist);
    trackRepository.insert(track);
    this.artist=new Artist(5,"Atif Aslam");
    this.track=new Track(5,"Teri Kahani",3,this.artist);
    trackRepository.insert(track);
    List<Track> trackList=trackRepository.findByTrackRating(4);
    assertEquals(2,trackList.size());
}

@Test
public void getAllTrack(){
    trackRepository.deleteAll();
    this.artist=new Artist(3,"HoneySingh");
    this.track=new Track(3,"Brown Rang",6,this.artist);
    trackRepository.insert(track);
   List<Track> tracks= trackRepository.findAll();
   assertEquals(trackList.size(),tracks.size());
}
    @AfterEach
    public void tearDown(){
    this.artist=null;
    this.track=null;
}


}
