package airport.psk.demo.utils;

import airport.psk.demo.models.flightThings.AirPort;

import java.util.LinkedList;
import java.util.List;

public class AirPortsUtlis {

    public static List<AirPort> get(String cityName){
        List<AirPort> airPortList = new LinkedList<>();
        AirPort hamburg = new AirPort("Hamburg","HAM","DE","Hamburg",53.631944,9.989444);
        AirPort madrid  = new AirPort("Madrid","MAD","ES","Hamburg",-3.559444,40.473333);
        AirPort miami = new AirPort("Miami","MIA","ES","Miami national airport",25.830500,-80.180374);
        AirPort berlin = new AirPort("Berlin","BER","DE","Berlin airport",52.531677,13.381777);

        switch (cityName) {
            case "Hamburg":
                airPortList.add(hamburg);
                break;
            case "Madrid":
                airPortList.add(madrid);
                break;
            case "Miami":
                airPortList.add(miami);
                break;
            case "Berlin":
                airPortList.add(berlin);
                break;
        }
        return airPortList;
    }
}
