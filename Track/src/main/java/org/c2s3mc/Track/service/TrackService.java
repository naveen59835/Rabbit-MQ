package org.c2s3mc.Track.service;

import org.c2s3mc.Track.config.TrackDTO;
import org.c2s3mc.Track.domain.Track;
import org.c2s3mc.Track.domain.User;
import org.c2s3mc.Track.exception.TrackAlreadyExistException;
import org.c2s3mc.Track.exception.TrackNotFoundException;
import org.c2s3mc.Track.proxy.UserProxy;
import org.c2s3mc.Track.repository.TrackRepository;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService implements ITrackService{

    TrackRepository trackRepository;
    UserProxy userProxy;
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
@Autowired
    public TrackService(TrackRepository trackRepository, UserProxy userProxy, RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.trackRepository = trackRepository;
        this.userProxy = userProxy;
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    @Override
    public List<User> getAllUser() {
      return trackRepository.findAll();
    }

    @Override
    public User addUser(User user) throws TrackAlreadyExistException {
        if(trackRepository.findById(user.getEmail()).isEmpty()){
            userProxy.saveData(user);
        return trackRepository.save(user);
    }
       throw new TrackAlreadyExistException();
}

    @Override
    public String deleteUser(String id) throws TrackNotFoundException {
            if(trackRepository.findById(id).isEmpty()){
        throw new TrackNotFoundException();
    }
     trackRepository.deleteById(id);
        return"User Deleted Successfully";
    }

    @Override
    public User saveUserTrackToList(Track track, String email) throws TrackNotFoundException {
        if(trackRepository.findById(email).isEmpty()){
            throw new TrackNotFoundException();
        }
        // user present
        User result=trackRepository.findById(email).get();
        if(result.getTrackList()!=null) {
            result.getTrackList().add(track);
        }else{
            result.setTrackList(new ArrayList<>());
            result.getTrackList().add(track);
        }
        trackRepository.save(result);
        return result;
    }

    @Override
    public List<Track> getAllUserTrack(String email) throws TrackNotFoundException {
        if(trackRepository.findById(email).isEmpty()){
            throw new TrackNotFoundException();
        }
        List<Track> allTrack=trackRepository.findById(email).get().getTrackList();
        List<Track> notPlayed=new ArrayList<>();
        for(Track tracks:allTrack){
            if(!tracks.isPlayed()){
                notPlayed.add(tracks);
            }
        }
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("trackList",notPlayed);
        jsonObject.put("email",email);
        TrackDTO trackDTO=new TrackDTO();
        rabbitTemplate.convertAndSend(directExchange.getName(),"product_routing",trackDTO);
        return allTrack;

    }
    }









//    @Override
//    public List<Track> getAllTrack() {
//        return trackRepository.findAll();
//    }
//
//    @Override
//    public Track addTrack(Track track) throws TrackAlreadyExistException {
//
//    if(trackRepository.findById(track.getTrackId()).isEmpty()){
//        return trackRepository.save(track);
//    }
//       throw new TrackAlreadyExistException();
//    }
//
//    @Override
//    public String deleteTrack(int id) throws TrackNotFoundException {
//    if(trackRepository.findById(id).isEmpty()){
//        throw new TrackNotFoundException();
//    }
//     trackRepository.deleteById(id);
//        return"Track Deleted Successfully";
//
//    }
//
//    @Override
//    public List<Track> findByTrackRating(int rating) throws TrackRatingNotFoundException {
//    if(trackRepository.findByTrackRating(rating).isEmpty()){
//        throw new TrackRatingNotFoundException();
//    }
//        return trackRepository.findByTrackRating(rating) ;
//    }
//
//    @Override
//    public List<Track> findAllByArtistName(String artistName) throws ArtistNameNotFoundException {
//       if(trackRepository.findAllByArtistName(artistName).isEmpty()){
//           throw new ArtistNameNotFoundException();
//       }
//       return trackRepository.findAllByArtistName(artistName);
//    }

