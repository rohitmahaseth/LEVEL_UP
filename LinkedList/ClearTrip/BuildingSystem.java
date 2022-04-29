package ClearTrip;

import java.util.ArrayList;
public class BuildingSystem {
    static ArrayList<Building> buildingList= new ArrayList<>();
    
    public void addBuilding(Building b){
        buildingList.add(b);
    }

    public void addFloorToBuilding(Building b1, Floor fl1){
        if(buildingList.contains(b1)) b1.addFlorr(fl1);
        else System.out.println("Building does not exist");
    }

    public void addConfroomToFloorBuilding(Building b1, Floor fl1, Confroom c1){
        if(buildingList.contains(b1)){
            if(b1.floorList.contains(fl1)){
                fl1.addConfroomInToFloor(c1);
            }
            else{
                System.out.println("Floor does not exist");
            }

        }
        else{
            System.out.println("Building does not exist");
        }
    }

    public void getAllConfroom(Building b1){
        if(buildingList.contains( b1)){
            System.out.println(b1);
        }
        else{
            System.out.println("Building does not exist");
        }
    }

    public void allBookings(Building b1, Floor fl1){
        if(buildingList.contains(b1)){
            if(b1.floorList.contains((fl1))){
                System.out.println(fl1.toString());
            }
            else{
                System.out.println("floor does not exist");
            }
        }
        else{
            System.out.println("Building does not exist");
        }
    }

    public void bookSlot(String slot, Building b1, Floor fl1, Confroom c1){
        if(buildingList.contains(b1)) {
            if(b1.floorList.contains(fl1)){
                if(fl1.confroomList.contains(c1)){
                    if(c1.ableToBook(slot)){
                        if(c1.addSlot(slot)){
                            System.out.println("Slot booked");
                        }
                        else{
                            System.out.println("Given slot is alerady booked");
                        }
                    }
                    else{
                        System.out.println("You can book mac 12 hrs ");
                    }
                }
                else{
                    System.out.println("Conference room does not exist");
                }
            }else{
                System.out.println("Floor does not exist");
            }  
        }        
        else{
            System.out.println("building does not exist");
        }
    }

    public void cancelSlot(String slot, Building b1, Floor fl1, Confroom c1){
        if(buildingList.contains(b1)){
            if(b1.floorList.contains(fl1)){
                if(fl1.confroomList.contains(c1)){
                    if(c1.cancelSlot(slot)){
                        System.out.println("Slot cancelled");
                    }
                    else{
                        System.out.println("Given slot is not booked");
                    }
                }
                else{
                    System.out.println("Conference room does not exist");
                }
            }
            else{
                System.out.println("Floor does not exist");
            }
        }
        else{
            System.out.println("Building does not exist");
        }
    
    }

}
