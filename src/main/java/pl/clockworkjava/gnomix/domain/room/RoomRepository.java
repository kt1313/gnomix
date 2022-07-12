package pl.clockworkjava.gnomix.domain.room;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long>{

     List<Room> findBySizeGreaterThanEqual(Integer size);

}