package yju.wdb.finalterm.service;

import yju.wdb.finalterm.Entity.Friend;
import yju.wdb.finalterm.dto.FriendDTO;

import java.util.List;

public interface FriendService {
    void register(FriendDTO dto) ;

    void update(FriendDTO dto);

    void delete(Long id);

    FriendDTO show(Long id);

    List<FriendDTO> getList();

    default Friend convertDTO2Entity(FriendDTO dto) {
        Friend friend = new Friend(dto.getId(), dto.getName() ,dto.getBirthYear(), dto.getGender(), dto.getPhone());
        return friend;
    }

    default <FriendDto> FriendDto convertEntity2DTO(Friend entity) {
        FriendDTO dto = new FriendDTO(entity.getId(), entity.getName(), entity.getBirthYear(), entity.getGender(), entity.getPhone(), entity.getReDate(), entity.getModDate());
        return (FriendDto) dto;
    }
}
