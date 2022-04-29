package ClearTrip;
import ClearTrip.BuildingSystem;
public class RunnerClass {
    public static void main(String[] args){
        BuildingSystem buildingSystem= new BuildingSystem();

        Building b1= new Building("b1");
        buildingSystem.addBuilding(b1);

        Floor fl1= new Floor("1");
        Floor fl2= new Floor("2");

        buildingSystem.addFloorToBuilding(b1, fl1);
        buildingSystem.addFloorToBuilding(b1, fl2);

        Confroom c1= new Confroom("c1");
        Confroom c2= new Confroom("c2");
        Confroom c3= new Confroom("c3");

        buildingSystem.addConfroomToFloorBuilding(b1, fl1, c1);
        buildingSystem.addConfroomToFloorBuilding(b1, fl1, c2);
        buildingSystem.addConfroomToFloorBuilding(b1, fl2, c3);

        buildingSystem.bookSlot("1:5", b1, fl1, c1);
        buildingSystem.bookSlot("4:9", b1, fl1, c2);
        buildingSystem.bookSlot("8:9", b1, fl2, c3);

        buildingSystem.cancelSlot(("12: 14"), b1, fl1, c1);
        buildingSystem.cancelSlot(("4: 9"), b1, fl1, c2);

        buildingSystem.allBookings(b1, fl1);
        buildingSystem.allBookings(b1, fl2);

        buildingSystem.getAllConfroom(b1);

    }
}
