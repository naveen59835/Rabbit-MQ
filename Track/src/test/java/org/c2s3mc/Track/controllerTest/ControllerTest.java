package org.c2s3mc.Track.controllerTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.c2s3mc.Track.controller.TrackController;
import org.c2s3mc.Track.domain.Artist;
import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.c2s3mc.Track.service.TrackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    @Mock
    TrackService trackService;
    @InjectMocks
    TrackController trackController;
    @Autowired
    MockMvc mockMvc;
    private Track track;
    private Artist artist;
    List<Track> trackList;


    @BeforeEach
    public void setUp(){
        this.artist=new Artist(1,"Arijit Singh");
        this.track=new Track(1,"Apna Bana Le",5,this.artist);
        mockMvc= MockMvcBuilders.standaloneSetup(trackController).build();
        trackList=new ArrayList<>();
        trackList.add(track);
        this.artist=new Artist(2,"Justin");
        this.track=new Track(2,"Baby",3,this.artist);
        trackList.add(track);
    }
    @Test
    public void testForSaveTrack() throws Exception {
        when(trackService.addTrack(any())).thenReturn(track);
mockMvc.perform(post("/api/track").contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(track))).
        andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testForSaveTrackFailure() throws Exception {
        when(trackService.addTrack(any())).thenThrow(TrackAlreadyExistException.class);
        mockMvc.perform(post("/api/track").contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(track))).
                andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testForDeleteTrack() throws Exception {
        String result="Track Deleted Successfully";
        when(trackService.deleteTrack(anyInt())).thenReturn(result);
        mockMvc.perform(delete("/api/track/1").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void testForDeleteTrackFailure() throws Exception {
        String result="Track Deleted Successfully";
        when(trackService.deleteTrack(anyInt())).thenThrow(TrackNotFoundException.class);
        mockMvc.perform(delete("/api/track/1").contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForGetByArtistName() throws Exception {
when(trackService.findAllByArtistName(anyString())).thenReturn(trackList);
        mockMvc.perform(get("/api/tracks/Justin").contentType(MediaType.APPLICATION_JSON).
                content(convertJsonToString(trackList))).andExpect(status().isFound()).andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void testForGetByArtistNameFailure() throws Exception {
        when(trackService.findAllByArtistName(anyString())).thenThrow(ArtistNameNotFoundException.class);
        mockMvc.perform(get("/api/tracks/Justin").contentType(MediaType.APPLICATION_JSON).
                content(convertJsonToString(trackList))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void testForGetTrackGreaterThan4() throws Exception {
        when(trackService.findByTrackRating(anyInt())).thenReturn(trackList);
        mockMvc.perform(get("/api/track/4").contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(trackList))).andExpect(status().isFound()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testForGetTrackGreaterThan4Failure() throws Exception {
        when(trackService.findByTrackRating(anyInt())).thenThrow(TrackRatingNotFoundException.class);
        mockMvc.perform(get("/api/track/4").contentType(MediaType.APPLICATION_JSON).content(convertJsonToString(trackList))).andExpect(status().isNotFound()).andDo(MockMvcResultHandlers.print());
    }




    private static String convertJsonToString(final Object ob){
        String result;
        ObjectMapper objectMapper=new ObjectMapper();
        try{
            String content=objectMapper.writeValueAsString(ob);
            result=content;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            result="Json Parser error";
        }
        return result;
    }
}
