package org.c2s3mc.Track.controller;

import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.domain.User;
import org.c2s3mc.Track.exception.ArtistNameNotFoundException;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;
import org.c2s3mc.Track.exception.TrackRatingNotFoundException;
import org.c2s3mc.Track.service.ITrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trackService")
public class TrackController {

    ITrackService iTrackService;
    private ResponseEntity<?> responseEntity;
@Autowired
    public TrackController(ITrackService iTrackService) {
        this.iTrackService = iTrackService;
    }

        @GetMapping("/user")
    public ResponseEntity<?> getAllData(){
            return new ResponseEntity<>(iTrackService.getAllUser(), HttpStatus.OK);
        }

    @PostMapping("/user")
    public ResponseEntity<?>addData(@RequestBody User user) throws TrackAlreadyExistException {
    return new ResponseEntity<>(iTrackService.addUser(user),HttpStatus.CREATED);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?>deleteData(@PathVariable String id) throws TrackNotFoundException {
    return new ResponseEntity<>(iTrackService.deleteUser(id),HttpStatus.OK);
    }

    @PostMapping("/track/{email}")
    public ResponseEntity<?>saveUserTrackToList(@RequestBody Track track,@PathVariable String email) throws TrackNotFoundException{
        try{
            responseEntity= new ResponseEntity<>(iTrackService.saveUserTrackToList(track,email),HttpStatus.CREATED);
        } catch (TrackNotFoundException e) {
            throw new TrackNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/track/{email}")
    public ResponseEntity<?>getAllUserTracks(@PathVariable  String email)throws TrackNotFoundException{
        return new ResponseEntity<>(iTrackService.getAllUserTrack(email),HttpStatus.OK);
    }




//    @GetMapping("/track")
//    public ResponseEntity<?> getAllData(){
//    return new ResponseEntity<>(iTrackService.getAllTrack(), HttpStatus.OK);
//    }
//    @PostMapping("/track")
//    public ResponseEntity<?>addData(@RequestBody Track track) throws TrackAlreadyExistException {
//    return new ResponseEntity<>(iTrackService.addTrack(track),HttpStatus.CREATED);
//    }
//    @DeleteMapping("/track/{id}")
//    public ResponseEntity<?>deleteData(@PathVariable int id) throws TrackNotFoundException {
//    return new ResponseEntity<>(iTrackService.deleteTrack(id),HttpStatus.OK);
//    }
//
//    @GetMapping("/track/{rating}")
//    public ResponseEntity<?>getByRating(@PathVariable int rating){
//    try {
//        List<Track> trackList=iTrackService.findByTrackRating(rating);
//        return new ResponseEntity<>(trackList,HttpStatus.FOUND);
//    }catch (TrackRatingNotFoundException e){
//        throw new RuntimeException(e);
//    }
//    }
//    @GetMapping("/tracks/{artistName}")
//    public ResponseEntity<?>getByArtistName(@PathVariable String artistName){
//    try {
//        List<Track> trackList=iTrackService.findAllByArtistName(artistName);
//        return new ResponseEntity<>(trackList,HttpStatus.FOUND);
//    }catch (ArtistNameNotFoundException e){
//        throw new RuntimeException(e);
//    }
//    }

}