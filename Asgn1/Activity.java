import java.text.*;
/**
 * Write a description of class Activity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Activity
{
    private Subject subject;
    private String group;
    private int number;
    private String day;
    private int start;
    private int duration;
    private String room;
    private int capacity;
    private int enrolled;
    
    public Activity(Subject subject, String group, int number, String day, int start, int duration, String room, int capacity) {
        this.subject = subject;
        this.group = group;
        this.number = number;
        this.day = day;
        this.start = start;
        this.duration = duration;
        this.room = room;
        this.capacity = capacity;
        enrolled = 0;
    }
    
    public String getName(){
        return group;
    }
    
    public int getNum(){
        return number;
    }
    
    public void enrolled(){
        enrolled++;
    }
    
    public void withdraw(){
        enrolled--;
    }
    
    public Subject getSubject(){
        return subject;
    }
    
    public boolean hasSpace(){
        return enrolled < capacity;
    }
    
    @Override
    public String toString() {
        return subject.getNum() + " " + group + " " + number + " " + day + " " + room + " " + formatted(start) + " " + duration + "hrs" + " " + enrolled + "/" + capacity;
    }
    
    private String formatted(int start) {
        if(start<10){
        return "0"+start+":00";
    }
    else{
        return start+":00";
    }
    }
    
}
