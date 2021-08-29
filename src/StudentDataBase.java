import com.sun.javaws.util.JfxHelper;
import jdk.nashorn.internal.scripts.JO;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentDataBase {
    public static void main(String[] args)throws Exception {
        new Login();


    }

}
class MainFrame {
    JFrame jf;
    MainFrame()throws IOException{
        jf=new JFrame("Student Information System");
        jf.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("bg.jpg")))));
        jf.setSize(1000,750);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);

        jf.setLayout(null);
        jf.setVisible(true);
    }
}
class Buttons{
    JFrame jf,changePassword;
    JButton button_add_student,button_view_student_list,button_update_student,button_delete_student,button_student_profile,button_marks_entry,button_transcript,button_exit;
    JLabel label;
    JMenuBar menuBar;
    JMenu menu,menu2;
    JMenuItem menuItem1,menuItem2;
    public Buttons(JFrame jf)throws Exception{
        this.jf=jf;
        label =new JLabel("University of Dhaka");
        label.setBounds(450,50,500,100);
        label.setFont(new Font("SansSerif",Font.BOLD,40));
        label.setForeground(Color.BLACK);


        creatButtons();
        creatMenu();
       // setButtonColor();
        setButtonFonts();
        addButtonsToFrame();
        listenTobuttons();
        listenTomenu();
    }
    public void creatMenu(){
        menuBar=new JMenuBar();
        menu=new JMenu("Settings");
        menu2=new JMenu("Help");
        menuItem1=new JMenuItem("Change Password");

        menu.add(menuItem1);
        menuBar.add(menu);
        menuBar.add(menu2);
        jf.setJMenuBar(menuBar);
    }
    public void creatButtons(){
        button_add_student=new JButton("Add Student");
        button_add_student.setBounds(300,200,200,40);
        button_view_student_list=new JButton("View Student List");
        button_view_student_list.setBounds(300,270,200,40);
        button_update_student=new JButton("Update Student");
        button_update_student.setBounds(300,340,200,40);
        button_delete_student=new JButton("Delete Student");
        button_delete_student.setBounds(300,410,200,40);
        button_student_profile=new JButton("Student Profile");
        button_student_profile.setBounds(750,200,200,40);
        button_marks_entry=new JButton("Marks Entry");
        button_marks_entry.setBounds(750,270,200,40);
        button_transcript=new JButton("Transcript");
        button_transcript.setBounds(750,340,200,40);
        button_exit=new JButton("Exit");
        button_exit.setBounds(750,410,200,40);
    }
    public void setButtonFonts(){
        button_add_student.setFont(new Font("Arial", Font.PLAIN, 20));
        button_view_student_list.setFont(new Font("Arial", Font.PLAIN, 20));
        button_update_student.setFont(new Font("Arial", Font.PLAIN, 20));
        button_delete_student.setFont(new Font("Arial", Font.PLAIN, 20));
        button_student_profile.setFont(new Font("Arial", Font.PLAIN, 20));
        button_marks_entry.setFont(new Font("Arial", Font.PLAIN, 20));
        button_transcript.setFont(new Font("Arial", Font.PLAIN, 20));
        button_exit.setFont(new Font("Arial", Font.PLAIN, 20));
    }
    public void setButtonColor(){
        button_add_student.setBackground(Color.magenta);
        button_view_student_list.setBackground(Color.magenta);
        button_update_student.setBackground(Color.magenta);
        button_delete_student.setBackground(Color.magenta);
        button_student_profile.setBackground(Color.magenta);
        button_marks_entry.setBackground(Color.magenta);
        button_transcript.setBackground(Color.magenta);
        button_exit.setBackground(Color.magenta);
    }
    public void addButtonsToFrame(){
        jf.add(button_view_student_list);
        jf.add(button_add_student);
        jf.add(button_update_student);
        jf.add(button_delete_student);
        jf.add(button_student_profile);
        jf.add(button_marks_entry);
        jf.add(button_transcript);
        jf.add(button_exit);
        jf.add(label);
        jf.setLayout(null);
        jf.setVisible(true);
    }
    public void listenTomenu()throws Exception{
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword=new JFrame("Change Password");
                changePassword.setBounds(200,150,600,400);
                JLabel oldpassword=new JLabel("Old Password:");
                JLabel newpassword=new JLabel("New Password:");
                JLabel confirmpassword=new JLabel("Confirm Password:");
                JTextField textField_oldPassword=new JTextField();
                JTextField textField_newPassword=new JTextField();
                JTextField textField_confirmPassword=new JTextField();
                JButton save=new JButton("Save");

                oldpassword.setBounds(50,50,150,50);
                newpassword.setBounds(50,150,150,50);
                confirmpassword.setBounds(50,200,150,50);
                textField_oldPassword.setBounds(160,60,200,30);
                textField_newPassword.setBounds(160,160,200,30);
                textField_confirmPassword.setBounds(160,210,200,30);
                save.setBounds(190,280,70,30);
                changePassword.setResizable(false);

                changePassword.add(oldpassword);
                changePassword.add(newpassword);
                changePassword.add(confirmpassword);
                changePassword.add(textField_oldPassword);
                changePassword.add(textField_newPassword);
                changePassword.add(textField_confirmPassword);
                changePassword.add(save);

                changePassword.setLayout(null);
                changePassword.setVisible(true);
                save.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try{
                            listenToSaveButton(textField_oldPassword.getText(),textField_newPassword.getText(),textField_confirmPassword.getText());
                        }catch (Exception ex){
                            System.out.println(ex);
                        }

                    }
                });
            }
        });
    }
    public void listenToSaveButton(String old,String newp ,String confirm)throws Exception{
        FileReader fr=new FileReader("password.txt");
        BufferedReader br=new BufferedReader(fr);

        String passw=br.readLine();
        if(passw.compareTo(old)!=0&&!passw.isEmpty()){
            JFrame warning=new JFrame();
            JOptionPane.showMessageDialog(warning,"Please enter the last password you remember.","Warning",JOptionPane.WARNING_MESSAGE);
        }
        else{
            if(newp.compareTo(confirm)!=0){
                JFrame message=new JFrame();
                JOptionPane.showMessageDialog(message,"Please Confirm the passwored you have enterd.","Warning",JOptionPane.WARNING_MESSAGE);
            }
            else{
                FileWriter fw=new FileWriter("password.txt");
                BufferedWriter bw=new BufferedWriter(fw);
                bw.write("");
                bw.write(newp);
                bw.close();
                changePassword.dispose();

            }
        }



    }
    public void listenTobuttons(){
        button_add_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddStudent();
            }
        });
        button_view_student_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewStudentList();
            }
        });
        button_update_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindStudent();
            }
        });
        button_delete_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FindToDeleteStudent();
            }
        });
        button_student_profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentProfile();
            }
        });
        button_marks_entry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarksEntry();
            }
        });
        button_transcript.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Transcript();
            }
        });
        button_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new Exit(jf);
            }
        });
    }
}
class Login extends JFrame{
    JFrame frame_login;
    JPasswordField password_field;
    JLabel password,label_info,label_university,label_faculty;
    JButton button_login;
      Socket s=new Socket("127.0.0.1",6263);
      DataInputStream in=new DataInputStream(s.getInputStream());
      DataOutputStream out=new DataOutputStream(s.getOutputStream());
    Login()throws IOException{
        frame_login=new JFrame("Login");
        frame_login.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("login.jpg")))));
        password_field=new JPasswordField(8);
        password=new JLabel("Password");
        button_login=new JButton("Login");
        label_info=new JLabel("Student Information System");
        label_faculty=new JLabel("Faculty of Engineering and Technology");
        label_university=new JLabel("University of Dhaka");

        password.setFont(new Font("Arial",Font.PLAIN,18));
        label_info.setFont(new Font("Arial",Font.PLAIN,25));
        label_faculty.setFont(new Font("Arial",Font.PLAIN,25));
        label_university.setFont(new Font("Arial",Font.PLAIN,25));
        label_info.setBounds(120,200,400,60);
        label_faculty.setBounds(20,250,500,50);
        label_university.setBounds(150,300,400,50);
        password.setBounds(20,500, 80,30);
        password_field.setBounds(140,500,150,30);
        button_login.setBounds(150,560,70,30);
        frame_login.add(password_field);
        frame_login.add(password);
        frame_login.add(button_login);
        frame_login.add(label_info);
        frame_login.add(label_faculty);
        frame_login.add(label_university);
        frame_login.setBounds(0,0,1000,700);
        frame_login.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame_login.setLayout(null);
        frame_login.setVisible(true);

        listenToLoginButton();
    }
    private void listenToLoginButton(){
        button_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(getPermission()){
                        MainFrame main_fram=new MainFrame();
                        Buttons buttons=new Buttons(main_fram.jf);
                        frame_login.dispose();
                        s.close();
                    }
                    else{
                        JFrame error=new JFrame("Wrong password");
                        JOptionPane.showMessageDialog(error,"Password is wrong.");
                    }
                }catch (IOException ex){
                    System.out.println(ex);
                }catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });
    }
    public boolean getPermission() throws Exception{

            String pass = String.valueOf(password_field.getPassword());
            String confirmation="";
            out.writeUTF(pass);
            out.flush();

            confirmation=in.readUTF();
            if(confirmation.compareTo("OK")==0)
                return true;
            else
                return false;

    }
}
class AddStudent {
    JFrame jf;
    JLabel label_student_id,label_student_name,label_father_name,label_gender,label_date_of_birth,label_department,label_year,label_semester,label_address,label_contactno,label_email,label_comment;
    JTextField textfield_studentid,textfield_student_name,textfield_father_name,t4,textfield_date_of_birth,t6,t7,t8,textfield_address,textfield_contactno,textfield_email,textfield_comment;
    JRadioButton radiobutton_male,radiobutton_female;
    JButton button_save,button_back;
    JComboBox combobutton_department,combobutton_year,combobutton_semester;

