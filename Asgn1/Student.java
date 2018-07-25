import java.util.*;
/**
 * Write a description of class Student here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Student
{
    private String number;
    private String name;
    private LinkedList<Activity> activities = new LinkedList<Activity>();

    public Student(String number, String name){
        this.number = number;
        this.name = name;
    }

    public void use(LinkedList<Subject> subjects){
        char choice;
        LinkedList<Subject> subjectlist = subjects;
        while((choice = readChoice()) != 'x'){
            switch(choice){
                case 'v': viewAct(); break;
                case 'e': enrol(subjectlist); break;
                case 'w': withdraw(); break;
                default: help(); break;
            }
        }
    }

    public void help(){
        System.out.println("Student menu options");
        System.out.println("v = view my activities");
        System.out.println("e = enrol in an activity");
        System.out.println("w = withdraw from an activity");
        System.out.println("x = exit");
    }

    public void enrol(LinkedList<Subject> subjectlist){
        System.out.println("Select a subject");
        for(Subject subject:subjectlist){
            System.out.println(subject);
        }
        int subjectSel = readSubject();
        if(sameSubNum(subjectSel, subjectlist)){
            System.out.println("Select an activity");
            for(int i=0;i<=subjectlist.size()-1;i++){
                if(subjectlist.get(i).getNum() == (subjectSel)){
                    subjectlist.get(i).printActs();
                    String enrolAct = readActivity();
                    String splitted[] = enrolAct.split(":");
                    LinkedList<Activity> subjActList = subjectlist.get(i).getActList();
                    if(splitted.length == 2){

                        if(reEnrol(splitted[0])){
                            for(int a = activities.size()-1; a>=0; a--){
                                Activity activity = activities.get(a);
                                if(activity.getSubject().getNum() == subjectSel && activity.getName().equals(splitted[0])){
                                    if(activity.hasSpace()){
                                        activity.withdraw();
                                        activities.remove(a);
                                    }
                                    else{
                                        System.out.println("No available seats");
                                        return;
                                    }
                                }
                            }
                            Activity foundAct = subjectlist.get(i).enrolAct(splitted[0],Integer.parseInt(splitted[1]));
                            activities.add(foundAct);
                            foundAct.enrolled();
                        }
                        else{
                            //for(Activity activity:subjActList){
                            //if(activity.getName().equals(splitted[0]) && activity.getNum() == (Integer.parseInt(splitted[1]))){
                            //if(activity.hasSpace()){
                            Activity foundAct = subjectlist.get(i).enrolAct(splitted[0],Integer.parseInt(splitted[1]));
                            if(foundAct == null){
                                System.out.println("No such activity");
                            }
                            else{
                                if(foundAct.hasSpace()){
                                    activities.add(foundAct);
                                    foundAct.enrolled();
                                }
                                else{
                                    System.out.println("No available seats");
                                }
                            }
                            //}
                            //}
                            //}
                        }

                    }
                    else{
                        //System.out.println("AYYY LMAOOO FOUND A NULL");
                        for(Activity activity:subjActList){
                            if(splitted[0].equals(activity.getName()) && activity.hasSpace()){
                                activities.add(activity);
                                activity.enrolled();
                                return;
                            }
                        }
                        System.out.println("No available seats");
                    }
                }
            }
        }
        else{
            System.out.println("No such subject");
        }
    }

    public void viewAct(){
        for(Activity activity:activities){
            System.out.println(activity);
        }
    }

    public void withdraw(){
        String withdrawinput[] = readWithdraw().split(":");
        if(checkWithdraw(withdrawinput)){
            for(int i = activities.size()-1; i>=0; i--){
                Activity activity = activities.get(i);
                if(activity.getSubject().getNum() == Integer.parseInt(withdrawinput[0]) && activity.getName().equals(withdrawinput[1])){
                    activity.withdraw();
                    activities.remove(i);
                }
            }
        }
        else{
            System.out.println("Not enrolled in activity");
        }
    }
    
    public void removeAll(){
        for(int i = activities.size()-1; i>=0; i--){
            activities.get(i).withdraw();
            activities.remove(i);
        }
    }
    /*
    public void selectAct(LinkedList<Subject> subjectlist, int i){
    String enrolAct = readActivity();
    String splitted[] = enrolAct.split(":");
    LinkedList<Activity> activityList = subjectlist.get(i).returnActs();

    //linkedlist = split enrolact
    //
    }
     */
    //to be removed
    /*
    public boolean sameNum2(String StuNum){
    return number.equals(StuNum);
    }
     */
    public String getStuNum(){
        return number;
    }

    @Override
    public String toString() {
        return number + " " + name;
    }

    //looper for subject
    public boolean sameSubNum(int SubNum, LinkedList<Subject> subjectlist){
        for(Subject subject:subjectlist){
            if (subject.getNum() == SubNum){
                return true;
            }
        }
        return false;
    }

    public boolean reEnrol(String splitted){
        for(Activity activity:activities){
            if(activity.getName().equals(splitted)){
                return true;
            }
        }
        return false;
    }

    public boolean checkWithdraw(String withdrawinput[]){
        for(Activity activity:activities){
            if(activity.getSubject().getNum() == Integer.parseInt(withdrawinput[0]) && activity.getName().equals(withdrawinput[1])){
                return true;
            }
        }
        return false;
    }

    //inputs
    public char readChoice(){
        System.out.print("Choice (v/e/w/x): ");
        return In.nextChar();
    }

    public int readSubject(){
        System.out.print("Subject number: ");
        return In.nextInt();
    }

    public String readActivity(){
        System.out.print("Activity code (group:activity): ");
        return In.nextLine();
    }

    public String readWithdraw(){
        System.out.print("Activity code (subject:group): ");
        return In.nextLine();
    }
}
