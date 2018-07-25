import java.util.*;
/**
 * Write a description of class Subject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Subject
{
    private int number;
    private String name;
    private LinkedList<Activity> activities = new LinkedList<Activity>();

    public Subject(int number, String name) {
        this.number = number;
        this.name = name;
    }
    //option 1
    public void setAppAct(){
        activities.add(new Activity(this,"Lec1",1,"Wed",18,1,"CB11.00.405",200));
        activities.add(new Activity(this,"Cmp1",1,"Wed",19,2,"CB11.B1.403",2));
        activities.add(new Activity(this,"Cmp1",2,"Wed",19,2,"CB11.B1.401",2));
        activities.add(new Activity(this,"Cmp1",3,"Wed",19,2,"CB11.B1.402",2));
    }
    //option 1
    public void setWebAct(){
        activities.add(new Activity(this,"Lec1",1,"Tue",16,1,"CB02.03.002",160));
        activities.add(new Activity(this,"Cmp1",1,"Tue",9,2,"CB11.B1.102",30));
        activities.add(new Activity(this,"Cmp1",2,"Tue",9,2,"CB11.B1.103",30));
        activities.add(new Activity(this,"Cmp1",3,"Tue",14,2,"CB11.B1.102",30));
        activities.add(new Activity(this,"Cmp1",4,"Tue",14,2,"CB11.B1.103",30));
    }

    public int getNum(){
        return number;
    }

    public void printActs(){
        for(Activity activity:activities){
            System.out.println(activity);
        }
    }

    public LinkedList<Activity> getActList(){
        return activities;
    }

    public Activity enrolAct(String group, int actNum){
        for(Activity activity:activities){
            if(activity.getName().equals(group) && activity.getNum() == (actNum)){
                    //activity.enrolled();
                    return activity;
            }
        }
        return null;
    }

    /*option 2
    public void addActivity(String group, int number, String day, int start, int duration, String room, int capacity){
    activities.add(new Activity(this, group, number, day, start, duration, room, capacity));
    }
     */

    @Override
    public String toString() {
        return number + " " + name;
    }
}