    String dept[]={"Computer Science and Engineering","Electric and Electrical Engineering","Robotics and Mechatronics Engineering","Applied Chemistry & Chemical Engineering","Nuclear Engineering","Software Engineering"};
    String year[]={"1st Year","2nd Year","3rd Year","4th Year","Masters"};
    String semester[]={"1st Semester","2nd Semester"};

    public AddStudent(){
        jf=new JFrame("Add Student");
        jf.setSize(1000,750);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // jf.getContentPane().setBackground(Color.GRAY);

        button_save=new JButton("Save");
        button_back=new JButton("Back");


        label_student_id=new JLabel("Student Id:");
        label_student_name=new JLabel("Student Name:");
        label_father_name=new JLabel("Father Name:");
        label_gender=new JLabel("Gender:");
        label_date_of_birth=new JLabel("Date of Birth:");
        label_department=new JLabel("Department:");
        label_year=new JLabel("Year:");
        label_semester=new JLabel("Semester:");
        label_address=new JLabel("Address:");
        label_contactno=new JLabel("Contact No:");
        label_email=new JLabel("Email:");
        label_comment=new JLabel("Comment:");

        textfield_studentid=new JTextField();
        textfield_student_name=new JTextField();
        textfield_father_name=new JTextField();
        textfield_date_of_birth=new JTextField();
        textfield_address=new JTextField();
        textfield_contactno=new JTextField();
        textfield_email=new JTextField();
        textfield_comment=new JTextField();

        combobutton_department=new JComboBox(dept);
        combobutton_year=new JComboBox(year);
        combobutton_semester=new JComboBox(semester);

        radiobutton_male=new JRadioButton("Male",true);
        radiobutton_female=new JRadioButton("Female",false);

        ButtonGroup grp=new ButtonGroup();
        grp.add(radiobutton_male);
        grp.add(radiobutton_female);


        saveButton();
        backButton();
        comboBoxandRadioButton();
        setBoundsOfLabel();
        setFontOfLabel();
        setBoundOfTextField();
        setFontOfTextField();
        addAlltoframe();

    }
    public void backButton(){
        button_back.setBounds(700,600,150,50);
        // button_back.setBackground(Color.RED);
        button_back.setFont(new Font("Arial",Font.PLAIN,25));

        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });
    }
    public void saveButton(){
        //button_save.setBackground(Color.RED);
        button_save.setBounds(300,600,150,50);
        button_save.setFont(new Font("Arial",Font.PLAIN,25));

        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s;
                CheckIdValidity idvalidity=new CheckIdValidity(textfield_studentid.getText());
                CheckPhoneNumberValidity pnvalidity= new CheckPhoneNumberValidity(textfield_contactno.getText());
                CheckEmailValidity mailvalidity=new CheckEmailValidity(textfield_email.getText());
                if((idvalidity.checkValidity(textfield_studentid.getText())&&!idvalidity.chekInFIle(textfield_studentid.getText()))&&(mailvalidity.checkEmail())){
                    if(!textfield_studentid.getText().isEmpty()&&!textfield_student_name.getText().isEmpty()&&!textfield_father_name.getText().isEmpty()&&!textfield_date_of_birth.getText().isEmpty()&&!textfield_address.getText().isEmpty()&&!textfield_contactno.getText().isEmpty()&&!textfield_email.getText().isEmpty()){
                        if(checkerId(textfield_studentid.getText())){
                            s=textfield_studentid.getText()+"#"+textfield_student_name.getText()+"#"+textfield_father_name.getText()+"#";
                            if(radiobutton_male.isSelected()){
                                s+=radiobutton_male.getText();
                            }
                            else{
                                s+=radiobutton_female.getText();
                            }
                            s+="#"+textfield_date_of_birth.getText()+"#"+combobutton_department.getSelectedItem()+"#"+combobutton_year.getSelectedItem()+"#"+combobutton_semester.getSelectedItem()+"#"+textfield_address.getText()+"#"+textfield_contactno.getText()+"#"+textfield_email.getText()+"#"+textfield_comment.getText()+"#";
                            writeInFile(s);
                            jf.dispose();
                        }
                        else{
                            JFrame j=new JFrame("Warning");
                            JOptionPane.showMessageDialog(j,"Student Id is invalid. Enter Id between 10000-99999");
                        }

                    }
                    else{
                        JFrame frame=new JFrame("Messege");
                        JOptionPane.showMessageDialog(frame, "Please fill the form");
                    }
                }

            }
        });
    }
    public void comboBoxandRadioButton(){
        combobutton_department.setBounds(300,355,200,30);
        combobutton_year.setBounds(300,405,200,30);
        combobutton_semester.setBounds(300,455,200,30);
        radiobutton_male.setBounds(300,255,100,30);
        radiobutton_female.setBounds(400,255,100,30);
        combobutton_department.setFont(new Font("Arial",Font.PLAIN,16));
        combobutton_year.setFont(new Font("Arial",Font.PLAIN,16));
        combobutton_semester.setFont(new Font("Arial",Font.PLAIN,16));
        radiobutton_male.setFont(new Font("Arial",Font.PLAIN,16));
        radiobutton_female.setFont(new Font("Arial",Font.PLAIN,16));
    }
    public void setBoundsOfLabel(){
        label_student_id.setBounds(150,100,150,40);
        label_student_name.setBounds(150,150,150,40);
        label_father_name.setBounds(150,200,150,40);
        label_gender.setBounds(150,250,150,40);
        label_date_of_birth.setBounds(150,300,150,40);
        label_department.setBounds(150,350,150,40);
        label_year.setBounds(150,400,150,40);
        label_semester.setBounds(150,450,150,40);
        label_address.setBounds(150,500,150,40);
        label_contactno.setBounds(550,100,150,40);
        label_email.setBounds(550,150,150,40);
        label_comment.setBounds(550,200,150,40);
    }
    public void setFontOfLabel(){
        label_student_id.setFont(new Font("Arial",Font.PLAIN,20));
        label_student_name.setFont(new Font("Arial",Font.PLAIN,20));
        label_father_name.setFont(new Font("Arial",Font.PLAIN,20));
        label_gender.setFont(new Font("Arial",Font.PLAIN,20));
        label_date_of_birth.setFont(new Font("Arial",Font.PLAIN,20));
        label_department.setFont(new Font("Arial",Font.PLAIN,20));
        label_year.setFont(new Font("Arial",Font.PLAIN,20));
        label_semester.setFont(new Font("Arial",Font.PLAIN,20));
        label_address.setFont(new Font("Arial",Font.PLAIN,20));
        label_contactno.setFont(new Font("Arial",Font.PLAIN,20));
        label_email.setFont(new Font("Arial",Font.PLAIN,20));
        label_comment.setFont(new Font("Arial",Font.PLAIN,20));
    }
    public void setBoundOfTextField(){
        textfield_studentid.setBounds(300,105,200,30);
        textfield_student_name.setBounds(300,155,200,30);
        textfield_father_name.setBounds(300,205,200,30);
        textfield_date_of_birth.setBounds(300,305,200,30);
        textfield_address.setBounds(300,505,200,60);
        textfield_contactno.setBounds(700,105,200,30);
        textfield_email.setBounds(700,155,200,30);
        textfield_comment.setBounds(700,210,200,200);
    }
    public void setFontOfTextField(){
        textfield_studentid.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_student_name.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_father_name.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_date_of_birth.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_address.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_contactno.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_email.setFont(new Font("Arial",Font.PLAIN,16));
        textfield_comment.setFont(new Font("Arial",Font.PLAIN,16));
    }
    public void addAlltoframe(){
        jf.add(label_student_id);
        jf.add(textfield_studentid);
        jf.add(label_student_name);
        jf.add(textfield_student_name);
        jf.add(label_father_name);
        jf.add(textfield_father_name);
        jf.add(label_gender);
        jf.add(radiobutton_male);
        jf.add(radiobutton_female);
        jf.add(label_date_of_birth);
        jf.add(label_department);
        jf.add(label_year);
        jf.add(label_semester);
        jf.add(label_address);
        jf.add(textfield_date_of_birth);
        jf.add(combobutton_department);
        jf.add(combobutton_year);
        jf.add(combobutton_semester);
        jf.add(textfield_address);
        jf.add(button_save);
        jf.add(textfield_contactno);
        jf.add(textfield_email);
        jf.add(label_contactno);
        jf.add(label_email);
        jf.add(label_comment);
        jf.add(textfield_comment);
        jf.add(button_back);

        jf.setLayout(null);
        jf.setVisible(true);
    }
    public void writeInFile(String s){
        try{
            FileWriter fw=new FileWriter("information.txt",true);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(s+"\n");
            bw.close();
            fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    private boolean checkerId(String s){
        int n=Integer.parseInt(s);
        if(n<10000&&n>99999){
            return false;
        }
        else
            return true;
    }
}
class CheckIdValidity extends Thread{
    String studentId;
    boolean set=false;
    public CheckIdValidity(String studentId){
        this.studentId=studentId;
        start();
    }
    public void run()
    {
        if(!checkValidity(studentId)){
            JFrame messageframe = new JFrame("Message");
            JOptionPane.showMessageDialog(messageframe, "Please enter a valid id.");
        }
        else if(chekInFIle(studentId)) {
            JFrame messageframe = new JFrame("Message");
            JOptionPane.showMessageDialog(messageframe, "The id you entered is already in the list.");
        }
        else{
            set=true;
            System.out.println("set true");
        }

    }
    public boolean chekInFIle(String studentId)
    {
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",s1="";
            StringTokenizer stk;
            int line=0;
            while((s=br.readLine())!=null){
                line++;
                stk=new StringTokenizer(s,"#");
                s1=stk.nextToken();
                if(s1.compareTo(studentId)==0){
                    return true;
                }
            }
            fr.close();
            br.close();
        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
    public boolean checkValidity(String studentId){
        try{
            int id=Integer.parseInt(studentId);
            if(id>=10001&&id<=99999){
                System.out.println(id);
                return true;
            }

            else
                return false;

        }catch (NumberFormatException e){
            System.out.println(e);
            return false;
        }


    }
}

class CheckPhoneNumberValidity extends Thread{
    String phonenumber;
    boolean set=false;
    public CheckPhoneNumberValidity(String phonenumber){
        this.phonenumber=phonenumber;
        start();
    }
    public void run(){
        if(!(phonenumber.charAt(0)=='0'&&(phonenumber.charAt(2)=='1'||phonenumber.charAt(2)=='5'||phonenumber.charAt(2)=='6'||phonenumber.charAt(2)=='7'||phonenumber.charAt(2)=='8'||phonenumber.charAt(2)=='9')&&checkNumber())){
            JFrame message=new JFrame("Message");
            JOptionPane.showMessageDialog(message,"Contact number is invalid");
        }else{
            set=true;
             System.out.println("set true");
        }

    }
    private boolean checkNumber() {
        try{
            int number;
            number=Integer.parseInt(phonenumber);
            if(phonenumber.length()==11){
                if(number>1111111111&&number<=1999999999)
                    return true;
            }
        }
        catch (NumberFormatException e){
            System.out.println(e);
            return false;
        }
        return false;

    }
}
class CheckEmailValidity extends Thread{
    String email;
    boolean set=false;
    public CheckEmailValidity(String email){
        this.email=email;
        start();
    }
    public void run(){
        if(!checkEmail()){
            JFrame message=new JFrame("Message");
            JOptionPane.showMessageDialog(message,"Please enter a valid email");

        }
        else{
            set=true;

            System.out.println("set true");
        }
    }
    public boolean checkEmail(){
        if((email.endsWith("@gmail.com")||email.endsWith("@yahoo.com")||email.endsWith("@hotmail.com")))
            return true;
        else
            return false;
    }

}
class ViewStudentList{
    JFrame f;
    String s1,s2,s3,s4,s5,s6;
    String data[][]=new String[10000][10000];
    String column[]={"Sl no","Student Id","Name","Department","Year","Email"};
    public ViewStudentList(){

        f=new JFrame("Student List");
        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);

        readStudents();

        sp.setFont(new Font("Arial", Font.BOLD, 14));
        jt.setFont(new Font("Arial", Font.BOLD, 14));
        jt.setBackground(Color.lightGray);
        jt.setBounds(50,200,900,500);
        f.setSize(1000,750);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);

        f.add(sp);
        f.setVisible(true);
    }
    private void readStudents(){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            StringTokenizer st;
            String s="",s1="";
            int line=0;

            while ((s=br.readLine())!=null){
                st=new StringTokenizer(s,"#");
                for(int i=0,j=1;i<11;i++){
                    s1=st.nextToken();
                    if(i==0){
                        data[line][j]=s1;
                        j++;
                    }
                    else if(i==1){
                        data[line][j]=s1;
                        j++;
                    }
                    else if(i==5){
                        data[line][j]=s1;
                        j++;
                    }
                    else if(i==6){
                        data[line][j]=s1;
                        j++;
                    }
                    else if(i==10){
                        data[line][j]=s1;
                        j++;
                    }
                }
                line++;
            }
            for(int i=0;i<line;i++){
                String s2="";
                s2=Integer.toString(i+1);
                //System.out.println(s2);
                data[i][0]=s2;
            }

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }

    }
}
class FindStudent{
    JFrame jf,frame_searchby_name;
    JLabel label_enter_student_id,label_student_id;
    JTextField textfield_studentid;
    JButton button_find;
    DefaultListModel<String> l1 = new DefaultListModel<>();
    JList<String> list = new JList<>(l1);
    String id;
    int line=0;
    public FindStudent() {
        jf=new JFrame("Find Student");
        textfield_studentid=new JTextField();
        button_find=new JButton("Find");
        jf.setSize(1000,750);
        jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
        try{
            jf.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("findbg.jpg")))));

        }catch (Exception e){
            System.out.println(e);
        }
        label_enter_student_id =new JLabel("Enter a student Id to Update student information");
        label_student_id=new JLabel("Student Id/Name:");
        label_enter_student_id.setBounds(100,100,700,70);
        label_student_id.setBounds(100,250,200,60);
        textfield_studentid.setBounds(300,250,200,50);
        button_find.setBounds(550,250,80,40);
        label_enter_student_id.setFont(new Font("Arial",Font.BOLD,30));
        label_student_id.setFont(new Font("Arial",Font.BOLD,20));
        textfield_studentid.setFont(new Font("Arial",Font.PLAIN,18));
        button_find.setFont(new Font("Arial", Font.BOLD,20));
        button_find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkNameOrId()){
                    if(find(textfield_studentid.getText())){
                        jf.dispose();
                        new UpdateStudent(line);
                    }
                    else{
                        JFrame f1=new JFrame("Message");
                        JOptionPane.showMessageDialog(f1,"Student not fount in the list.");
                    }
                }
                else{
                    if(findByname(textfield_studentid.getText())){
                        jf.dispose();
                        new UpdateStudent(line);
                    }
                    else{
                        JFrame f1=new JFrame("Message");
                        JOptionPane.showMessageDialog(f1,"Student not fount in the list.");
                    }
                }

            }
        });
        jf.add(label_enter_student_id);
        jf.add(label_student_id);
        jf.add(textfield_studentid);
        jf.add(button_find);
        jf.setLayout(null);
        jf.setVisible(true);
    }
    public boolean checkNameOrId(){
        try{
            String st=textfield_studentid.getText();
            int n=Integer.parseInt(st);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean find(String id){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",s1="";
            StringTokenizer stk;
            line=0;
            while((s=br.readLine())!=null){
                line++;
                stk=new StringTokenizer(s,"#");
                s1=stk.nextToken();
                if(s1.compareTo(id)==0){
                    return true;
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return false;
    }
    public boolean findByname(String name){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",s1="",s2="";
            StringTokenizer stk;
            line=0;
            while((s=br.readLine())!=null){
                line++;
                stk=new StringTokenizer(s,"#");
                s1=stk.nextToken();
                s2=stk.nextToken();
                if(s2.compareToIgnoreCase(name)==0){
                    return true;
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return false;
    }
}
class UpdateStudent extends AddStudent{
    int line;
    JButton button_update;
    public UpdateStudent(){

    }
    public UpdateStudent(int line){
        this.line=line;
        button_save.setVisible(false);
        //button_back.setBackground(Color.BLUE);
        button_update=new JButton("Update");
        //button_update.setBackground(Color.BLUE);
        button_update.setBounds(300,600,150,50);
        button_update.setFont(new Font("Arial",Font.PLAIN,25));
        textfield_studentid.setEditable(false);
        listenToUpdateButton();
        jf.add(button_update);
        find();

    }
    public void find(){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="";
            String s1[]=new String[20];
            StringTokenizer stk;
            for(int i=1; i<=line ;i++){
                s=br.readLine();
                if(i==line){
                    stk=new StringTokenizer(s,"#");
                    for(int j=0;j<15;j++){
                        s1[j]=stk.nextToken();
                        if(j==0){
                            textfield_studentid.setText(s1[j]);
                        }
                        if(j==1){
                            textfield_student_name.setText(s1[j]);
                        }
                        if(j==2){
                            textfield_father_name.setText(s1[j]);
                        }
                        if(j==3){
                            if(s1[j].compareTo("Male")==0){
                                radiobutton_male.setSelected(true);
                            }
                            else{
                                radiobutton_female.setSelected(true);
                            }
                        }
                        if(j==4){
                            textfield_date_of_birth.setText(s1[j]);
                        }
                        if(j==5){
                            combobutton_department.setSelectedItem(s1[j]);
                        }
                        if(j==6){
                            combobutton_year.setSelectedItem(s1[j]);
                        }
                        if(j==7){
                            combobutton_semester.setSelectedItem(s1[j]);
                        }
                        if(j==8){
                            textfield_address.setText(s1[j]);
                        }
                        if(j==9){
                            textfield_contactno.setText(s1[j]);
                        }
                        if(j==10){
                            textfield_email.setText(s1[j]);
                        }
                        if(j==11){
                            textfield_comment.setText(s1[j]);
                        }
                    }
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }
    }
    public void listenToUpdateButton(){
        button_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFile();
            }
        });
    }
    private void updateFile(){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            FileWriter fw=new FileWriter("in.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            FileWriter fw1=new FileWriter("information.txt",true);
            BufferedWriter bw1=new BufferedWriter(fw1);
            String s="",s1="";
            int j=0;

            while ((s=br.readLine())!=null){
                j++;
                if(j==line){
                    s1=getInfo();
                    bw.write(s1+"\n");
                }
                else if(j!=line){
                    bw.write(s+"\n");
                }
            }
            bw.close();
            br.close();
            bw1.write("");
            bw1.flush();
            bw1.close();
            copyData();
            jf.dispose();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    private String getInfo(){
        String s,s1="";
        if(!textfield_studentid.getText().isEmpty()&&!textfield_student_name.getText().isEmpty()&&!textfield_father_name.getText().isEmpty()&&!textfield_date_of_birth.getText().isEmpty()&&!textfield_address.getText().isEmpty()&&!textfield_contactno.getText().isEmpty()&&!textfield_email.getText().isEmpty()){

            s=textfield_studentid.getText()+"#"+textfield_student_name.getText()+"#"+textfield_father_name.getText()+"#";
            if(radiobutton_male.isSelected()){
                s+=radiobutton_male.getText();
            }
            else{
                s+=radiobutton_female.getText();
            }
            s+="#"+textfield_date_of_birth.getText()+"#"+combobutton_department.getSelectedItem()+"#"+combobutton_year.getSelectedItem()+"#"+combobutton_semester.getSelectedItem()+"#"+textfield_address.getText()+"#"+textfield_contactno.getText()+"#"+textfield_email.getText()+"#"+textfield_comment.getText()+"#";
            s1=s;
        }
        else{
            JFrame frame=new JFrame("Message");
            JOptionPane.showMessageDialog(frame, "Please fill the form");
        }
        return s1;
    }
    public void copyData(){
        try{
            FileReader fr=new FileReader("in.txt");
            BufferedReader br=new BufferedReader(fr);
            FileWriter fw=new FileWriter("information.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            String s="";
            while((s=br.readLine())!=null){
                bw.write(s+"\n");
            }
            bw.close();
            fw.close();
        }catch (Exception e){
            System.out.println(e);
        }

    }

}
class Exit{
    JFrame jf;
    public Exit(JFrame jf){
        this.jf=jf;
        jf.dispose();
        System.exit(0);
    }
}
class FindToDeleteStudent extends FindStudent{

    JButton button_find_new;
    public FindToDeleteStudent(){
        label_enter_student_id.setText("Enter Student Id to delete student information");
        button_find.setVisible(false);
        button_find_new=new JButton("Find");
        button_find_new.setBounds(550,250,80,40);
        button_find_new.setFont(new Font("Arial",Font.PLAIN,18));
        jf.add(button_find_new);
        button_find_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkNameOrId()){
                    if(find(textfield_studentid.getText())){
                        jf.dispose();
                        new UpdateStudent(line);
                    }
                    else{
                        JFrame f1=new JFrame("Message");
                        JOptionPane.showMessageDialog(f1,"Student not fount in the list.");
                    }
                }
                else{
                    if(findByname(textfield_studentid.getText())){
                        jf.dispose();
                        new UpdateStudent(line);
                    }
                    else{
                        JFrame f1=new JFrame("Message");
                        JOptionPane.showMessageDialog(f1,"Student not fount in the list.");
                    }
                }
            }
        });
    }
}

class DeleteStudent extends UpdateStudent{
    int line;
    JButton button_delete;

    public DeleteStudent(int line){
        super(line);
        this.line=line;
        buttonSetup();

    }
    public void buttonSetup(){
        button_update.setVisible(false);
        // button_back.setBackground(Color.RED);
        button_delete=new JButton("Delete");
        //button_delete.setBackground(Color.RED);
        button_delete.setBounds(300,600,150,50);
        button_delete.setFont(new Font("Arial",Font.PLAIN,25));
        listenToDeleteButton();
        jf.add(button_delete);
    }
    private void listenToDeleteButton(){
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmation();
            }
        });
    }
    private void confirmation(){
        JFrame delet_confirm=new JFrame("Confirmation");
        JLabel label=new JLabel("Confirm to delete.");
        JButton button_ok=new JButton("Ok");
        label.setBounds(70,50,200,30);
        button_ok.setBounds(120,100,50,30);
        delet_confirm.setBounds(300,250,350,200);
        label.setFont(new Font("Arial",Font.PLAIN,14));
        delet_confirm.setVisible(true);
        delet_confirm.setLayout(null);
        delet_confirm.setResizable(false);
        delet_confirm.add(label);
        delet_confirm.add(button_ok);

        button_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletFromList(line);
                delet_confirm.dispose();
                jf.dispose();
            }
        });
    }

    private void deletFromList(int line){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            FileWriter fw=new FileWriter("in.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            FileWriter fw1=new FileWriter("information.txt",true);
            BufferedWriter bw1=new BufferedWriter(fw1);
            String s="",s1="";
            int j=0;
            System.out.println(line);
            while ((s=br.readLine())!=null){
                j++;
                if(j!=line){
                    bw.write(s+"\n");

                }
            }
            bw.close();
            br.close();
            bw1.write("");
            bw1.flush();
            bw1.close();
            copyData();
            jf.dispose();
        }catch (Exception e){
            System.out.println(e);
        }
    }

}


