import java.util.*;
/**
 * Write a description of class University here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class University
{
    private LinkedList<Subject> subjects = new LinkedList<Subject>();
    private LinkedList<Student> students = new LinkedList<Student>();

    //main
    //use()
    //constructor

    public static void main(String[] args) {
        new University().use();
    }

    public University() {
        subjects.add(new Subject(48024, "Applications Programming"));
        subjects.add(new Subject(31284, "Web Services Development"));
        //option 1
        Subject a = subjects.get(0);
        a.setAppAct();
        Subject b = subjects.get(1);
        b.setWebAct();
        /* option 2
        subjects.get(0).addActivity("Lec1",1,"Wed",18,1,"CB11.00.405",200);
        subjects.get(0).addActivity("Cmp1",1,"Wed",19,2,"CB11.B1.403",2);
        subjects.get(0).addActivity("Cmp1",2,"Wed",19,2,"CB11.B1.401",2);
        subjects.get(0).addActivity("Cmp1",3,"Wed",19,2,"CB11.B1.402",2);
        subjects.get(1).addActivity("Lec1",1,"Tue",16,1,"CB02.03.002",160);
        subjects.get(1).addActivity("Cmp1",1,"Tue",9,2,"CB11.B1.102",30);
        subjects.get(1).addActivity("Cmp1",2,"Tue",9,2,"CB11.B1.103",30);
        subjects.get(1).addActivity("Cmp1",3,"Tue",14,2,"CB11.B1.102",30);
        subjects.get(1).addActivity("Cmp1",4,"Tue",14,2,"CB11.B1.103",30);
         */

    }

    public void use(){
        char choice;
        while((choice = readChoice()) != 'x'){
            switch(choice){
                case 'a': addStudent(); break;
                case 'r': removeStudent(); break;
                case 'v': viewStudent(); break;
                case 'l': login(); break;
                default: help(); break;
            }
        }
    }

    public void addStudent(){
        String StuNum = readStuNum();
        if(sameNum(StuNum)){
            System.out.println("Student number already exists");
        }
        else
        {
            String StuName = readName();
            students.add(new Student(StuNum,StuName));
        }
    }

    public void viewStudent(){
        for(Student student:students){
            System.out.println(student);
        }
    }
    //remove student can be improved. I forced a return to not print no such student
    public void removeStudent(){
        String RemStu = readStuNum();
        for(int i = students.size()-1;i>=0;i--){
            //if(students.get(i).sameNum2(RemStu)){
            if(students.get(i).getStuNum().equals(RemStu)){
                students.get(i).removeAll();
                students.remove(i);
                return;
            }
            //}
        }
        System.out.println("No such student");
    }

    public void login(){
        String user = readStuNum();
        if(sameNum(user)){
            for(int i=0;i<=students.size()-1;i++){
                //if(students.get(i).sameNum2(user)){
                if(students.get(i).getStuNum().equals(user)){
                    students.get(i).use(subjects);
                }
                //}
            }
        }
        else{
            System.out.println("No such student");
        }
    }

    public void help(){
        System.out.println("University menu options");
        System.out.println("a = add a student");
        System.out.println("r = remove a student");
        System.out.println("v = view all students");
        System.out.println("l = login");
        System.out.println("x = exit");
    }
    //looper
    public boolean sameNum(String StuNum){
        for(Student student:students){
            //if (student.sameNum2(StuNum)){
            if(student.getStuNum().equals(StuNum)){
                return true;
            }
            //}
        }
        return false;
    }

    /*
    public int getPos(String StuNum){
    for(int i=0;i<=students.size()-1;i++){
    if(students.get(i).sameNum2(StuNum)){
    return i;
    }
    }
    return -1;
    }
     */
    
    //inputs
    public char readChoice(){
        System.out.print("Choice (a/r/v/l/x): ");
        return In.nextChar();
    }

    public String readStuNum(){
        System.out.print("Number: ");
        return In.nextLine();
    }

    public String readName(){
        System.out.print("Name: ");
        return In.nextLine();
    }
}
