package one.digitalinnovation.parking.service;

import one.digitalinnovation.parking.exception.ParkingNotFoundException;
import one.digitalinnovation.parking.model.Parking;
import one.digitalinnovation.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

//    private static Map<String, Parking> parkingMap = new HashMap<>();

    //    static {
//        var id = getUUID();
//        var id1 = getUUID();
//        var id2 = "991e8dbaabcb4a01ac5c104a47ea1eec";
//        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO");
//        Parking parking1 = new Parking(id1, "SDS-2222", "SP", "GOL", "PRETO");
//        Parking parking2 = new Parking(id2, "TPS-4444", "SPR", "ONIX", "AZUL");
//        parkingMap.put(id, parking);
//        parkingMap.put(id1, parking1);
//        parkingMap.put(id2, parking2);
//    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    // metodos de find define como read only, pois nao alteram dados
    public List<Parking> findAll() {
//        return parkingMap.values().stream().collect(Collectors.toList());
        return parkingRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Parking findById(String id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
//        Parking parking = parkingMap.get(id);
//        if (parking == null) {
//            throw new ParkingNotFoundException(id);
//        }
//        return parking;
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
//        parkingMap.put(uuid, parkingCreate);

        parkingRepository.save(parkingCreate);

        return parkingCreate;
    }

    @Transactional
    public void delete(String id) {
        findById(id);
        parkingRepository.deleteById(id);
//        parkingMap.remove(id);
    }

    @Transactional
    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());

//        parkingMap.replace(id, parking);
        parkingRepository.save(parking);

        return parking;
    }

    @Transactional
    public Parking checkOut(String id) {
        Parking parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(ParkingCheckout.getBill(parking));
        parkingRepository.save(parking);

        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
