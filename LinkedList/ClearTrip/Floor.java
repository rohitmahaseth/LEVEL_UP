package ClearTrip;

import java.util.ArrayList;

public class Floor{
    private String floorId;
    ArrayList<Confroom> confroomList= new ArrayList<>();

    public Floor(String floorId){
        this.floorId= floorId;
    }

    public String getFloorId(){
        return floorId;
    }

    public void setFloorId(String floorId){
        this.floorId= floorId;
    }

    public void addConfroomFloor(Confroom confroom){
        confroomList.add(confroom);
    }

    public void getAllConfrooms(){
        System.out.println(confroomList);
    }

    @Override
    public String toString(){
        return "Floor{" + 
        "floorId= '" + floorId+ '\''+ 
        ", confroomList=" + confroomList +
        '}';
    }
}
