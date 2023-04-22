package org.c2s3mc.Track.serviceTest;

import org.c2s3mc.Track.domain.Artist;
import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.c2s3mc.Track.repository.TrackRepository;
import org.c2s3mc.Track.service.TrackService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrackServiceTest {
@Mock
    TrackRepository trackRepository;
@InjectMocks
    TrackService trackService;

private Track track;
private Artist artist;
List<Track> trackList;

@BeforeEach
public void setUp(){
    trackList=new ArrayList<>();
    this.artist=new Artist(1,"Arijit Singh");
    this.track=new Track(1,"Apna Bana Le",5,this.artist);
    trackList.add(track);
    this.artist=new Artist(3,"HoneySingh");
    this.track=new Track(3,"Brown Rang",6,this.artist);
    trackList.add(track);
}
@Test
public void saveTrackSuccessfully() throws TrackAlreadyExistException {
when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
when(trackRepository.save(track)).thenReturn(track);
assertEquals(track,trackService.addTrack(track));
}

    @Test
    public void saveTrackFailure(){
when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
assertThrows(TrackAlreadyExistException.class,()->trackService.addTrack(track));
    }
    @Test
    public void deleteTrackSuccessfully() throws TrackNotFoundException {
when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
String result=trackService.deleteTrack(track.getTrackId());
assertEquals("Track Deleted Successfully",result);
    }

    @Test
    public void deleteTrackFailure(){
when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TrackNotFoundException.class,()->trackService.deleteTrack(track.getTrackId()));
    }
    @Test
    public void getByArtistName() throws ArtistNameNotFoundException {
        trackRepository.deleteAll();
        List<Track> filterList=new ArrayList<>();
        for(Track tr:trackList)
        {
            if(tr.getTrackArtist().getArtistName().equals("Justin Bieber")){
                filterList.add(tr);
            }
        }
        when(trackRepository.findAllByArtistName("Justin Bieber")).thenReturn(filterList);
        List<Track> trackList1=new ArrayList<>();
        trackList1=trackService.findAllByArtistName("Justin Bieber");
        assertEquals(filterList,trackList1);
    }

    @Test
    public void getByArtistNameFailure() throws ArtistNameNotFoundException {
        trackRepository.deleteAll();
        List<Track> filterList=new ArrayList<>();
        for(Track tr:trackList)
        {
            if(tr.getTrackArtist().getArtistName().equals("Justin Bieber")){
                filterList.add(tr);
            }
        }
        when(trackRepository.findAllByArtistName("Justin Bieber")).thenReturn(filterList);
        List<Track> trackList1=new ArrayList<>();
        trackList1=trackService.findAllByArtistName("Justin Bieber");
        assertNotEquals(trackList,trackList1);
    }

    @Test
    public void getTrackGreaterThan4() throws TrackRatingNotFoundException {
        trackRepository.deleteAll();
        List<Track> filterList=new ArrayList<>();
        for(Track tr:trackList)
        {
            if(tr.getTrackRating()>4){
                filterList.add(tr);
            }
        }
        when(trackRepository.findByTrackRating(4)).thenReturn(filterList);
        List<Track> trackList1=new ArrayList<>();
        trackList1=trackService.findByTrackRating(4);
        assertEquals(filterList,trackList1);
    }








    @AfterEach
public void tearDown(){
    this.artist=null;
    this.track=null;
    }


}