class StudentProfile{
    JFrame frame_profile;
    JLabel label_search,label_profile,label_studentid,label_student_name,label_father_name,label_gender,label_dateofbirth,label_department,label_year,label_semester,label_address,label_contactno,label_email,label_comment,label_cgpa;
    JTextField textfield_search;
    JButton button_search,button_back;
    int line;
    public StudentProfile(){
        frame_profile=new JFrame("Student Profile");

        label_search=new JLabel("Enter Id to Search:");
        textfield_search=new JTextField();
        button_search=new JButton("Search");
        button_back=new JButton("Back");
        button_back.setBounds(860,650,80,50);
        //button_back.setBackground(Color.PINK);

        label_profile=new JLabel("Student Profile");
        label_studentid=new JLabel("Student ID        :");
        label_student_name=new JLabel("Student Name  :");
        label_father_name=new JLabel("Father Name    :");
        label_gender=new JLabel("Gender             :");
        label_dateofbirth=new JLabel("Date of Birth    :");
        label_department=new JLabel("Department      :");
        label_year=new JLabel("Year                  :");
        label_semester=new JLabel("Semester          :");
        label_address=new JLabel("Address            :");
        label_contactno=new JLabel("Contact No       :");
        label_email=new JLabel("Email Address  :");
        label_comment=new JLabel("About Student  :");
        label_cgpa=new JLabel("Result         :");

        setBoundsOfLabels();
        setLabelsSize();
        searchBar();
        addToFrame();
        listenToSearchButton();
        listenToBackButton();
        //frame_profile.getContentPane().setBackground(Color.CYAN);
        frame_profile.setSize(1000,750);
        frame_profile.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame_profile.add(button_back);
        frame_profile.setLayout(null);
        frame_profile.setVisible(true);
    }
    private void listenToBackButton(){
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_profile.dispose();
            }
        });
    }
    private void listenToSearchButton(){
        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(find(textfield_search.getText())){
                    clearInfo();
                    setStudentinfo();
                }
                else{
                    clearInfo();
                    JFrame message=new JFrame("Messsage");
                    JOptionPane.showMessageDialog(message,"No matches");
                }
            }
        });
    }
    private void searchBar(){
        label_search.setBounds(50,50,250,50);
        label_search.setFont(new Font("Arial",Font.PLAIN,20));
        button_search.setFont(new Font("Arial",Font.PLAIN,20));
        textfield_search.setFont(new Font("Arial",Font.PLAIN,16));
        //button_search.setBackground(Color.PINK);
        frame_profile.add(label_search);
        frame_profile.add(button_search);
        button_search.setBounds(775,50,150,50);
        textfield_search.setBounds(270,50,500,50);
        frame_profile.add(textfield_search);
    }
    private void addToFrame(){
        frame_profile.add(label_father_name);
        frame_profile.add(label_student_name);
        frame_profile.add(label_studentid);
        frame_profile.add(label_gender);
        frame_profile.add(label_dateofbirth);
        frame_profile.add(label_department);
        frame_profile.add(label_year);
        frame_profile.add(label_semester);
        frame_profile.add(label_address);
        frame_profile.add(label_comment);
        frame_profile.add(label_contactno);
        frame_profile.add(label_email);
        frame_profile.add(label_cgpa);
        frame_profile.add(label_profile);
    }
    private void setBoundsOfLabels(){
        label_profile.setBounds(300,150,250,50);

        label_studentid.setBounds(50,200,350,50);
        label_student_name.setBounds(50,270,350,50);
        label_father_name.setBounds(50,340,350,50);
        label_gender.setBounds(50,410,350,50);
        label_dateofbirth.setBounds(50,480,350,50);
        label_department.setBounds(50,550,450,50);

        label_year.setBounds(550,200,300,50);
        label_semester.setBounds(550,270,300,50);
        label_address.setBounds(550,340,400,50);
        label_contactno.setBounds(550,410,300,50);
        label_email.setBounds(550,480,450,50);
        label_comment.setBounds(550,550,500,50);

    }
    private void setLabelsSize(){
        label_studentid.setFont(new Font("Arial",Font.PLAIN,18));
        label_student_name.setFont(new Font("Arial",Font.PLAIN,18));
        label_father_name.setFont(new Font("Arial",Font.PLAIN,18));
        label_gender.setFont(new Font("Arial",Font.PLAIN,18));
        label_department.setFont(new Font("Arial",Font.PLAIN,18));
        label_dateofbirth.setFont(new Font("Arial",Font.PLAIN,18));
        label_semester.setFont(new Font("Arial",Font.PLAIN,18));
        label_year.setFont(new Font("Arial",Font.PLAIN,18));
        label_address.setFont(new Font("Arial",Font.PLAIN,18));
        label_comment.setFont(new Font("Arial",Font.PLAIN,18));
        label_contactno.setFont(new Font("Arial",Font.PLAIN,18));
        label_email.setFont(new Font("Arial",Font.PLAIN,18));
        label_cgpa.setFont(new Font("Arial",Font.PLAIN,18));
        label_profile.setFont(new Font("Arial",Font.PLAIN,25));
        button_back.setFont(new Font("Arial",Font.PLAIN,20));
    }
    public boolean find(String id){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",s1="";
            StringTokenizer stk;
            line=0;
            while((s=br.readLine())!=null){
                line++;
                stk=new StringTokenizer(s,"#");
                s1=stk.nextToken();
                System.out.println(s1);
                if(s1.compareTo(id)==0){

                    return true;
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return false;
    }
    public void setStudentinfo(){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",str="";
            String s1[]=new String[20];
            StringTokenizer stk;
            for(int i=1; i<=line ;i++){
                s=br.readLine();
                if(i==line){
                    stk=new StringTokenizer(s,"#");
                    for(int j=0;j<15;j++){
                        s1[j]=stk.nextToken();
                        if(j==0){
                            str=label_studentid.getText();
                            str+=" "+s1[j];
                            label_studentid.setText(str);
                        }
                        if(j==1){
                            str=label_student_name.getText();
                            str+=" "+s1[j];
                            label_student_name.setText(str);

                        }
                        if(j==2){
                            str=label_father_name.getText();
                            str+=" "+s1[j];
                            label_father_name.setText(str);
                        }
                        if(j==3){
                            if(s1[j].compareTo("Male")==0){
                                str=label_gender.getText();
                                str+=" "+"Male";
                                label_gender.setText(str);
                            }
                            else{
                                str=label_gender.getText();
                                str+=" "+"Female";
                                label_gender.setText(str);
                            }
                        }
                        if(j==4){
                            str=label_dateofbirth.getText();
                            str+=" "+s1[j];
                            label_dateofbirth.setText(str);
                        }
                        if(j==5){
                            str=label_department.getText();
                            str+=" "+s1[j];
                            label_department.setText(str);
                        }
                        if(j==6){
                            str=label_year.getText();
                            str+=" "+s1[j];
                            label_year.setText(str);
                        }
                        if(j==7){
                            str=label_semester.getText();
                            str+=" "+s1[j];
                            label_semester.setText(str);
                        }
                        if(j==8){
                            str=label_address.getText();
                            str+=" "+s1[j];
                            label_address.setText(str);
                        }
                        if(j==9){
                            str=label_contactno.getText();
                            str+=" "+s1[j];
                            label_contactno.setText(str);
                        }
                        if(j==10){
                            str=label_email.getText();
                            str+=" "+s1[j];
                            label_email.setText(str);
                        }
                        if(j==11){
                            str=label_comment.getText();
                            str+=" "+s1[j];
                            label_comment.setText(str);
                        }
                    }
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }
    }
    private void clearInfo(){
        label_studentid.setText("Student ID        :");
        label_student_name.setText("Student Name  :");
        label_father_name.setText("Father Name    :");
        label_gender.setText("Gender             :");
        label_dateofbirth.setText("Date of Birth    :");
        label_department.setText("Department      :");
        label_year.setText("Year                  :");
        label_semester.setText("Semester          :");
        label_address.setText("Address            :");
        label_contactno.setText("Contact No       :");
        label_email.setText("Email Address  :");
        label_comment.setText("About Student  :");
    }

}
class MarksEntry{
    JFrame frame_marks;
    JLabel label_examyear,label_search,label_number_of_courses,label_studentid,label_name,label_department,label_year,label_semester,label_result,label_entry,label_sl,label_course_name,label_coursecode,label_credit,label_marks,label_gpa;
    JTextField textfield_examyear,textfield_searchbox,textField_number_of_courses;
    JButton button_search,button_calculate_gpa,button_save,button_back,button_getsubject;
    JTextField textField_year,textField_semester;
    JLabel [] labels_sl=new JLabel[20];
    JTextField [] textfields_course_name=new JTextField[20];
    JTextField [] textfields_coursecode=new JTextField[20];
    JTextField [] textfields_marks=new JTextField[20];
    JTextField [] textFields_gpa=new JTextField[20];
    JTextField [] textFields_credit=new JTextField[20];


    int line=0,subjects_line=0,total_sub=0;
    String dept="",name="",id="",result_totalgpa="",year="",semester="";

    public MarksEntry(){
        frame_marks=new JFrame("Marks Entry");
        label_search=new JLabel("Student Id:");
        label_department=new JLabel("Department:");
        label_name=new JLabel("Name           : ");
        label_year=new JLabel("Year          : ");
        label_studentid=new JLabel("Student Id    :");
        label_semester=new JLabel("Semester  :");
        label_number_of_courses=new JLabel("Total course:");
        label_result=new JLabel("Result         : ");
        label_examyear=new JLabel("Exam Year:");

        label_entry=new JLabel("Enter Marks here");
        label_sl=new JLabel("Sl no");
        label_course_name=new JLabel("Course name");
        label_coursecode=new JLabel("Course code");
        label_marks=new JLabel("Marks");
        label_gpa=new JLabel("GPA");
        label_credit=new JLabel("Credit");

        textfield_searchbox=new JTextField();
        textfield_examyear=new JTextField();
        textField_number_of_courses=new JTextField();
        textField_year=new JTextField();
        textField_semester=new JTextField();

        creatButtons();
        setBounds();
        addToFrame();
        setFontSizes();
        slNo();
        textFieldCourseName();
        textFieldCourseCode();
        textFieldMarks();
        textFieldGpa();
        textFieldCredit();
        listenToSearchButton();
        listenToBackButton();
        listenToCalculateButton();
        listenToGetSubjectsButton();
        listenToSaveButton();
        frame_marks.getContentPane().setBackground(Color.ORANGE);
        frame_marks.setLayout(null);
        frame_marks.setSize(1400,750);
        frame_marks.setExtendedState(frame_marks.MAXIMIZED_BOTH);
        frame_marks.setVisible(true);

    }
    private void listenToSearchButton(){
        button_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(find(textfield_searchbox.getText())){
                    clearInfo();
                    clearField();
                    setStudentinfo();
                }
                else{
                    clearInfo();
                    JFrame message=new JFrame("Message");
                    JOptionPane.showMessageDialog(message,"Student not found.");
                }
            }
        });
    }
    private void listenToCalculateButton(){
        button_calculate_gpa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(textField_number_of_courses.getText().isEmpty()){
                    JFrame warning=new JFrame("Warning");
                    JOptionPane.showMessageDialog(warning,"Please enter a valid course number.");
                }
                else if(!checkCourseNumber()){
                    JFrame warning=new JFrame("Warning");
                    JOptionPane.showMessageDialog(warning,"Please enter a valid course number.");
                }
                else if(!checkForm()){
                    JFrame message=new JFrame("Message");
                    JOptionPane.showMessageDialog(message,"Enter all necessary data");
                }
                else {
                    calculateResult();
                }
            }
        });
    }
    private void listenToGetSubjectsButton(){
        button_getsubject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearField();
                String year="",semester="";
                year=""+textField_year.getText();
                semester+=""+textField_semester.getText();
                System.out.println(dept);
                if(dept.compareTo("Computer Science and Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/cse1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/cse1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/cse2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/cse2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/cse3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/cse3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/cse4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/cse4-2.txt");
                    }
                }
                else if(dept.compareTo("Electric and Electrical Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/eee1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/eee1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/eee2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/eee2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/eee3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/eee3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/eee4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/eee4-2.txt");
                    }
                }
                else if(dept.compareTo("Software Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/iit1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/iit1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/iit2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/iit2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/iit3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/iit3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/iit4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/iit4-2.txt");
                    }
                }
                else if(dept.compareTo("Robotics and Mechatronics Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/robo1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/robo1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/robo2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/robo2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/robo3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/robo3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/robo4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/robo4-2.txt");
                    }
                }
                else if(dept.compareTo("Applied Chemistry & Chemical Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/acce1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/acce1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/acce2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/acce2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/acce3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/acce3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/acce4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/acce4-2.txt");
                    }
                }
                else if(dept.compareTo("Nuclear Engineering")==0){
                    if(year.compareTo("1st Year")==0&&semester.compareTo("1st Semester")==0){
                        System.out.println(dept);
                        readSubjects("Marks/acce1-1.txt");
                    }
                    if(year.compareTo("1st Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/ne1-2.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/ne2-1.txt");
                    }
                    if(year.compareTo("2nd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/ne2-2.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/ne3-1.txt");
                    }
                    if(year.compareTo("3rd Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/ne3-2.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("1st Semester")==0){
                        readSubjects("Marks/ne4-1.txt");
                    }
                    if(year.compareTo("4th Year")==0&&semester.compareTo("2nd Semester")==0){
                        readSubjects("Marks/ne4-2.txt");
                    }
                }

            }
        });
    }
    private void listenToBackButton(){
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame_marks.dispose();
            }
        });
    }
    private void listenToSaveButton(){
        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    makeTranscript(id);
                    JFrame fr=new JFrame("Success");
                    JOptionPane.showMessageDialog(fr,"Marks saved successfully");
                }catch (IOException ex){
                    System.out.println(ex);
                }catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });
    }
    private void creatButtons(){
        button_search=new JButton("Search");
        button_calculate_gpa=new JButton("Calculate GPA");
        button_save=new JButton("Save");
        button_back=new JButton("Back");
        button_getsubject=new JButton("Get Subjects");

        button_getsubject.setBounds(50,600,120,35);
        button_calculate_gpa.setBounds(180,600,150,35);
        button_save.setBounds(340,600,100,35);
        button_back.setBounds(1200,650,80,35);
    }
    public void setFontSizes(){
        label_search.setFont(new Font("Arial",Font.PLAIN,20));
        label_studentid.setFont(new Font("Arial",Font.PLAIN,15));
        label_name.setFont(new Font("Arial",Font.PLAIN,15));
        label_department.setFont(new Font("Arial",Font.PLAIN,15));
        label_year.setFont(new Font("Arial",Font.PLAIN,15));
        label_semester.setFont(new Font("Arial",Font.PLAIN,15));
        label_result.setFont(new Font("Arial",Font.PLAIN,15));
        label_sl.setFont(new Font("Arial",Font.PLAIN,15));
        label_course_name.setFont(new Font("Arial",Font.PLAIN,15));
        label_coursecode.setFont(new Font("Arial",Font.PLAIN,15));
        label_marks.setFont(new Font("Arial",Font.PLAIN,15));
        label_gpa.setFont(new Font("Arial",Font.PLAIN,15));
        label_credit.setFont(new Font("Arial",Font.PLAIN,15));
        label_examyear.setFont(new Font("Arial",Font.PLAIN,15));
        label_entry.setFont(new Font("Arial",Font.PLAIN,25));
        button_search.setFont(new Font("Arial",Font.PLAIN,20));


    }
    public void setBounds(){
        label_search.setBounds(50,30,100,40);
        textfield_searchbox.setBounds(160,30,300,40);
        button_search.setBounds(470,30,100,40);

        label_studentid.setBounds(50,150,200,50);
        label_name.setBounds(50,200,250,50);
        label_department.setBounds(50,250,400,50);
        label_year.setBounds(50,300,80,50);
        label_semester.setBounds(50,350,80,50);
        label_number_of_courses.setBounds(50,400,100,50);
        label_examyear.setBounds(50,450,100,50);
        label_result.setBounds(50,500,200,50);
        textField_number_of_courses.setBounds(140,410,150,30);
        textfield_examyear.setBounds(140,460,150,30);

        label_entry.setBounds(700,100,200,50);

        label_sl.setBounds(450,150,50,30);
        label_course_name.setBounds(515,150,200,30);
        label_coursecode.setBounds(710,150,150,30);
        label_marks.setBounds(865,150,100,30);
        label_gpa.setBounds(970,150,50,30);
        label_credit.setBounds(1040,150,50,30);

        textField_year.setBounds(140,310,150,30);
        textField_semester.setBounds(140,360,150,30);
    }
    public void addToFrame(){
        frame_marks.add(label_search);
        frame_marks.add(textfield_searchbox);
        frame_marks.add(button_search);
        frame_marks.add(label_studentid);
        frame_marks.add(label_department);
        frame_marks.add(label_year);
        frame_marks.add(label_semester);
        frame_marks.add(label_result);
        frame_marks.add(label_name);
        frame_marks.add(textField_year);
        frame_marks.add(textField_semester);
        frame_marks.add(label_entry);

        frame_marks.add(label_sl);
        frame_marks.add(label_course_name);
        frame_marks.add(label_coursecode);
        frame_marks.add(label_marks);
        frame_marks.add(label_gpa);
        frame_marks.add(label_credit);
        frame_marks.add(label_number_of_courses);
        frame_marks.add(textField_number_of_courses);
        frame_marks.add(label_examyear);
        frame_marks.add(textfield_examyear);

        frame_marks.add(button_calculate_gpa);
        frame_marks.add(button_save);
        frame_marks.add(button_back);
        frame_marks.add(button_getsubject);
    }
    public void slNo(){
        try{
            int height=190;
            String s;
            for(int i=1;i<=10;i++){
                s=Integer.toString(i);
                labels_sl[i]=new JLabel(s);
                labels_sl[i].setBounds(460,height,40,30);
                labels_sl[i].setFont(new Font("Arial",Font.PLAIN,15));
                frame_marks.add(labels_sl[i]);
                height+=50;
                System.out.println(s);
            }
        }
        catch (NullPointerException e){
            System.out.println(e);
        }

    }
    public void textFieldCourseName(){
        int height=190;
        String s="";
        for(int i=1;i<=10;i++){
            textfields_course_name[i]=new JTextField();
            textfields_course_name[i].setBounds(510,height,180,30);
            textfields_course_name[i].setEditable(false);
            frame_marks.add(textfields_course_name[i]);
            height+=50;
        }
    }
    public void textFieldCourseCode(){
        int height=190;
        for(int i=1;i<=10;i++){
            textfields_coursecode[i]=new JTextField();
            textfields_coursecode[i].setBounds(695,height,140,30);
            textfields_coursecode[i].setEditable(false);
            frame_marks.add(textfields_coursecode[i]);
            height+=50;
        }
    }
    public void textFieldMarks(){
        int height=190;
        for(int i=1;i<=10;i++){
            textfields_marks[i]=new JTextField();
            textfields_marks[i].setBounds(840,height,100,30);
            frame_marks.add(textfields_marks[i]);
            height+=50;
        }
    }
    public void textFieldGpa(){
        int h=190;
        for(int i=1;i<=10;i++){
            textFields_gpa[i]=new JTextField();
            textFields_gpa[i].setBounds(945,h,80,30);
            frame_marks.add(textFields_gpa[i]);
            h+=50;
        }
    }
    public void textFieldCredit(){
        int h=190;
        for(int i=1;i<=10;i++){
            textFields_credit[i]=new JTextField();
            textFields_credit[i].setBounds(1030,h,80,30);
            frame_marks.add(textFields_credit[i]);
            textFields_credit[i].setEditable(false);
            h+=50;
        }
    }
    public boolean find(String id){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",s1="";
            StringTokenizer stk;
            line=0;
            while((s=br.readLine())!=null){
                line++;
                stk=new StringTokenizer(s,"#");
                s1=stk.nextToken();
                System.out.println(s1);
                if(s1.compareTo(id)==0){

                    return true;
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return false;
    }
    public void setStudentinfo(){
        try{
            FileReader fr=new FileReader("information.txt");
            BufferedReader br=new BufferedReader(fr);
            String s="",str="";
            String s1[]=new String[20];
            StringTokenizer stk;
            for(int i=1; i<=line ;i++){
                s=br.readLine();
                if(i==line){
                    stk=new StringTokenizer(s,"#");
                    for(int j=0;j<15;j++){
                        s1[j]=stk.nextToken();
                        if(j==0){
                            str=label_studentid.getText();
                            str+=" "+s1[j];
                            label_studentid.setText(str);
                            id=s1[j];
                        }
                        if(j==1){
                            str=label_name.getText();
                            str+=" "+s1[j];
                            label_name.setText(str);
                            name=s1[j];

                        }
                        if(j==5){
                            str=label_department.getText();
                            str+=" "+s1[j];
                            label_department.setText(str);
                            dept=s1[j];

                        }
                        if(j==6){
                            textField_year.setText(s1[j]);
                            textField_year.setEditable(false);
                            year=s1[j];
                        }
                        if(j==7){
                            textField_semester.setText(s1[j]);
                            textField_semester.setText(s1[j]);
                            textField_semester.setEditable(false);
                            semester=s1[j];
                        }

                    }
                }

            }
            fr.close();
            br.close();

        }catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (NoSuchElementException e){
            System.out.println(e);
        }
    }
    private void clearInfo(){
        label_studentid.setText("Student Id:");
        label_name.setText("Name         : ");
        label_department.setText("Department:");
        label_result.setText("Result         : ");
        textField_semester.setText("");
        textField_year.setText("");
        textfield_examyear.setText("");
    }
    private boolean checkForm(){
        int n;
        n=Integer.parseInt(textField_number_of_courses.getText());
        for(int i=1;i<=n;i++){
            if(textfields_course_name[i].getText().isEmpty())
                return false;
            if(textfields_coursecode[i].getText().isEmpty())
                return false;
            if(textfields_marks[i].getText().isEmpty())
                return false;
            if(textFields_gpa[i].getText().isEmpty())
                return false;
            if(textFields_credit[i].getText().isEmpty())
                return false;
        }
        return true;
    }
    private boolean checkCourseNumber(){
        int n=Integer.parseInt(textField_number_of_courses.getText());
        if(n>10||n<1)
            return false;
        return true;
    }

    private void calculateResult(){
        double credit,total_credit,gpa,total_gpa,result;
        total_credit=0;
        total_gpa=0;
        int n=Integer.parseInt(textField_number_of_courses.getText());
        for(int i=1;i<=n;i++){
            gpa=Double.parseDouble(textFields_gpa[i].getText());
            credit=Double.parseDouble(textFields_credit[i].getText());
            gpa*=credit;
            total_credit+=credit;
            total_gpa+=gpa;
        }
        result=total_gpa/total_credit;
       String str1="",str2="";
       str1+=result;
       for(int i=0;i<4;i++){
           str2+=str1.charAt(i);
       }

        String s=label_result.getText();
        result_totalgpa=""+str2;
        s+=" "+str2;
        label_result.setText(s);

    }
    private void readSubjects(String filename){
        try{
            FileReader fr=new FileReader(filename);
            BufferedReader br=new BufferedReader(fr);
            String s="";
            StringTokenizer stk;
            total_sub=0;
            int j=0;
            while((s=br.readLine())!=null){
                stk=new StringTokenizer(s,"#");
                j++;
                for(int i=0;i<3;i++){
                    String str=stk.nextToken();
                    if(i==0){
                        textfields_course_name[j].setText(str);
                    }
                    if(i==1){
                        textfields_coursecode[j].setText(str);
                    }
                    if(i==2){
                        textFields_credit[j].setText(str);
                    }
                }
            }
            subjects_line=j;
            total_sub=j;
            String ss="";
            ss+=""+j;
            textField_number_of_courses.setText(ss);
            textField_number_of_courses.setEditable(false);
        }catch (FileNotFoundException e){
            System.out.println(e);
        }catch (IOException e){
            System.out.println(e);
        }
    }
    public void clearField(){
        for(int i=1;i<=10;i++){
            textFields_credit[i].setText("");
            textfields_coursecode[i].setText("");
            textfields_course_name[i].setText("");
            textfields_marks[i].setText("");
            textFields_gpa[i].setText("");
        }
    }
    private void makeTranscript(String filename) throws IOException, InvalidFormatException {
        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph p_logo = doc.createParagraph();
        XWPFRun run_logo = p_logo.createRun();
        File img1 = new File("logo.jpeg");
        int height1 = 100;
        String imgFile1 = img1.getName();
        int imgFormat1 = getImageFormat(imgFile1);
        p_logo.setAlignment(ParagraphAlignment.CENTER);
        run_logo.addBreak();
        run_logo.addPicture(new FileInputStream(img1), imgFormat1, imgFile1, Units.toEMU(100), Units.toEMU(height1));

        XWPFParagraph p_varsityname=doc.createParagraph();
        XWPFRun run_varsityname=p_varsityname.createRun();
        p_varsityname.setAlignment(ParagraphAlignment.CENTER);
        run_varsityname.setFontSize(20);
        run_varsityname.setText("University of Dhaka");

        XWPFParagraph p_transcript=doc.createParagraph();
        XWPFRun run_transcript=p_transcript.createRun();
        p_transcript.setAlignment(ParagraphAlignment.CENTER);
        run_transcript.setFontSize(16);
        run_transcript.setText("Grade Certificate/Academic Transcript" +
                "");

        XWPFParagraph p_year=doc.createParagraph();
        XWPFRun run_year=p_year.createRun();
        p_year.setAlignment(ParagraphAlignment.CENTER);
        run_year.setFontSize(15);
        run_year.setText(year+" "+semester+" B.Sc. in "+dept+""+
                "" );

        XWPFParagraph p_dept=doc.createParagraph();
        XWPFRun run_dept=p_dept.createRun();
        p_dept.setAlignment(ParagraphAlignment.CENTER);
        run_dept.setFontSize(15);
        run_dept.setText("Subject: "+dept+" Examination "+textfield_examyear.getText() +
                "" +
                "");

        XWPFParagraph p_name=doc.createParagraph();
        XWPFRun run_name=p_name.createRun();
        p_name.setAlignment(ParagraphAlignment.LEFT);
        run_name.setFontSize(14);
        run_name.setText("The following grades are obtained by-   "+name +
                "" +
                "" +
                "");

        XWPFParagraph p_id=doc.createParagraph();
        XWPFRun run_id=p_id.createRun();
        p_id.setAlignment(ParagraphAlignment.LEFT);
        run_id.setFontSize(16);
        run_id.setText("Student Id: "+id+"                 Exam Year: "+textfield_examyear.getText());

        XWPFParagraph p_table=doc.createParagraph();
        XWPFRun run_table=p_table.createRun();
        p_table.setAlignment(ParagraphAlignment.LEFT);

        XWPFTable table=doc.createTable();

        XWPFTableRow row[]=new XWPFTableRow[12];

        row[0]=table.getRow(0);
        row[0].getCell(0).setText("Course No.");
        row[0].addNewTableCell().setText("Course Title");
        row[0].addNewTableCell().setText("Credit");
        row[0].addNewTableCell().setText("Marks");
        row[0].addNewTableCell().setText("Grade");
        row[0].addNewTableCell().setText("Grade Point");

        try{
            for(int i=1;i<=total_sub;i++){
                row[i]=table.createRow();
                for(int j=0;j<6;j++){
                    XWPFTableCell cell =row[i].getCell(j);
                    if(j==0){
                        cell.setText(textfields_coursecode[i].getText());
                    }
                    if(j==1){
                        cell.setText(textfields_course_name[i].getText());
                    }
                    if(j==2){
                        cell.setText(textFields_credit[i].getText());
                    }
                    if(j==3){
                        cell.setText(textfields_marks[i].getText());
                    }
                    if(j==4){
                        cell.setText(setGrade(textfields_marks[i].getText()));
                    }
                    if(j==5){
                        cell.setText(textFields_gpa[i].getText());
                    }
                }
            }
        }catch (NullPointerException e){
            System.out.println(e);
        }

        XWPFParagraph p_result=doc.createParagraph();
        XWPFRun run_result=p_result.createRun();
        p_result.setAlignment(ParagraphAlignment.CENTER);
        run_result.setText("Total GPA: " +result_totalgpa+
                "" +
                "" +
                "");

        XWPFParagraph p_sig=doc.createParagraph();
        XWPFRun run_sig=p_sig.createRun();
        run_sig.setFontSize(12);

        run_sig.setText("ADMINISTRATIVE BUILDING                                       Deputy Controller of Examinations" +
                "   UNIVERSITY OF DHAKA                                                           UNIVERSITY OF DHAKA" +
                "      NILKHET,DHAKA-1000  " +
                "BANGLADESH");





        String fname=filename+".docx";
        FileOutputStream out = new FileOutputStream("Transcript\\"+fname);
        doc.write(out);
        out.close();
        doc.close();
    }
    private static int getImageFormat(String imgFile) {
        int format;
        if (imgFile.endsWith(".emf"))
            format = XWPFDocument.PICTURE_TYPE_EMF;
        else if (imgFile.endsWith(".wmf"))
            format = XWPFDocument.PICTURE_TYPE_WMF;
        else if (imgFile.endsWith(".pict"))
            format = XWPFDocument.PICTURE_TYPE_PICT;
        else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
            format = XWPFDocument.PICTURE_TYPE_JPEG;
        else if (imgFile.endsWith(".png"))
            format = XWPFDocument.PICTURE_TYPE_PNG;
        else if (imgFile.endsWith(".dib"))
            format = XWPFDocument.PICTURE_TYPE_DIB;
        else if (imgFile.endsWith(".gif"))
            format = XWPFDocument.PICTURE_TYPE_GIF;
        else if (imgFile.endsWith(".tiff"))
            format = XWPFDocument.PICTURE_TYPE_TIFF;
        else if (imgFile.endsWith(".eps"))
            format = XWPFDocument.PICTURE_TYPE_EPS;
        else if (imgFile.endsWith(".bmp"))
            format = XWPFDocument.PICTURE_TYPE_BMP;
        else if (imgFile.endsWith(".wpg"))
            format = XWPFDocument.PICTURE_TYPE_WPG;
        else {
            return 0;
        }
        return format;
    }
    private static void widthCellsAcrossRow (XWPFTable table, int rowNum, int colNum, int width) {
        XWPFTableCell cell = table.getRow(rowNum).getCell(colNum);
        if (cell.getCTTc().getTcPr() == null)
            cell.getCTTc().addNewTcPr();
        if (cell.getCTTc().getTcPr().getTcW()==null)
            cell.getCTTc().getTcPr().addNewTcW();
        cell.getCTTc().getTcPr().getTcW().setW(BigInteger.valueOf((long) width));

    }
    private String setGrade(String marks){
        int mark=Integer.parseInt(marks);
        if(mark>=80&&mark<=100)
            return "A+";
        else if(mark>=75&&mark<=79)
            return "A";
        else if(mark>=70&&mark<=74)
            return "A-";
        else if(mark>=65&&mark<=69)
            return "B+";
        else if(mark>=60&&mark<=64)
            return "B";
        else if(mark>=55&&mark<=59)
            return "B-";
        else if(mark>=50&&mark<=54)
            return "C+";
        else if(mark>=45&&mark<=49)
            return "C";
        else if(mark>=40&&mark<=44)
            return "D";
        else if(mark<40)
            return "F";
        return "";
    }

}
class Transcript extends FindStudent{
    JButton button_open,button_back;

    public Transcript(){
        label_enter_student_id.setText("Enter Student Id to Open Transcript of the student");
        button_find.setVisible(false);
        label_student_id.setText("Student Id:");
        label_enter_student_id.setBounds(50,100,750,50);
        button_open=new JButton("Open");
        button_back=new JButton("Back");
        button_open.setBounds(540,250,80,40);
        button_back.setBounds(540,650,80,40);
        button_open.setFont(new Font("Arial",Font.PLAIN,18));
        button_back.setFont(new Font("Arial",Font.PLAIN,18));
        jf.add(button_open);
        jf.add(button_back);
        listenToOpenButton();
        listenToBackButton();
    }
    private void listenToBackButton(){
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
            }
        });
    }
    private void listenToOpenButton(){
        button_open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Desktop desktop = Desktop.getDesktop();
                String filename=textfield_studentid.getText();
                filename+=".docx";
                try {
                    File f = new File("Transcript\\"+filename);
                    desktop.open(f);  // opens application (MSWord) associated with .doc file
                }
                catch(Exception ex) {
                    // Swing01.this is to refer to outer class's instance from inner class
                    JFrame jf=new JFrame("Error");
                    JOptionPane.showMessageDialog(jf,ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
