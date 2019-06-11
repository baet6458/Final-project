package android.example.finalproject;

public class Member {

    private int mBondNumber;
    private int machineNumber;
    private String mName;
    private String mEmail;
    private String mNumber;



    public  Member(int bondNumber,String name, String email,int machineNumber,String number){
        mBondNumber=bondNumber;
        mName=name;
        mEmail=email;
        this.machineNumber=machineNumber;
        mNumber=number;
    }

    public int getBondNumber(){
        return mBondNumber;
    }

    public String getName(){
        return mName;
    }

    public String getmEmail(){
        return mEmail;
    }
    public int getMachineNumber(){
        return machineNumber;
    }

    public String getInfo(){
        return machineNumber+" "+mName+" "+mBondNumber+" "+mNumber+" "+mEmail;
    }

}
